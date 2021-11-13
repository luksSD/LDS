package br.fai.lds.model;

import java.sql.Timestamp;

public class Usuario extends BasePojo {

	private String nomeUsuario;
	private String senha;
	private String nomeCompleto;
	private String email;
	private String tipo;
	private boolean estaAtivo;
	private Timestamp dataNascimento;
	private Timestamp ultimoAcesso;
	private Timestamp criadoEm;
	private byte[] avatar;
	private String token;
	
	

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(final String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(final String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public boolean getEstaAtivo() {
		return estaAtivo;
	}

	public void setEstaAtivo(final boolean estaAtivo) {
		this.estaAtivo = estaAtivo;
	}

	public Timestamp getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(final Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Timestamp getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(final Timestamp ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Timestamp getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(final Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(final byte[] avatar) {
		this.avatar = avatar;
	}

}
