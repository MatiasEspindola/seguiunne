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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagenes.findAll", query = "SELECT i FROM Imagenes i")
    , @NamedQuery(name = "Imagenes.findByPkIdImg", query = "SELECT i FROM Imagenes i WHERE i.pkIdImg = :pkIdImg")
    , @NamedQuery(name = "Imagenes.findByNombre", query = "SELECT i FROM Imagenes i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Imagenes.findByServer", query = "SELECT i FROM Imagenes i WHERE i.server = :server")})
public class Imagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_img")
    private Integer pkIdImg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "server")
    private String server;
    @JoinColumn(name = "fk_id_infotar", referencedColumnName = "pk_id_infotar")
    @ManyToOne(optional = false)
    private InformesTareas fkIdInfotar;
    @JoinColumn(name = "fk_id_url", referencedColumnName = "pk_id_url")
    @ManyToOne(optional = false)
    private UrlsImagenes fkIdUrl;

    public Imagenes() {
    }

    public Imagenes(Integer pkIdImg) {
        this.pkIdImg = pkIdImg;
    }

    public Imagenes(Integer pkIdImg, String nombre, String server) {
        this.pkIdImg = pkIdImg;
        this.nombre = nombre;
        this.server = server;
    }

    public Integer getPkIdImg() {
        return pkIdImg;
    }

    public void setPkIdImg(Integer pkIdImg) {
        this.pkIdImg = pkIdImg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public InformesTareas getFkIdInfotar() {
        return fkIdInfotar;
    }

    public void setFkIdInfotar(InformesTareas fkIdInfotar) {
        this.fkIdInfotar = fkIdInfotar;
    }

    public UrlsImagenes getFkIdUrl() {
        return fkIdUrl;
    }

    public void setFkIdUrl(UrlsImagenes fkIdUrl) {
        this.fkIdUrl = fkIdUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdImg != null ? pkIdImg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenes)) {
            return false;
        }
        Imagenes other = (Imagenes) object;
        if ((this.pkIdImg == null && other.pkIdImg != null) || (this.pkIdImg != null && !this.pkIdImg.equals(other.pkIdImg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Imagenes[ pkIdImg=" + pkIdImg + " ]";
    }
    
}
