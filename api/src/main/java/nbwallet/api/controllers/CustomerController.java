package nbwallet.api.controllers;


import nbwallet.api.ApiRequest;
import nbwallet.api.entity.CustomerSignUp;

import java.util.HashMap;

import static nbwallet.api.enums.NBWalletEndPoints.*;
import static nbwallet.api.enums.NBWalletEndPoints.SIGNUP;


public class CustomerController extends ApiRequest {
    public CustomerController(String url){
        super(url);
    }

    public CustomerSignUp signUpCustomer(CustomerSignUp customer){
        HashMap<String, String> params = new HashMap<>(){{
            put("FirstName", customer.getFirstName());
            put("LastName", customer.getLastName());
            put("Password", customer.getPassword());
            put("Email", customer.getEmail());
            put("PhoneNumber", customer.getPhoneNumber());
        }};
        return super.post(getEndpoint(API, V1, AUTHENTICATION, SIGNUP), params).as(CustomerSignUp.class);
    }
}