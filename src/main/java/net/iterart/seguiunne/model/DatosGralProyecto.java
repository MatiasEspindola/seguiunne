/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "DatosGralProyecto")
public class DatosGralProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_dat")
    private Integer pkIdDat;
    @Size(max = 250)
    @Column(name = "descri")
    private String descri;
    @Size(max = 50)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fk_id_eval")
    private Integer fkIdEval;
    @Column(name = "fk_id_eval2")
    private Integer fkIdEval2;
    @Column(name = "fk_id_eval3")
    private Integer fkIdEval3;
    @Column(name = "fechaFinan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinan;
    @Size(max = 4)
    @Column(name = "anio")
    private String anio;
    
    @OneToMany(mappedBy = "fkIdDat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Proyectos> proyectosList;
    
    @OneToMany(mappedBy = "fkIdDat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Evaluaciones> evaluacionesList;
    
    @OneToMany(mappedBy = "fkiddatGral", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<ResponsablesProyecto> responsablesProyectoList;
    
    @JoinColumn(name = "fk_id_fac", referencedColumnName = "pk_id_fac")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facultades fkIdFac;
    
    @JoinColumn(name = "fk_id_eje_linea", referencedColumnName = "pk_id_eje_linea")
    @ManyToOne(fetch = FetchType.LAZY)
    private EjesLineas fkIdEjeLinea;
    
    @JoinColumn(name = "fk_id_usu", referencedColumnName = "pk_id_usu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios fkIdUsu;
    
    @JoinColumn(name = "fk_id_eje", referencedColumnName = "pk_id_eje")
    @ManyToOne(fetch = FetchType.LAZY)
    private EjeTematicos fkIdEje;
    
    @OneToMany(mappedBy = "fkIdDat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asignaciones> asignacionesList;

    public DatosGralProyecto() {
    }

    public DatosGralProyecto(Integer pkIdDat) {
        this.pkIdDat = pkIdDat;
    }

    public Integer getPkIdDat() {
        return pkIdDat;
    }

    public void setPkIdDat(Integer pkIdDat) {
        this.pkIdDat = pkIdDat;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getFkIdEval() {
        return fkIdEval;
    }

    public void setFkIdEval(Integer fkIdEval) {
        this.fkIdEval = fkIdEval;
    }

    public Integer getFkIdEval2() {
        return fkIdEval2;
    }

    public void setFkIdEval2(Integer fkIdEval2) {
        this.fkIdEval2 = fkIdEval2;
    }

    public Integer getFkIdEval3() {
        return fkIdEval3;
    }

    public void setFkIdEval3(Integer fkIdEval3) {
        this.fkIdEval3 = fkIdEval3;
    }

    public Date getFechaFinan() {
        return fechaFinan;
    }

    public void setFechaFinan(Date fechaFinan) {
        this.fechaFinan = fechaFinan;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @XmlTransient
    public List<Proyectos> getProyectosList() {
        return proyectosList;
    }

    public void setProyectosList(List<Proyectos> proyectosList) {
        this.proyectosList = proyectosList;
    }

    @XmlTransient
    public List<Evaluaciones> getEvaluacionesList() {
        return evaluacionesList;
    }

    public void setEvaluacionesList(List<Evaluaciones> evaluacionesList) {
        this.evaluacionesList = evaluacionesList;
    }

    @XmlTransient
    public List<ResponsablesProyecto> getResponsablesProyectoList() {
        return responsablesProyectoList;
    }

    public void setResponsablesProyectoList(List<ResponsablesProyecto> responsablesProyectoList) {
        this.responsablesProyectoList = responsablesProyectoList;
    }

    public Facultades getFkIdFac() {
        return fkIdFac;
    }

    public void setFkIdFac(Facultades fkIdFac) {
        this.fkIdFac = fkIdFac;
    }

    public EjesLineas getFkIdEjeLinea() {
        return fkIdEjeLinea;
    }

    public void setFkIdEjeLinea(EjesLineas fkIdEjeLinea) {
        this.fkIdEjeLinea = fkIdEjeLinea;
    }

    public Usuarios getFkIdUsu() {
        return fkIdUsu;
    }

    public void setFkIdUsu(Usuarios fkIdUsu) {
        this.fkIdUsu = fkIdUsu;
    }

    public EjeTematicos getFkIdEje() {
        return fkIdEje;
    }

    public void setFkIdEje(EjeTematicos fkIdEje) {
        this.fkIdEje = fkIdEje;
    }

    @XmlTransient
    public List<Asignaciones> getAsignacionesList() {
        return asignacionesList;
    }

    public void setAsignacionesList(List<Asignaciones> asignacionesList) {
        this.asignacionesList = asignacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdDat != null ? pkIdDat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosGralProyecto)) {
            return false;
        }
        DatosGralProyecto other = (DatosGralProyecto) object;
        if ((this.pkIdDat == null && other.pkIdDat != null) || (this.pkIdDat != null && !this.pkIdDat.equals(other.pkIdDat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pkIdDat + " - " + descri;
    }
    
    public String obtenerDirector(){
        return responsablesProyectoList.get(0).getApellido() + ", " + responsablesProyectoList.get(0).getNombre();
    }
    
     public String obtenerFacultad(){
        return fkIdFac.getFacultad();
    }
    
    public String obtenerEmail(){
        return responsablesProyectoList.get(0).getEmail();
    }
    
}
