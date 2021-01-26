package ch.mobi.ueliloetscher.learning.usermanagement.client;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class TestClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        CollectionWrapper all = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .request(MediaType.APPLICATION_JSON)
                .get(CollectionWrapper.class);

        System.out.println(all);

        CollectionWrapper searched = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .queryParam("ename", "a")
                .request(MediaType.APPLICATION_JSON)
                .get(CollectionWrapper.class);

        System.out.println(searched);

        Employee newEmployee = new Employee();
        newEmployee.setEname("Testclient");
        newEmployee.getSkills().add(new Skill(0, "Programming"));
        newEmployee.setDepartment(new Department(0, "Home Office"));

        Employee created = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newEmployee))
                .readEntity(Employee.class);

        System.out.println(created);

        Employee read = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getEid() + "")
                .request(MediaType.APPLICATION_JSON)
                .get(Employee.class);

        System.out.println(read);


        Response delete = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getEid() + "")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        System.out.println("Deleted: " + delete.getStatusInfo());
        delete.close();

        Response notThere = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getEid() + "")
                .request(MediaType.APPLICATION_JSON)
                .get();

        System.out.println("Status: " + notThere.getStatusInfo());
        notThere.close();

        client.close();

    }

}
