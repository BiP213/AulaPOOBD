package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Pedido;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {

  public int save(Pedido pedido) {
    String sql = "INSERT INTO PEDIDO VALUES (?,?,?);";
    int pedidoId = -1;

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

      pst.setString(1, pedido.getCliente().getCpf());
      pst.setString(2, pedido.getFuncionario().getCpf());
      pst.setDouble(3, pedido.getValorTotal());

      pst.executeUpdate();

      ResultSet rs = pst.getGeneratedKeys();
      if (rs.next()) {
        pedidoId = rs.getInt(1);
      }

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return pedidoId;
  }

  public List<Pedido> findAll() {
    String sql = "SELECT * FROM PEDIDO;";

    List<Pedido> lista = new ArrayList<>();

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        int id = rs.getInt("ID");
        String cpfCliente = rs.getString("CPF_CLIENTE_FK");
        String cpfFuncionario = rs.getString("CPF_FUNCIONARIO_FK");
        double valorTotal = rs.getDouble("VALORTOTAL");

        Cliente cliente = new Cliente(cpfCliente);
        Funcionario funcionario = new Funcionario(cpfFuncionario);
        Pedido pedido = new Pedido(id, cliente, funcionario, valorTotal);
        lista.add(pedido);
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
