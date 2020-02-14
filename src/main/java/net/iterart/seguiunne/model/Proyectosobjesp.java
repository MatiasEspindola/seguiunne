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
import javax.persistence.Lob;
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
@Table(name = "Proyectos_obj_esp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectosobjesp.findAll", query = "SELECT p FROM Proyectosobjesp p")
    , @NamedQuery(name = "Proyectosobjesp.findByPkIdObjEsp", query = "SELECT p FROM Proyectosobjesp p WHERE p.pkIdObjEsp = :pkIdObjEsp")
    , @NamedQuery(name = "Proyectosobjesp.findByNum", query = "SELECT p FROM Proyectosobjesp p WHERE p.num = :num")})
public class Proyectosobjesp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_obj_esp")
    private Integer pkIdObjEsp;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objetivo")
    private String objetivo;
    @Column(name = "num")
    private Integer num;
    @OneToMany(mappedBy = "fkProyectoObjEsp")
    private List<Metas> metasList;
    @JoinColumn(name = "fk_id_proyecto", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdProyecto;

    public Proyectosobjesp() {
    }

    public Proyectosobjesp(Integer pkIdObjEsp) {
        this.pkIdObjEsp = pkIdObjEsp;
    }

    public Integer getPkIdObjEsp() {
        return pkIdObjEsp;
    }

    public void setPkIdObjEsp(Integer pkIdObjEsp) {
        this.pkIdObjEsp = pkIdObjEsp;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @XmlTransient
    public List<Metas> getMetasList() {
        return metasList;
    }

    public void setMetasList(List<Metas> metasList) {
        this.metasList = metasList;
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
        hash += (pkIdObjEsp != null ? pkIdObjEsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectosobjesp)) {
            return false;
        }
        Proyectosobjesp other = (Proyectosobjesp) object;
        if ((this.pkIdObjEsp == null && other.pkIdObjEsp != null) || (this.pkIdObjEsp != null && !this.pkIdObjEsp.equals(other.pkIdObjEsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Proyectosobjesp[ pkIdObjEsp=" + pkIdObjEsp + " ]";
    }
    
}
