/**
 * Default entry point for the framework
 *
 */
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import util.Listeners.TestListener;

@Listeners({ TestListener.class })
public class Login {
    static TestNG testNG;
    public static void main(String[] args) {
        System.out.println("Starting Integration Tests!");
        // Create object of TestNG Class
        TestNG runner=new TestNG();
        // Create a list of String
        List<String> suitefiles=new ArrayList<String>();
        // Add xml file which you have to execute
        suitefiles.add("classes/RegressionSuite.xml");
        // now set xml file for execution
        runner.setTestSuites(suitefiles);
        // finally execute the runner using run method
        runner.run();
    }

}