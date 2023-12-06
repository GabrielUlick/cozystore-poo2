/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import model.Cliente;
import model.ItemVenda;

/**
 *
 * @author Belsa
 */
@Entity
public class Venda implements Serializable {
    

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idVenda = 0;
    @Column
    private double valorTotalVenda;
    @Column
    private String dataVenda;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @Column
    String formaPagamento ;
    
    @OneToMany(mappedBy = "chComposta.venda",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemVenda> itensVenda;

   
    public Venda() {
    }

    public Venda(double valorTotalVenda, String dataVenda, Cliente cliente,String formapagamento) {
        idVenda++;
        this.valorTotalVenda = valorTotalVenda;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.formaPagamento = formapagamento;
        this.itensVenda = new ArrayList();
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }


    public double getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(double valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
    
    

}
