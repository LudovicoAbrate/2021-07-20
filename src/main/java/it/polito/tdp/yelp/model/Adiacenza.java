package it.polito.tdp.yelp.model;

public class Adiacenza {

	
	String u1;
	String u2;
	int peso;
	
	
	
	public Adiacenza(String u1, String u2, int peso) {
		super();
		this.u1 = u1;
		this.u2 = u2;
		this.peso = peso;
	}
	public String getU1() {
		return u1;
	}
	public void setU1(String u1) {
		this.u1 = u1;
	}
	public String getU2() {
		return u2;
	}
	public void setU2(String u2) {
		this.u2 = u2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
}