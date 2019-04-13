/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Negocio.Venda;
import Telas.VendaDetalhada;
import dao.VendaDAO;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JOÃO VITOR
 */
public class ModeloDetalhamentoVenda {

    Venda v;
    
    public void setTabelaDetalhamentoVendas(Venda venda){
        VendaDAO vendaDAO = new VendaDAO();
        v = vendaDAO.recuperarVendaPorCodigo(venda.getCodigo());
        
        DefaultTableModel dtm = (DefaultTableModel) VendaDetalhada.tblDetVendas.getModel();
        dtm.setNumRows(0);
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        VendaDetalhada.txtCodigoVenda.setText(String.valueOf(v.getCodigo()));
        VendaDetalhada.txtValorTotal.setText(String.valueOf(v.getValor()));
        VendaDetalhada.txtDataVenda.setText(df.format(v.getData()));
        
        for(int i=0; i<v.getItens().size(); i++){
            dtm.addRow(new Object[]{
                v.getItens().get(i).getProduto().getCodProduto(),
                v.getItens().get(i).getProduto().getNomeProduto(),
                v.getItens().get(i).getQuantidade(),
                v.getItens().get(i).getProduto().getValorVenda()
            });
        }
    }
    
}
