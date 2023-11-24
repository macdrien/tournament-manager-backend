package fr.sidranie.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.credential.Credential;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Person")
public class User extends PanacheEntity {
  public String username;
  public String password;
  public String email;
}
