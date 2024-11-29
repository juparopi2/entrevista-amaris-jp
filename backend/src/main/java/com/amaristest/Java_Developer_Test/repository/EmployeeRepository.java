package com.amaristest.Java_Developer_Test.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.amaristest.Java_Developer_Test.model.Employee;
import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class EmployeeRepository {

    private final RestTemplate restTemplate;

    //private final String allEmployeesUrl = "http://dummy.restapiexample.com/api/v1/employees";
    //private final String employeeByIdUrl = "http://dummy.restapiexample.com/api/v1/employee/";
    private final String allEmployeesUrl = "https://www.freetestapi.com/api/v1/users";
    private final String employeeByIdUrl = "https://www.freetestapi.com/api/v1/users/";

    public EmployeeRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Employee> findAll() {

        // Esta era la forma de hacerlo con el endpoint propuesto. Aquí no estaba manejando errores.
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Cookie", "humans_21909=1; __gads=ID=47ffb1f02311910b:T=1732675220:RT=1732675220:S=ALNI_MaJpT6veIRo3Owk1IVXPtxnKjKggQ; __gpi=UID=00000ea4a54dc6f2:T=1732675220:RT=1732675220:S=ALNI_MZPZMRD-xJAD6PVc5hfLUIMC1liXg; __eoi=ID=19821b699ef79268:T=1732675220:RT=1732675220:S=AA-AfjbU6dmcDmTGiLHTeIgse2hp; _ga=GA1.2.979028437.1732675220; FCNEC=%5B%5B%22AKsRol_iTywQyTCHQUubH4PVzNs5HUivrnxaw6SNA7olXzNupFe2cblKiS_nXag3W7lfIiBQdJ-yRlkvgwIb9kjmCdhzfT79l1dulozPG89OStI-iWlPCVPi9eLMuPeaZDT80kd3BA-TegPz5aKz-1enFrtpeWxZoQ%3D%3D%22%5D%5D; _ga_GC9YM40HPS=GS1.1.1732675219.1.1.1732675269.10.0.0");
        // HttpEntity<String> entity = new HttpEntity<>(headers);
        // ResponseEntity<Object> response = restTemplate.exchange(allEmployeesUrl, HttpMethod.GET, entity, Object.class);
        List<Employee> employees = new ArrayList<>();
        try {
            ResponseEntity<JsonNode[]> response = restTemplate.getForEntity(allEmployeesUrl, JsonNode[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode[] users = response.getBody();
                if (users != null) {
                    for (JsonNode user : users) {
                        JsonNode address = user.get("address");
                        Employee employee = new Employee(
                                user.get("id").asLong(),
                                user.get("name").asText(),
                                address.get("zip").asInt(),
                                user.get("age").asInt(),
                                ""
                        );
                        employees.add(employee);
                    }
                }
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error de cliente: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Error del servidor: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
        return employees;
    }

    public List<Employee> findById(Long id) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Cookie", "humans_21909=1; __gads=ID=47ffb1f02311910b:T=1732675220:RT=1732675220:S=ALNI_MaJpT6veIRo3Owk1IVXPtxnKjKggQ; __gpi=UID=00000ea4a54dc6f2:T=1732675220:RT=1732675220:S=ALNI_MZPZMRD-xJAD6PVc5hfLUIMC1liXg; __eoi=ID=19821b699ef79268:T=1732675220:RT=1732675220:S=AA-AfjbU6dmcDmTGiLHTeIgse2hp; _ga=GA1.2.979028437.1732675220; FCNEC=%5B%5B%22AKsRol_iTywQyTCHQUubH4PVzNs5HUivrnxaw6SNA7olXzNupFe2cblKiS_nXag3W7lfIiBQdJ-yRlkvgwIb9kjmCdhzfT79l1dulozPG89OStI-iWlPCVPi9eLMuPeaZDT80kd3BA-TegPz5aKz-1enFrtpeWxZoQ%3D%3D%22%5D%5D; _ga_GC9YM40HPS=GS1.1.1732675219.1.1.1732675269.10.0.0");

        // HttpEntity<String> entity = new HttpEntity<>(headers);
        // ResponseEntity<Object> response = restTemplate.exchange(employeeByIdUrl+"/"+id, HttpMethod.GET, entity, Object.class);
        List<Employee> employee = new ArrayList<>();

        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    employeeByIdUrl + id,
                    HttpMethod.GET,
                    null,
                    JsonNode.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode user = response.getBody();
                if (user != null) {
                    JsonNode address = user.get("address");
                    employee.add(new Employee(
                            user.get("id").asLong(),
                            user.get("name").asText(),
                            address.get("zip").asInt(),
                            user.get("age").asInt(),
                            ""
                    ));
                }
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Error de cliente: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            System.err.println("Error del servidor: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }

        return employee;
    }
}
