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
    
    Connection conexao = new Conexao().getConnection();
    
    public void inserir(Produto produto) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO produtos (nomeProduto, descricao, quantidade, valorCompra, valorVenda, unidadeMedida)"
                + "VALUES(?,?,?,?,?,?)";
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
    }
    
    public ArrayList<Produto> listar() throws SQLException{
        
        String sql = "SELECT * FROM produtos";
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        ArrayList<Produto> produtos = new ArrayList<>();
        
        while(rs.next()){
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
        //pst.close();
        //rs.close();
        //conexao.close();
        return produtos;
    } 
    
}
