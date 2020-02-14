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
import javax.persistence.Lob;
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
@Table(name = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicadores.findAll", query = "SELECT i FROM Indicadores i")
    , @NamedQuery(name = "Indicadores.findByPkIdIndicadores", query = "SELECT i FROM Indicadores i WHERE i.pkIdIndicadores = :pkIdIndicadores")
    , @NamedQuery(name = "Indicadores.findByOrden", query = "SELECT i FROM Indicadores i WHERE i.orden = :orden")})
public class Indicadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_indicadores")
    private Integer pkIdIndicadores;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "indicador")
    private String indicador;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "fk_id_metas", referencedColumnName = "pk_id_metas")
    @ManyToOne
    private Metas fkIdMetas;

    public Indicadores() {
    }

    public Indicadores(Integer pkIdIndicadores) {
        this.pkIdIndicadores = pkIdIndicadores;
    }

    public Integer getPkIdIndicadores() {
        return pkIdIndicadores;
    }

    public void setPkIdIndicadores(Integer pkIdIndicadores) {
        this.pkIdIndicadores = pkIdIndicadores;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Metas getFkIdMetas() {
        return fkIdMetas;
    }

    public void setFkIdMetas(Metas fkIdMetas) {
        this.fkIdMetas = fkIdMetas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdIndicadores != null ? pkIdIndicadores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicadores)) {
            return false;
        }
        Indicadores other = (Indicadores) object;
        if ((this.pkIdIndicadores == null && other.pkIdIndicadores != null) || (this.pkIdIndicadores != null && !this.pkIdIndicadores.equals(other.pkIdIndicadores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Indicadores[ pkIdIndicadores=" + pkIdIndicadores + " ]";
    }
    
}
