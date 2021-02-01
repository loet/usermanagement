package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.control.*;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.MessageDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.MessageWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ValidationException;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ValidationInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
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

    @GET
    @Path("/slow/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeCached(@PathParam("eid") Integer eid) {
        Employee employee = this.employeeGetService.getEmployee(eid);
        if (employee == null) {
            // return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build();
            // throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.ok(employee).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addEmployee(Employee employee) throws ValidationException {
        return Response.status(Response.Status.CREATED).entity(this.employeeAddService.addEmployee(employee)).build();
    }

    @PUT
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response updateEmployee(@PathParam("eid") Integer eid, Employee employee) {
        try {
            Employee updated = this.employeeUpdateService.updateEmployee(eid, employee);
            if (updated == null) {
                throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            }
            return Response.ok(updated).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MessageWrapper(ex.getMessage(), "")).build();
        }
    }

    @DELETE
    @Path("/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteEmployee(@PathParam("eid") Integer eid) {
        if (this.employeeDeleteService.deleteEmployee(eid)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            throw new NotFoundException(Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with eid " + eid)).build());
            // return Response.status(Response.Status.NOT_FOUND).entity(new MessageDTO("employee not found with id " + eid)).build();
        }
    }

}
