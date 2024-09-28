package nbwallet.api.controllers;

import nbwallet.api.ApiRequest;
import nbwallet.api.asserts.ApiAssert;
import nbwallet.api.entity.blacklist.BlackList;
import nbwallet.api.entity.customer.Login;
import nbwallet.api.entity.customer.account.AccountPlan;
import nbwallet.api.entity.customer.account.GetAllAccounts;
import nbwallet.api.entity.transactions.Transaction;
import nbwallet.api.entity.transactions.TransactionStatus;

import static nbwallet.api.enums.NBWalletEndPoints.ACCOUNT_PLANS;
import static nbwallet.api.enums.NBWalletEndPoints.API;
import static nbwallet.api.enums.NBWalletEndPoints.AUTHENTICATION;
import static nbwallet.api.enums.NBWalletEndPoints.BLACKLISTS;
import static nbwallet.api.enums.NBWalletEndPoints.ID;
import static nbwallet.api.enums.NBWalletEndPoints.LOGIN;
import static nbwallet.api.enums.NBWalletEndPoints.MANAGER_API;
import static nbwallet.api.enums.NBWalletEndPoints.STATUS;
import static nbwallet.api.enums.NBWalletEndPoints.TRANSACTIONS;
import static nbwallet.api.enums.NBWalletEndPoints.V1;

public class ManagerController extends ApiRequest {
    public ManagerController(String url) {
        super(url);
    }

    public void managerLogin(Login manager){
        super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), manager.toJson());
    }


    public Transaction getAllTransactions() {
        return super.getManager(getEndpoint(MANAGER_API, V1, TRANSACTIONS)).as(Transaction.class);
    }

    public void approveAllTransactions(TransactionStatus status) {
        super.putBodyAndPath(getEndpoint(MANAGER_API, V1, TRANSACTIONS, STATUS), status.toJson());
    }

    public void createAccountPlan(AccountPlan accountPlan){
        super.postBody(getEndpoint(MANAGER_API, V1, ACCOUNT_PLANS), accountPlan.toJson());
        ApiAssert.assertThat(response).isCorrectStatusCode(200);
    }

    public GetAllAccounts getAllAccountPlans(){
        return super.getManager(getEndpoint(MANAGER_API, V1, ACCOUNT_PLANS)).as(GetAllAccounts.class);
    }

    public void deleteAccountPlanById(AccountPlan accountPlan){
        int id = accountPlan.getId();
        super.deleteAccountPlan(getEndpoint(MANAGER_API, V1, ACCOUNT_PLANS), id);
    }

    public void putAccountPlanById(AccountPlan accountPlan){
        super.putBodyAndPath(getEndpoint(MANAGER_API, V1, ACCOUNT_PLANS, ID), accountPlan.toJson());
    }

    public void getAccountFromBlackList(){
        super.getManager(getEndpoint(MANAGER_API, V1, BLACKLISTS)).as(BlackList.class);
    }
}
