package br.fai.lds.model;

public class Animal extends BasePojo {

	private String nome;
	private String descricao;
	private Long idade;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(final Long idade) {
		this.idade = idade;
	}

}
