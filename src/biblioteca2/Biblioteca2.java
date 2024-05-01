/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author edgar
 */
public class Biblioteca2 {
    Connection con;
    Statement st;
    ResultSet rs;
    private Object connection;
    
     public void AbrirConexxion()
    {
        try
        {
            String userName = "root";
            String password = "edgar";
            String url = "jdbc:mysql://localhost/DatosCoches";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexion a la BD");
        }
        catch (Exception e)
        {
            System.out.println(e);  
        }
    }
      public void CerrarConexion()
    {
        try
        {
            con.close();
            System.out.println("Conexion Cerrada");
        }
        catch (SQLException e){
            System.out.println("Error al cerrar conexion");
        }
    }
          
       public void MostrarLibrosPrestados(Connection conn) 
       {
    try {
        String sql = "SELECT * FROM Prestamos";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int NumPed = rs.getInt("NumeroPedido");
            int libroId = rs.getInt("libro_Codigo");
            int usuarioId = rs.getInt("CodigoUsuario"); 
                Date fechaSalida = rs.getDate("FechaSalida"); 

                // Mostrar los valores obtenidos de la consulta
                System.out.println("Num Ped: " + NumPed + ", Libro ID: " + libroId + ", Usuario ID: " + usuarioId + ", Fecha Salida: " + fechaSalida);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    public void MostrarLibroPrestadosporDia(Connection conn, Date dia) {
        try {
            String sql = "SELECT * FROM Prestamos WHERE FechaSalida = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, dia);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int NumPed = rs.getInt("NumeroPedido");
                int libroId = rs.getInt("FKCodigoLibro");
                int usuarioId = rs.getInt("FKCodigoUsuario");
                Date fechaSalida = rs.getDate("FechaSalida");

                // Mostrar los valores obtenidos de la consulta
                System.out.println("Num Ped: " + NumPed + ", Libro ID: " + libroId + ", Usuario ID: " + usuarioId + ", Fecha Salida: " + fechaSalida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        public void InsertarUsuario(Connection conn, String nombre, String apellidos, String dni, String domicilio, String poblacion, String provincia, Date ){
        {
        try {
            String sql = "INSERT INTO Usuarios (NombreUsuario, Apellidos, DNI, Domicilio, Poblacion, Provincia, FechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, dni);
            stmt.setString(4, domicilio);
            stmt.setString(5, poblacion);
            stmt.setString(6, provincia);
            stmt.setDate(7, new java.sql.Date(fechaNacimiento.getTime()));
            
            // Ejecutar la consulta de inserción
            int filasInsertadas = stmt.executeUpdate();
            
            if (filasInsertadas > 0) {
                System.out.println("Usuario insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        public void InsertarLibro(Connection conn, int codigoLibro, String nombreLibro, String editorial, String autor, String genero, String paisAutor, int numeroDePaginas, Date anioDeEdicion, double precio) {
        try {
            String sql = "INSERT INTO Libros (CodigoLibro, NombreLibro, Editorial, Autor, Genero, PaisAutor, NumeroDePaginas, AnioDeEdicion, Precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigoLibro);
            stmt.setString(2, nombreLibro);
            stmt.setString(3, editorial);
            stmt.setString(4, autor);
            stmt.setString(5, genero);
            stmt.setString(6, paisAutor);
            stmt.setInt(7, numeroDePaginas);
            stmt.setDate(8, new java.sql.Date(anioDeEdicion.getTime()));
            stmt.setDouble(9, precio);
            
            // Ejecutar la consulta de inserción
            int filasInsertadas = stmt.executeUpdate();
            
            if (filasInsertadas > 0) {
                System.out.println("Libro insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el libro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        public void ActualizarUsuario(Connection conn, int codigoUsuario, String nombre, String apellidos, String dni, String domicilio, String poblacion, String provincia) {
        try {
            String sql = "UPDATE Usuarios SET NombreUsuario=?, Apellidos=?, DNI=?, Domicilio=?, Poblacion=?, Provincia=? WHERE CodigoUsuario=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, dni);
            stmt.setString(4, domicilio);
            stmt.setString(5, poblacion);
            stmt.setString(6, provincia);
            stmt.setInt(7, codigoUsuario);
            
            int filasActualizadas = stmt.executeUpdate();
            
            if (filasActualizadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        public void ActualizarLibro(Connection conn, int codigoLibro, String nombreLibro, String editorial, String autor, String genero, String paisAutor, int numeroDePaginas, int anioDeEdicion, double precio) {
        try {
            String sql = "UPDATE Libros SET NombreLibro=?, Editorial=?, Autor=?, Genero=?, PaisAutor=?, NumeroDePaginas=?, AnioDeEdicion=?, Precio=? WHERE CodigoLibro=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreLibro);
            stmt.setString(2, editorial);
            stmt.setString(3, autor);
            stmt.setString(4, genero);
            stmt.setString(5, paisAutor);
            stmt.setInt(6, numeroDePaginas);
            stmt.setInt(7, anioDeEdicion);
            stmt.setDouble(8, precio);
            stmt.setInt(9, codigoLibro);
            
            int filasActualizadas = stmt.executeUpdate();
            
            if (filasActualizadas > 0) {
                System.out.println("Libro actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el libro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }        
}

      

