package sas.sdet.techtest.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sas.sdet.techtest.domain.User;
import sas.sdet.techtest.repository.RepositoryClass;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ControllerClass.class)
public class ControllerClassTest {

    //Mocking the repository class
    @MockBean
    private RepositoryClass repositoryClass;

    @Autowired
    private MockMvc mockMvc;

    /**
     * This test performs get method call for uri endpoint '/user/{name}' by mocking the repository
     * @throws Exception
     */
    @Test
    void shouldLoadUser() throws Exception {
        //Creating a user
        User user1 = new User("Jack", 10);

        //Mocking the behavior using BDDMockito to give expected result
        BDDMockito.given(repositoryClass.loadUser(user1.getName())).willReturn(user1);

        //Performing get api call using uri endpoint provided in controller method getUserDexterity()
        mockMvc.perform(get("/user/{name}", user1.getName())).andExpect(status().isOk());
    }
}
