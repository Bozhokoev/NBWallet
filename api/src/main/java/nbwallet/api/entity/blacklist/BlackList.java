package nbwallet.api.entity.blacklist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import nbwallet.api.entity.BaseEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlackList extends BaseEntity {
    String accountNumber;
    LockType lockType;
    int pageNumber;
    int totalPages;
    int totalCount;
    boolean hasPreviousPage;
    boolean hasNextPage;
}
