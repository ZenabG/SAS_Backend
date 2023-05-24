# Test Execution
You can run the tests directly by going to each class or just run `mvn clean test -Dtest=!sas.sdet.techtest.integration.ControllersIntegrationTest
` from command line for running all tests except ControllersIntegrationTest as it fails to load actual application context when running with other tests

Error: 
```
java.lang.IllegalStateException: Failed to load ApplicationContext
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker': Invocation of init method failed; nested exception is org.springframework.jdbc.datasource.init.ScriptStatementFailedException: Failed to execute SQL script statement #1 of URL [file:/Users/gorachz/Downloads/SAS/SAS_Backend/target/classes/schema.sql]: drop table t_users if exists; nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Cannot drop "T_USERS" because "FKSNXDWBWUFEQFWFJTY7E81AB28" depends on it; SQL statement:
drop table t_users if exists [90107-200]
Caused by: org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Initialization of bean failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker': Invocation of init method failed; nested exception is org.springframework.jdbc.datasource.init.ScriptStatementFailedException: Failed to execute SQL script statement #1 of URL [file:/Users/gorachz/Downloads/SAS/SAS_Backend/target/classes/schema.sql]: drop table t_users if exists; nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Cannot drop "T_USERS" because "FKSNXDWBWUFEQFWFJTY7E81AB28" depends on it; SQL statement:
drop table t_users if exists [90107-200]
Caused by: org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker': Invocation of init method failed; nested exception is org.springframework.jdbc.datasource.init.ScriptStatementFailedException: Failed to execute SQL script statement #1 of URL [file:/Users/gorachz/Downloads/SAS/SAS_Backend/target/classes/schema.sql]: drop table t_users if exists; nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Cannot drop "T_USERS" because "FKSNXDWBWUFEQFWFJTY7E81AB28" depends on it; SQL statement:
drop table t_users if exists [90107-200]
Caused by: org.springframework.jdbc.datasource.init.ScriptStatementFailedException: 
Failed to execute SQL script statement #1 of URL [file:/Users/gorachz/Downloads/SAS/SAS_Backend/target/classes/schema.sql]: drop table t_users if exists; nested exception is org.h2.jdbc.JdbcSQLSyntaxErrorException: Cannot drop "T_USERS" because "FKSNXDWBWUFEQFWFJTY7E81AB28" depends on it; SQL statement:
drop table t_users if exists [90107-200]
Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: 
Cannot drop "T_USERS" because "FKSNXDWBWUFEQFWFJTY7E81AB28" depends on it; SQL statement:
drop table t_users if exists [90107-200]
```

Run ControllersIntegrationTest separately using `mvn clean test -Dtest=sas.sdet.techtest.integration.ControllersIntegrationTest`

**Pre-requisites** - maven installed and added to path variables. 

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

- While writing slice test for ControllerClass I noticed that `getUserDexterity()` method was not handling the case where user object is null. Added the improvement in `ControllerClass`.
- After adding above improvement, I noticed that `shouldLoadUser()` test from **ControllersIntegrationTest** started failing as user was never getting created. I dug deep and found that **RepositoryClass** was missing `createUser()` method. Made the necessary improvements in **RepositoryClass** and **ControllersIntegration** test. 