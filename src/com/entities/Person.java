/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lissette
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByCodigoPersona", query = "SELECT p FROM Person p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "Person.findByNombre", query = "SELECT p FROM Person p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Person.findByApellidos", query = "SELECT p FROM Person p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Person.findBySexo", query = "SELECT p FROM Person p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Person.findByFechaNacimiento", query = "SELECT p FROM Person p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Person.findByCargo", query = "SELECT p FROM Person p WHERE p.cargo = :cargo")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_persona")
    private Integer codigoPersona;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "cargo")
    private String cargo;

    public Person() {
    }

    public Person(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Person(Integer codigoPersona, String nombre, String apellidos, String sexo, Date fechaNacimiento, String cargo) {
        this.codigoPersona = codigoPersona;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.cargo = cargo;
    }

    public Integer getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Integer codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Person[ codigoPersona=" + codigoPersona + " ]";
    }
    
}
