package fr.sidranie.auth.filters;

import java.io.IOException;
import java.util.Map;

import fr.sidranie.auth.filters.annotations.Authenticated;
import fr.sidranie.dao.SessionDao;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;

@Provider
@Authenticated
public class AuthenticatedFilter implements ContainerRequestFilter {
    
    @Inject
    SessionDao sessionDao;

    /**
     * Check if the session cookie is present, valid, and always linked to a valid user.
     * Else, launch abortWith method with the UNAUTHORIZED error code.
     */
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            Map<String, Cookie> cookies = requestContext.getCookies();
            Cookie sessionCookie = cookies.get("session");
            sessionDao.findByIdOptional(sessionCookie.getValue()).orElseThrow(() -> new NullPointerException());
        } catch (NullPointerException exception) {
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
        }
    }
    
}
