/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *
 * @author Belsa
 */
@Entity
public class ItemVenda implements Serializable{
  
    @EmbeddedId
    private ItemDaVendaPK chComposta;
    @Column
    private int qtdProduto;
    @Column
    private double valorTotalPorQtdProduto;
    
    

    public ItemVenda() {
    }

    public ItemVenda(Produto prod, Venda vend, int qtdProduto, double valorTotalPorQtdProduto) {
        this.chComposta = new ItemDaVendaPK(vend,prod);
        this.qtdProduto = qtdProduto;
        this.valorTotalPorQtdProduto = valorTotalPorQtdProduto;
    }

    public ItemDaVendaPK getChComposta() {
        return chComposta;
    }

    public void setChComposta(ItemDaVendaPK chComposta) {
        this.chComposta = chComposta;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public double getValorTotalPorQtdProduto() {
        return valorTotalPorQtdProduto;
    }

    public void setValorTotalPorQtdProduto(double valorTotalPorQtdProduto) {
        this.valorTotalPorQtdProduto = valorTotalPorQtdProduto;
    }
    
    public Produto getProduto() {
        return this.chComposta.getProduto();
    }
}
