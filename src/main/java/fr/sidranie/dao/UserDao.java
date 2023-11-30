package fr.sidranie.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import fr.sidranie.auth.Credential;
import fr.sidranie.entities.User;

@ApplicationScoped
public class UserDao implements PanacheRepository<User> {

    /**
     * Search a user given using the given credential
     * 
     * @param credential The credential identifying the user.
     * @return An optional which can contain the find user.
     */
    public Optional<User> findByCredential(Credential credential) {
        Parameters parameters = Parameters.with("identifier", credential.identifier)
            .and("password", credential.password);
        return find("(username = :identifier or email = :identifier) and password = :password", parameters).firstResultOptional();
    }
}
