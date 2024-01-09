package fr.sidranie.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Role extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_SEQ")
  @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "Role_SEQ")
  public Long id;

  public String scope;

  @ManyToMany(mappedBy = "roles")
  public List<User> users;
}
