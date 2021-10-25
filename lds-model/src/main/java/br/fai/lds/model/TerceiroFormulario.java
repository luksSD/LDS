package br.fai.lds.model;

public class TerceiroFormulario extends BasePojo {

	private String noticia;
	private Long quantidadePaginas;
	private String titulo;

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(final String noticia) {
		this.noticia = noticia;
	}

	public Long getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(final Long quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

}
