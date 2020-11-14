import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/Users/siembrahielotrucho/Documents/RestAPI2020/EmployeeServices/src/test/resources/features/GetValidSingleEmployee.Feature",
        glue = "step/definitions",
        plugin = {"pretty", "html:test-output"})
public class RunCucumberTest {
}
