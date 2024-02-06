package fr.sidranie.entities;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Scope extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Scope_SEQ")
  @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "Scope_SEQ")
  public Long id;

  @Column(name = "scope_name")
  public String name;

  @ManyToMany(mappedBy = "scopes")
  public List<Role> roles;
}
