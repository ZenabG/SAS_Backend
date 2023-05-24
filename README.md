# Test Execution
You can run the tests directly by going to each class or just run `mvn clean test` from command line. This latter command requires maven installed and added to path variables. 

## Unit Tests
Business logic i.e. ServiceClass can be tested using Mockito. Mockito will mock the RepositoryClass using @Mock and inject in ServiceClass using @InjectMocks

### Test Folder Structure
- test/java/sas/sdet/techtest/service/ServiceClassTest - Consists of unit test for ServiceClass method 'orderListByUser()'

## Slice Tests
In Spring Boot, a slice test is a type of integration test that focuses on testing a specific slice or layer of your application rather than the entire application. It allows you to test a specific feature or functionality in isolation, typically by loading only the relevant components and dependencies related to that feature.
It avoids the overhead of loading the entire application context and dependencies. 


### What will be tested 
ControllerClass using @WebMvcTest.

Repository class using @DataJpaTest.

### Test Folder Structure
- test/java/sas/sdet/techtest/controller/ControllerClassTest - Consists of 2 slice tests for ControllerClass method 'getUserDexterity()'
- test/java/sas/sdet/techtest/repository/RepositoryClassTest - Consists of slice test for RepositoryClass method 'loadUser()'

## Integration Tests
Whole system's integration testing using actual system under test. This is done using @SpringBootTest

### Test Folder Structure
- test/java/sas/sdet/techtest/integration/ - Consists of 2 integration test classes, one with controller and one for write and delete in DB


# Improvement

While writing slice test for ControllerClass I noticed that `getUserDexterity()` method was not handling the case where user object is null. Added the improvement in `ControllerClass`. 