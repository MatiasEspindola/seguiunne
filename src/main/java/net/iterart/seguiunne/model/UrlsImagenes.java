/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "UrlsImagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrlsImagenes.findAll", query = "SELECT u FROM UrlsImagenes u")
    , @NamedQuery(name = "UrlsImagenes.findByPkIdUrl", query = "SELECT u FROM UrlsImagenes u WHERE u.pkIdUrl = :pkIdUrl")
    , @NamedQuery(name = "UrlsImagenes.findByUrl", query = "SELECT u FROM UrlsImagenes u WHERE u.url = :url")})
public class UrlsImagenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_url")
    private Integer pkIdUrl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "url")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUrl")
    private List<Imagenes> imagenesList;

    public UrlsImagenes() {
    }

    public UrlsImagenes(Integer pkIdUrl) {
        this.pkIdUrl = pkIdUrl;
    }

    public UrlsImagenes(Integer pkIdUrl, String url) {
        this.pkIdUrl = pkIdUrl;
        this.url = url;
    }

    public Integer getPkIdUrl() {
        return pkIdUrl;
    }

    public void setPkIdUrl(Integer pkIdUrl) {
        this.pkIdUrl = pkIdUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<Imagenes> getImagenesList() {
        return imagenesList;
    }

    public void setImagenesList(List<Imagenes> imagenesList) {
        this.imagenesList = imagenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdUrl != null ? pkIdUrl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrlsImagenes)) {
            return false;
        }
        UrlsImagenes other = (UrlsImagenes) object;
        if ((this.pkIdUrl == null && other.pkIdUrl != null) || (this.pkIdUrl != null && !this.pkIdUrl.equals(other.pkIdUrl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UrlsImagenes[ pkIdUrl=" + pkIdUrl + " ]";
    }
    
}
