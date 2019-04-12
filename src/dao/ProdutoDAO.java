package dao;

import Negocio.Produto;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author JOÃO VITOR
 */
public class ProdutoDAO {

    public void inserir(Produto produto){
        Connection conexao = new Conexao().getConnection();
        String sql = "INSERT INTO produtos (nomeProduto, descricao, quantidade, valorCompra, valorVenda, unidadeMedida)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, produto.getNomeProduto());
            pst.setString(2, produto.getDescricacao());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getValorCompra());
            pst.setDouble(5, produto.getValorVenda());
            pst.setString(6, produto.getUnidadeMedida());

            pst.execute();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ArrayList<Produto> listar() {

        Connection conexao = new Conexao().getConnection();
        String sql = "SELECT * FROM produtos";
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodProduto(rs.getInt("codProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricacao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValorCompra(rs.getDouble("valorCompra"));
                produto.setValorVenda(rs.getDouble("valorVenda"));
                produto.setUnidadeMedida(rs.getString("unidadeMedida"));
                produtos.add(produto);
            }
            pst.close();
            rs.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return produtos;
    }

    public Produto recuperarPorCodigo(int codigo) {
        Connection conexao = new Conexao().getConnection();
        String sql = "SELECT * FROM produtos WHERE codProduto = ?";
        Produto produto = new Produto();
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                produto.setCodProduto(rs.getInt("codProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setDescricacao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValorCompra(rs.getDouble("valorCompra"));
                produto.setValorVenda(rs.getDouble("valorVenda"));
                produto.setUnidadeMedida(rs.getString("unidadeMedida"));
            }
            pst.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return produto;
    }

    public void excluir(int codigo) {
        Connection conexao = new Conexao().getConnection();
        String sql = "DELETE FROM produtos WHERE codProduto = ?";

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void editar(Produto produto) {
        Connection conexao = new Conexao().getConnection();
        String sql = "UPDATE produtos SET nomeProduto=?, descricao=?, quantidade=?, valorCompra=?,"
                + " valorVenda=?, unidadeMedida=? WHERE codProduto=?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, produto.getNomeProduto());
            pst.setString(2, produto.getDescricacao());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getValorCompra());
            pst.setDouble(5, produto.getValorVenda());
            pst.setString(6, produto.getUnidadeMedida());
            pst.setInt(7, produto.getCodProduto());
            pst.executeLargeUpdate();

            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    public ArrayList<Produto> pesquisaInteligenteProduto(String texto){
        Connection conexao = new Conexao().getConnection();
        String sql = "SELECT * FROM produtos WHERE nomeProduto LIKE ?";
        ArrayList<Produto> produtos = new ArrayList<>();
        
        ResultSet rs = null;
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, texto + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodProduto(rs.getInt("codProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return produtos;
    }
}
