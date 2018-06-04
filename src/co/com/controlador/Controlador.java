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
    private static final String COMBO_NIT_VALUES = "Numero de identificación";
    private static final String COMBO_CODE_VALUES = "Código asignatura";

    public List<Parcial> recuperarInformacion(String clientMessage) {
        String[] parts = clientMessage.split("-");
        String inputType = parts[0];
        String input = parts[1];
        List<Parcial> response = new ArrayList();
        try {
            if (COMBO_CODE_VALUES.equals(inputType)) {
                response = recuperarPorIdTarea(input);
            } else {
                response = recuperarPorIdEstudiante(input);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        if (0 == response.size()) {
            Parcial parcial = generateObjectError();
            response.add(parcial);
        }

        return response;
    }

    public List<Parcial> recuperarPorIdEstudiante(String id_estudiante) throws SQLException, ClassNotFoundException {
        System.out.println("recuperarPorIdEstudiante");
        Parcial parcial = null;
        Connection conexion = Conexion.obtener();
        List lParciales = new ArrayList();
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM  " + this.tabla + " WHERE ESTUDIENTEPARCIAL = ?");
            consulta.setString(1, id_estudiante);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
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
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return lParciales;
    }

    public List<Parcial> recuperarPorIdTarea(String id_parcial) throws SQLException, ClassNotFoundException {
        System.out.println("recuperarPorIdTarea");
        Parcial parcial = null;
        Connection conexion = Conexion.obtener();
        List lParciales = new ArrayList();
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM " + this.tabla + " WHERE ASIGNATURAPARCIAL = ?");
            consulta.setString(1, id_parcial);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int count = 0;
                System.out.println("contador " + count);
                count++;
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
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return lParciales;
    }

    public Parcial generateObjectError() {
        Bloque bloque = new Bloque();
        Estudiante estudiate = new Estudiante();
        Asignatura asignatura = new Asignatura();
        Salon salon = new Salon();

        bloque.setCodigo("-1");
        salon.setCodigo("-1");
        estudiate.setId("-1");
        asignatura.setId("-1");

        Parcial parcial = new Parcial("-1", asignatura, estudiate, bloque, salon, "", "");
        return parcial;
    }

}
