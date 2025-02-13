package br.edu.ifal.domain;

public class Pedido {
  private int id;
  private Cliente cliente;
  private Funcionario funcionario;
  private double valorTotal;

  // Construtor completo
  public Pedido(int id, Cliente cliente, Funcionario funcionario, double valorTotal) {
    this.id = id;
    this.cliente = cliente;
    this.funcionario = funcionario;
    this.valorTotal = valorTotal;
  }

  // Construtor sem id, id automático
  public Pedido(Cliente cliente, Funcionario funcionario, double valorTotal) {
    this.cliente = cliente;
    this.funcionario = funcionario;
    this.valorTotal = valorTotal;
  }

  // Getters
  public int getId() { return id; }
  public Cliente getCliente() { return cliente; }
  public Funcionario getFuncionario() { return funcionario; }
  public double getValorTotal() { return valorTotal; }

  // Setters
  public void setId(int id) { this.id = id; }
  public void setCliente(Cliente cliente) { this.cliente = cliente; }
  public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
  public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

  @Override
  public String toString() {
    return "Pedido{" +
        "id='" + id + '\'' +
        ", cliente='" + cliente + '\'' +
        ", funcionario='" + funcionario + '\'' +
        ", valorTotal='" + valorTotal + '\'' +
        '}';
  }
}
