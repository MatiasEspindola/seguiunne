/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Localidades") 
  
public class Localidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_loc")
    private Integer pkIdLoc;
     @JoinColumn(name = "fk_id_prv", referencedColumnName = "pk_id_prv")
    @ManyToOne(optional = false)
    private Provincias fkIdPrv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cpa")
    private short cpa;
    @OneToMany(mappedBy = "fkIdLocalidad")
    @JsonIgnore
    private List<OrganizacionesCoPar> organizacionesCoParList;
    @OneToMany(mappedBy = "fkIdLoc")
    @JsonIgnore
    private List<Universidades> universidadesList;
    @OneToMany(mappedBy = "fkIdLoc")
    @JsonIgnore
    private List<ResponsablesProyecto> responsablesProyectoList;

    public Localidades() {
    }

    public Localidades(Integer pkIdLoc) {
        this.pkIdLoc = pkIdLoc;
    }

    public Localidades(Integer pkIdLoc, String nombre) {
        this.pkIdLoc = pkIdLoc;
        this.nombre = nombre;
    }
    public Integer getPkIdLoc() {
        return pkIdLoc;
    }

    public void setPkIdLoc(Integer pkIdLoc) {
        this.pkIdLoc = pkIdLoc;
    }

    public Provincias getFkIdPrv() {
        return fkIdPrv;
    }

    public void setFkIdPrv(Provincias fkIdPrv) {
        this.fkIdPrv = fkIdPrv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCpa() {
        return cpa;
    }

    public void setCpa(short cpa) {
        this.cpa = cpa;
    }

    @XmlTransient
    public List<OrganizacionesCoPar> getOrganizacionesCoParList() {
        return organizacionesCoParList;
    }

    public void setOrganizacionesCoParList(List<OrganizacionesCoPar> organizacionesCoParList) {
        this.organizacionesCoParList = organizacionesCoParList;
    }

    @XmlTransient
    public List<Universidades> getUniversidadesList() {
        return universidadesList;
    }

    public void setUniversidadesList(List<Universidades> universidadesList) {
        this.universidadesList = universidadesList;
    }

    @XmlTransient
    public List<ResponsablesProyecto> getResponsablesProyectoList() {
        return responsablesProyectoList;
    }

    public void setResponsablesProyectoList(List<ResponsablesProyecto> responsablesProyectoList) {
        this.responsablesProyectoList = responsablesProyectoList;
    }
    
    public static String ucFirst(String str) {
  	  if (str == null || str.isEmpty()) {
  	    return str;            
  	  } else {
  	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(); 
  	  }
  }

  
    @Override
    public String toString() {
        return  this.cpa + " - " + ucFirst(this.nombre);
    }
    
    
     
    public String toCompletName() {
        return  this.fkIdPrv.getNombre() + "-" + this.nombre;
    }

    
}
