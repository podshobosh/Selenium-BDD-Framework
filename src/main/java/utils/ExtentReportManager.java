package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("Reports/SparkReport.html");

            // Inject Featherlight correctly
            spark.config().setJs(
                    "document.write(\"<script src='https://cdnjs.cloudflare.com/ajax/libs/featherlight/1.7.14/featherlight.min.js'></script>\");"
            );

            spark.config().setCss(
                    "document.write(\"<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/featherlight/1.7.14/featherlight.min.css' />\");"
            );

            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void attachScreenshot(String base64Image, String description) {
        getTest().info(
                description,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build()
        );
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
