package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Dado{
	String origem;
	String destino;
	float peso;
	public Dado( String origem, String destino, float peso) {
		this.destino = destino;
		this.origem = origem;
		this.peso = peso;
	}
}

public class Grafo {
	 private  ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	 
	 /**
	  * Busca em profundidade 
	  * @return ArrayList<String> com o nome dos vetores visitados
	  */
	 public ArrayList<String> DFS(String nomeVerticeOrigem, String verticeAlvo){
		Vertice origem = this.getVerticeByNome(nomeVerticeOrigem);
		if(origem == null)
			return null;
		
		return this.DFS(origem, verticeAlvo);
		
	 }
	 
	 public ArrayList<String> DFS(Vertice origem, String verticeAlvo){
		ArrayList<String> caminho = new ArrayList<String>();
		caminho.add(origem.getNome());
		origem.visitado = true;
		if(origem.getNome().equals(verticeAlvo)) {
			return caminho;
		}
		
		for(Vertice vertice : this.vertices) {
			if(!vertice.visitado)
				caminho.addAll( this.DFS(vertice, verticeAlvo) );
		}
			
		return caminho;
		 
	 }

	 
	 public void pringListaDeAdjacencia() {
		 for(Vertice vertice : this.vertices ) {
			 vertice.printAdjacencia();
		 }
	 }
	 
	 /**
	  * Método que forma o grafo atraves do array de dados(formado pela método readFile() )
	  * @param dados Dado
	  */
	 public void formarGrafo(ArrayList<Dado> dados) {
		 for(Dado dado : dados) {
			 Vertice verticeOrigem = this.getVerticeByNome(dado.origem);
			 Vertice verticeDestino = this.getVerticeByNome(dado.destino);
			 if(verticeOrigem == null) 
				 vertices.add(new Vertice(dado.origem));
			 if(verticeDestino == null) 
				 vertices.add(new Vertice(dado.destino));
			 
			 verticeOrigem = this.getVerticeByNome(dado.origem);
			 verticeDestino = this.getVerticeByNome(dado.destino);
			
			 verticeOrigem.addAresta(verticeDestino, dado.peso);	 
		 }
	 }
	 
	 /**
	  * Este método lê o arquivo de entrada para um Arraylist de Dado
	  * O arquivo de entrada deve obedecer ao seguinte formato: origem,destino,peso
	  * @param String filePath caminho para arquivo de entrada
	  * @return ArrayList<Dado>
	  * @throws IOException
	  */
	 protected ArrayList<Dado> readFile(String filePath) throws IOException{
		 File file = new File(filePath);
		 BufferedReader buffer = null;
		 buffer = new BufferedReader(new FileReader(file));
		 
		 ArrayList<Dado> dados = new ArrayList<Dado>();
		 String line = null;
		 while ((line = buffer.readLine()) != null) {
			 String[] splited = line.split(",");
			 Dado novoDado = new Dado(splited[0], splited[1], Float.parseFloat(splited[2]));
			 dados.add(novoDado);
		 }
		 return dados;
	 }
	 
	 /**
	  * Busca Grafo::vertices o vertice pelo atributo nome
	  * @param nome String que representa o nome do vertice
	  * @return Vertice
	  */
	 public Vertice getVerticeByNome(String nome){
		 for(Vertice vertice: this.vertices) {
			 if(vertice.getNome().equals(nome))
				 return vertice;
		 }
		 return null;
	 }


}
