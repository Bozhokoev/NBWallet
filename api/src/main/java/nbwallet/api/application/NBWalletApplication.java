package nbwallet.api.application;

import lombok.Data;
import nbwallet.api.controllers.CustomerController;


@Data
public class NBWalletApplication {
    private CustomerController customerController;

    public NBWalletApplication(String url) {
        this.customerController = new CustomerController(url);
    }
}