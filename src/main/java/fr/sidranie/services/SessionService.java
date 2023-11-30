package fr.sidranie.services;

import java.time.Instant;
import java.util.UUID;

import fr.sidranie.dao.SessionDao;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Service managing sessions.
 */
@ApplicationScoped
public class SessionService {
    @Inject
    SessionDao sessionDao;

    /**
     * Initialize a session for the given user.
     * The UUID is generated via @see java.util.UUID
     * 
     * @param user The user for who a session is needed.
     * @return The created session.
     */
    @Transactional
    public Session initializeSessionForUser(User user) {
        Session session = new Session();
        session.id = UUID.randomUUID().toString();
        session.user = user;
        session.creation = Instant.now();

        sessionDao.persist(session);

        return session;
    }

    @Transactional
    public void deleteSession(String sessionId) {
        Session session = sessionDao.findById(sessionId);
        sessionDao.delete(session);
    }
}
