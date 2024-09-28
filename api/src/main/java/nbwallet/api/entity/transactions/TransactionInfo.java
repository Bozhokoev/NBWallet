package nbwallet.api.entity.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbwallet.api.entity.BaseEntity;
import nbwallet.api.entity.customer.account.DestinationAccount;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TransactionInfo extends BaseEntity {
     int id;
     String number;
     Object sourceAccount;
     DestinationAccount destinationAccount;
     int fee;
     int amount;
     String currency;
     String status;
     Date created;
     Object lastModified;
}
