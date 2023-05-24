package sas.sdet.techtest.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import sas.sdet.techtest.domain.User;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ComponentScan("sas.sdet.techtest.repository")
final public class RepositoryClassTest {

    @Autowired
    private RepositoryClass repositoryClass;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * This is a slice test for repository method loadUser()
     */
    @Test
    void shouldLoadUser() {
        //Creating user
        entityManager.persist(new User("Jack", 10));

        //Retrieving user
        User user1 = repositoryClass.loadUser("Jack");

        //Asserting correct user was retrieved
        assertThat(user1.getName()).isEqualTo("Jack");
    }

}
