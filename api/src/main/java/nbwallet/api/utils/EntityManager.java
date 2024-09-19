package nbwallet.api.utils;


import com.github.javafaker.Faker;
import nbwallet.api.entity.CustomerSignUp;

public class EntityManager {
    private static final Faker faker = new Faker();

    public static CustomerSignUp generateCustomer() {
        faker.number().digits(3);
        return CustomerSignUp.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .password(faker.internet().password(10, 15, true, true, true))
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().toString())
                .build();
    }
    private static boolean isValidPassword(String password) {
        return password.length() >= 6 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase) &&
                password.chars().distinct().count() > 1; // Проверка на наличие хотя бы одного другого символа
    }

    public static void main(String[] args) {
        System.out.println(EntityManager.isValidPassword(EntityManager.generateCustomer().getPassword()));
    }
}