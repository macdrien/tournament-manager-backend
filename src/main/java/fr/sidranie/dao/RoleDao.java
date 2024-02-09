package fr.sidranie.dao;

import fr.sidranie.entities.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleDao implements PanacheRepository<Role> {

  public Role findByName(String roleName) {
    Parameters parameters = Parameters.with("name", roleName);
    return find("name = :name", parameters).firstResult();
  }
}
