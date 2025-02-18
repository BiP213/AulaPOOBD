package br.edu.ifal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.dao.FuncionarioDao;
import br.edu.ifal.dao.ProdutoDao;
import br.edu.ifal.dao.PedidoDao;
import br.edu.ifal.dao.ItemPedidoDao;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.Funcionario;
import br.edu.ifal.domain.Produto;
import br.edu.ifal.domain.Pedido;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.exceptions.CpfInvalidoException;
import br.edu.ifal.exceptions.ValorInvalidoException;

public class Main {

    // Variáveis de classe, acesso global
    private static Scanner scan = new Scanner(System.in);
    private static ProdutoDao produtoDao = new ProdutoDao();
    private static ClienteDao clienteDao = new ClienteDao();
    private static FuncionarioDao funcionarioDao = new FuncionarioDao();
    private static PedidoDao pedidoDao = new PedidoDao();
    private static ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

    public static void main(String[] args) {
        while(true) {
            exibirMenu();
            int opc = scan.nextInt();
            scan.nextLine();

            switch(opc) {
                case 1: cadastrarProduto(); break;
                case 2: cadastrarCliente(); break;
                case 3: buscarProduto(); break;
                case 4: listarProdutos(); break;
                case 5: efetuarVenda(); break;
                case 6: listarVendas(); break;
                case 0:
                    System.out.println("\nFinalizando o programa...");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }
        }
    }

    public static void validarCpf(String cpf) throws CpfInvalidoException {
        if (cpf.length() != 11) {
            throw new CpfInvalidoException("\nCPF inválido: Deve conter exatamente 11 caracteres.");
        }
    }

