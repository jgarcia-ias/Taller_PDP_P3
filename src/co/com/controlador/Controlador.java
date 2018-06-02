/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import co.com.entidad.*;
import co.com.util.Conexion;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author chori
 */

public class Controlador {
   private final String tabla = "PARCIAL";
 
   public List<Parcial> recuperarPorIdEstudiante(String id_estudiante) throws SQLException, ClassNotFoundException {
      System.out.println("recuperarPorIdEstudiante");
      Parcial parcial = null;
      Connection conexion = Conexion.obtener();
      List lParciales = new ArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM  " + this.tabla + " WHERE ESTUDIENTEPARCIAL = ?" );
         consulta.setString(1, id_estudiante);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Asignatura asignatura = new Asignatura();
             Bloque bloque = new Bloque();
             Estudiante estudiante = new Estudiante();
             Salon salon = new Salon();
             
             asignatura.setId(resultado.getString("ASIGNATURAPARCIAL"));
             bloque.setCodigo(resultado.getString("BLOQUEPARCIAL"));
             salon.setCodigo(resultado.getString("SALONPARCIAL"));
             estudiante.setId(resultado.getString("ESTUDIENTEPARCIAL"));
             
            parcial = new Parcial(resultado.getString("IDPARCIAL"), asignatura, estudiante, bloque, salon, resultado.getString("FECHA"), resultado.getString("NOTA"));
            lParciales.add(parcial);
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return lParciales;
   }
   
    public Parcial recuperarPorIdTarea(String id_parcial) throws SQLException, ClassNotFoundException {
      System.out.println("recuperarPorIdTarea");
      Parcial parcial = null;
      Connection conexion = Conexion.obtener();
      List lParciales = new ArrayList();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla + " WHERE IDPARCIAL = ?" );
         consulta.setString(1, id_parcial);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
             Asignatura asignatura = new Asignatura();
             Bloque bloque = new Bloque();
             Estudiante estudiante = new Estudiante();
             Salon salon = new Salon();
             
             asignatura.setId(resultado.getString("ASIGNATURAPARCIAL"));
             bloque.setCodigo(resultado.getString("BLOQUEPARCIAL"));
             salon.setCodigo(resultado.getString("SALONPARCIAL"));
             estudiante.setId(resultado.getString("ESTUDIENTEPARCIAL"));
             
            parcial = new Parcial(resultado.getString("IDPARCIAL"), asignatura, estudiante, bloque, salon,  resultado.getString("FECHA"), resultado.getString("NOTA"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return parcial;
   }

}
