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
@Table(name = "Curriculum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curriculum.findAll", query = "SELECT c FROM Curriculum c")
    , @NamedQuery(name = "Curriculum.findByPkIdCur", query = "SELECT c FROM Curriculum c WHERE c.pkIdCur = :pkIdCur")
    , @NamedQuery(name = "Curriculum.findByTituloGrado", query = "SELECT c FROM Curriculum c WHERE c.tituloGrado = :tituloGrado")
    , @NamedQuery(name = "Curriculum.findByTituloPosGrado", query = "SELECT c FROM Curriculum c WHERE c.tituloPosGrado = :tituloPosGrado")
    , @NamedQuery(name = "Curriculum.findByAntDocente", query = "SELECT c FROM Curriculum c WHERE c.antDocente = :antDocente")
    , @NamedQuery(name = "Curriculum.findByAntInvestigacion", query = "SELECT c FROM Curriculum c WHERE c.antInvestigacion = :antInvestigacion")
    , @NamedQuery(name = "Curriculum.findByAntExtension", query = "SELECT c FROM Curriculum c WHERE c.antExtension = :antExtension")})
public class Curriculum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_cur")
    private Integer pkIdCur;
    @Size(max = 2000)
    @Column(name = "tituloGrado")
    private String tituloGrado;
    @Size(max = 2000)
    @Column(name = "tituloPosGrado")
    private String tituloPosGrado;
    @Size(max = 2000)
    @Column(name = "antDocente")
    private String antDocente;
    @Size(max = 2000)
    @Column(name = "antInvestigacion")
    private String antInvestigacion;
    @Size(max = 2000)
    @Column(name = "antExtension")
    private String antExtension;
    @JoinColumn(name = "fk_id_res", referencedColumnName = "pk_id_res")
    @ManyToOne
    private ResponsablesProyecto fkIdRes;

    public Curriculum() {
    }

    public Curriculum(Integer pkIdCur) {
        this.pkIdCur = pkIdCur;
    }

    public Integer getPkIdCur() {
        return pkIdCur;
    }

    public void setPkIdCur(Integer pkIdCur) {
        this.pkIdCur = pkIdCur;
    }

    public String getTituloGrado() {
        return tituloGrado;
    }

    public void setTituloGrado(String tituloGrado) {
        this.tituloGrado = tituloGrado;
    }

    public String getTituloPosGrado() {
        return tituloPosGrado;
    }

    public void setTituloPosGrado(String tituloPosGrado) {
        this.tituloPosGrado = tituloPosGrado;
    }

    public String getAntDocente() {
        return antDocente;
    }

    public void setAntDocente(String antDocente) {
        this.antDocente = antDocente;
    }

    public String getAntInvestigacion() {
        return antInvestigacion;
    }

    public void setAntInvestigacion(String antInvestigacion) {
        this.antInvestigacion = antInvestigacion;
    }

    public String getAntExtension() {
        return antExtension;
    }

    public void setAntExtension(String antExtension) {
        this.antExtension = antExtension;
    }

    public ResponsablesProyecto getFkIdRes() {
        return fkIdRes;
    }

    public void setFkIdRes(ResponsablesProyecto fkIdRes) {
        this.fkIdRes = fkIdRes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdCur != null ? pkIdCur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curriculum)) {
            return false;
        }
        Curriculum other = (Curriculum) object;
        if ((this.pkIdCur == null && other.pkIdCur != null) || (this.pkIdCur != null && !this.pkIdCur.equals(other.pkIdCur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Curriculum[ pkIdCur=" + pkIdCur + " ]";
    }
    
}
