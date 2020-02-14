/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "proyectos_activ_estud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectosActivEstud.findAll", query = "SELECT p FROM ProyectosActivEstud p")
    , @NamedQuery(name = "ProyectosActivEstud.findByPkIdActiv", query = "SELECT p FROM ProyectosActivEstud p WHERE p.pkIdActiv = :pkIdActiv")})
public class ProyectosActivEstud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_activ")
    private Integer pkIdActiv;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "actividades")
    private String actividades;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "fk_id_etapa", referencedColumnName = "pk_id_etapa")
    @ManyToOne
    private EtapasDeProyectos fkIdEtapa;
    @JoinColumn(name = "fk_id_proyecto", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdProyecto;

    public ProyectosActivEstud() {
    }

    public ProyectosActivEstud(Integer pkIdActiv) {
        this.pkIdActiv = pkIdActiv;
    }

    public Integer getPkIdActiv() {
        return pkIdActiv;
    }

    public void setPkIdActiv(Integer pkIdActiv) {
        this.pkIdActiv = pkIdActiv;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EtapasDeProyectos getFkIdEtapa() {
        return fkIdEtapa;
    }

    public void setFkIdEtapa(EtapasDeProyectos fkIdEtapa) {
        this.fkIdEtapa = fkIdEtapa;
    }

    public Proyectos getFkIdProyecto() {
        return fkIdProyecto;
    }

    public void setFkIdProyecto(Proyectos fkIdProyecto) {
        this.fkIdProyecto = fkIdProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdActiv != null ? pkIdActiv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectosActivEstud)) {
            return false;
        }
        ProyectosActivEstud other = (ProyectosActivEstud) object;
        if ((this.pkIdActiv == null && other.pkIdActiv != null) || (this.pkIdActiv != null && !this.pkIdActiv.equals(other.pkIdActiv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProyectosActivEstud[ pkIdActiv=" + pkIdActiv + " ]";
    }
    
}
