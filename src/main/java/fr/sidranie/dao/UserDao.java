package fr.sidranie.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import fr.sidranie.auth.Credential;
import fr.sidranie.entities.User;

@ApplicationScoped
public class UserDao implements PanacheRepository<User> {
    
    public Optional<User> findByIndentifier(String identifier) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", identifier);
        params.put("email", identifier);
        return find("username = :username or email = :email", params).firstResultOptional();
    }

    public Optional<User> findByCredential(Credential credential) {
        Parameters parameters = Parameters.with("identifier", credential.identifier)
            .and("password", credential.password);
        return find("(username = :identifier or email = :identifier) and password = :password", parameters).firstResultOptional();
    }
}
