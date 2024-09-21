package nbwallet.api.controllers;


import nbwallet.api.ApiRequest;
import nbwallet.api.entity.customer.CustomerLogin;
import nbwallet.api.entity.customer.CustomerSignUp;
import nbwallet.api.entity.customer.account.GetAllAccounts;
import nbwallet.api.entity.customer.account.RequestAccount;


import java.util.HashMap;

import static nbwallet.api.enums.NBWalletEndPoints.*;
import static nbwallet.api.enums.NBWalletEndPoints.SIGNUP;


public class CustomerController extends ApiRequest {
    public CustomerController(String url){
        super(url);
    }

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

    public void getTokenCustomer(CustomerLogin customerLogin) {
        super.post(getEndpoint(API, V1, AUTHENTICATION, LOGIN), customerLogin.toJson());
    }

    public void createAccount(CustomerLogin customerLogin, RequestAccount account){
        getTokenCustomer(customerLogin);
        super.postAuth(getEndpoint(API, V1, ACCOUNT), account.toJson());
    }

    public GetAllAccounts[] getAllAccounts(){
        return super.get(getEndpoint(API, V1, ACCOUNT)).as(GetAllAccounts[].class);
    }
}