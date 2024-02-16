package tests.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.*;
import org.testng.xml.XmlSuite;
import tests.setup.BaseTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static tests.properties.Environment.ENV;
import static tests.properties.Environment.URL;

@Log4j2
public class TestListeners extends BaseTest implements ITestListener, IReporter, IMethodInterceptor {

    private LocalDateTime testRunStart;

    @Override
    public void onStart(ITestContext context) {
        log.info("================= TEST VARIABLES =================");
        log.info("TEST ENV:   {}", ENV);
        log.info("URL:        {}", URL);
        String scope = System.getProperty("scope");
        String scopeGitlab = System.getProperty("surefire.suiteXmlFiles");
        if (scope != null) log.info("TEST SCOPE: {}", System.getProperty("scope"));
        if (scopeGitlab != null) log.info("TEST SCOPE: {}", scopeGitlab);
        log.info("==================================================");
        testRunStart = LocalDateTime.now();
        log.info("TEST RUN STARTED AT: {}", testRunStart);
    }

    @Override
    public void onFinish(ITestContext context) {
        LocalDateTime finishTime = LocalDateTime.now();
        log.info("TEST RUN FINISHED AT: {}", finishTime);

        Duration between = Duration.between(testRunStart, finishTime);
        log.info("TESTED IN: {}s", between);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("EXECUTE TEST: {}", getFullTestName(iTestResult));
        String description = iTestResult.getMethod().getDescription();
        if (!description.isEmpty()) {
            log.info("TEST DESCRIPTION: {}", description);
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("FINISHED TEST SUCCESSFULLY: %s%n", getFullTestName(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("FAILED TEST: {}", getFullTestName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("SKIPPING TEST: {}", getFullTestName(iTestResult));
    }

    private static String getFullTestName(ITestResult iTestResult) {
        return iTestResult.getInstanceName() + "." + iTestResult.getName();
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        log.info("TEST EXECUTION RESULTS:");
        for (ISuite iSuite : suites) {
            Set<Map.Entry<String, ISuiteResult>> results = iSuite.getResults().entrySet();
            for (Map.Entry<String, ISuiteResult> result : results) {
                ITestContext testContext = result.getValue().getTestContext();

                testContext.getPassedTests().getAllResults().forEach(e -> log.info("PASSED: " + e.getMethod().getMethodName()));
                testContext.getSkippedTests().getAllResults().forEach(e -> log.info("SKIPPED: " + e.getMethod().getMethodName()));
                testContext.getFailedTests().getAllResults().forEach(e -> log.info("FAILED: " + e.getMethod().getMethodName()));
            }
        }
    }

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        methods.forEach(e -> e.getMethod().setRetryAnalyzerClass(RetryAnalyzer.class));
        return methods;
    }
}