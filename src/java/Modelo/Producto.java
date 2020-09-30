/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel Ruiz
 */
public class Producto {
    private String producto;
    private String descripcion;
    private double precio_costo;
    private double precio_venta;
    private int id_marca;
    private int existencia;
    private Conexion cn;
    private int id;

    public Producto(){}

    public Producto(String producto, String descripcion, double precio_costo, double precio_venta, int id_marca, int existencia, int id) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.id_marca = id_marca;
        this.existencia = existencia;
        this.id = id;
    }
    
    
    

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO productos (producto, id_marca, descripcion, precio_costo, precio_venta, existencia) VALUES (?, ?, ?, ?, ?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getId_marca());
            parametro.setString(3,getDescripcion());
            parametro.setDouble(4,getPrecio_costo());
            parametro.setDouble(5,getPrecio_venta());
            parametro.setInt(6,getExistencia());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    public DefaultTableModel leer(){
    
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            
            String query = "SELECT e.id_producto as id,e.producto,e.descripcion,e.precio_costo,e.precio_venta,e.existencia,p.marca,p.id_marcas FROM productos as e,marcas as p where e.id_marca = p.id_marcas;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","producto","descripcion","precio_costo","precio_venta","existencia","marca","id_marcas"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[8];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("producto");
          datos[2] = consulta.getString("descripcion");
          datos[3] = consulta.getString("precio_costo");
          datos[4] = consulta.getString("precio_venta");
          datos[5] = consulta.getString("existencia");
          datos[6] = consulta.getString("marca");
          datos[7] = consulta.getString("id_marcas");
          tabla.addRow(datos);
      
      }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tabla;
    
    }

     public int modificar(){
        
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update productos set producto=?, id_marca=?,descripcion=?,precio_costo=?,precio_venta=?,existencia=? where id_producto =? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getId_marca());
            parametro.setString(3,getDescripcion());
            parametro.setDouble(4,getPrecio_costo());
            parametro.setDouble(5,getPrecio_venta());
            parametro.setInt(6,getExistencia());
            parametro.setInt(7,getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    return retorno;
        
    }
   
    public int eliminar(){
    int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from productos where id_producto =? ;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
           
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
    return retorno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
