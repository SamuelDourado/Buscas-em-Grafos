package Main;

public class Aresta {
	private int id;
	public Vertice destino;
	public float peso = 0;
	
	public Aresta(Vertice destino, float peso) {
		this.destino = destino;
		this.peso = peso;
	}
}
