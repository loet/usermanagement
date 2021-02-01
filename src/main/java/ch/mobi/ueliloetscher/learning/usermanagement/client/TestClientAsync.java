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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestClientAsync {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // System.out.println("Start: " + start);

        List<Future<Response>> responses = new ArrayList<>();
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Client client = ClientBuilder.newClient();
            clients.add(client);
            Future<Response> read = client
                    .target("http://127.0.0.1:8080/usermanagement/rest/employee/slow")
                    .path("176")
                    .request(MediaType.APPLICATION_JSON)
                    .async()
                    .get();
            responses.add(read);
        }

        responses.stream()
                .forEach(responesFuture -> {
                    Response response = null;
                    try {
                        response = responesFuture.get();
                        Employee employee = response.readEntity(Employee.class);
                        System.out.println(employee);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        if (response != null) {
                            response.close();
                        }
                    }
                });

        // System.out.println(read);

        long end = System.currentTimeMillis();
        // System.out.println("End: " + end);
        System.out.println("Time in s: " + ((end - start) / 1000));

        clients.stream().forEach(client -> client.close());

    }

}
