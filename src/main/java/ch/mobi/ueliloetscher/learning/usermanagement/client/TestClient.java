package ch.mobi.ueliloetscher.learning.usermanagement.client;

import ch.mobi.ueliloetscher.learning.usermanagement.dto.AddDepartmentDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.AddEmployeeDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.AddSkillDTO;
import ch.mobi.ueliloetscher.learning.usermanagement.dto.CollectionWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Department;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Employee;
import ch.mobi.ueliloetscher.learning.usermanagement.entity.Skill;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.MessageWrapper;
import ch.mobi.ueliloetscher.learning.usermanagement.validation.ValidationException;

import javax.jms.Message;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
                .queryParam("ename", "a")
                .request(MediaType.APPLICATION_JSON)
                .get(CollectionWrapper.class);

        System.out.println(searched);

        AddEmployeeDTO newEmployee = new AddEmployeeDTO();
        newEmployee.setEname("Testclient");
        newEmployee.setSalary(1000.10);
        newEmployee.setDeg("PhD");
        newEmployee.getSkills().add(new AddSkillDTO("Programming"));
        newEmployee.setDepartment(new AddDepartmentDTO("Home Office"));

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
                .get()
                .readEntity(Employee.class);

        System.out.println(read);


        // Response is AutoClosable
        // Response.close() has to be called if without try-with-resources statement
        try (Response delete = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getEid() + "")
                .request(MediaType.APPLICATION_JSON)
                .delete()) {
            System.out.println("Deleted: " + delete.getStatusInfo());
        }


        try (Response notThere = client
                .target("http://127.0.0.1:8080/usermanagement/rest/employee")
                .path(created.getEid() + "")
                .request(MediaType.APPLICATION_JSON)
                .get()) {
            System.out.println("Status: " + notThere.getStatusInfo());
        }

        AddEmployeeDTO notValidEmployee = new AddEmployeeDTO();
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
