package fr.sidranie.dao;

import fr.sidranie.entities.Session;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SessionDao implements PanacheRepositoryBase<Session, String> {

}
