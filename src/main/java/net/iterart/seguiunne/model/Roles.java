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
@Table(name = "Roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByPkIdRol", query = "SELECT r FROM Roles r WHERE r.pkIdRol = :pkIdRol")
    , @NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rol = :rol")
    , @NamedQuery(name = "Roles.findByDescrip", query = "SELECT r FROM Roles r WHERE r.descrip = :descrip")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_rol")
    private Integer pkIdRol;
    @Size(max = 40)
    @Column(name = "rol")
    private String rol;
    @Size(max = 60)
    @Column(name = "descrip")
    private String descrip;
    @OneToMany(mappedBy = "fkIdRol")
    private List<Usuarios> usuariosList;

    public Roles() {
    }

    public Roles(Integer pkIdRol) {
        this.pkIdRol = pkIdRol;
    }

    public Integer getPkIdRol() {
        return pkIdRol;
    }

    public void setPkIdRol(Integer pkIdRol) {
        this.pkIdRol = pkIdRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdRol != null ? pkIdRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.pkIdRol == null && other.pkIdRol != null) || (this.pkIdRol != null && !this.pkIdRol.equals(other.pkIdRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Roles[ pkIdRol=" + pkIdRol + " ]";
    }
    
}
