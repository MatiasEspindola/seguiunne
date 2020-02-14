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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Facultades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultades.findAll", query = "SELECT f FROM Facultades f")
    , @NamedQuery(name = "Facultades.findByPkIdFac", query = "SELECT f FROM Facultades f WHERE f.pkIdFac = :pkIdFac")
    , @NamedQuery(name = "Facultades.findByFacultad", query = "SELECT f FROM Facultades f WHERE f.facultad = :facultad")
    , @NamedQuery(name = "Facultades.findByHab", query = "SELECT f FROM Facultades f WHERE f.hab = :hab")})
public class Facultades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_fac")
    private Integer pkIdFac;
    @Size(max = 125)
    @Column(name = "facultad")
    private String facultad;
    @Column(name = "hab")
    private Boolean hab;
    @JoinColumn(name = "fk_id_uni", referencedColumnName = "pk_id_uni")
    @ManyToOne
    private Universidades fkIdUni;
    @OneToMany(mappedBy = "fkIdFac")
    private List<AdminsFacultades> adminsFacultadesList;
    @OneToMany(mappedBy = "fkIdFac")
    private List<Evaluadores> evaluadoresList;
    @OneToMany(mappedBy = "fkIdFac")
    private List<Carreras> carrerasList;
    @OneToMany(mappedBy = "fkIdFac")
    private List<DatosGralProyecto> datosGralProyectoList;

    public Facultades() {
    }

    public Facultades(Integer pkIdFac) {
        this.pkIdFac = pkIdFac;
    }

    public Integer getPkIdFac() {
        return pkIdFac;
    }

    public void setPkIdFac(Integer pkIdFac) {
        this.pkIdFac = pkIdFac;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Boolean getHab() {
        return hab;
    }

    public void setHab(Boolean hab) {
        this.hab = hab;
    }

    public Universidades getFkIdUni() {
        return fkIdUni;
    }

    public void setFkIdUni(Universidades fkIdUni) {
        this.fkIdUni = fkIdUni;
    }

    @XmlTransient
    public List<AdminsFacultades> getAdminsFacultadesList() {
        return adminsFacultadesList;
    }

    public void setAdminsFacultadesList(List<AdminsFacultades> adminsFacultadesList) {
        this.adminsFacultadesList = adminsFacultadesList;
    }

    @XmlTransient
    public List<Evaluadores> getEvaluadoresList() {
        return evaluadoresList;
    }

    public void setEvaluadoresList(List<Evaluadores> evaluadoresList) {
        this.evaluadoresList = evaluadoresList;
    }

    @XmlTransient
    public List<Carreras> getCarrerasList() {
        return carrerasList;
    }

    public void setCarrerasList(List<Carreras> carrerasList) {
        this.carrerasList = carrerasList;
    }

    @XmlTransient
    public List<DatosGralProyecto> getDatosGralProyectoList() {
        return datosGralProyectoList;
    }

    public void setDatosGralProyectoList(List<DatosGralProyecto> datosGralProyectoList) {
        this.datosGralProyectoList = datosGralProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdFac != null ? pkIdFac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultades)) {
            return false;
        }
        Facultades other = (Facultades) object;
        if ((this.pkIdFac == null && other.pkIdFac != null) || (this.pkIdFac != null && !this.pkIdFac.equals(other.pkIdFac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return facultad;
    }
    
}
