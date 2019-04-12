/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JOÃO VITOR
 */
public class Venda {
    
    private int codigo;
    private Date data;
    private double valorTotal;
    private List<ItemVenda> itens = new ArrayList();

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
    
    public void setValor(Double valor){
        this.valorTotal = valor;
    }
    
    public Double getValor(){
        return valorTotal;
    }
    
    public Double getValorTotal() {
        double total = 0;
        for (ItemVenda iv : itens) {
            total += (iv.getProduto().getValorVenda() * iv.getQuantidade());
        }
        return total;
    }
    
    public void addItem(ItemVenda itemVenda) {
        itens.add(itemVenda);
    }
    
    public List<ItemVenda> getItens() {
        return itens;
    }
}
