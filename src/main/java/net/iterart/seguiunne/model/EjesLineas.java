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
@Table(name = "Ejes_Lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjesLineas.findAll", query = "SELECT e FROM EjesLineas e")
    , @NamedQuery(name = "EjesLineas.findByPkIdEjeLinea", query = "SELECT e FROM EjesLineas e WHERE e.pkIdEjeLinea = :pkIdEjeLinea")
    , @NamedQuery(name = "EjesLineas.findByDescrip", query = "SELECT e FROM EjesLineas e WHERE e.descrip = :descrip")})
public class EjesLineas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_eje_linea")
    private Integer pkIdEjeLinea;
    @Size(max = 85)
    @Column(name = "descrip")
    private String descrip;
    @JoinColumn(name = "fk_id_eje", referencedColumnName = "pk_id_eje")
    @ManyToOne
    private EjeTematicos fkIdEje;
    @OneToMany(mappedBy = "fkIdEjeLinea")
    private List<DatosGralProyecto> datosGralProyectoList;

    public EjesLineas() {
    }

    public EjesLineas(Integer pkIdEjeLinea) {
        this.pkIdEjeLinea = pkIdEjeLinea;
    }

    public Integer getPkIdEjeLinea() {
        return pkIdEjeLinea;
    }

    public void setPkIdEjeLinea(Integer pkIdEjeLinea) {
        this.pkIdEjeLinea = pkIdEjeLinea;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public EjeTematicos getFkIdEje() {
        return fkIdEje;
    }

    public void setFkIdEje(EjeTematicos fkIdEje) {
        this.fkIdEje = fkIdEje;
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
        hash += (pkIdEjeLinea != null ? pkIdEjeLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjesLineas)) {
            return false;
        }
        EjesLineas other = (EjesLineas) object;
        if ((this.pkIdEjeLinea == null && other.pkIdEjeLinea != null) || (this.pkIdEjeLinea != null && !this.pkIdEjeLinea.equals(other.pkIdEjeLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descrip;
    }
    
}
