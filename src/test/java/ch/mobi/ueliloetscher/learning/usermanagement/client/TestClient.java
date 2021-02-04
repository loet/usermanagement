package ch.mobi.ueliloetscher.learning.usermanagement.client;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.MessageWrapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

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
                .queryParam("ename", "e")
                .request(MediaType.APPLICATION_JSON)
                .get(CollectionWrapper.class);

        System.out.println(searched);

        Employee newEmployee = new Employee();
        newEmployee.setEname("Testclient");
        newEmployee.setSalary(BigDecimal.valueOf(1000.10));
        newEmployee.setDeg("PhD");
        newEmployee.getSkills().add(new Skill("Programming"));
        newEmployee.setDepartment(new Department("Home Office"));

        Employee created = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newEmployee))
                .readEntity(Employee.class);

        System.out.println(created);

        Employee read = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getId() + "")
                .request(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(Employee.class);

        System.out.println(read);


        // Response is AutoClosable
        // Response.close() has to be called if without try-with-resources statement
        try (Response delete = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getId() + "")
                .request(MediaType.APPLICATION_JSON)
                .delete()) {
            System.out.println("Deleted: " + delete.getStatusInfo());
        }


        try (Response notThere = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getId() + "")
                .request(MediaType.APPLICATION_JSON)
                .get()) {
            System.out.println("Status: " + notThere.getStatusInfo());
        }

        Employee notValidEmployee = new Employee();
        try (Response notValid = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(notValidEmployee))) {
            System.out.println("Status: " + notValid.getStatusInfo());
            System.out.println("Entity: " + notValid.getHeaders());
            if (notValid.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
                // bad request
                List<MessageWrapper> messageWrappers = notValid.readEntity(new GenericType<List<MessageWrapper>>() {
                });
                messageWrappers.stream()
                        .map(w -> "\t" + w.toString())
                        .forEach(System.out::println);
            }
        }

        client.close();

    }

}
