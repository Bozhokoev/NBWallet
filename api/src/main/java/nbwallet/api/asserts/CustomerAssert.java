package nbwallet.api.asserts;

import nbwallet.api.entity.BaseEntity;
import nbwallet.api.entity.customer.CustomerSignUp;

public class CustomerAssert extends EntityAssert{

    public CustomerAssert(BaseEntity actualBaseEntity) {
        super(actualBaseEntity);
    }

    public static CustomerAssert assertThat(CustomerSignUp customer){
        return new CustomerAssert(customer);
    }
}
