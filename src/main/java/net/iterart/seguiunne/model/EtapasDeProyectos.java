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
@Table(name = "EtapasDeProyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtapasDeProyectos.findAll", query = "SELECT e FROM EtapasDeProyectos e")
    , @NamedQuery(name = "EtapasDeProyectos.findByPkIdEtapa", query = "SELECT e FROM EtapasDeProyectos e WHERE e.pkIdEtapa = :pkIdEtapa")
    , @NamedQuery(name = "EtapasDeProyectos.findByEtapa", query = "SELECT e FROM EtapasDeProyectos e WHERE e.etapa = :etapa")})
public class EtapasDeProyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_etapa")
    private Integer pkIdEtapa;
    @Size(max = 245)
    @Column(name = "etapa")
    private String etapa;
    @OneToMany(mappedBy = "fkIdEtapa")
    private List<ProyectosActivEstud> proyectosActivEstudList;
    @OneToMany(mappedBy = "fkIdEtapa")
    private List<ProyectosActivOrg> proyectosActivOrgList;

    public EtapasDeProyectos() {
    }

    public EtapasDeProyectos(Integer pkIdEtapa) {
        this.pkIdEtapa = pkIdEtapa;
    }

    public Integer getPkIdEtapa() {
        return pkIdEtapa;
    }

    public void setPkIdEtapa(Integer pkIdEtapa) {
        this.pkIdEtapa = pkIdEtapa;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    @XmlTransient
    public List<ProyectosActivEstud> getProyectosActivEstudList() {
        return proyectosActivEstudList;
    }

    public void setProyectosActivEstudList(List<ProyectosActivEstud> proyectosActivEstudList) {
        this.proyectosActivEstudList = proyectosActivEstudList;
    }

    @XmlTransient
    public List<ProyectosActivOrg> getProyectosActivOrgList() {
        return proyectosActivOrgList;
    }

    public void setProyectosActivOrgList(List<ProyectosActivOrg> proyectosActivOrgList) {
        this.proyectosActivOrgList = proyectosActivOrgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdEtapa != null ? pkIdEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtapasDeProyectos)) {
            return false;
        }
        EtapasDeProyectos other = (EtapasDeProyectos) object;
        if ((this.pkIdEtapa == null && other.pkIdEtapa != null) || (this.pkIdEtapa != null && !this.pkIdEtapa.equals(other.pkIdEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EtapasDeProyectos[ pkIdEtapa=" + pkIdEtapa + " ]";
    }
    
}
