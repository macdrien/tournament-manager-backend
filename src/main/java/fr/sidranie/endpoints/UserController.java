package fr.sidranie.endpoints;

import java.util.List;

import org.jboss.resteasy.reactive.RestCookie;
import fr.sidranie.auth.filters.annotations.Authenticated;
import fr.sidranie.dao.SessionDao;
import fr.sidranie.dao.UserDao;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Cookie;

@Path("/users")
public class UserController {

    @Inject
    UserDao userDao;

    @Inject
    SessionDao sessionDao;

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
    @Path("/me")
    @Authenticated
    public User findMe(@RestCookie("session") Cookie cookie) {
        Session session = sessionDao.findById(cookie.getValue());
        return session.user;
    }
}
