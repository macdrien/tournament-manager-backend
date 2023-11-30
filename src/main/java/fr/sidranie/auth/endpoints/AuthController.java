package fr.sidranie.auth.endpoints;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.server.jaxrs.ResponseBuilderImpl;

import fr.sidranie.auth.Credential;
import fr.sidranie.auth.filters.annotations.Authenticated;
import fr.sidranie.dao.SessionDao;
import fr.sidranie.dao.UserDao;
import fr.sidranie.endpoints.dto.CreateUser;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import fr.sidranie.services.SessionService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.NewCookie.SameSite;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
public class AuthController {

    @Inject
    UserDao userDao;

    @Inject
    SessionService sessionService;

    @Inject
    SessionDao sessionDao;

    @ConfigProperty(name = "session.expiration")
    Long expiration;

    @POST
    @Path("/login")
    public Response login(Credential credential) {
        Optional<User> userOptional = userDao.findByCredential(credential);

        userOptional.orElseThrow(() -> new NotFoundException("User not found"));

        Session session = sessionService.initializeSessionForUser(userOptional.get());
        NewCookie sessionCookie = new NewCookie.Builder("session")
                .sameSite(SameSite.STRICT)
                .value(session.id)
                .expiry(new Date(session.creation.plusSeconds(expiration).toEpochMilli()))
                .build();

        ResponseBuilder builder = Response.ok(userOptional.get()).cookie(sessionCookie);
        return builder.build();
    }

    @POST
    @Path("/register")
    @Transactional
    public Response register(CreateUser newUser) {
        List<String> errors = new ArrayList<>();
        if (newUser.username == null || newUser.username.length() < 3) {
            errors.add("Le username est obligatoire et doit faire plus de 3 caractères.");
        }
        if (newUser.email == null || newUser.email.isBlank()) {
            errors.add("L'adresse mail est obligatoire.");
        }
        if (newUser.password == null || newUser.password.length() <= 3) {
            errors.add("Le mot de passe est obligatoire et doit faire 3 caractères ou plus.");
        }
        if (errors.size() != 0) {
            return new ResponseBuilderImpl()
                .entity(errors, null)
                .status(Status.BAD_REQUEST)
                .build();
        }

        User user = new User();
        user.username = newUser.username;
        user.password = newUser.password;
        user.email = newUser.email;
        userDao.persist(user);

        Session session = sessionService.initializeSessionForUser(user);
        NewCookie sessionCookie = new NewCookie.Builder("session")
                .sameSite(SameSite.STRICT)
                .value(session.id)
                .expiry(new Date(session.creation.plusMillis(expiration).toEpochMilli()))
                .build();

        return new ResponseBuilderImpl()
            .entity(user, null)
            .cookie(sessionCookie)
            .status(Status.CREATED)
            .build();
    }

    @POST
    @Path("/logout")
    @Authenticated
    @Transactional
    public Response logout(@RestCookie("session") Cookie cookie) {
        Log.info(cookie);
        Session session = sessionDao.findById(cookie.getValue());
        sessionDao.delete(session);

        return Response.ok().build();
    }
}
