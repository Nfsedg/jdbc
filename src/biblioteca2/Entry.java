/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca2;
import java.sql.*;

/**
 *
 * @author edgar
 */
public class Entry {
    public static void main(String arg[]) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("Jdbc:Odbc:EDGARCLUPP");
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Libros");
            
            while(rs.next()) {
                System.out.println(rs.getString("NombreLibro"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
