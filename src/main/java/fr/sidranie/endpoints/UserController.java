package fr.sidranie.endpoints;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import fr.sidranie.auth.Credential;
import fr.sidranie.dao.UserDao;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import fr.sidranie.services.SessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.NewCookie.SameSite;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/users")
public class UserController {

    @Inject
    UserDao userDao;

    @Inject
    SessionService sessionService;

    @ConfigProperty(name = "session.expiration")
    Long expiration;

    @GET
    public List<User> listAll() {
        return userDao.listAll();
    }

    @GET
    @Path("/{id}")
    public User findById(@PathParam("id") Long id) {
        return userDao.findById(id);
    }

    @POST
    @Path("/login")
    public Response login(Credential credential) {
        Optional<User> userOptional = userDao.findByCredential(credential);

        userOptional.orElseThrow(() -> new NotFoundException("User not found"));

        Session session = sessionService.initializeSessionForUser(userOptional.get());
        NewCookie sessionCookie = new NewCookie.Builder("session")
                .sameSite(SameSite.STRICT)
                .value(session.id)
                .expiry(new Date(session.creation.plusMillis(expiration).toEpochMilli()))
                .build();

        ResponseBuilder builder = Response.ok(userOptional.get()).cookie(sessionCookie);
        return builder.build();
    }
}
