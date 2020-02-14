/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Where;

/**
 *
 * @author ander
 */
@Entity
@DynamicUpdate
@Table(name = "Actividades")
@Where(clause = "hab=1")
public class Actividades implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "hab")
    private boolean hab;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_act")
    private Integer pkIdAct;
    @Size(max = 125)
    @Column(name = "descri")
    private String descri;
    @Column(name = "m1")
    private Boolean m1;
    @Column(name = "m2")
    private Boolean m2;
    @Column(name = "m3")
    private Boolean m3;
    @Column(name = "m4")
    private Boolean m4;
    @Column(name = "m5")
    private Boolean m5;
    @Column(name = "m6")
    private Boolean m6;
    @Column(name = "m7")
    private Boolean m7;
    @Column(name = "m8")
    private Boolean m8;
    @Column(name = "m9")
    private Boolean m9;
    @Column(name = "m10")
    private Boolean m10;
    @Column(name = "m11")
    private Boolean m11;
    @Column(name = "m12")
    private Boolean m12;
    @Column(name = "fin")
    private Boolean fin;

//    @OneToMany(mappedBy = "fkIdAct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ProgramasTareas> programasTareasList;
    @OneToMany(mappedBy = "fkIdAct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tareas> tareasList;

    @JoinColumn(name = "fk_id_pro", referencedColumnName = "pk_id_pro")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proyectos fkIdPro;

    public Actividades() {
    }

    public Actividades(Integer pkIdAct) {
        this.pkIdAct = pkIdAct;
    }

    public Integer getPkIdAct() {
        return pkIdAct;
    }

    public void setPkIdAct(Integer pkIdAct) {
        this.pkIdAct = pkIdAct;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Boolean getM1() {
        return m1;
    }

    public void setM1(Boolean m1) {
        this.m1 = m1;
    }

    public Boolean getM2() {
        return m2;
    }

    public void setM2(Boolean m2) {
        this.m2 = m2;
    }

    public Boolean getM3() {
        return m3;
    }

    public void setM3(Boolean m3) {
        this.m3 = m3;
    }

    public Boolean getM4() {
        return m4;
    }

    public void setM4(Boolean m4) {
        this.m4 = m4;
    }

    public Boolean getM5() {
        return m5;
    }

    public void setM5(Boolean m5) {
        this.m5 = m5;
    }

    public Boolean getM6() {
        return m6;
    }

    public void setM6(Boolean m6) {
        this.m6 = m6;
    }

    public Boolean getM7() {
        return m7;
    }

    public void setM7(Boolean m7) {
        this.m7 = m7;
    }

    public Boolean getM8() {
        return m8;
    }

    public void setM8(Boolean m8) {
        this.m8 = m8;
    }

    public Boolean getM9() {
        return m9;
    }

    public void setM9(Boolean m9) {
        this.m9 = m9;
    }

    public Boolean getM10() {
        return m10;
    }

    public void setM10(Boolean m10) {
        this.m10 = m10;
    }

    public Boolean getM11() {
        return m11;
    }

    public void setM11(Boolean m11) {
        this.m11 = m11;
    }

    public Boolean getM12() {
        return m12;
    }

    public void setM12(Boolean m12) {
        this.m12 = m12;
    }

    public Boolean getFin() {
        return fin;
    }

    public void setFin(Boolean fin) {
        this.fin = fin;
    }

//    @XmlTransient
//    public List<ProgramasTareas> getProgramasTareasList() {
//        return programasTareasList;
//    }
//
//    public void setProgramasTareasList(List<ProgramasTareas> programasTareasList) {
//        this.programasTareasList = programasTareasList;
//    }
    public List<Tareas> getTareasList() {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

    public Proyectos getFkIdPro() {
        return fkIdPro;
    }

    public void setFkIdPro(Proyectos fkIdPro) {
        this.fkIdPro = fkIdPro;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (pkIdAct != null ? pkIdAct.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Actividades)) {
//            return false;
//        }
//        Actividades other = (Actividades) object;
//        if ((this.pkIdAct == null && other.pkIdAct != null) || (this.pkIdAct != null && !this.pkIdAct.equals(other.pkIdAct))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        //this.fkIdPro.getFkIdDat().getFkIdFac();
        return descri;
    }
    
    public String facultad() {
        return this.fkIdPro.getFkIdDat().getFkIdFac().getFacultad();
    }

    public boolean getHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    //Lista de meses en que se realiza:
    public List<String> getMeses() {
        List<String> meses = new ArrayList<>();
        if (m1) {
            meses.add("Mes 1");
        }
        if (m2) {
            meses.add("Mes 2");
        }
        if (m3) {
            meses.add("Mes 3");
        }
        if (m4) {
            meses.add("Mes 4");
        }
        if (m5) {
            meses.add("Mes 5");
        }
        if (m6) {
            meses.add("Mes 6");
        }
        if (m7) {
            meses.add("Mes 7");
        }
        if (m8) {
            meses.add("Mes 8");
        }
        if (m9) {
            meses.add("Mes 9");
        }
        if (m10) {
            meses.add("Mes 10");
        }
        if (m11) {
            meses.add("Mes 11");
        }
        if (m12) {
            meses.add("Mes 12");
        }

        return meses;
    }

    //cantidad de Tareas en esta actividad:
    public Integer cantidadTareasProgramadas() {
        return this.tareasList.size();
    }

}
