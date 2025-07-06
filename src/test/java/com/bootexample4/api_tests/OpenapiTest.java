
package com.bootexample4.api_tests;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
// import com.intuit.karate.http.HttpServer;
// import com.intuit.karate.http.ServerConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenapiTest {

	@Test
	void testAll() {
		String apiHostServer = System.getenv().getOrDefault("nnnn_URL_BASE", "http://127.0.0.1:4010");
		String nnnnauthtoken = System.getenv().getOrDefault("nnnn_AUTH_TOKEN", "dummy_nnnn_AUTH_TOKEN");
		Results results = Runner.path("src/test/java/com/bootexample4/api_tests/Openapi")
			.systemProperty("nnnn_URL_BASE", apiHostServer)
			.systemProperty("nnnn_AUTH_TOKEN", nnnnauthtoken)
			.reportDir("testReport")
			.parallel(1);
		assertEquals(0, results.getFailCount(), results.getErrorMessages());
	}

}
