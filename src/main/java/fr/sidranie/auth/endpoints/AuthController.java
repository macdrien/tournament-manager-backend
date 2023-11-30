package fr.sidranie.auth.endpoints;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.server.jaxrs.ResponseBuilderImpl;

import fr.sidranie.auth.Credential;
import fr.sidranie.auth.filters.annotations.Authenticated;
import fr.sidranie.endpoints.dto.CreateUser;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import fr.sidranie.services.SessionService;
import fr.sidranie.services.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.NewCookie.SameSite;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
public class AuthController {
    @Inject
    SessionService sessionService;

    @Inject
    UserService userService;

    /**
     * The delay, in second, of session cookie validity
     */
    @ConfigProperty(name = "session.expiration")
    Long expiration;

    /**
     * Login a user. It will initialize the session and returns it in a cookie named session.
     * 
     * @param credential The credential identifying the user to login
     * @return An HTTP response with the session cookie.
     * @throws NotFoundException If the credential doesn't match any user
     */
    @POST
    @Path("/login")
    public Response login(Credential credential) throws NotFoundException {
        User user = userService.findUserMatchingCredentials(credential);
        Session session = sessionService.initializeSessionForUser(user);
        NewCookie sessionCookie = createCookieForSession(session);

        return Response.ok(user).cookie(sessionCookie).build();
    }

    /**
     * Regsiter a new user in the system.
     * After the user creation, it will initialize a session for him and returns it in the session cookie as for the login endpoint but with the user entity in the Response body.
     * 
     * @param newUser The user to create
     * @return An HTTP response with the created user and the session body if registration succeed.
     *         Else (validation error mainly), it will raise an error and returns the corresponding HTTP code.
     */
    @POST
    @Path("/register")
    @Transactional
    public Response register(CreateUser newUser) {
        List<String> errors = validateUserToCreate(newUser);
        if (errors.size() != 0) {
            return new ResponseBuilderImpl()
                .entity(errors, null)
                .status(Status.BAD_REQUEST)
                .build();
        }

        User user = userService.createUser(newUser);
        Session session = sessionService.initializeSessionForUser(user);
        NewCookie sessionCookie = createCookieForSession(session);

        return new ResponseBuilderImpl()
            .entity(user, null)
            .cookie(sessionCookie)
            .status(Status.CREATED)
            .build();
    }

    /**
     * Logout a user by deleting the session.
     * 
     * @param cookie The session cookie identifying the user
     * @return An empty OK Reponse
     */
    @POST
    @Path("/logout")
    @Authenticated
    public Response logout(@RestCookie("session") Cookie cookie) {
        sessionService.deleteSession(cookie.getValue());
        return Response.ok().build();
    }

    /**
     * Create the session cookie using the session.
     * 
     * @param session The session for which we want the cookie
     * @return The created cookie
     */
    private NewCookie createCookieForSession(Session session) {
        return new NewCookie.Builder("session")
                .sameSite(SameSite.STRICT)
                .value(session.id)
                .expiry(new Date(session.creation.plusSeconds(expiration).toEpochMilli()))
                .build();
    }

    /**
     * Validate the user to create by checking the given fields.
     * 
     * @param newUser The user to create.
     * @return A list of errors in the user creation. The list will be empty if the user object is valid.
     */
    private List<String> validateUserToCreate(CreateUser newUser) {
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
        return errors;
    }
}
