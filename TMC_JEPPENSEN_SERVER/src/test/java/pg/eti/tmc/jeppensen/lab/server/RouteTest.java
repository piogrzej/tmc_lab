/**
 * Test of Route class.
 */

package pg.eti.tmc.jeppensen.lab.server;

import java.util.ArrayList;
import java.util.List;
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
public class RouteTest {
    
    private Route instance;
    private static double x2;
    private static double y2;
    private static double x1;
    private static double y1;
       
    @BeforeClass
    public static void setUpClass() {
        x2 = 54.37128;
        y2 = 18.49172;
        x1 = 54.3669;
        y1 = 18.5104;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Route();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setGate method, of class Route.
     */
    @Test
    public void testSetGate() {
        int gateNum = 1;       
        instance.setGate(gateNum);       
        assertTrue(instance.gate == 0);
        
        gateNum = 2;       
        instance.setGate(gateNum);       
        assertTrue(instance.gate == 1);
        
        for(int i = 3; i<=11; i++) {
            instance.setGate(i);       
            assertTrue(instance.gate == i - 1);
        }
        for(int i = 20; i<=29; i++) {
            instance.setGate(i);       
            assertTrue(instance.gate == i - 9);
        }
    }
    
     /**
     * Test of getRoute method, of class Route.
     */
    @Test
    public void testGetGate() {      
        for(int i = 0; i <= 10; i++) {
            instance.gate = i;       
            assertTrue(instance.getGate() == instance.gate + 1);
        }
        for(int i = 11; i <= 20; i++) {
            instance.gate = i;       
            assertTrue(instance.getGate() == instance.gate + 9);
        }
        instance.gate = 21;
        assertTrue(instance.getGate() == 21);
    }
    
    /**
     * Test of getGatePosition method, of class Route.
     */
    @Test
    public void testGetGatePosition() {
        Position[] expResult = new Position[100]; 
        instance.gatesPostions = expResult;
        Position[] result = instance.getGatePosition();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRoute method, of class Route.
     */
    @Test
    public void testGetRoute() {
        List<Position> expResult = new ArrayList<Position>();
        expResult.add(new Position(54.3456, 18.6095));
        instance.route = expResult;
        List<Position> result = instance.getRoute();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getCurrentPosition method, of class Route.
     */
    @Test
    public void testGetCurrentPositionIfNull() {
        Position expResult = new Position(x1,y1);
        instance.lastPosition = null;
        Position result = instance.getCurrentPosition();
        assertEquals(expResult.getX(), result.getX(), 0.00001);
        assertEquals(expResult.getY(), result.getY(), 0.00001);
    }
    
    /**
     * Test of getCurrentPosition method, of class Route.
     */
    @Test
    public void testGetCurrentPositionIfGreaterThanX2() {
        Position expResult = new Position(x1, y1);
        instance.lastPosition = new Position(x2 + 0.0001, y1);
        Position result = instance.getCurrentPosition();
        assertEquals(expResult.getX(), result.getX(), 0.00001);
        assertEquals(expResult.getY(), result.getY(), 0.00001);
    }
    
    /**
     * Test of getCurrentPosition method, of class Route.
     */
    @Test
    public void testGetCurrentPositionIfLessThanX2() {
        instance.lastPosition = new Position(x2 - 0.0001, y1);
        double x = ((x2 - x1) / instance.PLANE_SPEED) + instance.lastPosition.getX();
        double y = ((y2 - y1) / (x2 - x1)) * x + y1-((y2 - y1) / (x2 - x1)) * x1;
        Position expResult = new Position(x, y);
        Position result = instance.getCurrentPosition();
        assertEquals(expResult.getX(), result.getX(), 0.00001);
        assertEquals(expResult.getY(), result.getY(), 0.00001);
    }
    
}
