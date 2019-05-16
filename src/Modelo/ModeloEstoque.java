/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Negocio.Produto;
import Negocio.Venda;
import static Telas.PDV.jTable1;
import dao.ProdutoDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author JOÃO VITOR
 */
public class ModeloEstoque {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean validarProdutosEstoque(Venda venda) {
        for (int i = 0; i < venda.getItens().size(); i++) {
            Produto prod = produtoDAO.recuperarPorCodigo(venda.getItens().get(i).getProduto().getCodProduto());
            if (Integer.parseInt(jTable1.getValueAt(i, 2).toString()) > prod.getQuantidade()) {
                JOptionPane.showMessageDialog(null, "A quantidade do produto "
                        + prod.getNomeProduto()
                        + " no estoque é menor que a quantidade a ser vendida");
                return false;
            }
        }
        System.out.println("Executou");
        return true;
    }
}