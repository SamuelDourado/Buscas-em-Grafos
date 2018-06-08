package Main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		String filePath = "dados de entrada/dados6.txt";
		try {
			grafo.formarGrafo(grafo.readFile(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		grafo.pringListaDeAdjacencia();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Origem do Grafo:");
		String origem = scanner.nextLine();
		
		System.out.println(' ');
		System.out.println("DFS:");
		System.out.println(String.join("," ,grafo.DFS(origem, "100")));
		
		grafo.resetCaminho();
		System.out.println(' ');
		System.out.println("BFS:");
		System.out.println(String.join("," ,grafo.BFS(origem, "100")));
		
		System.out.println(' ');
		System.out.println("Dijkstra:");
		for(Dado dado : grafo.Dijkstra(origem)) {
			System.out.println(dado.origem +  ',' + dado.destino + ',' + dado.peso);
		}
		
		System.out.println(' ');
		System.out.println("Prim:");
		for(Dado dado : grafo.Prim(origem)) {
			System.out.println(dado.origem +  ',' + dado.destino + ',' + dado.peso);
		}
		
		grafo.resetCaminho();
		System.out.println(' ');
		System.out.println("KRUSKAL:");
		for(Dado dado : grafo.kruskal()) {
			System.out.println(dado.origem +  ',' + dado.destino + ',' + dado.peso);
		}
		
	}

}
