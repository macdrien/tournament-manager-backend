package fr.sidranie.services;

import fr.sidranie.auth.Credential;
import fr.sidranie.dao.UserDao;
import fr.sidranie.endpoints.dto.CreateUser;
import fr.sidranie.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {
    @Inject
    UserDao userDao;

    public User findUserMatchingCredentials(Credential credential) throws NotFoundException {
        return userDao.findByCredential(credential).orElseThrow(() -> new NotFoundException("User does not exist."));
    }

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
