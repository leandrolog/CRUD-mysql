package Application;

import Tabela.produtos;

import conection.conexao;
import estoque.Produtos;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws SQLException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        conexao c = new conexao();
        c.getConexao();

        System.out.println("c para consulta, i para inserir, e para consultar todos, d para deletar item, u para edutar item: ");
        char ch = sc.next().charAt(0);
        sc.nextLine();


        if (ch == 'c') {
            System.out.println("Digite o id do produto para consulta: ");
            int id = sc.nextInt();
            sc.nextLine();
            produtos prod = new produtos();
            Produtos consultaId = prod.consult(id);

            System.out.println("Produto: " + consultaId.getProduto());
            System.out.println("Preço: " + consultaId.getPreco());
            System.out.println("Quantidade: " + consultaId.getQuantidade());
            System.out.println("Categoria: " + consultaId.getCategoria());
        }
        if (ch == 'i') {
            System.out.println("Insira o nome do Produto: ");
            String produto1 = sc.nextLine();
            System.out.println("Preço: ");
            double preco = sc.nextDouble();
            System.out.println("Quantidade em estoque: ");
            int quantidade = sc.nextInt();
            sc.nextLine();
            System.out.println("Categoria: ");
            String categoria = sc.nextLine();

            Produtos produto = new Produtos();

            produto.setProduto(produto1);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
            produto.setCategoria(categoria);

            produtos produto2 = new produtos();
            produto2.inserir(produto);
        }
        if (ch == 't') {
            produtos novaConsulta = new produtos();
            List<Produtos> consultarPrdutos = novaConsulta.consultarTodos();

            for (Produtos produtos : consultarPrdutos) {
                System.out.println("id: " + produtos.getId());
                System.out.println("Produto: " + produtos.getProduto());
                System.out.println("Preço: " + produtos.getPreco());
                System.out.println("Quantidade: " + produtos.getQuantidade());
                System.out.println("Categoria: " + produtos.getCategoria());
                System.out.println();

            }

        }

        if(ch == 'd') {
            System.out.println("Digite o id do Produto para deletar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Produtos produtos = new Produtos();
            produtos.setId(id);


            produtos produtos3 = new produtos();
            produtos3.deletarProduto(produtos);

        }

        if(ch == 'u') {
            System.out.println("Digite o id do produto para Editar");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Insira o nome do Produto : ");
            String produto2 = sc.nextLine();
            System.out.println("Preço: ");
            double preco2 = sc.nextDouble();
            System.out.println("Quantidade em estoque: ");
            int quantidade3 = sc.nextInt();
            sc.nextLine();
            System.out.println("Categoria: ");
            String categoria4 = sc.nextLine();

            Produtos produtos = new Produtos();

            produtos.setId(id);
            produtos.setProduto(produto2);
            produtos.setPreco(preco2);
            produtos.setQuantidade(quantidade3);
            produtos.setCategoria(categoria4);

            produtos editar = new produtos();
            editar.alterar(produtos);
        }
        sc.close();
    }
}
