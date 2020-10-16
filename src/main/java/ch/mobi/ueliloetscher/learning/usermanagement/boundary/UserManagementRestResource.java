package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;

@Path("users")
public class UserManagementRestResource implements Serializable {

    @Inject
    private UserManagementBean userManagementBean;

    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok(new PingInfo("ping at " + new Date())).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(new CollectionWrapper(this.userManagementBean.getAllUsers())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        UserReadDTO user = this.userManagementBean.getUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("user not found with id " + id)).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserCreateDTO user) {
        return Response.ok(this.userManagementBean.addUser(user)).build();
    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserUpdateDTO user) {
        UserReadDTO userReadDTO = this.userManagementBean.updateUser(user);
        if (userReadDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("user not found with id " + user.getId())).build();
        }
        return Response.ok(userReadDTO).build();
    }

    @DELETE
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(UserDeleteDTO user) {
        if (this.userManagementBean.deleteUser(user)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("user not found with id " + user.getId())).build();
        }
    }

}
