package apitest;

import nbwallet.api.application.NBWalletApplication;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static nbwallet.api.enums.NBWalletEndPoints.URL;


public class NBWalletApiTest {
    protected NBWalletApplication application;

    @BeforeClass
    public void setup() {
        application = new NBWalletApplication(URL);
    }
}
