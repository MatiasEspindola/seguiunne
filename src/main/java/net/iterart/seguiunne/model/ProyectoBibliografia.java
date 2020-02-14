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
@Table(name = "Proyecto_Bibliografia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectoBibliografia.findAll", query = "SELECT p FROM ProyectoBibliografia p")
    , @NamedQuery(name = "ProyectoBibliografia.findByPkProyectoBibliografia", query = "SELECT p FROM ProyectoBibliografia p WHERE p.pkProyectoBibliografia = :pkProyectoBibliografia")
    , @NamedQuery(name = "ProyectoBibliografia.findByAutor", query = "SELECT p FROM ProyectoBibliografia p WHERE p.autor = :autor")
    , @NamedQuery(name = "ProyectoBibliografia.findByTitulo", query = "SELECT p FROM ProyectoBibliografia p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "ProyectoBibliografia.findByLugar", query = "SELECT p FROM ProyectoBibliografia p WHERE p.lugar = :lugar")
    , @NamedQuery(name = "ProyectoBibliografia.findByAnio", query = "SELECT p FROM ProyectoBibliografia p WHERE p.anio = :anio")})
public class ProyectoBibliografia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_proyecto_bibliografia")
    private Integer pkProyectoBibliografia;
    @Size(max = 100)
    @Column(name = "autor")
    private String autor;
    @Size(max = 500)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 250)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 4)
    @Column(name = "anio")
    private String anio;
    @JoinColumn(name = "fk_id_proyecto", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdProyecto;

    public ProyectoBibliografia() {
    }

    public ProyectoBibliografia(Integer pkProyectoBibliografia) {
        this.pkProyectoBibliografia = pkProyectoBibliografia;
    }

    public Integer getPkProyectoBibliografia() {
        return pkProyectoBibliografia;
    }

    public void setPkProyectoBibliografia(Integer pkProyectoBibliografia) {
        this.pkProyectoBibliografia = pkProyectoBibliografia;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Proyectos getFkIdProyecto() {
        return fkIdProyecto;
    }

    public void setFkIdProyecto(Proyectos fkIdProyecto) {
        this.fkIdProyecto = fkIdProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProyectoBibliografia != null ? pkProyectoBibliografia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoBibliografia)) {
            return false;
        }
        ProyectoBibliografia other = (ProyectoBibliografia) object;
        if ((this.pkProyectoBibliografia == null && other.pkProyectoBibliografia != null) || (this.pkProyectoBibliografia != null && !this.pkProyectoBibliografia.equals(other.pkProyectoBibliografia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProyectoBibliografia[ pkProyectoBibliografia=" + pkProyectoBibliografia + " ]";
    }
    
}
