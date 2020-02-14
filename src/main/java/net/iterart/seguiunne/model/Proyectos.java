/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Proyectos")
public class Proyectos implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_pro")
    private Integer pkIdPro;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descri")
    private String descri;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "palabraClaves")
    private String palabraClaves;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resumenTecnico")
    private String resumenTecnico;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objetivoGral")
    private String objetivoGral;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "objetivoEspecifico")
    private String objetivoEspecifico;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "caracteristicasDestinatarios")
    private String caracteristicasDestinatarios;
    @Column(name = "cantPersonasDirecta")
    private Integer cantPersonasDirecta;
    @Column(name = "cantPersonasIndirecta")
    private Integer cantPersonasIndirecta;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "marcoTeorico")
    private String marcoTeorico;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "antecedentes")
    private String antecedentes;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "metodologias")
    private String metodologias;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "actividadEstudiante")
    private String actividadEstudiante;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "actividadOrgComunitaria")
    private String actividadOrgComunitaria;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "impactoEsperado")
    private String impactoEsperado;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resultadoEsperado")
    private String resultadoEsperado;
    @Size(max = 5000)
    @Column(name = "bibliografia")
    private String bibliografia;
    
//    @OneToMany(mappedBy = "fkIdProyecto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<ProyectoBibliografia> proyectoBibliografiaList;
//    
//    @OneToMany(mappedBy = "fkIdProyecto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Proyectosobjesp> proyectosobjespList;
//    
    @JoinColumn(name = "fk_id_dat", referencedColumnName = "pk_id_dat")
    @ManyToOne(fetch = FetchType.LAZY)
    private DatosGralProyecto fkIdDat;
