package fr.sidranie.entities;

import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Session extends PanacheEntityBase {

    @Id
    public String id;

    @ManyToOne(optional=false) 
    @JoinColumn(name="userId", nullable=false, updatable=false)
    public User user;

    public Instant creation;
}
