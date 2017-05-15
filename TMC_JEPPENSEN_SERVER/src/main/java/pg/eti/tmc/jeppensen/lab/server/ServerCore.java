package pg.eti.tmc.jeppensen.lab.server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static spark.Spark.*;

/**
 *
 * @author piotr
 */
public class ServerCore {
    
    private static Gson gson;
    
    private static class GsonExample
    {
        private int ID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
        
    }
    
    private static String jsonTest()
    {
        GsonExample example = new GsonExample();
        example.setID(15);
        return gson.toJson(example);
    } 
    
    public static void main(String[] args) {
        //GSON INIT
        gson = new GsonBuilder().create();
        
        //JVM CONFIG, IN SOME OS CONFIGURATIONS SPARK WON'T START
        System.setProperty("java.net.preferIPv4Stack", "true");
        
        //THIS MUST BE CHANGED IN LABORATORY TO PORT 80 DUE TO ADMINISTRATOR RIGHTS ON WINDOWS BASED SYSTEMS,
        //SOME UNIX MACHINES WILL RESERVE ALL PORTS BELOW 1024 FOR ROOT USERS
        port(8080);
        
        //EXAMPLES--------------------------------------------------------------
        
        //SIMPLE GET: http://localhost:8080/hello
        get("/hello", (req, res) -> "Hello World");
        //GET WITH PARAMS: http://localhost:8080/hello/piotr
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });
        //EXAMPLE OF RETURNING JSON OBJECT
        get("/json", (request, response) -> jsonTest());
        
        //POST/PUT/DELETE ARE ANALOGICAL
        
        //EXAMPLES--------------------------------------------------------------
    }

}
