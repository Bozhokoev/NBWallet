package talentLMS.api_tests;

import org.testng.annotations.BeforeSuite;
import talentlms.api.application.TalentLMSApplication;

import static talentlms.api.TalentLMSEndpoints.URL;


public class BaseApiTest {
    protected TalentLMSApplication application;

    @BeforeSuite
    public void setup() {
        application = new TalentLMSApplication(URL);
    }
}