package Main;

import java.util.ArrayList;

public class Vertice {
	 private String nome;
	 private  ArrayList<Aresta> arestas;
	 public Boolean visitado = false;
	 
	 public Vertice(String nome) {
		 this.nome = nome;
		 this.arestas = null;
		 this.arestas = new ArrayList<Aresta>();
	 }
	 
	 /**
	  * Adicionar uma aresta ao array de adjacencia(arestas) 
	  * @param destino
	  * @param peso
	  */
	 public void addAresta(Vertice destino,float peso) {
		 Aresta a = new Aresta(destino, peso);
		 this.arestas.add(a);
	 }
	 
	 /**
	  * Retorna peso da aresta para um determinado vertice
	  * caso não exista uma aresta que conecte os vertices retorna -1
	  * @param Destino
	  * @return float peso
	  */
	 public float getPeso(Vertice destino) {
		 Aresta a = this.getAresta(destino);
		 if(a != null)
			 return a.peso;
		 else
			 return (-1);
	 }
	 
	 /**
	  * Busca determinado vertice no array da adjacencia
	  * @param destino
	  * @return Aresta ou null
	  */
	 private Aresta getAresta(Vertice destino) {
		 for (Aresta aresta: this.arestas) {
			 if(aresta.destino == destino)
				 return aresta;
		 }
		 return null;
	 }
	 
	 public ArrayList<Vertice> getVerticesAdjacentes(){
		 ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		 for(Aresta aresta : this.arestas) {
			 vertices.add(aresta.destino);
		 }
		 return vertices;
	 }
	 
	 public void printAdjacencia() {
		 System.out.print(this.nome + " : ");
		 if(this.arestas == null)
			 return ;
		 for(Aresta aresta : this.arestas) {
			 System.out.print(aresta.destino.nome + " -> ");
		 }
		 System.out.println('*');
	 }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	 
	 
}
