package nbwallet.api.environmentmanager;

import nbwallet.api.utils.ApiConfigReader;

public class ManagerApiHandler implements EndpointHandler {
    private int port;

    public ManagerApiHandler() {
        this.port = Integer.parseInt(ApiConfigReader.getValue("manager.port"));
    }

    @Override
    public int getPort() {
        return Integer.parseInt(ApiConfigReader.getValue("manager.port"));
    }
}
