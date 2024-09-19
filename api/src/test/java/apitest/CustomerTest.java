package apitest;

import nbwallet.api.controllers.CustomerController;
import nbwallet.api.entity.CustomerSignUp;
import nbwallet.api.utils.EntityManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerTest extends NBWalletApiTest{
    CustomerController customerController;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        customerController = application.getCustomerController();
    }

    @Test
    public void createCustomer(){
        CustomerSignUp expectedCustomer = EntityManager.generateCustomer();
        CustomerSignUp actualCustomer = customerController.signUpCustomer(expectedCustomer);
        System.out.println(actualCustomer);
    }
}
