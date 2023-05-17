package sas.sdet.techtest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sas.sdet.techtest.domain.User;
import sas.sdet.techtest.repository.RepositoryClass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllersIntegrationTest {

    @Autowired
    private RepositoryClass repositoryClass;

    @Autowired
    private MockMvc mockMvc;

    /**
     * This test performs get method call for uri endpoint '/user/{name}' by using actual application
     * @throws Exception
     */
    @Test
    void shouldLoadUser() throws Exception {
        //Creating a user
        User user1 = new User("Jack", 10);

        repositoryClass.loadUser(user1.getName());
        mockMvc.perform(get("/user/{name}", user1.getName())).andExpect(status().isOk());
    }
}
