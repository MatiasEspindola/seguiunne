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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "OrganizacionesCoPar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizacionesCoPar.findAll", query = "SELECT o FROM OrganizacionesCoPar o")
    , @NamedQuery(name = "OrganizacionesCoPar.findByPkIdOrg", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.pkIdOrg = :pkIdOrg")
    , @NamedQuery(name = "OrganizacionesCoPar.findByNombre", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.nombre = :nombre")
    , @NamedQuery(name = "OrganizacionesCoPar.findByKmFacu", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.kmFacu = :kmFacu")
    , @NamedQuery(name = "OrganizacionesCoPar.findByResponsableInstitucional", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.responsableInstitucional = :responsableInstitucional")
    , @NamedQuery(name = "OrganizacionesCoPar.findByTelefonoFijo", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "OrganizacionesCoPar.findByTelefonoCelular", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.telefonoCelular = :telefonoCelular")
    , @NamedQuery(name = "OrganizacionesCoPar.findByEmail", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.email = :email")
    , @NamedQuery(name = "OrganizacionesCoPar.findByCordenadas", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.cordenadas = :cordenadas")
    , @NamedQuery(name = "OrganizacionesCoPar.findByBarrio", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.barrio = :barrio")
    , @NamedQuery(name = "OrganizacionesCoPar.findByCalle", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.calle = :calle")
    , @NamedQuery(name = "OrganizacionesCoPar.findByNumCalle", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.numCalle = :numCalle")
    , @NamedQuery(name = "OrganizacionesCoPar.findByDireccionPostal", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.direccionPostal = :direccionPostal")
    , @NamedQuery(name = "OrganizacionesCoPar.findByLocalidad", query = "SELECT o FROM OrganizacionesCoPar o WHERE o.localidad = :localidad")})
public class OrganizacionesCoPar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_org")
    private Integer pkIdOrg;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "km_facu")
    private Float kmFacu;
    @Size(max = 125)
    @Column(name = "responsableInstitucional")
    private String responsableInstitucional;
    @Size(max = 12)
    @Column(name = "telefonoFijo")
    private String telefonoFijo;
    @Size(max = 12)
    @Column(name = "telefonoCelular")
    private String telefonoCelular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 160)
    @Column(name = "email")
    private String email;
    @Size(max = 125)
    @Column(name = "cordenadas")
    private String cordenadas;
    @Size(max = 245)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 245)
    @Column(name = "calle")
    private String calle;
    @Column(name = "num_calle")
    private Integer numCalle;
    @Size(max = 125)
    @Column(name = "direccionPostal")
    private String direccionPostal;
    @Size(max = 65)
    @Column(name = "localidad")
    private String localidad;
    @JoinColumn(name = "fk_id_pro", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdPro;
    @JoinColumn(name = "fk_id_localidad", referencedColumnName = "pk_id_loc")
    @ManyToOne
    private Localidades fkIdLocalidad;

    public OrganizacionesCoPar() {
    }

    public OrganizacionesCoPar(Integer pkIdOrg) {
        this.pkIdOrg = pkIdOrg;
    }

    public Integer getPkIdOrg() {
        return pkIdOrg;
    }

    public void setPkIdOrg(Integer pkIdOrg) {
        this.pkIdOrg = pkIdOrg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getKmFacu() {
        return kmFacu;
    }

    public void setKmFacu(Float kmFacu) {
        this.kmFacu = kmFacu;
    }

    public String getResponsableInstitucional() {
        return responsableInstitucional;
    }

    public void setResponsableInstitucional(String responsableInstitucional) {
        this.responsableInstitucional = responsableInstitucional;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(String cordenadas) {
        this.cordenadas = cordenadas;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumCalle() {
        return numCalle;
    }

    public void setNumCalle(Integer numCalle) {
        this.numCalle = numCalle;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Proyectos getFkIdPro() {
        return fkIdPro;
    }

    public void setFkIdPro(Proyectos fkIdPro) {
        this.fkIdPro = fkIdPro;
    }

    public Localidades getFkIdLocalidad() {
        return fkIdLocalidad;
    }

    public void setFkIdLocalidad(Localidades fkIdLocalidad) {
        this.fkIdLocalidad = fkIdLocalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdOrg != null ? pkIdOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizacionesCoPar)) {
            return false;
        }
        OrganizacionesCoPar other = (OrganizacionesCoPar) object;
        if ((this.pkIdOrg == null && other.pkIdOrg != null) || (this.pkIdOrg != null && !this.pkIdOrg.equals(other.pkIdOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrganizacionesCoPar[ pkIdOrg=" + pkIdOrg + " ]";
    }
    
}
