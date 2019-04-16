/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Negocio.Produto;
import Telas.PDV;
import static Telas.PDV.jTable1;
import dao.ProdutoDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOÃO VITOR
 */
public class ModeloPDV {

    Produto p;
    
    public void setProdutoTabela(Produto produto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        //System.out.println(produto.getCodProduto());
        p = produtoDAO.recuperarPorCodigo(produto.getCodProduto());
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        int linha = jTable1.getSelectedRow();
        
        //System.out.println(produto.getNomeProduto());
        dtm.addRow(new Object[]{
            p.getCodProduto(),
            p.getNomeProduto(),
            0,
            p.getValorVenda()
        });
        
        PDV.jMenuItem2.setEnabled(true);
    }
    
    public void setProdutoTabelaPorCodigoBarras(Produto produto){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        //System.out.println(produto.getCodProduto());
        p = produtoDAO.recuperarPorCodigoBarras(produto.getCodigoBarras());
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        int linha = jTable1.getSelectedRow();
        
        //System.out.println(produto.getNomeProduto());
        dtm.addRow(new Object[]{
            p.getCodProduto(),
            p.getNomeProduto(),
            0,
            p.getValorVenda()
        });
        
        PDV.jMenuItem2.setEnabled(true);
    }
}
