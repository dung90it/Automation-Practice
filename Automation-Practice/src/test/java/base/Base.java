package base;

import com.kisslab.testrail.APIException;
import com.kisslab.testrail.TestRailClient;
import com.kisslab.testrail.TestRailStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class Base {
    private TestRailClient trClient = new TestRailClient();
    private String testRunId = "101";

    @BeforeClass
    public void setUp() {


    }


    @AfterMethod
    public void cleanUp(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description != null) {
            String[] arrayDes = description.split(":");
            if (arrayDes.length >= 2) {
                String caseID = arrayDes[0];
                String comment = arrayDes[1];
                TestRailStatus statusId = TestRailStatus.Failed;
                if (result.isSuccess()) {
                    statusId = TestRailStatus.Passed;
                }
                try {
                    trClient.addResultForCase(testRunId, caseID, statusId, comment);
                } catch (APIException | IOException apiException) {
                    apiException.printStackTrace();
                }

            }
        }

    }
}
