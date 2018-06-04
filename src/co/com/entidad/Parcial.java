/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.entidad;

/**
 *
 * @author chori
 */
public class Parcial {
    String id;
    Asignatura asignatura;
    Estudiante estudiante;
    Bloque bloque;
    Salon salon;
    String fecha;
    String nota;

    public Parcial(String id, Asignatura asignatura, Estudiante estudiante, Bloque bloque, Salon salon, String fecha, String nota) {
        this.id = id;
        this.asignatura = asignatura;
        this.estudiante = estudiante;
        this.bloque = bloque;
        this.salon = salon;
        this.fecha = fecha;
        this.nota = nota;
    }

    public Parcial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
    
    
}
