package apitest;

import nbwallet.api.application.NBWalletApplication;
import org.testng.annotations.BeforeSuite;

import static nbwallet.api.enums.NBWalletEndPoints.URL;


public class NBWalletApiTest {
    protected NBWalletApplication application;

    @BeforeSuite
    public void setup() {
        application = new NBWalletApplication(URL);
    }
}
