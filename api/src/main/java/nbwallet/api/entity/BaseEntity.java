package nbwallet.api.entity;

import nbwallet.api.utils.JsonUtils;

public abstract class BaseEntity {
    public String toJson() {
        return JsonUtils.toJson(this);
    }
}