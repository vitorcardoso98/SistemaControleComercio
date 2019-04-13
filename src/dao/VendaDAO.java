/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Negocio.ItemVenda;
import Negocio.Produto;
import Negocio.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author JOÃO VITOR
 */
public class VendaDAO {

    public void salvar(Venda venda){
        Connection conexao = new Conexao().getConnection();
        String sql = "INSERT INTO vendas (dataVenda, valorTotal) VALUES(?,?)";
        
        try {
            PreparedStatement pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, new Date(venda.getData().getTime()));
            pst.setDouble(2, venda.getValorTotal());
            pst.execute();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            rs.next();
            int idVenda = rs.getInt(1);
            
            for(ItemVenda iv: venda.getItens()){
                sql = "INSERT INTO itemvenda (codigoProduto, codigoVenda, quantidade, valorUnitario) VALUES (?,?,?,?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, iv.getProduto().getCodProduto());
                ps.setInt(2, idVenda);
                ps.setInt(3, iv.getQuantidade());
                ps.setDouble(4, iv.getProduto().getValorVenda());
                ps.execute();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<Venda> consultarVendasRealiazadas(){
        Connection conexao = new Conexao().getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //System.out.println("OK!");
            while(rs.next()){
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("codigo"));              
                //venda.setData(null);
                venda.setValor(rs.getDouble("valorTotal"));
                venda.setData(rs.getDate("dataVenda"));
                vendas.add(venda);
            }
            pst.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return vendas;
    }
    
    public Venda recuperarVendaPorCodigo(int codigo){
        Connection conexao = new Conexao().getConnection();
        Venda venda = new Venda();
        ProdutoDAO prodDAO = new ProdutoDAO();
        String sql = "SELECT * FROM vendas WHERE codigo = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                venda.setCodigo(rs.getInt("codigo"));
                venda.setValor(rs.getDouble("valorTotal"));
                venda.setData(rs.getDate("dataVenda"));
                String sqlItem = "SELECT * FROM itemvenda WHERE codigoVenda = ?";
                
                PreparedStatement pstItem = conexao.prepareStatement(sqlItem);
                pstItem.setInt(1, venda.getCodigo());
                ResultSet rsItem = pstItem.executeQuery();
                
                
                while(rsItem.next()){
                    ItemVenda item = new ItemVenda();
                    Produto produto;
                    //produto.setCodProduto();
                    produto = prodDAO.recuperarPorCodigo(rsItem.getInt("codigoProduto"));
                    //produto.setNomeProduto(rsItem.getString("nomeProduto"));
                    //produto.setValorVenda(rsItem.getDouble("valorVenda"));
                    
                    item.setProduto(produto);
                    item.setQuantidade(rsItem.getInt("quantidade"));

                    venda.addItem(item);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return venda;
    }
    
    public void excluir(Venda venda){
        Connection conexao = new Conexao().getConnection();
        String sql = "DELETE FROM vendas WHERE codigo = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, venda.getCodigo());
            
            pst.execute();
            
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public ArrayList<Venda> consultarVendaPorData(java.util.Date data){
        Connection conexao = new Conexao().getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE dataVenda = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setDate(1, new Date(data.getTime()));
            ResultSet rs = pst.executeQuery();
            //System.out.println("OK!");
            while(rs.next()){
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("codigo"));              
                //venda.setData(null);
                venda.setValor(rs.getDouble("valorTotal"));
                venda.setData(rs.getDate("dataVenda"));
                vendas.add(venda);
            }
            pst.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return vendas;
    }
    
    public ArrayList<Venda> consultarVendaPorPeríodo(java.util.Date dataInicio, java.util.Date dataFim){
        Connection conexao = new Conexao().getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas WHERE dataVenda BETWEEN '"+new Date(dataInicio.getTime())+"' AND '"+new Date(dataFim.getTime())+"' ";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            //System.out.println("OK!");
            while(rs.next()){
                Venda venda = new Venda();
                venda.setCodigo(rs.getInt("codigo"));              
                //venda.setData(null);
                venda.setValor(rs.getDouble("valorTotal"));
                venda.setData(rs.getDate("dataVenda"));
                vendas.add(venda);
            }
            pst.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return vendas;
    }
}
