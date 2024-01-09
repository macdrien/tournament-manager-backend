package fr.sidranie.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Person")
public class User extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Person_SEQ")
  @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "Person_SEQ")
  public Long id;

  public String username;
  public String password;
  public String email;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "Person_Roles",
    joinColumns =
      @JoinColumn(name="id_person",
        referencedColumnName = "id"),
    inverseJoinColumns =
      @JoinColumn(name="id_role",
        referencedColumnName = "id"),
    uniqueConstraints =
      @UniqueConstraint(columnNames={"id_person", "id_role"})
  )
  @JsonIgnoreProperties("users")
  public Set<Role> roles;
}
