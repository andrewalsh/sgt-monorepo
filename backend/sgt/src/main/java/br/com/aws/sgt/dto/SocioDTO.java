package br.com.aws.sgt.dto;

public class SocioDTO {

	private String nome;
	private String cpf;
	
	public SocioDTO() {}

	public SocioDTO(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
}