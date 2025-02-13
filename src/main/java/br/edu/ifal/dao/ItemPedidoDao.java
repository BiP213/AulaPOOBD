package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemPedidoDao {

  public void save(ItemPedido itemPedido) {
    String sql = "INSERT INTO ITEM_PEDIDO VALUES (?,?,?,?);";

    try {
      Connection connection = ConnectionHelper.getConnection();
      PreparedStatement pst = connection.prepareStatement(sql);

      pst.setInt(1, itemPedido.getPedidoId());
      pst.setInt(2, itemPedido.getProdutoId());
      pst.setInt(3, itemPedido.getQuantidade());
      pst.setDouble(4, itemPedido.getValor());

      pst.execute();

      pst.close();
      connection.close();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
