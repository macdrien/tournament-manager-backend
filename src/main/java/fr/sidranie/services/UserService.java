package fr.sidranie.services;

import fr.sidranie.auth.Credential;
import fr.sidranie.dao.UserDao;
import fr.sidranie.endpoints.dto.CreateUser;
import fr.sidranie.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

/**
 * Service managing users.
 */
@ApplicationScoped
public class UserService {
    @Inject
    UserDao userDao;

    /**
     * Search a user matching the given Credential. The identifier can refer to the username or the mail address.
     * 
     * @param credential The credential identifying the user
     * @return The found User. Will never return null.
     * @throws NotFoundException Thrown if no user matchs the given Credential.
     */
    public User findUserMatchingCredentials(Credential credential) throws NotFoundException {
        return userDao.findByCredential(credential).orElseThrow(() -> new NotFoundException("User does not exist."));
    }

    /**
     * Persists and return the given user.
     * 
     * @param newUser The user to create
     * @return The created user (with its id)
     */
    @Transactional
    public User createUser(CreateUser newUser) {
        User user = new User();
        user.username = newUser.username;
        user.password = newUser.password;
        user.email = newUser.email;
        userDao.persist(user);
        return user;
    }
    
}
