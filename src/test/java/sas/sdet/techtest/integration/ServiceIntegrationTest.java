
/**
 * Instrucciones:
 * <p>
 * - Crea un repo privado compartido sólo con dfleta en GitHub.
 * - Realiza un commit al pasar cada caso test.
 * - Sin este commit tras cada caso, no corrijo el examen.
 */

package sas.sdet.techtest.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import sas.sdet.techtest.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import sas.sdet.techtest.service.ServiceClass;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/create-data.sql") //consists of insert queries
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD) //consists of delete queries
public class ServiceIntegrationTest {

    @Autowired
    private ServiceClass serviceClass;

    /**
     * Sql scripts create-data.sql and cleanup-data.sql is for inserting data into DB and deleting it.
     * There are 2 orders for the user 'Munson' in create-data.sql
     * This test returns ascending ordered list of orders for user 'Munson'
     */
    @Test
    void getOrdersListByName() {
        List<Order> ordersList = serviceClass.orderListByUser("Munson");
        assertThat(ordersList).hasSize(2);
        assertThat(ordersList.get(0).getId()).isEqualTo(2);
    }
}
