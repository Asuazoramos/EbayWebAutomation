package empserviccalls;

import org.testng.annotations.Test;
import services.SingleEmployeeResources;

public class TestSingleEmployee extends SingleEmployeeResources {

    @Test
    public void singleEmployeeServicesSuccess(){
        callSingleEmployeeResourcesSuccessStatus();
    }

    @Test
    public void singleEmployeeServicesFailed(){
        callSingleEmployeeResourcesFailStatus();
    }


}