//    
//    @OneToMany(mappedBy = "fkIdPro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Actividades> actividadesList;
//    
//    @OneToMany(mappedBy = "fkIdPro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<OrganizacionesCoPar> organizacionesCoParList;
//    
//    @OneToMany(mappedBy = "fkIdPro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Miembros> miembrosList;
//    
//    @OneToMany(mappedBy = "fkIdProyecto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProyectosActivEstud> proyectosActivEstudList;
//    
//    @OneToMany(mappedBy = "fkIdPro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Financiacion> financiacionList;
//    
//    @OneToMany(mappedBy = "fkIdProyecto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProyectosActivOrg> proyectosActivOrgList;  
//    
//    @OneToMany(mappedBy = "fkIdPro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Vistaspaginas> vistaspaginasList;
    

    public Proyectos() {
    }

    public Proyectos(Integer pkIdPro) {
        this.pkIdPro = pkIdPro;
    }

    public Integer getPkIdPro() {
        return pkIdPro;
    }

    public void setPkIdPro(Integer pkIdPro) {
        this.pkIdPro = pkIdPro;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getPalabraClaves() {
        return palabraClaves;
    }

    public void setPalabraClaves(String palabraClaves) {
        this.palabraClaves = palabraClaves;
    }

    public String getResumenTecnico() {
        return resumenTecnico;
    }

    public void setResumenTecnico(String resumenTecnico) {
        this.resumenTecnico = resumenTecnico;
    }

    public String getObjetivoGral() {
        return objetivoGral;
    }

    public void setObjetivoGral(String objetivoGral) {
        this.objetivoGral = objetivoGral;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getCaracteristicasDestinatarios() {
        return caracteristicasDestinatarios;
    }

    public void setCaracteristicasDestinatarios(String caracteristicasDestinatarios) {
        this.caracteristicasDestinatarios = caracteristicasDestinatarios;
    }

    public Integer getCantPersonasDirecta() {
        return cantPersonasDirecta;
    }

    public void setCantPersonasDirecta(Integer cantPersonasDirecta) {
        this.cantPersonasDirecta = cantPersonasDirecta;
    }

    public Integer getCantPersonasIndirecta() {
        return cantPersonasIndirecta;
    }

    public void setCantPersonasIndirecta(Integer cantPersonasIndirecta) {
        this.cantPersonasIndirecta = cantPersonasIndirecta;
    }

    public String getMarcoTeorico() {
        return marcoTeorico;
    }

    public void setMarcoTeorico(String marcoTeorico) {
        this.marcoTeorico = marcoTeorico;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getMetodologias() {
        return metodologias;
    }

    public void setMetodologias(String metodologias) {
        this.metodologias = metodologias;
    }

    public String getActividadEstudiante() {
        return actividadEstudiante;
    }

    public void setActividadEstudiante(String actividadEstudiante) {
        this.actividadEstudiante = actividadEstudiante;
    }

    public String getActividadOrgComunitaria() {
        return actividadOrgComunitaria;
    }

    public void setActividadOrgComunitaria(String actividadOrgComunitaria) {
        this.actividadOrgComunitaria = actividadOrgComunitaria;
    }

    public String getImpactoEsperado() {
        return impactoEsperado;
    }

    public void setImpactoEsperado(String impactoEsperado) {
        this.impactoEsperado = impactoEsperado;
    }

    public String getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(String resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

//    @XmlTransient
//    public List<ProyectoBibliografia> getProyectoBibliografiaList() {
//        return proyectoBibliografiaList;
//    }
//
//    public void setProyectoBibliografiaList(List<ProyectoBibliografia> proyectoBibliografiaList) {
//        this.proyectoBibliografiaList = proyectoBibliografiaList;
//    }
//
//    @XmlTransient
//    public List<Proyectosobjesp> getProyectosobjespList() {
//        return proyectosobjespList;
//    }
//
//    public void setProyectosobjespList(List<Proyectosobjesp> proyectosobjespList) {
//        this.proyectosobjespList = proyectosobjespList;
//    }
//
    public DatosGralProyecto getFkIdDat() {
        return fkIdDat;
    }

    public void setFkIdDat(DatosGralProyecto fkIdDat) {
        this.fkIdDat = fkIdDat;
    }
//
//    @XmlTransient
//    public List<Actividades> getActividadesList() {
//        return actividadesList;
//    }
//
//    public void setActividadesList(List<Actividades> actividadesList) {
//        this.actividadesList = actividadesList;
//    }
//
//    @XmlTransient
//    public List<OrganizacionesCoPar> getOrganizacionesCoParList() {
//        return organizacionesCoParList;
//    }
//
//    public void setOrganizacionesCoParList(List<OrganizacionesCoPar> organizacionesCoParList) {
//        this.organizacionesCoParList = organizacionesCoParList;
//    }
//
//    @XmlTransient
//    public List<Miembros> getMiembrosList() {
//        return miembrosList;
//    }
//
//    public void setMiembrosList(List<Miembros> miembrosList) {
//        this.miembrosList = miembrosList;
//    }
//
//    @XmlTransient
//    public List<ProyectosActivEstud> getProyectosActivEstudList() {
//        return proyectosActivEstudList;
//    }
//
//    public void setProyectosActivEstudList(List<ProyectosActivEstud> proyectosActivEstudList) {
//        this.proyectosActivEstudList = proyectosActivEstudList;
//    }
//
//    @XmlTransient
//    public List<Financiacion> getFinanciacionList() {
//        return financiacionList;
//    }
//
//    public void setFinanciacionList(List<Financiacion> financiacionList) {
//        this.financiacionList = financiacionList;
//    }
//
//    @XmlTransient
//    public List<ProyectosActivOrg> getProyectosActivOrgList() {
//        return proyectosActivOrgList;
//    }
//
//    public void setProyectosActivOrgList(List<ProyectosActivOrg> proyectosActivOrgList) {
//        this.proyectosActivOrgList = proyectosActivOrgList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdPro != null ? pkIdPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.pkIdPro == null && other.pkIdPro != null) || (this.pkIdPro != null && !this.pkIdPro.equals(other.pkIdPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Proyectos[ pkIdPro=" + pkIdPro + " ]";
    }

//    @XmlTransient
//    public List<Vistaspaginas> getVistaspaginasList() {
//        return vistaspaginasList;
//    }
//
//    public void setVistaspaginasList(List<Vistaspaginas> vistaspaginasList) {
//        this.vistaspaginasList = vistaspaginasList;
//    }
    
}
