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
@Table(name = "ResponsablesProyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsablesProyecto.findAll", query = "SELECT r FROM ResponsablesProyecto r")
    , @NamedQuery(name = "ResponsablesProyecto.findByPkIdRes", query = "SELECT r FROM ResponsablesProyecto r WHERE r.pkIdRes = :pkIdRes")
    , @NamedQuery(name = "ResponsablesProyecto.findByApellido", query = "SELECT r FROM ResponsablesProyecto r WHERE r.apellido = :apellido")
    , @NamedQuery(name = "ResponsablesProyecto.findByNombre", query = "SELECT r FROM ResponsablesProyecto r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "ResponsablesProyecto.findByNroDoc", query = "SELECT r FROM ResponsablesProyecto r WHERE r.nroDoc = :nroDoc")
    , @NamedQuery(name = "ResponsablesProyecto.findByTelcel", query = "SELECT r FROM ResponsablesProyecto r WHERE r.telcel = :telcel")
    , @NamedQuery(name = "ResponsablesProyecto.findByDireccion", query = "SELECT r FROM ResponsablesProyecto r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "ResponsablesProyecto.findByEmail", query = "SELECT r FROM ResponsablesProyecto r WHERE r.email = :email")})
public class ResponsablesProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_res")
    private Integer pkIdRes;
    @Size(max = 125)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 125)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 12)
    @Column(name = "nroDoc")
    private String nroDoc;
    @Size(max = 20)
    @Column(name = "telcel")
    private String telcel;
    @Size(max = 225)
    @Column(name = "direccion")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "fk_id_datGral", referencedColumnName = "pk_id_dat")
    @ManyToOne
    private DatosGralProyecto fkiddatGral;
    @JoinColumn(name = "fk_id_tpDoc", referencedColumnName = "pk_id_tpd")
    @ManyToOne
    private TiposDocumento fkidtpDoc;
    @JoinColumn(name = "fk_id_tpCar", referencedColumnName = "pk_id_tpc")
    @ManyToOne
    private TiposCargos fkidtpCar;
    @JoinColumn(name = "fk_id_carDoc", referencedColumnName = "pk_id_cdo")
    @ManyToOne
    private CargosDocente fkidcarDoc;
    @JoinColumn(name = "fk_id_loc", referencedColumnName = "pk_id_loc")
    @ManyToOne
    private Localidades fkIdLoc;
    @OneToMany(mappedBy = "fkIdRes")
    private List<Curriculum> curriculumList;

    public ResponsablesProyecto() {
    }

    public ResponsablesProyecto(Integer pkIdRes) {
        this.pkIdRes = pkIdRes;
    }

    public Integer getPkIdRes() {
        return pkIdRes;
    }

    public void setPkIdRes(Integer pkIdRes) {
        this.pkIdRes = pkIdRes;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getTelcel() {
        return telcel;
    }

    public void setTelcel(String telcel) {
        this.telcel = telcel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DatosGralProyecto getFkiddatGral() {
        return fkiddatGral;
    }

    public void setFkiddatGral(DatosGralProyecto fkiddatGral) {
        this.fkiddatGral = fkiddatGral;
    }

    public TiposDocumento getFkidtpDoc() {
        return fkidtpDoc;
    }

    public void setFkidtpDoc(TiposDocumento fkidtpDoc) {
        this.fkidtpDoc = fkidtpDoc;
    }

    public TiposCargos getFkidtpCar() {
        return fkidtpCar;
    }

    public void setFkidtpCar(TiposCargos fkidtpCar) {
        this.fkidtpCar = fkidtpCar;
    }

    public CargosDocente getFkidcarDoc() {
        return fkidcarDoc;
    }

    public void setFkidcarDoc(CargosDocente fkidcarDoc) {
        this.fkidcarDoc = fkidcarDoc;
    }

    public Localidades getFkIdLoc() {
        return fkIdLoc;
    }

    public void setFkIdLoc(Localidades fkIdLoc) {
        this.fkIdLoc = fkIdLoc;
    }

    @XmlTransient
    public List<Curriculum> getCurriculumList() {
        return curriculumList;
    }

    public void setCurriculumList(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdRes != null ? pkIdRes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsablesProyecto)) {
            return false;
        }
        ResponsablesProyecto other = (ResponsablesProyecto) object;
        if ((this.pkIdRes == null && other.pkIdRes != null) || (this.pkIdRes != null && !this.pkIdRes.equals(other.pkIdRes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre;
    }
    
}
