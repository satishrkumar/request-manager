package net.pay.you.back.request.manager;

import net.pay.you.back.request.manager.domain.user.User;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = RequestManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    final User userModel = User.builder()
            .emailId("satish.kumar@yahoo.com")
            .firstName("Satish")
            .lastName("Kumar")
            .postalcode("BH8 9EG")
            .town("Bournemouth")
            .address1("St Albans Avenue")
            .build();
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;


    @Test
    public void testUserRestEndPoints() throws JSONException {

        testCreateUser();

        testGetUser();

        testDelete();


    }

    private void testCreateUser() {
        //Test create user
        HttpEntity<User> entity = new HttpEntity<>(userModel, headers);

        ResponseEntity<User> response = restTemplate.exchange(
                createURLWithPort("/user/create"),
                HttpMethod.POST, entity, User.class);
        userModel.setId(response.getBody().getId());
        assertEquals(userModel, response.getBody());
    }

    private void testGetUser() {
        //test Get User with id
        HttpEntity<String> entityGet = new HttpEntity<>(null, headers);
        ResponseEntity<User> responseGet = restTemplate.exchange(
                createURLWithPort("/user/read/" + userModel.getId()),
                HttpMethod.GET, entityGet, User.class);

        assertEquals(userModel, responseGet.getBody());
    }

    private void testDelete() {
        //test delete user with id
        HttpEntity<String> entityDelete = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseDelete = restTemplate.exchange(
                createURLWithPort("/user/delete/" + userModel.getId()),
                HttpMethod.DELETE, entityDelete, String.class);

        assertEquals("User deleted with id " + userModel.getId(), responseDelete.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
