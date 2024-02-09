package fr.sidranie.endpoints;

import java.util.List;

import fr.sidranie.auth.filters.annotations.Authenticated;
import fr.sidranie.dao.RoleDao;
import fr.sidranie.entities.Role;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("roles")
public class RoleController {

    @Inject
    RoleDao roleDao;

    @GET
    @Authenticated
    public List<Role> listAll() {
        return roleDao.listAll();
    }

    @GET
    @Path("{id}")
    @Authenticated
    public Role getById(@PathParam("id") Long roleId) {
        return roleDao.findById(roleId);
    }

    @GET
    @Path("{name}")
    @Authenticated
    public Role getByName(@PathParam("name") String roleName) {
        return roleDao.findByName(roleName);
    }
}