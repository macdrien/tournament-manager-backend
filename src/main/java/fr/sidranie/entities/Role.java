package fr.sidranie.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Role extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Role_SEQ")
  @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "Role_SEQ")
  public Long id;

  @Column(name = "role_name")
  public String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "Roles_Permissions", joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_scope", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
      "id_role", "id_permission" }))
  @JsonIgnoreProperties("permissions")
  public Set<Permission> permissions;

  @ManyToMany(mappedBy = "roles")
  public List<User> users;
}
