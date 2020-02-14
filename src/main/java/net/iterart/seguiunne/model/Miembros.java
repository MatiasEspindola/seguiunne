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
@Table(name = "Miembros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miembros.findAll", query = "SELECT m FROM Miembros m")
    , @NamedQuery(name = "Miembros.findByPkIdMie", query = "SELECT m FROM Miembros m WHERE m.pkIdMie = :pkIdMie")
    , @NamedQuery(name = "Miembros.findByUnidadAcadCarrera", query = "SELECT m FROM Miembros m WHERE m.unidadAcadCarrera = :unidadAcadCarrera")
    , @NamedQuery(name = "Miembros.findByApellido", query = "SELECT m FROM Miembros m WHERE m.apellido = :apellido")
    , @NamedQuery(name = "Miembros.findByNombre", query = "SELECT m FROM Miembros m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Miembros.findByNroDoc", query = "SELECT m FROM Miembros m WHERE m.nroDoc = :nroDoc")
    , @NamedQuery(name = "Miembros.findByEmail", query = "SELECT m FROM Miembros m WHERE m.email = :email")
    , @NamedQuery(name = "Miembros.findByObservacion", query = "SELECT m FROM Miembros m WHERE m.observacion = :observacion")
    , @NamedQuery(name = "Miembros.findByActividadRealiza", query = "SELECT m FROM Miembros m WHERE m.actividadRealiza = :actividadRealiza")
    , @NamedQuery(name = "Miembros.findByProfesion", query = "SELECT m FROM Miembros m WHERE m.profesion = :profesion")
    , @NamedQuery(name = "Miembros.findByAnioCursado", query = "SELECT m FROM Miembros m WHERE m.anioCursado = :anioCursado")})
public class Miembros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_mie")
    private Integer pkIdMie;
    @Size(max = 200)
    @Column(name = "unidadAcadCarrera")
    private String unidadAcadCarrera;
    @Size(max = 125)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 125)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 12)
    @Column(name = "nroDoc")
    private String nroDoc;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 500)
    @Column(name = "actividad_realiza")
    private String actividadRealiza;
    @Size(max = 100)
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "anio_cursado")
    private Integer anioCursado;
    @JoinColumn(name = "fk_id_tpm", referencedColumnName = "pk_id_tpm")
    @ManyToOne
    private TiposMiembro fkIdTpm;
    @JoinColumn(name = "fk_id_pro", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdPro;

    public Miembros() {
    }

    public Miembros(Integer pkIdMie) {
        this.pkIdMie = pkIdMie;
    }

    public Integer getPkIdMie() {
        return pkIdMie;
    }

    public void setPkIdMie(Integer pkIdMie) {
        this.pkIdMie = pkIdMie;
    }

    public String getUnidadAcadCarrera() {
        return unidadAcadCarrera;
    }

    public void setUnidadAcadCarrera(String unidadAcadCarrera) {
        this.unidadAcadCarrera = unidadAcadCarrera;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getActividadRealiza() {
        return actividadRealiza;
    }

    public void setActividadRealiza(String actividadRealiza) {
        this.actividadRealiza = actividadRealiza;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Integer getAnioCursado() {
        return anioCursado;
    }

    public void setAnioCursado(Integer anioCursado) {
        this.anioCursado = anioCursado;
    }

    public TiposMiembro getFkIdTpm() {
        return fkIdTpm;
    }

    public void setFkIdTpm(TiposMiembro fkIdTpm) {
        this.fkIdTpm = fkIdTpm;
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
        hash += (pkIdMie != null ? pkIdMie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miembros)) {
            return false;
        }
        Miembros other = (Miembros) object;
        if ((this.pkIdMie == null && other.pkIdMie != null) || (this.pkIdMie != null && !this.pkIdMie.equals(other.pkIdMie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Miembros[ pkIdMie=" + pkIdMie + " ]";
    }
    
}
