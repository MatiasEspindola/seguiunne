/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Mensajes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensajes.findAll", query = "SELECT m FROM Mensajes m")
    , @NamedQuery(name = "Mensajes.findByPkIdMsg", query = "SELECT m FROM Mensajes m WHERE m.pkIdMsg = :pkIdMsg")
    , @NamedQuery(name = "Mensajes.findByBandeja", query = "SELECT m FROM Mensajes m WHERE m.bandeja = :bandeja")
    , @NamedQuery(name = "Mensajes.findByFecCreacion", query = "SELECT m FROM Mensajes m WHERE m.fecCreacion = :fecCreacion")
    , @NamedQuery(name = "Mensajes.findByFecUpdate", query = "SELECT m FROM Mensajes m WHERE m.fecUpdate = :fecUpdate")
    , @NamedQuery(name = "Mensajes.findByFecDeleted", query = "SELECT m FROM Mensajes m WHERE m.fecDeleted = :fecDeleted")
    , @NamedQuery(name = "Mensajes.findBySubject", query = "SELECT m FROM Mensajes m WHERE m.subject = :subject")
    , @NamedQuery(name = "Mensajes.findByLeido", query = "SELECT m FROM Mensajes m WHERE m.leido = :leido")
    , @NamedQuery(name = "Mensajes.findByImportante", query = "SELECT m FROM Mensajes m WHERE m.importante = :importante")
    , @NamedQuery(name = "Mensajes.findByEliminado", query = "SELECT m FROM Mensajes m WHERE m.eliminado = :eliminado")})
public class Mensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_msg")
    private Integer pkIdMsg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "bandeja")
    private String bandeja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Column(name = "fec_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecUpdate;
    @Column(name = "fec_deleted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecDeleted;
    @Size(max = 255)
    @Column(name = "subject")
    private String subject;
    @Lob
    @Size(max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "leido")
    private boolean leido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importante")
    private boolean importante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminado")
    private boolean eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdMsgPrevio")
    private Collection<Mensajes> mensajesCollection;
    @JoinColumn(name = "fk_id_msg_previo", referencedColumnName = "pk_id_msg")
    @ManyToOne(optional = false)
    private Mensajes fkIdMsgPrevio;
    @JoinColumn(name = "fk_id_usu_from", referencedColumnName = "pk_id_usu")
    @ManyToOne(optional = false)
    private Usuarios fkIdUsuFrom;
    @JoinColumn(name = "fk_id_usu_to", referencedColumnName = "pk_id_usu")
    @ManyToOne(optional = false)
    private Usuarios fkIdUsuTo;

    public Mensajes() {
    }

    public Mensajes(Integer pkIdMsg) {
        this.pkIdMsg = pkIdMsg;
    }

    public Mensajes(Integer pkIdMsg, String bandeja, Date fecCreacion, boolean leido, boolean importante, boolean eliminado) {
        this.pkIdMsg = pkIdMsg;
        this.bandeja = bandeja;
        this.fecCreacion = fecCreacion;
        this.leido = leido;
        this.importante = importante;
        this.eliminado = eliminado;
    }

    public Integer getPkIdMsg() {
        return pkIdMsg;
    }

    public void setPkIdMsg(Integer pkIdMsg) {
        this.pkIdMsg = pkIdMsg;
    }

    public String getBandeja() {
        return bandeja;
    }

    public void setBandeja(String bandeja) {
        this.bandeja = bandeja;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Date getFecUpdate() {
        return fecUpdate;
    }

    public void setFecUpdate(Date fecUpdate) {
        this.fecUpdate = fecUpdate;
    }

    public Date getFecDeleted() {
        return fecDeleted;
    }

    public void setFecDeleted(Date fecDeleted) {
        this.fecDeleted = fecDeleted;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public boolean getImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Mensajes> getMensajesCollection() {
        return mensajesCollection;
    }

    public void setMensajesCollection(Collection<Mensajes> mensajesCollection) {
        this.mensajesCollection = mensajesCollection;
    }

    public Mensajes getFkIdMsgPrevio() {
        return fkIdMsgPrevio;
    }

    public void setFkIdMsgPrevio(Mensajes fkIdMsgPrevio) {
        this.fkIdMsgPrevio = fkIdMsgPrevio;
    }

    public Usuarios getFkIdUsuFrom() {
        return fkIdUsuFrom;
    }

    public void setFkIdUsuFrom(Usuarios fkIdUsuFrom) {
        this.fkIdUsuFrom = fkIdUsuFrom;
    }

    public Usuarios getFkIdUsuTo() {
        return fkIdUsuTo;
    }

    public void setFkIdUsuTo(Usuarios fkIdUsuTo) {
        this.fkIdUsuTo = fkIdUsuTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdMsg != null ? pkIdMsg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajes)) {
            return false;
        }
        Mensajes other = (Mensajes) object;
        if ((this.pkIdMsg == null && other.pkIdMsg != null) || (this.pkIdMsg != null && !this.pkIdMsg.equals(other.pkIdMsg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mensajes[ pkIdMsg=" + pkIdMsg + " ]";
    }
    
}
