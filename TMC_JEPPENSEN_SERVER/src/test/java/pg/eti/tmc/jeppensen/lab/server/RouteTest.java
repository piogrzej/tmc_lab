/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg.eti.tmc.jeppensen.lab.server;

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
    
    Route instance;
       
    @BeforeClass
    public static void setUpClass() {
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

}
