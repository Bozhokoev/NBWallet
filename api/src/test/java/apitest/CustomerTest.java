package apitest;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import nbwallet.api.asserts.ApiAssert;
import nbwallet.api.controllers.CustomerController;
import nbwallet.api.entity.customer.Login;
import nbwallet.api.entity.customer.account.AccountTransactionLimit;
import nbwallet.api.entity.customer.account.Funds;
import nbwallet.api.entity.customer.account.GetAllAccounts;
import nbwallet.api.entity.customer.account.Transfer;
import nbwallet.api.utils.EntityManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CustomerTest extends NBWalletApiTest{
    CustomerController customerController;
    GetAllAccounts accounts;


    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        customerController = application.getCustomerController();
        customerController.customerLogin(Login.getCustomer());
        ApiAssert.assertThat(customerController.getResponse()).isCorrectStatusCode(200);
        Assert.assertNotNull(customerController.getResponse());
    }

//    @Test
//    public void getTokenCustomer(){
//        CustomerSignUp expectedCustomer = EntityManager.generateCustomer();
//        customerController.getTokenCustomer(CustomerLogin.builder()
//                .userName(expectedCustomer.getEmail())
//                .password(expectedCustomer.getPassword())
//                .build());
//    }

//    @Test
//    public void getTokenCustomerTest() {
//        customerController.getTokenCustomer( CustomerLogin.builder()
//                .userName(generatedCustomer.getEmail())
//                .password(generatedCustomer.getPassword())
//                .build());
//    }
    @Test
    @Description("Verify that account can be created and expects a successful response (status code 200)")
    @Owner("Akim")
    public void customerSignUpTest() {
        customerController.signUpCustomer(EntityManager.generateCustomer());
        ApiAssert.assertThat(customerController.getResponse()).isCorrectStatusCode(201);
    }

    @Test
    @Description("Verify that account can be created and expects a successful response (status code 200)")
    @Owner("Akim")
    public void createAccountTest(){
      customerController.createAccount(EntityManager.generateCreationForm());
    }

    @Test
    @Description("Fetches all accounts and expects a successful response (status code 200)")
    @Owner("Akim")
    public void getAllAccountsTest(){
        accounts = customerController.getAllAccounts();
    }

    @Test
    @Description("Fetches all available account plans and expects a successful response (status code 200)")
    @Owner("Akim")
    public void getAllAccountsPlanTest(){
        customerController.getAllAccountPlan();
    }

    @Test
    @Description("Sets a limit on a transaction by ID, expected response status code 200")
    @Owner("Akim")
    public void setAccountTransactionLimitByIdTest(){
        accounts = customerController.getAllAccounts();
        int id = accounts.getItems().get(0).getId();
        customerController.setAccountTransactionLimitById(AccountTransactionLimit.builder()
                        .accountId(id)
                        .amount(200)
                        .isPermanently(true)
                        .build());
    }

    @Test
    @Description("Change a limit on a transaction by ID, expected response status code 200")
    @Owner("Akim")
    public void putAccountTransactionLimitByIdTest(){
        accounts = customerController.getAllAccounts();
        int id = accounts.getItems().get(0).getId();
        customerController.putAccountTransactionLimitById(AccountTransactionLimit.builder()
                .accountId(id)
                .amount(100)
                .isPermanently(true)
                .build());
    }

    @Test
    @Description("Adding funds to an account")
    @Owner("Akim")
    public void addFundsAccountById(){
        accounts = customerController.getAllAccounts();
        String accountNumber = accounts.getItems().get(0).getNumber();
        customerController.addFundsAccountById(Funds.builder()
                        .accountNumber(accountNumber)
                        .amount(100)
                        .build());
        ApiAssert.assertThat(customerController.getResponse()).isCorrectStatusCode(200);
    }


    @Test
    @Description("Transfer funds from an account to another account")
    @Owner("Akim")
    public void transferFundsTest() {
        accounts = customerController.getAllAccounts();
        Faker faker = new Faker();
        customerController.transferFunds(Transfer.builder()
                        .amount(50)
                        .sourceAccountNumber(accounts.getItems().get(0).getNumber())
                        .destinationAccountNumber(accounts.getItems().get(1).getNumber())
                        .notes(faker.lorem().characters(20))
                .build());
    }

    @Test
    @Description("Deletes a transaction limit by the specified ID, expected response status code 200")
    @Owner("Akim")
    public void deleteAccountTransactionLimitByIdTest(){
        accounts = customerController.getAllAccounts();
        String accountNumber = accounts.getItems().get(0).getNumber();
        customerController.deleteAccountTransactionLimitById(AccountTransactionLimit.builder()
                        .accountId(Integer.parseInt(accountNumber))
                        .build());
    }
}
