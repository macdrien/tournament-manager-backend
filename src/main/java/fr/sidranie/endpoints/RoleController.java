package fr.sidranie.endpoints;

import java.util.List;

import fr.sidranie.dao.RoleDao;
import fr.sidranie.entities.Role;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/roles")
public class RoleController {

    @Inject
    RoleDao roleDao;

    @GET
    public List<Role> listAll() {
        return roleDao.listAll();
    }
}