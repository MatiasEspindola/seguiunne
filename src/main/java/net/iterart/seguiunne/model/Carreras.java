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
@Table(name = "Carreras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carreras.findAll", query = "SELECT c FROM Carreras c")
    , @NamedQuery(name = "Carreras.findByPkIdCar", query = "SELECT c FROM Carreras c WHERE c.pkIdCar = :pkIdCar")
    , @NamedQuery(name = "Carreras.findByCarrera", query = "SELECT c FROM Carreras c WHERE c.carrera = :carrera")})
public class Carreras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_car")
    private Integer pkIdCar;
    @Size(max = 125)
    @Column(name = "carrera")
    private String carrera;
    @JoinColumn(name = "fk_id_fac", referencedColumnName = "pk_id_fac")
    @ManyToOne
    private Facultades fkIdFac;

    public Carreras() {
    }

    public Carreras(Integer pkIdCar) {
        this.pkIdCar = pkIdCar;
    }

    public Integer getPkIdCar() {
        return pkIdCar;
    }

    public void setPkIdCar(Integer pkIdCar) {
        this.pkIdCar = pkIdCar;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Facultades getFkIdFac() {
        return fkIdFac;
    }

    public void setFkIdFac(Facultades fkIdFac) {
        this.fkIdFac = fkIdFac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdCar != null ? pkIdCar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carreras)) {
            return false;
        }
        Carreras other = (Carreras) object;
        if ((this.pkIdCar == null && other.pkIdCar != null) || (this.pkIdCar != null && !this.pkIdCar.equals(other.pkIdCar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Carreras[ pkIdCar=" + pkIdCar + " ]";
    }
    
}
