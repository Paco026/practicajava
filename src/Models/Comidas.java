/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;


import conexion.ClaseConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comidas {
    private int idComidas;
    private String nombrecomida;
    private String descripcionComida;

    private ClaseConexion claseConexion;
    private java.sql.Connection conexioDB;
    private PreparedStatement pstm;

    private ArrayList<Comidas> listacomidas;

    public int getIdComidas() {
        return idComidas;
    }

    public String getNombrecomida() {
        return nombrecomida;
    }

    public String getDecripcionComida() {
        return descripcionComida;
    }

    public void setIdComidas(int idComidas) {
        this.idComidas = idComidas;
    }

    public void setNombrecomida(String nombrecomida) {
        this.nombrecomida = nombrecomida;
    }

    public void setDecripcionComida(String decripcionComida) {
        this.descripcionComida = decripcionComida;
    }

    public Comidas() {
        this.claseConexion = new ClaseConexion();
        this.listacomidas = new ArrayList<Comidas>();
    }

    public boolean insertarComida(int id, String nombre, String descripcion) {
        conexioDB = claseConexion.iniciarConexion();
        String insertQuery = "INSERT INTO tbl_comida(idcomida, nombreplato, descripcionplato) VALUES (?, ?, ?)";

        try {
            pstm = conexioDB.prepareStatement(insertQuery);
            pstm.setInt(1, id);
            pstm.setString(2, nombre);
            pstm.setString(3, descripcion);

            int rowsAffected = pstm.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conexioDB != null) {
                    conexioDB.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
public boolean eliminarComida(int idComida) {
    conexioDB = claseConexion.iniciarConexion();
    
    String deleteQuery = "DELETE FROM tbl_comida WHERE idcomida = ?";

    try {
        pstm = conexioDB.prepareStatement(deleteQuery);
        pstm.setInt(1, idComida);

        int rowsAffected = pstm.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException ex) {
        Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (pstm != null) {
                pstm.close();
            }
            if (conexioDB != null) {
                conexioDB.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return false;
}
    public ArrayList<Comidas> obtenerComidas() {
        listacomidas.clear();

        conexioDB = claseConexion.iniciarConexion();
        String selectQuery = "SELECT * FROM tbl_comida";

        try {
            pstm = conexioDB.prepareStatement(selectQuery);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Comidas comida = new Comidas();
                comida.setIdComidas(resultSet.getInt("idcomida"));
                comida.setNombrecomida(resultSet.getString("nombreplato"));
                comida.setDecripcionComida(resultSet.getString("descripcionplato"));
                listacomidas.add(comida);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conexioDB != null) {
                    conexioDB.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Comidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listacomidas;
    }
    
}

