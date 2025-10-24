package com.kisslab.testrail;

import java.util.HashMap;
import java.util.Map;

public class TestRailClient {

    private APIClient client;

    public TestRailClient() {
        client = new APIClient("https://YOUR-INSTANCE.testrail.io/");
        client.setUser("your-email@example.com");
        client.setPassword("YOUR-API-KEY");
    }
    /* 1: Passed
     2: Blocked
     3: Untested
     4: Retest
     5: Failed
     */
    public void addResultForCase(String runId, String caseId, TestRailStatus testStatus, String comment)
            throws APIException, java.io.IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("status_id", testStatus.getValue());
        data.put("comment", comment);
        // API endpoint to add a result for a specific case in a specific run
        client.sendPost("add_result_for_case/" + runId + "/" + caseId, data);
    }
}
