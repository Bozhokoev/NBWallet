package nbwallet.api.controllers;


import io.qameta.allure.Step;
import nbwallet.api.ApiRequest;
import nbwallet.api.asserts.ApiAssert;
import nbwallet.api.entity.customer.CustomerSignUp;
import nbwallet.api.entity.customer.Login;
import nbwallet.api.entity.customer.account.AccountTransactionLimit;
import nbwallet.api.entity.customer.account.Funds;
import nbwallet.api.entity.customer.account.GetAllAccounts;
import nbwallet.api.entity.customer.account.RequestAccount;
import nbwallet.api.entity.customer.account.ResponseAccount;
import nbwallet.api.entity.customer.account.Transfer;
import java.util.HashMap;

import static nbwallet.api.enums.NBWalletEndPoints.*;
import static nbwallet.api.enums.NBWalletEndPoints.SIGNUP;


public class CustomerController extends ApiRequest {
    public CustomerController(String url){
        super(url);
    }
    @Step("Sign up a new customer with provided details {0}")
    public void signUpCustomer(CustomerSignUp customer){
        HashMap<String, String> params = new HashMap<>(){{
            put("FirstName", customer.getFirstName());
            put("LastName", customer.getLastName());
            put("Password", customer.getPassword());
            put("Email", customer.getEmail());
            put("PhoneNumber", customer.getPhoneNumber());
        }};
        super.post(getEndpoint(API, V1, AUTHENTICATION, SIGNUP), params);
    }

//    public void getTokenCustomer(CustomerLogin customerLogin){
//        HashMap<String, String> params = new HashMap<>(){{
//            put("userName", customerLogin.getUserName());
//            put("password", customerLogin.getPassword());
//        }};
//        CustomerToken customerToken = super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), params).as(CustomerToken.class);
//        customerToken.toJson();
//    }

    @Step("Requests an authentication token using the customer's login credentials {0}")
    public void customerLogin(Login customerLogin) {
        super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), customerLogin.toJson());
        ApiAssert.assertThat(response).isCorrectStatusCode(200);
    }

    @Step("Create account {0} {1}")
    public ResponseAccount createAccount(RequestAccount account){
        return super.postAuth(getEndpoint(API, V1, ACCOUNT), account.toJson()).as(ResponseAccount.class);
    }

    @Step("Get all accounts")
    public GetAllAccounts getAllAccounts(){
        return super.get(getEndpoint(API, V1, ACCOUNT)).as(GetAllAccounts.class);
    }

    @Step("Get all account plans")
    public GetAllAccounts getAllAccountPlan(){
        return super.get(getEndpoint(API, V1, ACCOUNT_PLANS)).as(GetAllAccounts.class);
    }

    @Step ("Set transaction limit for the specified account by id {0}")
    public void setAccountTransactionLimitById(AccountTransactionLimit account){
        super.postAccountTL(getEndpoint(API, V1, ACCOUNT_TRANSACTION_LIMIT), account.toJson());
        ApiAssert.assertThat(response).isCorrectStatusCode(200);
    }

    public void putAccountTransactionLimitById(AccountTransactionLimit account){
        super.putAccountTL(getEndpoint(API, V1, ACCOUNT_TRANSACTION_LIMIT), account.toJson());
        ApiAssert.assertThat(response).isCorrectStatusCode(200);
    }

    public void deleteAccountTransactionLimitById(AccountTransactionLimit account){
        int id = account.getAccountId();
        super.deleteAccountTL(getEndpoint(API, V1, ACCOUNT_TRANSACTION_LIMIT), id);
    }

    public void getAccountTransactionLimitById(AccountTransactionLimit account){
        super.get(getEndpoint(API, V1, ACCOUNT_TRANSACTION_LIMIT)).as(AccountTransactionLimit.class);
    }

    public void addFundsAccountById(Funds funds){
        super.postAuth(getEndpoint(API, V1, TRANSACTIONS, ADD_FUNDS), funds.toJson());
        ApiAssert.assertThat(response).isCorrectStatusCode(200);
    }

    public void transferFunds(Transfer transfer) {
        super.postAuth(getEndpoint(API, V1, TRANSACTIONS), transfer.toJson());
    }
}