package nbwallet.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CustomerSignUp extends BaseEntity{
    @JsonProperty(value = "FirstName")
    String firstName;
    @JsonProperty(value = "LastName")
    String lastName;
    @JsonProperty(value = "Password")
    String password;
    @JsonProperty(value = "Email")
    String email;
    @JsonProperty(value = "PhoneNumber")
    String phoneNumber;
}
