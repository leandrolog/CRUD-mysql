package Tabela;

import conection.conexao;
import estoque.Produtos;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class produtos {
    PreparedStatement st;
    ResultSet rs;
    private conexao conexao;
    private Connection conn;
    public produtos() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Produtos produto) {
        String sql = "INSERT INTO produtos(produto, preço, quantidade, categoria) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getProduto());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.execute();
            System.out.println("Produto Inserido.  ");
            conn.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Erro ao inserir" + e.getMessage());
        }
    }

    public Produtos consult(int id) {

        try {
            Produtos consultaId = new Produtos();
            String sql = "SELECT * FROM produtos WHERE id =?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                consultaId.setId(rs.getInt("id"));
                consultaId.setProduto(rs.getString("produto"));
                consultaId.setPreco(rs.getDouble("preço"));
                consultaId.setQuantidade(rs.getInt("quantidade"));
                consultaId.setCategoria(rs.getString("categoria"));
                return consultaId;
            }

            else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Deu Erro " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro" + e.getMessage());
            }
        }

        return null;
    }

    public List<Produtos> consultarTodos() {
        List<Produtos> consultarPrdutos = new ArrayList<Produtos>();
        try {
            String sql = "SELECT * FROM produtos";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos produtos = new Produtos();
                produtos.setId(rs.getInt("id"));
                produtos.setProduto(rs.getString("produto"));
                produtos.setPreco(rs.getDouble("preço"));
                produtos.setQuantidade(rs.getInt("quantidade"));
                produtos.setCategoria(rs.getString("categoria"));

                consultarPrdutos.add(produtos);
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro" + e.getMessage());
            ;
        }
        return consultarPrdutos;
    }


    public void deletarProduto(Produtos produtos) {

        try {
            String sql = "DELETE  FROM produtos WHERE id = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, produtos.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluido:");
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "excluir " + e.getMessage());

        }
    }
    public void alterar(Produtos produtos){

        try{
            String sql = "UPDATE produtos SET produto = ?, preço = ?, quantidade = ?, categoria = ? where id = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, produtos.getProduto());
            stmt.setDouble(2, produtos.getPreco());
            stmt.setInt(3, produtos.getQuantidade());
            stmt.setString(4, produtos.getCategoria());
            stmt.setInt(5, produtos.getId());

            JOptionPane.showMessageDialog(null, "Produto alteradO! ");
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao Alterar "+ e.getMessage());
        }
    }
}


