/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Javier
 */
public class ClaseConexion {
    private String usuario;
    private String pass;
    private String url;
    private Connection conexion;
    public ClaseConexion() {
        this.usuario = "postgres";
        this.pass = "112233";
        this.url = "jdbc:postgresql://localhost:5432/nuevadb";
    }
    public Connection iniciarConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, pass);
            boolean estado = conexion.isValid(50000);
            System.out.println(estado ? "OK":"ERROR");
            return conexion;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClaseConexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClaseConexion.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }
    
    
}
