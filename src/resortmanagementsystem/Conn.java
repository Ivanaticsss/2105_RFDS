package resortmanagementsystem;

import java.sql.*;

public class Conn implements AutoCloseable {
    
    Connection c;
    Statement s;
    
    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            c = DriverManager.getConnection("jdbc:mysql:///newschema", "root", "mysql");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
