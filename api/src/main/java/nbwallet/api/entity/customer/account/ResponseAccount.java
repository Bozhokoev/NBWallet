package nbwallet.api.entity.customer.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbwallet.api.entity.BaseEntity;

import java.util.Date;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAccount extends BaseEntity {
     int id;
     String number;
     int balance;
     int currency;
     int status;
     List<AccountPlan> accountPlan;
     List<AccountTransactionLimit> accountTransactionLimit;
     Date createdDate;
}
