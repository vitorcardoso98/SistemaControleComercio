/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import dao.VendaDAO;
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
    
    public ArrayList<Double> faturamentoDiario(java.util.Date data){
        VendaDAO vendaDAO = new VendaDAO();
        ArrayList<Venda> vendas = vendaDAO.consultarVendaPorData(data);
        ArrayList<Double> valores = new ArrayList<>();
        double valorProdutos = 0;
        double vt = 0;
        for(int i=0; i<vendas.size(); i++){
            vt = vt + vendas.get(i).getValor();
            System.out.println(vt);
            Venda v = vendaDAO.recuperarVendaPorCodigo(vendas.get(i).getCodigo());
            
            for(int j=0; j<v.getItens().size(); j++){
                
                valorProdutos = valorProdutos + (v.getItens().get(j).getProduto().getValorCompra()*v.getItens().get(j).getQuantidade());
            }
            
        }
        valores.add(vt);
        valores.add(valorProdutos);
        double lucro = vt-valorProdutos;
        valores.add(lucro);
        
        return valores;
    }
}
