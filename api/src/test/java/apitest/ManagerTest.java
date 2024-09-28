package apitest;

import nbwallet.api.asserts.ApiAssert;
import nbwallet.api.controllers.CustomerController;
import nbwallet.api.controllers.ManagerController;
import nbwallet.api.entity.customer.Login;
import nbwallet.api.entity.customer.account.AccountPlan;
import nbwallet.api.entity.customer.account.GetAllAccounts;
import nbwallet.api.entity.transactions.TransactionInfo;
import nbwallet.api.entity.transactions.TransactionStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ManagerTest extends NBWalletApiTest{
    ManagerController managerController;
    CustomerController  customerController;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        managerController = application.getManagerController();
        customerController = application.getCustomerController();
        managerController.managerLogin(Login.getManager());
        ApiAssert.assertThat(managerController.getResponse()).isCorrectStatusCode(200);
        Assert.assertNotNull(managerController.getResponse());
    }

    @Test
    public void getAllTransactionsTest() {
        managerController.getAllTransactions();
        Assert.assertNotNull(managerController.getResponse());
        ApiAssert.assertThat(managerController.getResponse()).isCorrectStatusCode(200);
    }

    @Test
    public void approveAllTransactionsTest() {
        List<TransactionInfo> transactions = managerController.getAllTransactions().getItems();
        for (TransactionInfo transaction : transactions) {
            TransactionStatus status = TransactionStatus.builder()
                    .id(transaction.getId())
                    .status(1)
                    .build();
            managerController.approveAllTransactions(status);
        }
    }

    @Test
    public void createAccountPlanTest(){
        managerController.createAccountPlan(AccountPlan.builder()
                        .name("VIPPlatinum")
                        .annualServicePrice(10000)
                        .build());

    }

    @Test
    public void deleteAccountPlanById(){
        managerController.getAllAccountPlans();
        managerController.deleteAccountPlanById(AccountPlan.builder()
                        .id(152)
                        .build());
    }

    @Test
    public void putAccountPlansByIdTest(){
        GetAllAccounts accountPlans = managerController.getAllAccountPlans();
        int id = accountPlans.getItems().get(accountPlans.getItems().size()-1).getId();
        managerController.putAccountPlanById(AccountPlan.builder()
                        .id(id)
                        .name("Altyn-Kazyna")
                        .annualServicePrice(10000)
                        .build());
    }

    @Test
    public void getAllAccountPlansTest(){
        managerController.getAllAccountPlans();
    }

    @Test
    public void getAccountFromBlackListTest(){
        managerController.getAccountFromBlackList();
    }
}
