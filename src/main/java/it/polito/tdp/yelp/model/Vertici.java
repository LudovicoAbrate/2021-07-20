package it.polito.tdp.yelp.model;

public class Vertici {
	
	String id;
	String nome;
	int recensioni;
	
	
	
	public Vertici(String id, String nome, int recensioni) {
		super();
		this.id = id;
		this.nome = nome;
		this.recensioni = recensioni;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getRecensioni() {
		return recensioni;
	}



	public void setRecensioni(int recensioni) {
		this.recensioni = recensioni;
	}



	public String toString() {
		return   nome+ "("+ id+ ")\n";
		
	}
}