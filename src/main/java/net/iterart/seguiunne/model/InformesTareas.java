/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "informes_tareas")
public class InformesTareas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "pk_id_infotar")
	private Integer pkIdInfotar;
	@Basic(optional = false)
	@NotNull
	@Column(name = "cantEqui")
	private int cantEqui;
	@Basic(optional = false)
	@NotNull
	@Column(name = "cantExt")
	private int cantExt;
	@Size(max = 255)
	@Column(name = "descri")
	private String descri;

	// Cambios añadidos el 16-8-2018 - @ander

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;

	@Basic(optional = false)
	@NotNull
	@Column(name = "nro_dir")
	private int nroDir;

	@Size(max = 30)
	@NotEmpty
	@Column(name = "calle")
	private String calle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_loc", referencedColumnName = "pk_id_loc")
	private Localidades fkIdLoc;

	@Size(max = 255)
	@Column(name = "foto")
	private String foto;

	// Fin cambios

	@Column(name = "localidad")
	private String localidad;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "kilometro")
	private BigDecimal kilometro;
	@JoinColumn(name = "fk_id_tar", referencedColumnName = "pk_id_tar")
	@ManyToOne(optional = false)
	private Tareas fkIdTar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdInfotar")
	private List<Imagenes> imagenesList;

	public InformesTareas() {
		imagenesList = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

	public void addlinea(Imagenes img) {
		img.setFkIdInfotar(this);
		imagenesList.add(img);

	}

	public InformesTareas(Integer pkIdInfotar) {
		this.pkIdInfotar = pkIdInfotar;
	}

	public InformesTareas(Integer pkIdInfotar, int cantEqui, int cantExt) {
		this.pkIdInfotar = pkIdInfotar;
		this.cantEqui = cantEqui;
		this.cantExt = cantExt;
	}

	public Integer getPkIdInfotar() {
		return pkIdInfotar;
	}

	public void setPkIdInfotar(Integer pkIdInfotar) {
		this.pkIdInfotar = pkIdInfotar;
	}

	public int getCantEqui() {
		return cantEqui;
	}

	public void setCantEqui(int cantEqui) {
		this.cantEqui = cantEqui;
	}

	public int getCantExt() {
		return cantExt;
	}

	public void setCantExt(int cantExt) {
		this.cantExt = cantExt;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public BigDecimal getKilometro() {
		return kilometro;
	}

	public void setKilometro(BigDecimal kilometro) {
		this.kilometro = kilometro;
	}

	public Tareas getFkIdTar() {
		return fkIdTar;
	}

	public void setFkIdTar(Tareas fkIdTar) {
		this.fkIdTar = fkIdTar;
	}

	@XmlTransient
	public List<Imagenes> getImagenesList() {
		return imagenesList;
	}

	public void setImagenesList(List<Imagenes> imagenesList) {
		this.imagenesList = imagenesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkIdInfotar != null ? pkIdInfotar.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof InformesTareas)) {
			return false;
		}
		InformesTareas other = (InformesTareas) object;
		if ((this.pkIdInfotar == null && other.pkIdInfotar != null)
				|| (this.pkIdInfotar != null && !this.pkIdInfotar.equals(other.pkIdInfotar))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.InformesTareas[ pkIdInfotar=" + pkIdInfotar + " ]";
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNroDir() {
		return nroDir;
	}

	public void setNroDir(int nroDir) {
		this.nroDir = nroDir;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Localidades getFkIdLoc() {
		return fkIdLoc;
	}

	public void setFkIdLoc(Localidades fkIdLoc) {
		this.fkIdLoc = fkIdLoc;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
