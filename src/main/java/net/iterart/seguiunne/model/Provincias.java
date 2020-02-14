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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author gtoffa
 */
@Entity
@Table(name = "Provincias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p ORDER BY p.nombre ASC")})
public class Provincias implements Serializable {

    
    @Column(name = "capi")
    private Integer capi;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_id_prv")
    private Integer pkIdPrv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    

    public Provincias() {
    }

    public Provincias(Integer pkIdPrv) {
        this.pkIdPrv = pkIdPrv;
    }

    public Provincias(Integer pkIdPrv, String nombre) {
        this.pkIdPrv = pkIdPrv;
        this.nombre = nombre;
    }

    public Integer getPkIdPrv() {
        return pkIdPrv;
    }

    public void setPkIdPrv(Integer pkIdPrv) {
        this.pkIdPrv = pkIdPrv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdPrv != null ? pkIdPrv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.pkIdPrv == null && other.pkIdPrv != null) || (this.pkIdPrv != null && !this.pkIdPrv.equals(other.pkIdPrv))) {
            return false;
        }
        return true;
    }
    
    public static String ucFirst(String str) {
    	  if (str == null || str.isEmpty()) {
    	    return str;            
    	  } else {
    	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase(); 
    	  }
    }

    @Override
    public String toString() {
        return ucFirst(getNombre());
    }

   

    public Integer getCapi() {
        return capi;
    }

    public void setCapi(Integer capi) {
        this.capi = capi;
    }
    
}