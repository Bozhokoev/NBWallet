package apitest;

import nbwallet.api.controllers.CustomerController;
import nbwallet.api.entity.customer.CustomerLogin;
import nbwallet.api.entity.customer.CustomerSignUp;
import nbwallet.api.entity.customer.account.RequestAccount;
import nbwallet.api.utils.EntityManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

public class CustomerTest extends NBWalletApiTest{
    CustomerController customerController;
    CustomerSignUp generatedCustomer;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        customerController = application.getCustomerController();
        generatedCustomer = EntityManager.generateCustomer();
        customerController.signUpCustomer(generatedCustomer);
    }

//    @Test
//    public void getTokenCustomer(){
//        CustomerSignUp expectedCustomer = EntityManager.generateCustomer();
//        customerController.getTokenCustomer(CustomerLogin.builder()
//                .userName(expectedCustomer.getEmail())
//                .password(expectedCustomer.getPassword())
//                .build());
//    }

    @Test
    public void getTokenCustomerTest() {
        customerController.getTokenCustomer( CustomerLogin.builder()
                .userName(generatedCustomer.getEmail())
                .password(generatedCustomer.getPassword())
                .build());
    }

    @Test
    public void createAccountTest(){
        customerController.createAccount(CustomerLogin.builder()
                        .userName(generatedCustomer.getEmail())
                        .password(generatedCustomer.getPassword())
                        .build(), EntityManager.generateCreationForm());
    }

    @Test
    public void getAllAccountsTest(){
        getTokenCustomerTest();
        customerController.getAllAccounts();
    }
}
