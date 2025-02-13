package br.edu.ifal.domain;

public class ItemPedido {
  private int id;
  private int pedidoId;
  private int produtoId;
  private int quantidade;
  private double valor;

  // Construtor completo
  public ItemPedido(int id, int pedidoId, int produtoId, int quantidade, double valor) {
    this.id = id;
    this.pedidoId = pedidoId;
    this.produtoId = produtoId;
    this.quantidade = quantidade;
    this.valor = valor;
  }

  // Construtor id automático
  public ItemPedido(int pedidoId, int produtoId, int quantidade, double valor) {
    this.pedidoId = pedidoId;
    this.produtoId = produtoId;
    this.quantidade = quantidade;
    this.valor = valor;
  }

  // Getters
  public int getId() { return id; }
  public int getPedidoId() { return pedidoId; }
  public int getProdutoId() { return produtoId; }
  public int getQuantidade() { return quantidade; }
  public double getValor() { return valor; }

  // Setters
  public void setId(int id) { this.id = id; }
  public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }
  public void setProdutoId(int produtoId) { this.produtoId = produtoId; }
  public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
  public void setValor(double valor) { this.valor = valor; }

  @Override
  public String toString() {
    return "ItemPedido{" +
            "id='" + id + '\'' +
            ", pedidoId='" + pedidoId + '\'' +
            ", produtoId='" + produtoId + '\'' +
            ", quantidade='" + quantidade + '\'' +
            ", valor='" + valor + '\'' +
            '}';
  }
}
