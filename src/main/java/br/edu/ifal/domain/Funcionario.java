package br.edu.ifal.domain;

public class Funcionario {
  private String cpf;
  private String nome;
  private String endereco;
  private String telefone;

  // Construtor com todos os atributos
  public Funcionario(String cpf, String nome, String endereco, String telefone) {
    this.cpf = cpf;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
  }

  // Somente cpf
  public Funcionario(String cpf) { this.cpf = cpf; }

  // Getters
  public String getCpf() { return cpf; }

  public String getNome() { return nome; }

  public String getEndereco() { return endereco; }

  public String getTelefone() { return telefone; }

  // Setters
  public void setCpf(String cpf) { this.cpf = cpf; }

  public void setNome(String nome) { this.nome = nome; }

  public void setEndereco(String endereco) { this.endereco = endereco; }

  public void setTelefone(String telefone) { this.telefone = telefone; }

  // Sobrescreve o método toString para uma melhor visualização dos objetos
  @Override
  public String toString() {
    return "Funcionario{" +
            "cpf='" + cpf + '\'' +
            ", nome='" + nome + '\'' +
            ", endereco='" + endereco + '\'' +
            ", telefone='" + telefone + '\'' +
            '}';
  }
}