    private static void exibirMenu() {
        System.out.println("\n===================== MENU =====================");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Cadastrar cliente");
        System.out.println("3. Buscar produto (por ID)");
        System.out.println("4. Listar todos os produtos disponíveis");
        System.out.println("5. Efetuar venda");
        System.out.println("6. Listar vendas realizadas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        try {
            System.out.print("\nNome do produto: ");
            String nomeProduto = scan.nextLine().trim().toUpperCase();

            System.out.print("Valor unitário: ");
            double valorUnit = scan.nextDouble();
            if (valorUnit <= 0) {
                throw new ValorInvalidoException("\nO valor unitário deve ser maior que zero.");
            }

            System.out.print("Quantidade em estoque: ");
            int quantidade = scan.nextInt();
            scan.nextLine();
            if (quantidade < 0) {
                throw new ValorInvalidoException("\nQuantidade em estoque deve ser maior ou igual a zero.");
            }

            Produto produto = new Produto(nomeProduto, valorUnit, quantidade);
            produtoDao.save(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("\nTipo de dado inválido!");
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao cadastrar o produto: " + e.getMessage());
        }
    }

    private static void cadastrarCliente() {
        System.out.print("\nCPF do cliente: ");
        String cpfCliente = scan.nextLine().trim();

        try {
            validarCpf(cpfCliente);

            System.out.print("Nome: ");
            String nomeCliente = scan.nextLine().trim().toUpperCase();

            System.out.print("Endereço: ");
            String enderecoCliente = scan.nextLine().trim().toUpperCase();

            System.out.print("Telefone: ");
            String telefoneCliente = scan.nextLine().trim();

            Cliente novoCliente = new Cliente(cpfCliente, nomeCliente, enderecoCliente, telefoneCliente);
            clienteDao.save(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao cadastrar o cliente: " + e.getMessage());
        }
    }

    private static void buscarProduto() {
        try {
            System.out.print("\nID do produto: ");
            int idProduto = scan.nextInt();
            scan.nextLine();

            Produto produto = produtoDao.findById(idProduto);
            if (produto != null) {
                System.out.println("\nProduto encontrado:");
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Valor unitário: " + produto.getValorUnit());
                System.out.println("Quantidade em estoque: " + produto.getQuantidade());

            } else {
                System.out.println("\nProduto não existe.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nTipo de dado inválido! Por favor, insira um número inteiro para o ID do produto.");
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao buscar o produto: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        try {
            List<Produto> listaProdutos = produtoDao.findAll();

            System.out.println("\n=============== Lista de Produtos Disponíveis ===============");
            System.out.println();
            for (Produto prod : listaProdutos) {
                System.out.println("ID: " + prod.getId() +
                    " | Nome: " + prod.getNome() +
                    " | Valor unitário: R$" + prod.getValorUnit() +
                    " | Quantidade em estoque: " + prod.getQuantidade());
            }
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao listar os produtos: " + e.getMessage());
        }
    }

    private static void efetuarVenda() {
        try {
            System.out.print("\nCPF do cliente: ");
            String cpfClienteVenda = scan.nextLine().trim();
            validarCpf(cpfClienteVenda);

            Cliente clienteVenda = clienteDao.findByCpf(cpfClienteVenda);
            if (clienteVenda == null) {
                System.out.println("\nCliente não encontrado. Tente novamente.");
                return;
            }

            System.out.print("CPF do vendedor: ");
            String cpfFuncionarioVenda = scan.nextLine().trim();
            validarCpf(cpfFuncionarioVenda);

            Funcionario funcionarioVenda = funcionarioDao.findByCpf(cpfFuncionarioVenda);
            if (funcionarioVenda == null) {
                System.out.println("\nFuncionario não encontrado. Tente novamente.");
                return;
            }

            double valorTotal = 0;

            List<ItemPedido> itens = new ArrayList<>();

            while (true) {
                System.out.print("\nDigite o ID do produto ou 0 para finalizar: ");
                int prodId = scan.nextInt();
                if (prodId < 0) {
                    throw new ValorInvalidoException("\nO ID do produto deve ser maior que zero.");
                }
                if (prodId == 0) break;

                System.out.print("Digite a quantidade desejada: ");
                int qtd = scan.nextInt();
                scan.nextLine();
                if (qtd <= 0) {
                  throw new ValorInvalidoException("\nQuantidade deve ser maior que zero.");
                }

                Produto prod = produtoDao.findById(prodId);
                if (prod == null) {
                    System.out.println("Produto não encontrado. Tente novamente.");
                    continue;
                }
                if (qtd > prod.getQuantidade()) {
                    System.out.println("Estoque insuficiente. Produto: " + prod.getNome() +
                        ". Quantidade disponível: " + prod.getQuantidade());
                    continue;
                }

                valorTotal += qtd * prod.getValorUnit();

                ItemPedido item = new ItemPedido(0, prodId, qtd, prod.getValorUnit());
                itens.add(item);
            }

            if (itens.isEmpty()) {
                System.out.println("\nNenhum item foi adicionado ao pedido. Venda não efetuada.");
                return;
            }

            Pedido pedido = new Pedido(clienteVenda, funcionarioVenda, valorTotal);
            int pedidoId = pedidoDao.save(pedido);

            for (ItemPedido item : itens) {
                item.setPedidoId(pedidoId);
                itemPedidoDao.save(item);

                Produto prod = produtoDao.findById(item.getProdutoId());

                int novaQtd = prod.getQuantidade() - item.getQuantidade();

                produtoDao.updateQuantity(prod.getId(), novaQtd);
            }

            System.out.println("\nVenda efetuada com sucesso. ID do pedido: " + pedidoId);
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao efetuar a venda: " + e.getMessage());
        }

    }

    private static void listarVendas() {
        try {
            List<Pedido> listaPedidos = pedidoDao.findAll();
            System.out.println("\n=============== Lista de Pedidos Efetuados ===============");
            for (Pedido ped : listaPedidos) {
                System.out.println("Pedido ID: " + ped.getId() +
                    " | Cliente (CPF): " + ped.getCliente().getCpf() +
                    " | Vendedor (CPF): " + ped.getFuncionario().getCpf() +
                    " | Valor Total: R$" + ped.getValorTotal());
            }
        } catch (Exception e) {
            System.out.println("\nOcorreu um erro ao listar os pedidos: " + e.getMessage());
        }
    }
}
