/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

        


/**
 *
 * @author Daniel Ruiz
 */
public class Conexion {
    public Connection conexionBD;
    public final String bd="db_parcial";
    public final String urlConexion= String.format("jdbc:mysql://localhost:3306/%s ", bd +  "?useTimezone=true&serverTimezone=UTC") ;
    public final String usuario = "parcial"; 
    public final String contra="Parcial1";
    public final String jdbc="com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexionBD = DriverManager.getConnection(urlConexion,usuario,contra);
        /*JOptionPane.showMessageDialog(null, "Conexion etsitosa :3", "Exito",JOptionPane.INFORMATION_MESSAGE);*/
    }catch(ClassNotFoundException | SQLException ex){
    System.out.println("Error  "+ ex.getMessage());
    }
    }
   
    public void cerrar_conexion(){
    try{
        conexionBD.close();
    }catch(SQLException ex){
        System.out.println("Error  "+ ex.getMessage());
    }
        
    
    }
    
}
