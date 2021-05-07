package br.com.aws.sgt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="t_pessoa")
@Entity
public class Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_PESSOA")
	private Long idPessoa;
	
	@Column(name="CPF", unique=true, nullable=false)
	private String cpf;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ENDERECO_COMERCIAL")
	private String enderecoComercial;
	
	@Column(name="ENDERECO_RESIDENCIAL", nullable=false)
	private String enderecoResidencial;
	
	@Column(name="NASCIMENTO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="PROFISSAO")
	private String profissao;
	
	@Column(name="TELEFONE_CELULAR1")
	private String telefoneCelular1;
	
	@Column(name="TELEFONE_CELULAR2")
	private String telefoneCelular2;
	
	/*
	 * @Column(name="TELEFONE_COMERCIAL") private String telefoneComercial;
	 */
	
	@Column(name="TELEFONE_RESIDENCIA")
	private String telefoneResidencial;
	
	
	public Pessoa() {
		
	}

	public Pessoa(String cpf, String email, String enderecoComercial, String enderecoResidencial, Date nascimento,
			String nome, String profissao, String telefoneCelular1, String telefoneCelular2,
			String telefoneResidencial) {
		this.cpf = cpf;
		this.email = email;
		this.enderecoComercial = enderecoComercial;
		this.enderecoResidencial = enderecoResidencial;
		this.nascimento = nascimento;
		this.nome = nome;
		this.profissao = profissao;
		this.telefoneCelular1 = telefoneCelular1;
		this.telefoneCelular2 = telefoneCelular2;
		this.telefoneResidencial = telefoneResidencial;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderecoComercial() {
		return enderecoComercial;
	}

	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}

	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getTelefoneCelular1() {
		return telefoneCelular1;
	}

	public void setTelefoneCelular1(String telefoneCelular1) {
		this.telefoneCelular1 = telefoneCelular1;
	}

	public String getTelefoneCelular2() {
		return telefoneCelular2;
	}

	public void setTelefoneCelular2(String telefoneCelular2) {
		this.telefoneCelular2 = telefoneCelular2;
	}

	/*
	 * public String getTelefoneComercial() { return telefoneComercial; }
	 * 
	 * public void setTelefoneComercial(String telefoneComercial) {
	 * this.telefoneComercial = telefoneComercial; }
	 */

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	
	@Transient
	public int idade() {
		LocalDate nascimento = this.nascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate hoje = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return Period.between(nascimento, hoje).getYears();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, idPessoa, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(idPessoa, other.idPessoa) && Objects.equals(nome, other.nome);
	}

}
