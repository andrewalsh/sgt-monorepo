package br.com.aws.sgt.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="t_socio")
@Entity
public class Socio implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_SOCIO", nullable=false)
	private Long idSocio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENTRADA", nullable=false)
	private Date dataEntrada;
	
	@Column(name="TIPO_SOCIO", nullable=false)
	private String tipoSocio;
	
	@OneToOne
	@JoinColumn(name="id_pessoa",referencedColumnName="id_pessoa",nullable=false)
	private Pessoa pessoa;

	public Socio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Socio(Date dataEntrada, String tipoSocio, Pessoa pessoa) {
		super();
		this.dataEntrada = dataEntrada;
		this.tipoSocio = tipoSocio;
		this.pessoa = pessoa;
	}
	
	@Transient
	public List<String> getListaTipoSocios(){
		return Arrays.asList("COLABORADOR","EFETIVO","VOLUNTÁRIO","ALUNO");
	}

	public Long getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(Long idSocio) {
		this.idSocio = idSocio;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(String tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSocio, pessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(idSocio, other.idSocio) && Objects.equals(pessoa, other.pessoa);
	}

	@Override
	public String toString() {
		return "Socio [idSocio=" + idSocio + ", dataEntrada=" + dataEntrada + ", tipoSocio=" + tipoSocio + ", pessoa="
				+ pessoa + "]";
	}
	
}
