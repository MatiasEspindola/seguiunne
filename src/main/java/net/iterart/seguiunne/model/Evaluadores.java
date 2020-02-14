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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Evaluadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluadores.findAll", query = "SELECT e FROM Evaluadores e")
    , @NamedQuery(name = "Evaluadores.findByPkIdEval", query = "SELECT e FROM Evaluadores e WHERE e.pkIdEval = :pkIdEval")
    , @NamedQuery(name = "Evaluadores.findByNomyape", query = "SELECT e FROM Evaluadores e WHERE e.nomyape = :nomyape")
    , @NamedQuery(name = "Evaluadores.findByDni", query = "SELECT e FROM Evaluadores e WHERE e.dni = :dni")
    , @NamedQuery(name = "Evaluadores.findByEmail", query = "SELECT e FROM Evaluadores e WHERE e.email = :email")
    , @NamedQuery(name = "Evaluadores.findByUsuario", query = "SELECT e FROM Evaluadores e WHERE e.usuario = :usuario")
    , @NamedQuery(name = "Evaluadores.findByPass", query = "SELECT e FROM Evaluadores e WHERE e.pass = :pass")
    , @NamedQuery(name = "Evaluadores.findByNotificado", query = "SELECT e FROM Evaluadores e WHERE e.notificado = :notificado")})
public class Evaluadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_eval")
    private Integer pkIdEval;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomyape")
    private String nomyape;
    @Size(max = 10)
    @Column(name = "dni")
    private String dni;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 30)
    @Column(name = "pass")
    private String pass;
    @Size(max = 2)
    @Column(name = "notificado")
    private String notificado;
    @JoinColumn(name = "fk_id_fac", referencedColumnName = "pk_id_fac")
    @ManyToOne
    private Facultades fkIdFac;

    public Evaluadores() {
    }

    public Evaluadores(Integer pkIdEval) {
        this.pkIdEval = pkIdEval;
    }

    public Evaluadores(Integer pkIdEval, String nomyape, String email) {
        this.pkIdEval = pkIdEval;
        this.nomyape = nomyape;
        this.email = email;
    }

    public Integer getPkIdEval() {
        return pkIdEval;
    }

    public void setPkIdEval(Integer pkIdEval) {
        this.pkIdEval = pkIdEval;
    }

    public String getNomyape() {
        return nomyape;
    }

    public void setNomyape(String nomyape) {
        this.nomyape = nomyape;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNotificado() {
        return notificado;
    }

    public void setNotificado(String notificado) {
        this.notificado = notificado;
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
        hash += (pkIdEval != null ? pkIdEval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluadores)) {
            return false;
        }
        Evaluadores other = (Evaluadores) object;
        if ((this.pkIdEval == null && other.pkIdEval != null) || (this.pkIdEval != null && !this.pkIdEval.equals(other.pkIdEval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Evaluadores[ pkIdEval=" + pkIdEval + " ]";
    }
    
}
