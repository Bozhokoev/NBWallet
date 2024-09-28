package nbwallet.api.application;

import lombok.Data;
import nbwallet.api.controllers.CustomerController;
import nbwallet.api.controllers.ManagerController;


@Data
public class NBWalletApplication {
    private CustomerController customerController;
    private ManagerController managerController;

    public NBWalletApplication(String url) {
        this.customerController = new CustomerController(url);
        this.managerController = new ManagerController(url);

    }
}