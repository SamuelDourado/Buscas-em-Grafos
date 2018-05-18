package Main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		String filePath = "dados de entrada/dados3.txt";
		try {
			grafo.formarGrafo(grafo.readFile(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grafo.pringListaDeAdjacencia();
		
		System.out.println(' ');
		System.out.println("DFS:");
		System.out.println(String.join("," ,grafo.DFS("1", "100")));
		
		grafo.resetCaminho();
		System.out.println(' ');
		System.out.println("BFS:");
		System.out.println(String.join("," ,grafo.BFS("1", "100")));
		
		System.out.println(' ');
		System.out.println("KRUSKAL:");
		for(Dado dado : grafo.kruskal()) {
			System.out.println(dado.origem +  ',' + dado.destino + ',' + dado.peso);
		}
		
	}

}
