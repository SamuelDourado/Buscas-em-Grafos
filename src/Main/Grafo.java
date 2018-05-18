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
	 
	 /**
	  * Busca em Largura 
	  * @return ArrayList<String> com o nome dos vetores visitados
	  */
	 public ArrayList<String> BFS(String nomeVerticeOrigem, String verticeAlvo){
		 Vertice origem = this.getVerticeByNome(nomeVerticeOrigem);
		 if(origem == null)
			return null;
		
		ArrayList<Vertice> origens = new ArrayList<Vertice>();
		origens.add(origem);
		return this.BFS(origens, verticeAlvo);
	 }
	 
	 public ArrayList<String> BFS(ArrayList<Vertice> origens, String verticeAlvo){
		 ArrayList<String> caminho = new ArrayList<String>();
		 ArrayList<Vertice> novoNivel = new ArrayList<Vertice>();
		 
		 for(Vertice origem : origens) {
			 if(!origem.visitado) {
				 caminho.add(origem.getNome());
				 origem.visitado = true;
				 if(origem.getNome().equals(verticeAlvo))
					 return caminho;
				 else
					 novoNivel.addAll(origem.getVerticesAdjacentes());
			 }	 
		 }
		 if(!novoNivel.isEmpty())
			 caminho.addAll( this.BFS(novoNivel, verticeAlvo) );
		 return caminho;
	 }
	 
	 /**
	  * Retorna representação da Arvore geradora mínima do Kruskal
	  * @return ArrayList<Dado>
	  */
	 public ArrayList<Dado> kruskal() {
			ArrayList<Dado> mst= new ArrayList<Dado>();
			ArrayList<Vertice> verticesAux = (ArrayList<Vertice>) this.vertices.clone();
			Dado novo = this.getArestaMinima(verticesAux, mst);
			while( novo != null) {
				mst.add(novo);
				novo = this.getArestaMinima(verticesAux, mst);
			}
			return mst;
	 }
	 
	 /**
	  * Retorna Aresta com o menor peso no grafo
	  * Que não gera ciclo
	  * @param vertices ArrayList<Vertice>
	  * @param mst ArrayList<Dado> Que representa a árvore geradora mínima
	  * @return Dado 
	  */
	 private Dado getArestaMinima(ArrayList<Vertice> vertices, ArrayList<Dado> mst){
		Dado arestaMinima = null;
		for(Vertice vertice : vertices) {
			ArrayList<Aresta> arestaRemover = new ArrayList<Aresta>();
			for(Aresta aresta : vertice.getAresta()) {
				Dado novo = new Dado(vertice.getNome(), aresta.destino.getNome(), aresta.peso);
				if(this.isCycle(mst,novo))
					arestaRemover.add(aresta);
				else {
					if(arestaMinima == null || arestaMinima.peso > aresta.peso) 
						arestaMinima = novo;		
				}
			}
			vertice.getAresta().removeAll(arestaRemover);
		}
				
		return arestaMinima;
	 }
	 
	 /**
	  * Verifica se o vertice repesentado por Dado novo gerara ciclo na mst
	  * @param caminho Árvore geradora mínima
	  * @param novo Dado
	  * @return Boolean 
	  */
	 public Boolean isCycle(ArrayList<Dado> caminho, Dado novo) {
		 Boolean destinoAdd = false;
		 Boolean origemAdd = false;
		 int i = 0;
		 while(i < caminho.size() && !(destinoAdd && origemAdd)) {
			 if(novo.origem.equals(caminho.get(i).origem) || novo.origem.equals(caminho.get(i).destino))
				 origemAdd = true;
			 
			 if(novo.destino.equals(caminho.get(i).origem) || novo.destino.equals(caminho.get(i).destino))
				 destinoAdd = true;
			 i++;
		 }
		 
		 return origemAdd && destinoAdd;
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
	 
	 /**
	  * reseta campo visitado do ArrayList de Vertices do grafo
	  */
	 public void resetCaminho() {
		 for(Vertice vertice : this.vertices) {
			 vertice.visitado = false;
		 }
	 }


}
