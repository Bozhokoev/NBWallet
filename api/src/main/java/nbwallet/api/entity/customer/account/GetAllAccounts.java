package nbwallet.api.entity.customer.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbwallet.api.entity.BaseEntity;

import java.util.List;
@Data
@AllArgsConstructor@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllAccounts extends BaseEntity {
    List<ResponseAccount> items;
    int pageNumber;
    int totalPages;
    int totalCount;
    boolean hasPreviousPage;
    boolean hasNextPage;
}
