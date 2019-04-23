/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Negocio.Produto;
import dao.ProdutoDAO;

/**
 *
 * @author JOÃO VITOR
 */
public class ModeloEstoque {

    public void realizarBaixaNoEstoque(Produto produto, int quantidade){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int novaQuantidade = produto.getQuantidade() - quantidade;
        produto.setQuantidade(novaQuantidade);
        
        produtoDAO.editar(produto);
    }
    
}
