
package resortmanagementsystem;
import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;
    Conn(){
        try{
           Class.forName("com.mysql.cj.jdbc.Driver"); 
           c = DriverManager.getConnection("jdbc:mysql:///resortmanagementsystem", "root", "coding_database123");
           s = c.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        } 
    }
    
    
}
