/**
 * Integration Test of ServerCore class.
 */
package pg.eti.tmc.jeppensen.lab.server;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Przemek
 */
public class ServerCoreIT {
    
    private static ServerCore server;
    
    @BeforeClass
    public static void setUpClass() {
        server = new ServerCore();
        server.initServer();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCurrentPostion method, of class ServerCore.
     */
    @Test
    public void testGetCurrentPostion() {      
        expect().
        statusCode(200).
        body(
            "x", equalTo(54.3669),
            "y", equalTo(18.5104)).        
        when().
        get("http://localhost:8080/getCurrentPostion");
    }

    /**
     * Test of setGate method, of class ServerCore.
     */
    @Test
    public void testSetGate() {
        String response = given().
        queryParam("id", "1").
        expect().
        statusCode(200).
        when().
        post("http://localhost:8080/setGate").getBody().asString();
        
        assertEquals(response, "\"OK\"");
    }

    /**
     * Test of getRouteToGate method, of class ServerCore.
     */
    @Test
    public void testGetRouteToGate() {    
        server.route.setGate(1);
        given().
        expect().
        statusCode(200).       
        when().
        get("http://localhost:8080/getRouteToGate");      
    }

    /**
     * Test of getGate method, of class ServerCore.
     */
    @Test
    public void testGetGate() {     
        server.route.gate = 1;
        String response = given().
        expect().
        contentType("application/json").
        statusCode(200).       
        when().
        get("http://localhost:8080/getGate").getBody().asString();
        
        assertEquals(response, "2");
    }
 
}
