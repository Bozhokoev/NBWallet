package nbwallet.api.entity.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nbwallet.api.entity.BaseEntity;
import nbwallet.api.utils.ApiConfigReader;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Login extends BaseEntity {
    String userName;
    String password;

    public static Login getCustomer(){
        Login customer = Login.builder()
                .userName(ApiConfigReader.getValue("test.customer.username"))
                .password(ApiConfigReader.getValue("test.customer.password"))
                .build();
        return customer;
    }

    public static Login getManager(){
        Login manager = Login.builder()
                .userName(ApiConfigReader.getValue("manager.email"))
                .password(ApiConfigReader.getValue("manager.password"))
                .build();
        return manager;
    }
}
