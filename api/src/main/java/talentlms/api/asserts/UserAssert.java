package talentlms.api.asserts;

import talentlms.api.entity.BaseEntity;
import talentlms.api.entity.User;

public class UserAssert extends EntityAssert {
    public UserAssert(BaseEntity actualBaseEntity) {
        super(actualBaseEntity);
    }

    public static UserAssert assertThat(User user) {
        return new UserAssert(user);
    }
}