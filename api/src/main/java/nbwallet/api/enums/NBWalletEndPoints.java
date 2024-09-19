package nbwallet.api.enums;

import nbwallet.api.utils.ApiConfigReader;

public class NBWalletEndPoints {
    public static final String URL = ApiConfigReader.getValue("url");
    public static final String API = "/api";
    public static final String V1= "v1";
    public static final String AUTHENTICATION= "authentication";
    public static final String SIGNUP= "signup";
}
