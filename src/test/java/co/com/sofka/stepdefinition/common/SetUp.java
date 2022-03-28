package co.com.sofka.stepdefinition.common;

import org.apache.log4j.PropertyConfigurator;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;

public class SetUp {
    protected static final String BASE_URI = "https://reqres.in";
    protected static final String SINGLE_RESOURCE = "/api/unknown/2";
    protected static final String CREATE_RESOURCE = "/api/users";
    protected static final String SINGLE_RESOURCE_NOT_FOUND = "/apii/users404";


    protected void generalSetUp(){
        setUpLog4j2();
    }

    private void setUpLog4j2(){
        PropertyConfigurator.configure(USER_DIR.getValue()  + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }


}
