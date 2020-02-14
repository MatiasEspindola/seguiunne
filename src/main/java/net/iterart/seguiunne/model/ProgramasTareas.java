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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "programas_tareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramasTareas.findAll", query = "SELECT p FROM ProgramasTareas p")
    , @NamedQuery(name = "ProgramasTareas.findByPkIdProtar", query = "SELECT p FROM ProgramasTareas p WHERE p.pkIdProtar = :pkIdProtar")
    , @NamedQuery(name = "ProgramasTareas.findByPrio", query = "SELECT p FROM ProgramasTareas p WHERE p.prio = :prio")
    , @NamedQuery(name = "ProgramasTareas.findByTitu", query = "SELECT p FROM ProgramasTareas p WHERE p.titu = :titu")
    , @NamedQuery(name = "ProgramasTareas.findByFec", query = "SELECT p FROM ProgramasTareas p WHERE p.fec = :fec")
    , @NamedQuery(name = "ProgramasTareas.findByLugar", query = "SELECT p FROM ProgramasTareas p WHERE p.lugar = :lugar")})
public class ProgramasTareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_protar")
    private Integer pkIdProtar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "prio")
    private String prio;
    @Size(max = 60)
    @Column(name = "titu")
    private String titu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fec;
    @Size(max = 125)
    @Column(name = "lugar")
    private String lugar;
    @JoinColumn(name = "fk_id_act", referencedColumnName = "pk_id_act")
    @ManyToOne
    private Actividades fkIdAct;

    public ProgramasTareas() {
    }

    public ProgramasTareas(Integer pkIdProtar) {
        this.pkIdProtar = pkIdProtar;
    }

    public ProgramasTareas(Integer pkIdProtar, String prio, Date fec) {
        this.pkIdProtar = pkIdProtar;
        this.prio = prio;
        this.fec = fec;
    }

    public Integer getPkIdProtar() {
        return pkIdProtar;
    }

    public void setPkIdProtar(Integer pkIdProtar) {
        this.pkIdProtar = pkIdProtar;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getTitu() {
        return titu;
    }

    public void setTitu(String titu) {
        this.titu = titu;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    
    public Actividades getFkIdAct() {
        return fkIdAct;
    }

    public void setFkIdAct(Actividades fkIdAct) {
        this.fkIdAct = fkIdAct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdProtar != null ? pkIdProtar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramasTareas)) {
            return false;
        }
        ProgramasTareas other = (ProgramasTareas) object;
        if ((this.pkIdProtar == null && other.pkIdProtar != null) || (this.pkIdProtar != null && !this.pkIdProtar.equals(other.pkIdProtar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProgramasTareas[ pkIdProtar=" + pkIdProtar + " ]";
    }
    
}
