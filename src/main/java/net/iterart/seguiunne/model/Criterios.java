/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Criterios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criterios.findAll", query = "SELECT c FROM Criterios c")
    , @NamedQuery(name = "Criterios.findByPkIdCri", query = "SELECT c FROM Criterios c WHERE c.pkIdCri = :pkIdCri")
    , @NamedQuery(name = "Criterios.findByDescri", query = "SELECT c FROM Criterios c WHERE c.descri = :descri")
    , @NamedQuery(name = "Criterios.findByFactor", query = "SELECT c FROM Criterios c WHERE c.factor = :factor")})
public class Criterios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_cri")
    private Integer pkIdCri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 220)
    @Column(name = "descri")
    private String descri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "factor")
    private BigDecimal factor;
    @JoinColumn(name = "fk_id_enf", referencedColumnName = "pk_id_enf")
    @ManyToOne(optional = false)
    private Enfoques fkIdEnf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdCri")
    private List<Evaluaciones> evaluacionesList;

    public Criterios() {
    }

    public Criterios(Integer pkIdCri) {
        this.pkIdCri = pkIdCri;
    }

    public Criterios(Integer pkIdCri, String descri, BigDecimal factor) {
        this.pkIdCri = pkIdCri;
        this.descri = descri;
        this.factor = factor;
    }

    public Integer getPkIdCri() {
        return pkIdCri;
    }

    public void setPkIdCri(Integer pkIdCri) {
        this.pkIdCri = pkIdCri;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public Enfoques getFkIdEnf() {
        return fkIdEnf;
    }

    public void setFkIdEnf(Enfoques fkIdEnf) {
        this.fkIdEnf = fkIdEnf;
    }

    @XmlTransient
    public List<Evaluaciones> getEvaluacionesList() {
        return evaluacionesList;
    }

    public void setEvaluacionesList(List<Evaluaciones> evaluacionesList) {
        this.evaluacionesList = evaluacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdCri != null ? pkIdCri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterios)) {
            return false;
        }
        Criterios other = (Criterios) object;
        if ((this.pkIdCri == null && other.pkIdCri != null) || (this.pkIdCri != null && !this.pkIdCri.equals(other.pkIdCri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Criterios[ pkIdCri=" + pkIdCri + " ]";
    }
    
}
