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
import org.hibernate.annotations.Where;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Tareas")
@Where(clause = "hab=1")
public class Tareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_tar")
    private Integer pkIdTar;
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
    @Column(name = "fec_ini")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecIni;

    @Column(name = "fec_fin")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecFin;

    @Size(max = 125)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 110)
    @Column(name = "descri")
    private String descri;
    @Column(name = "motivo_rep")
    @Size(max = 100)
    private String motivo;
    @Column(name = "atrasada")
    private Boolean atrasada;
    @Column(name = "fin")
    private Boolean fin;
    @Column(name = "hab")
    private Boolean hab;
    @Column(name = "reprogramado")
    private Boolean rep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTar")
    private List<InformesTareas> informesTareasList;
    @JoinColumn(name = "fk_id_act", referencedColumnName = "pk_id_act")
    @ManyToOne(fetch = FetchType.LAZY)
    private Actividades fkIdAct;
    @JoinColumn(name = "fk_id_loc", referencedColumnName = "pk_id_loc")
    @ManyToOne(fetch = FetchType.LAZY)
    private Localidades fkIdLoc;

    public Localidades getFkIdLoc() {
        return fkIdLoc;
    }

    public void setFkIdLoc(Localidades fkIdLoc) {
        this.fkIdLoc = fkIdLoc;
    }

    public Tareas() {
    }

    public Tareas(Integer pkIdTar) {
        this.pkIdTar = pkIdTar;
    }

    public Tareas(Integer pkIdTar, String prio, Date fec) {
        this.pkIdTar = pkIdTar;
        this.prio = prio;
    }

    public Integer getPkIdTar() {
        return pkIdTar;
    }

    public void setPkIdTar(Integer pkIdTar) {
        this.pkIdTar = pkIdTar;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public String getTitu() {
        return titu;
    }

    public void setTitu(String titu) {
        this.titu = titu;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Boolean getAtrasada() {
        return atrasada;
    }

    public void setAtrasada(Boolean atrasada) {
        this.atrasada = atrasada;
    }

    public Boolean getFin() {
        return fin;
    }

    public void setFin(Boolean fin) {
        this.fin = fin;
    }

    public Boolean getHab() {
        return hab;
    }

    public void setHab(Boolean hab) {
        this.hab = hab;
    }

    public Boolean getRep() {
        return rep;
    }

    public void setRep(Boolean rep) {
        this.rep = rep;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @XmlTransient
    public List<InformesTareas> getInformesTareasList() {
        return informesTareasList;
    }

    public void setInformesTareasList(List<InformesTareas> informesTareasList) {
        this.informesTareasList = informesTareasList;
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
        hash += (pkIdTar != null ? pkIdTar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.pkIdTar == null && other.pkIdTar != null) || (this.pkIdTar != null && !this.pkIdTar.equals(other.pkIdTar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Tareas[ pkIdTar=" + pkIdTar + " ]";
    }

}
