package it.polito.tdp.yelp.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;


import it.polito.tdp.yelp.db.YelpDao;

public class Model {

	YelpDao dao;
	Graph<Vertici,DefaultWeightedEdge> grafo;
	List<Vertici> listaVertici ;
	Map<String,Vertici> idMap;
	
	public Model() {
		this.dao = new YelpDao();
		this.listaVertici = new ArrayList<>();
		this.idMap= new HashMap<>();
	}
	
	public void creaGrafo(Year anno,Integer rece) {
		
		this.dao = new YelpDao();
		this.listaVertici = new ArrayList<>();
		this.idMap= new HashMap<>();
		
		
		listaVertici.clear();
		for(Vertici v: dao.getVertici(rece)) {
			idMap.put(v.getId(), v);
			listaVertici.add(v);
		}
		
		this.grafo = new SimpleWeightedGraph<Vertici,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, listaVertici);
		
		for (Adiacenza a: dao.getAdiacenze(anno,rece)) {
			
				if(idMap.containsKey(a.u1) && idMap.containsKey(a.u2))
			Graphs.addEdgeWithVertices(this.grafo, idMap.get(a.u1),idMap.get(a.u2),a.getPeso());
		}
		
		
	}
	
	public Set<Vertici> VerticiGrafo(){
		return this.grafo.vertexSet();
 	}
	public Set<DefaultWeightedEdge> ArchiGrafo(Vertici utente){
		return this.grafo.edgeSet();
 	}

	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public  String UtentiConMaggiorGradoDiSimilarità(Vertici utente) {
	
		
		
		
		
		List<Vertici> utenti= new ArrayList<Vertici>();
 		double grado=0;
 		String s ="";
 		for(DefaultWeightedEdge a:grafo.edgesOf(utente))  // prendo archi di quel vertice 
 		{
 			if(grafo.getEdgeWeight(a)>grado)   // se peso arco è maggiore del mio massimo che avevo
 			{
 				grado=grafo.getEdgeWeight(a); // me lo salvo
 				utenti= new ArrayList<Vertici>(); // nuova lista
 				
 				if(grafo.getEdgeTarget(a).equals(utente))  // essendo un grafo non orientato
 				{
 					utenti.add(grafo.getEdgeSource(a)); //prendo vertice da cui è partito
 				}
 				else 
 				{
 					utenti.add(grafo.getEdgeTarget(a)); // sennò prendo quello fi arrivio
 				}
 			}
 			else if(grafo.getEdgeWeight(a)==grado) 
 			{
 				if(grafo.getEdgeTarget(a).equals(utente)) 
 				{
 					utenti.add(grafo.getEdgeSource(a));
 				}
 				else 
 				{
 					utenti.add(grafo.getEdgeTarget(a));
 				}
 			}
 		}
 			
 		int peso1 = (int) grado;
 		for (Vertici v: utenti) {
 			s+= v.nome+ "("+ v.id +")     " + peso1+ "\n";
 		}
		
		
	
		return s;
	}


	
	
	
}
