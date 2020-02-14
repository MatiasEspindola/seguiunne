/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Vistas_paginas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistaspaginas.findAll", query = "SELECT v FROM Vistaspaginas v")
    , @NamedQuery(name = "Vistaspaginas.findByPkIdVista", query = "SELECT v FROM Vistaspaginas v WHERE v.pkIdVista = :pkIdVista")
    , @NamedQuery(name = "Vistaspaginas.findByPagina", query = "SELECT v FROM Vistaspaginas v WHERE v.pagina = :pagina")
    , @NamedQuery(name = "Vistaspaginas.findByFec", query = "SELECT v FROM Vistaspaginas v WHERE v.fec = :fec")
    , @NamedQuery(name = "Vistaspaginas.findByDescri", query = "SELECT v FROM Vistaspaginas v WHERE v.descri = :descri")})
public class Vistaspaginas implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_vista")
    private Integer pkIdVista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "pagina")
    private String pagina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fec;
    @Size(max = 110)
    @Column(name = "descri")
    private String descri;
    @JoinColumn(name = "fk_id_usu", referencedColumnName = "pk_id_usu")
    @ManyToOne(optional = false)
    private Usuarios fkIdUsu;
    @JoinColumn(name = "fk_id_pro", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdPro;

    public Vistaspaginas() {
    }

    public Vistaspaginas(Integer pkIdVista) {
        this.pkIdVista = pkIdVista;
    }

    public Vistaspaginas(Integer pkIdVista, String pagina, Date fec) {
        this.pkIdVista = pkIdVista;
        this.pagina = pagina;
        this.fec = fec;
    }

    public Integer getPkIdVista() {
        return pkIdVista;
    }

    public void setPkIdVista(Integer pkIdVista) {
        this.pkIdVista = pkIdVista;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public Date getFec() {
        return fec;
    }

    public void setFec(Date fec) {
        this.fec = fec;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Usuarios getFkIdUsu() {
        return fkIdUsu;
    }

    public void setFkIdUsu(Usuarios fkIdUsu) {
        this.fkIdUsu = fkIdUsu;
    }
    
    public Proyectos getFkIdPro() {
        return fkIdPro;
    }

    public void setFkIdPro(Proyectos fkIdPro) {
        this.fkIdPro = fkIdPro;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdVista != null ? pkIdVista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vistaspaginas)) {
            return false;
        }
        Vistaspaginas other = (Vistaspaginas) object;
        if ((this.pkIdVista == null && other.pkIdVista != null) || (this.pkIdVista != null && !this.pkIdVista.equals(other.pkIdVista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Vistaspaginas[ pkIdVista=" + pkIdVista + " ]";
    }

}
