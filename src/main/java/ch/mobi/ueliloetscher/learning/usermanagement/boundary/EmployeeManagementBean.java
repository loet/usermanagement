package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.MessageDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ValidationException;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ValidationInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/employee")
@Interceptors({ValidationInterceptor.class})
public class EmployeeManagementBean {

    @Inject
    private EmployeeAddService employeeAddService;
    @Inject
    private EmployeeUpdateService employeeUpdateService;
    @Inject
    private EmployeeSearchService employeeSearchService;
    @Inject
    private EmployeeGetService employeeGetService;
    @Inject
    private EmployeeDeleteService employeeDeleteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchEmployees(@QueryParam("ename") String ename) {
        if (ename != null) {
            return Response.ok(new CollectionWrapper(this.employeeSearchService.searchEmployees(ename))).build();
        } else {
            return Response.ok(new CollectionWrapper(this.employeeSearchService.getAllEmployees())).build();
        }
    }

    @GET
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("eid") Integer eid) {
        Employee employee = this.employeeGetService.getEmployee(eid);
        if (employee == null) {
            // return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build();
            // throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }
        return Response.ok(employee).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws ValidationException {
        return Response.ok(this.employeeAddService.addEmployee(employee)).build();
    }

    @PUT
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("eid") Integer eid, Employee employee) {
        Employee updated = this.employeeUpdateService.updateEmployee(eid, employee);
        if (updated == null) {
            // return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with id " + employee.getEid())).build();
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + employee.getEid())).build());
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("eid") Integer eid) {
        if (this.employeeDeleteService.deleteEmployee(eid)) {
            return Response.ok().build();
        } else {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            // return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with id " + eid)).build();
        }
    }

}
