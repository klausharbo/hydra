package hydra.ext.tinkerpop.v3;

/**
 * A graph; a self-contained collection of vertices and edges
 */
public class Graph<V, E, P> {
  public final java.util.Set<hydra.ext.tinkerpop.v3.Vertex<V, P>> vertices;
  
  public final java.util.Set<hydra.ext.tinkerpop.v3.Edge<V, E, P>> edges;
  
  public Graph (java.util.Set<hydra.ext.tinkerpop.v3.Vertex<V, P>> vertices, java.util.Set<hydra.ext.tinkerpop.v3.Edge<V, E, P>> edges) {
    this.vertices = vertices;
    this.edges = edges;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Graph)) {
      return false;
    }
    Graph o = (Graph) (other);
    return vertices.equals(o.vertices) && edges.equals(o.edges);
  }
  
  @Override
  public int hashCode() {
    return 2 * vertices.hashCode() + 3 * edges.hashCode();
  }
  
  public Graph withVertices(java.util.Set<hydra.ext.tinkerpop.v3.Vertex<V, P>> vertices) {
    return new Graph(vertices, edges);
  }
  
  public Graph withEdges(java.util.Set<hydra.ext.tinkerpop.v3.Edge<V, E, P>> edges) {
    return new Graph(vertices, edges);
  }
}