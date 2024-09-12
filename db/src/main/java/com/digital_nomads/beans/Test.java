package com.digital_nomads.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(onlyExplicitlyIncluded = true) // Only include fields explicitly marked with @ToString.Include
public class Test {

    @ToString.Include(name = "name")  // Will include if not null
    private String name;

    @ToString.Include(name = "lastName")  // Will include if not null
    private String lastName;

    @ToString.Include(name = "email")  // Will include even if not explicitly marked because of onlyExplicitlyIncluded = true
    private String email;

    public static Test generateTest() {
        // Using the builder to only set the email field
        Test test = Test.builder()
                .email("Test123")
                .build();
        return test;
    }

    public static void main(String[] args) {
        Test test = Test.generateTest();
        System.out.println(test);
    }
}
