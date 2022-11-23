package Projeto;

public class Professor {

	private String nome, rg, rgf;

	public Professor(String nome, String rg, String rgf) {
		this.nome = nome;
		this.rg = rg;
		this.rgf = rgf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRgf() {
		return rgf;
	}

	public void setRgf(String rgf) {
		this.rgf = rgf;
	}

	
	
}
