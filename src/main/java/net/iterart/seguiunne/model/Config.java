/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c")
    , @NamedQuery(name = "Config.findByPkidConfig", query = "SELECT c FROM Config c WHERE c.pkidConfig = :pkidConfig")
    , @NamedQuery(name = "Config.findByFechaFinanciacion", query = "SELECT c FROM Config c WHERE c.fechaFinanciacion = :fechaFinanciacion")
    , @NamedQuery(name = "Config.findByMontoMinFinan", query = "SELECT c FROM Config c WHERE c.montoMinFinan = :montoMinFinan")
    , @NamedQuery(name = "Config.findByAporteUnneMin80", query = "SELECT c FROM Config c WHERE c.aporteUnneMin80 = :aporteUnneMin80")
    , @NamedQuery(name = "Config.findByAporteUnneMay80", query = "SELECT c FROM Config c WHERE c.aporteUnneMay80 = :aporteUnneMay80")})
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_Config")
    private Integer pkidConfig;
    @Column(name = "fecha_financiacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinanciacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoMinFinan")
    private Float montoMinFinan;
    @Column(name = "aporteUnneMin80")
    private Float aporteUnneMin80;
    @Column(name = "aporteUnneMay80")
    private Float aporteUnneMay80;

    public Config() {
    }

    public Config(Integer pkidConfig) {
        this.pkidConfig = pkidConfig;
    }

    public Integer getPkidConfig() {
        return pkidConfig;
    }

    public void setPkidConfig(Integer pkidConfig) {
        this.pkidConfig = pkidConfig;
    }

    public Date getFechaFinanciacion() {
        return fechaFinanciacion;
    }

    public void setFechaFinanciacion(Date fechaFinanciacion) {
        this.fechaFinanciacion = fechaFinanciacion;
    }

    public Float getMontoMinFinan() {
        return montoMinFinan;
    }

    public void setMontoMinFinan(Float montoMinFinan) {
        this.montoMinFinan = montoMinFinan;
    }

    public Float getAporteUnneMin80() {
        return aporteUnneMin80;
    }

    public void setAporteUnneMin80(Float aporteUnneMin80) {
        this.aporteUnneMin80 = aporteUnneMin80;
    }

    public Float getAporteUnneMay80() {
        return aporteUnneMay80;
    }

    public void setAporteUnneMay80(Float aporteUnneMay80) {
        this.aporteUnneMay80 = aporteUnneMay80;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkidConfig != null ? pkidConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.pkidConfig == null && other.pkidConfig != null) || (this.pkidConfig != null && !this.pkidConfig.equals(other.pkidConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Config[ pkidConfig=" + pkidConfig + " ]";
    }
    
}
