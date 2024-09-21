package nbwallet.api.utils;


import com.github.javafaker.Faker;
import nbwallet.api.entity.customer.CustomerSignUp;
import nbwallet.api.entity.customer.account.RequestAccount;

import java.util.Random;

public class EntityManager {
    private static final Faker faker = new Faker();

    public static CustomerSignUp generateCustomer() {
        return CustomerSignUp.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .password(faker.internet().password(10, 15, true, true, true))
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().toString())
                .build();
    }

    public static RequestAccount generateCreationForm(){
        return RequestAccount.builder()
                .accountPlanId(faker.number().numberBetween(1, 4))
                .currency(faker.number().numberBetween(0,3))
                .build();
    }
}