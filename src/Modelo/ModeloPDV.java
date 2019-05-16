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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOÃO VITOR
 */
public class ModeloPDV {

    Produto p;
    private int quantidade;

    public void atualizarValorTotal() {
        double valorTotal = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            valorTotal = valorTotal + Double.parseDouble(jTable1.getValueAt(i, 4).toString());
        }
        PDV.txtValorTotal.setText(String.valueOf(valorTotal));
    }

    public void setProdutoTabela(Produto produto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        //System.out.println(produto.getCodProduto());
        p = produtoDAO.recuperarPorCodigo(produto.getCodProduto());

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        //int linha = jTable1.getSelectedRow();

        if (jTable1.getRowCount() == 0) {
            dtm.addRow(new Object[]{
                p.getCodProduto(),
                p.getNomeProduto(),
                quantidade,
                p.getValorVenda(),
                p.getValorVenda() * quantidade
            });

            PDV.jMenuItem2.setEnabled(true);
            atualizarValorTotal();
        } else {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (Integer.parseInt(jTable1.getValueAt(i, 0).toString()) == produto.getCodProduto()) {
                    JOptionPane.showMessageDialog(null, "O produto já está adiconado à compra");
                } else {
                    dtm.addRow(new Object[]{
                        p.getCodProduto(),
                        p.getNomeProduto(),
                        quantidade,
                        p.getValorVenda(),
                        p.getValorVenda() * quantidade
                    });

                    PDV.jMenuItem2.setEnabled(true);
                    atualizarValorTotal();
                }
            }
        }
    }

    public void setProdutoTabelaPorCodigoBarras(Produto produto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        p = produtoDAO.recuperarPorCodigoBarras(produto.getCodigoBarras());

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        if (jTable1.getRowCount() == 0) {
            dtm.addRow(new Object[]{
                p.getCodProduto(),
                p.getNomeProduto(),
                quantidade,
                p.getValorVenda(),
                p.getValorVenda() * quantidade
            });

            PDV.jMenuItem2.setEnabled(true);
            atualizarValorTotal();
        } else {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (Integer.parseInt(jTable1.getValueAt(i, 0).toString()) == produto.getCodProduto()) {
                    System.out.println(jTable1.getValueAt(i, 0).toString());
                    System.out.println(produto.getCodProduto());
                    JOptionPane.showMessageDialog(null, "O produto já está adiconado à compra");
                    PDV.txtCodigoBarras.setText("");
                } else {
                    dtm.addRow(new Object[]{
                        p.getCodProduto(),
                        p.getNomeProduto(),
                        quantidade,
                        p.getValorVenda(),
                        p.getValorVenda() * quantidade
                    });

                    PDV.jMenuItem2.setEnabled(true);
                    atualizarValorTotal();
                }
            }
        }
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quanitdade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
