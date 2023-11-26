package fr.sidranie.entities;

import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Session extends PanacheEntityBase {

    @Id
    public String id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    public User user;

    public Instant creation;
}
