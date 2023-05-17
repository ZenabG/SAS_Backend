package sas.sdet.techtest.service;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sas.sdet.techtest.domain.Order;
import sas.sdet.techtest.domain.Tournament;
import sas.sdet.techtest.domain.User;
import sas.sdet.techtest.repository.NotEnoughProException;
import sas.sdet.techtest.repository.RepositoryClass;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ServiceClassTest {


    @InjectMocks
    private ServiceClass serviceClass;
    @Mock
    private RepositoryClass repositoryClass;

    /**
     * This test creates 2 orders for different tournaments for user Jack.
     * Mocks the repository method orderListByUser() to return ascending ordered list of orders - {order2, order1}
     * Asserting that 2 orders are created and order list is in ascending order
     * @throws NotEnoughProException
     */

    @Test
    void shouldReturnOrderedListByUser() throws NotEnoughProException {
        //Create 2 orders for 2 different tournaments for user 'Jack'
        User user = new User("Jack", 10);
        Tournament t1 = new Tournament("Murfreesboro Strike and Spare", 20, "Torneo");
        Tournament t2 = new Tournament("Bowlerama Lanes Iowa", 7, "Torneo");
        Order order1 = new Order(3L, user, t1);
        Order order2 = new Order(2L, user, t2);

        //Use BDDMockito to create an ordered list by user Jack's name. This is expected behavior.
        BDDMockito.given(repositoryClass.orderListByUser(user.getName())).willReturn(List.of(order2,order1));

        //This is the actual behavior we want to test
        List<Order> ordersList = serviceClass.orderListByUser(user.getName());

        // Assert that 2 orders are created
        // Assert that orders are listed in ascending order i.e. order2 then order1
        assertThat(ordersList).hasSize(2);
        assertThat(ordersList.get(0).getId()).isEqualTo(2);
    }

}
