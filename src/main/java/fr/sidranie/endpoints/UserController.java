package fr.sidranie.endpoints;

import java.util.List;
import java.util.Optional;

import fr.sidranie.auth.Credential;
import fr.sidranie.dao.UserDao;
import fr.sidranie.entities.User;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.NewCookie.SameSite;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/users")
public class UserController {
    
    @Inject
    UserDao userDao;

    @GET
    public List<User> listAll() {
        return userDao.listAll();
    }

    @GET
    @Path("/{id}")
    public User findById(@PathParam("id") Long id) {
        return userDao.findById(id);
    }

    @GET
    @Path("/doesExist/{identifier}")
    public Boolean findByIdentifier(@PathParam("identifier") String identifier) {
        return userDao.findByIndentifier(identifier).isPresent();
    }

    @POST
    @Path("/login")
    public Response login(Credential credential) {
        Log.info(credential.identifier);
        Log.error("user login request");
        Optional<User> userOptional = userDao.findByCredential(credential);
        System.out.println("result ------- " + userOptional.get().username);
        userOptional.orElseThrow(() -> new NotFoundException("User not found"));
        NewCookie sessionCookie = new NewCookie.Builder("session")
                 .sameSite(SameSite.STRICT)
                 .value(userOptional.get().id.toString())
                .build();

        ResponseBuilder builder = Response.ok(userOptional.get()).cookie(sessionCookie);
        return builder.build();
    }
}
