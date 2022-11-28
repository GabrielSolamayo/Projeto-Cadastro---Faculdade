package Projeto;

public class Aluno {

	private String nome, ra, rg;

	public Aluno(String nome, String ra, String rg) {
		this.nome = nome;
		this.ra = ra;
		this.rg = rg;
	}
	
	public Aluno(String nome, String rg) {
		this.nome = nome;
		this.rg = rg;
	}

	
	public String getNome() {
		return nome;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getRa() {
		return ra;
	}

	
	public void setRa(String ra) {
		this.ra = ra;
	}

	
	public String getRg() {
		return rg;
	}

	
	public void setRg(String rg) {
		this.rg = rg;
	}

	
	
}