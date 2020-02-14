/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Where;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Usuarios")
@Where(clause = "hab = true")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
//    , @NamedQuery(name = "Usuarios.findByPkIdUsu", query = "SELECT u FROM Usuarios u WHERE u.pkIdUsu = :pkIdUsu")
//    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
//    , @NamedQuery(name = "Usuarios.findByClave", query = "SELECT u FROM Usuarios u WHERE u.clave = :clave")
//    , @NamedQuery(name = "Usuarios.findBySalt", query = "SELECT u FROM Usuarios u WHERE u.salt = :salt")
//    , @NamedQuery(name = "Usuarios.findByHab", query = "SELECT u FROM Usuarios u WHERE u.hab = :hab")})
public class Usuarios implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsuFrom")
    private Collection<Mensajes> mensajesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsuTo")
    private Collection<Mensajes> mensajesCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdUsu")
    private Collection<Vistaspaginas> vistaspaginasCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_usu")
    private Integer pkIdUsu;
    @Size(max = 100)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 255)
    @Column(name = "clave")
    private String clave;
    @Size(max = 255)
    @Column(name = "salt")
    private String salt;
    @Column(name = "hab")
    private Boolean hab;
    @JoinColumn(name = "fk_id_rol", referencedColumnName = "pk_id_rol")
    @ManyToOne
    private Roles fkIdRol;
    @OneToMany(mappedBy = "fkIdUsu")
    private List<AdminsFacultades> adminsFacultadesList;
    @OneToMany(mappedBy = "fkIdUsu")
    private List<DatosGralProyecto> datosGralProyectoList;

    public Usuarios() {
    }

    public Usuarios(Integer pkIdUsu) {
        this.pkIdUsu = pkIdUsu;
    }

    public Integer getPkIdUsu() {
        return pkIdUsu;
    }

    public void setPkIdUsu(Integer pkIdUsu) {
        this.pkIdUsu = pkIdUsu;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getHab() {
        return hab;
    }

    public void setHab(Boolean hab) {
        this.hab = hab;
    }

    public Roles getFkIdRol() {
        return fkIdRol;
    }

    public void setFkIdRol(Roles fkIdRol) {
        this.fkIdRol = fkIdRol;
    }

    @XmlTransient
    public List<AdminsFacultades> getAdminsFacultadesList() {
        return adminsFacultadesList;
    }

    public void setAdminsFacultadesList(List<AdminsFacultades> adminsFacultadesList) {
        this.adminsFacultadesList = adminsFacultadesList;
    }

    @XmlTransient
    public List<DatosGralProyecto> getDatosGralProyectoList() {
        return datosGralProyectoList;
    }

    public void setDatosGralProyectoList(List<DatosGralProyecto> datosGralProyectoList) {
        this.datosGralProyectoList = datosGralProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdUsu != null ? pkIdUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.pkIdUsu == null && other.pkIdUsu != null) || (this.pkIdUsu != null && !this.pkIdUsu.equals(other.pkIdUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario;
    }

    @XmlTransient
    public Collection<Mensajes> getMensajesCollection() {
        return mensajesCollection;
    }

    public void setMensajesCollection(Collection<Mensajes> mensajesCollection) {
        this.mensajesCollection = mensajesCollection;
    }

    @XmlTransient
    public Collection<Mensajes> getMensajesCollection1() {
        return mensajesCollection1;
    }

    public void setMensajesCollection1(Collection<Mensajes> mensajesCollection1) {
        this.mensajesCollection1 = mensajesCollection1;
    }

    @XmlTransient
    public Collection<Vistaspaginas> getVistaspaginasCollection() {
        return vistaspaginasCollection;
    }

    public void setVistaspaginasCollection(Collection<Vistaspaginas> vistaspaginasCollection) {
        this.vistaspaginasCollection = vistaspaginasCollection;
    }
    
}
