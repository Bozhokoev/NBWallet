package nbwallet.api.environmentmanager;

import nbwallet.api.utils.ApiConfigReader;

public class AuthenticationHandler implements EndpointHandler {
    @Override
    public int getPort() {
        return Integer.parseInt(ApiConfigReader.getValue("identity.port"));
    }
}
