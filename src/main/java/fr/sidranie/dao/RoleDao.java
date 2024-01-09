package fr.sidranie.dao;

import fr.sidranie.entities.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleDao implements PanacheRepository<Role> {
  // Empty body
}
