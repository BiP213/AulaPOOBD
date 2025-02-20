package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Funcionario;
import br.edu.ifal.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
  public void save(Funcionario funcionario) {
    String sql = "INSERT INTO FUNCIONARIO (CPF, NOME, ENDERECO, TELEFONE) VALUES (?,?,?,?);";

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      pst.setString(1, funcionario.getCpf());
      pst.setString(2, funcionario.getNome());
      pst.setString(3, funcionario.getEndereco());
      pst.setString(4, funcionario.getTelefone());

      pst.execute();
      pst.close();

      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Funcionario> findAll() {
    String sql = "SELECT * FROM FUNCIONARIO;";

    List<Funcionario> lista = new ArrayList<>();

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        String cpf = rs.getString("CPF");
        String nome = rs.getString("NOME");
        String endereco = rs.getString("ENDERECO");
        String telefone = rs.getString("TELEFONE");

        Funcionario funcionario = new Funcionario(cpf, nome, endereco, telefone);
        lista.add(funcionario);
      }

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return lista;
  }

  public Funcionario findByCpf(String cpfFuncionarioVenda) {
    String sql = "SELECT * FROM FUNCIONARIO WHERE CPF = ?;";
    Funcionario funcionario = null;

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      pst.setString(1, cpfFuncionarioVenda);
      ResultSet rs = pst.executeQuery();
      if (rs.next()) {
        String nome = rs.getString("NOME");
        String endereco = rs.getString("ENDERECO");
        String telefone = rs.getString("TELEFONE");

        funcionario = new Funcionario(cpfFuncionarioVenda, nome, endereco, telefone);
      }

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return funcionario;
  }
}
