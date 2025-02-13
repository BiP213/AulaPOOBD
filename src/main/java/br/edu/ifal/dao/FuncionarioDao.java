package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
  public void save(Funcionario funcionario) {
    String sql = "INSERT INTO FUNCIONARIO VALUES (?,?,?,?);";

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
}
