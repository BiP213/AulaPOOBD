package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
  public void save(Produto produto) {
    String sql = "INSERT INTO PRODUTO VALUES (?,?,?);";

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      pst.setString(1, produto.getNome());
      pst.setDouble(2, produto.getValorUnit());
      pst.setInt(3, produto.getQuantidade());

      pst.execute();

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Produto findById(int id) {
    String sql = "SELECT * FROM PRODUTO WHERE ID = ?;";
    Produto produto = null;

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      pst.setInt(1, id);
      ResultSet rs = pst.executeQuery();
      if (rs.next()) {
        String nome = rs.getString("NOME");
        double valorUnit = rs.getDouble("VALORUNIT");
        int quantidade = rs.getInt("QUANTIDADE");

        produto = new Produto(id, nome, valorUnit, quantidade);
      }

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return produto;
  }

  public List<Produto> findAll() {
    String sql = "SELECT * FROM PRODUTO;";

    List<Produto> lista = new ArrayList<>();

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("ID");
        String nome = rs.getString("NOME");
        double valorUnit = rs.getDouble("VALORUNIT");
        int quantidade = rs.getInt("QUANTIDADE");

        Produto produto = new Produto(id, nome, valorUnit, quantidade);
        lista.add(produto);
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
