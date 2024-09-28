package nbwallet.api.environmentmanager;

import nbwallet.api.utils.ApiConfigReader;

public class CustomerHandler implements EndpointHandler {
    @Override
    public int getPort() {
        return Integer.parseInt(ApiConfigReader.getValue("customer.port"));
    }
}
