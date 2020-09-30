/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Daniel Ruiz
 */
public class Marca {
    private String marca;
    private int id_marca;
    private Conexion cn;

    public Marca(){}
    
    public Marca(String marca, int id_marca) {
        this.marca = marca;
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }
    public HashMap drop_marca(){
        HashMap<String,String> drop= new HashMap();
        
        try{
            cn = new Conexion();
            String query = " SELECT id_marcas as id,marca FROM marcas; ";
            cn.abrir_conexion();
            
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
}
