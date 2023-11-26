package fr.sidranie.services;

import java.time.Instant;
import java.util.UUID;

import fr.sidranie.dao.SessionDao;
import fr.sidranie.entities.Session;
import fr.sidranie.entities.User;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SessionService {
    @Inject
    SessionDao sessionDao;

    @Transactional
    public Session initializeSessionForUser(User user) {
        Session session = new Session();
        session.id = UUID.randomUUID().toString();
        session.user = user;
        session.creation = Instant.now();

        sessionDao.persist(session);

        Log.info(sessionDao.findById(session.id));

        return session;
    }
}
