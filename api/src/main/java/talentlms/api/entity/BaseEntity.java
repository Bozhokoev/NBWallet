package talentlms.api.entity;

import talentlms.api.utils.JsonUtils;

public abstract class BaseEntity {
    public String toJson() {
        return JsonUtils.toJson(this);
    }
}