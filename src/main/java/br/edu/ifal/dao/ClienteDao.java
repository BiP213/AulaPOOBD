package br.edu.ifal.dao;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public void save(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (CPF, NOME, ENDERECO, TELEFONE) VALUES (?,?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, cliente.getCpf());
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getEndereco());
            pst.setString(4, cliente.getTelefone());

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM CLIENTE;";

        List<Cliente> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String nome = rs.getString("NOME");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");

                Cliente cliente = new Cliente(cpf, nome, endereco, telefone);
                lista.add(cliente);
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

    public Cliente findByCpf(String cpfClienteVenda) {
        String sql = "SELECT * FROM CLIENTE WHERE CPF = ?;";
        Cliente cliente = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, cpfClienteVenda);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("NOME");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");

                cliente = new Cliente(cpfClienteVenda, nome, endereco, telefone);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }
}
