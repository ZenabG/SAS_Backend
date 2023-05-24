package sas.sdet.techtest.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import sas.sdet.techtest.domain.User;
import sas.sdet.techtest.repository.RepositoryClass;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllersIntegrationTest {

    @Autowired
    private RepositoryClass repositoryClass;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldLoadUser() throws Exception {
        //Create User
        User user1 = new User("Jack", 10);
        repositoryClass.createUser(user1);

        //Making HTTP get call to see user content is loaded
        mockMvc.perform(get("/user/{name}", user1.getName())).andExpect(status().isOk()).andExpect(content().json("{\"name\":\"Jack\",\"dexterity\":10}"));;
    }
}
