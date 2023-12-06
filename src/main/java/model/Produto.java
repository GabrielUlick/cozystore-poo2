/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author 2020122760307
 */
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;
    private String nome;
    private String marca;

    private String codigo_barra;
    private double valor;
    private int qtde;
    private byte[] foto;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultima_alteracao;

    public Produto() {
    }

    public Produto(int idProduto, String nome, String marca, String codigo_barra, double valor, int qtde, byte[] foto, Date criacao, Date ultima_alteracao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.marca = marca;
        this.codigo_barra = codigo_barra;
        this.valor = valor;
        this.qtde = qtde;
        this.foto = foto;
        this.criacao = criacao;
        this.ultima_alteracao = ultima_alteracao;
    }

    public Produto(String nome, String marca, String codigo_barra, double valor, int qtde, byte[] foto, Date criacao, Date ultima_alteracao) {
        this.nome = nome;
        this.marca = marca;
        this.codigo_barra = codigo_barra;
        this.valor = valor;
        this.qtde = qtde;
        this.foto = foto;
        this.criacao = criacao;
        this.ultima_alteracao = ultima_alteracao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getUltima_alteracao() {
        return ultima_alteracao;
    }

    public void setUltima_alteracao(Date ultima_alteracao) {
        this.ultima_alteracao = ultima_alteracao;
    }

    public Object[] toArray3() {
        return new Object[]{this, marca, valor, qtde, foto, criacao, ultima_alteracao};

    }

    @Override
    public String toString() {
        return getNome();
    }
}
