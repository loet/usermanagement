package ch.mobi.ueliloetscher.learning.usermanagement;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.PingInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;

@Path("users")
public class UserService implements Serializable {


    @GET
    @Path("ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok(new PingInfo("ping at " + new Date())).build();
    }


}
