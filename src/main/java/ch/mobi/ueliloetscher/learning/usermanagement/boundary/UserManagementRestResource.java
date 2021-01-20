package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.*;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.RestValidationInterceptor;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Path("users")
@Interceptors(RestValidationInterceptor.class)
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
    public Response getUser(@PathParam("id") Long id) {
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

    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountries() {
        return Response.ok().entity(this.userManagementBean.getCountryCodes()).build();
    }

    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployess() {
        return Response.ok(new CollectionWrapper(this.userManagementBean.getAllEmployees())).build();
    }

    @GET
    @Path("/employee/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("eid") Integer eid) {
        Employee employee = this.userManagementBean.getEmployee(eid);
        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build();
        }
        return Response.ok(employee).build();
    }

    @GET
    @Path("/employee/search/{ename}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployees(@PathParam("ename") String ename) {
        return Response.ok(new CollectionWrapper(this.userManagementBean.searchEmployees(ename))).build();
    }

    @POST
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) {
        return Response.ok(this.userManagementBean.addEmployee(employee)).build();
    }

    @PUT
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {
        Employee updated = this.userManagementBean.updateEmployee(employee);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with id " + employee.getEid())).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/employee/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("eid") Integer eid) {
        if (this.userManagementBean.deleteEmployee(eid)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with id " + eid)).build();
        }
    }
}
