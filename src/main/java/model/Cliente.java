 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author 1547816
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(length = 40)
    private String nome;
    private String cpf;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtNasc;
    private char sexo;
    private String telFixo;
    private String celular;
    private String email;
    private byte[] foto;
    private String cep;
    private int numero;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String referencia;
    @Column(length = 2)
    private String uf;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;
    private Venda venda;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultima_alteracao;

    public Cliente() {
    }

    public Cliente(int idCliente, String nome, String cpf, Date dtNasc, char sexo, String telFixo, String celular, String email, byte[] foto, String cep, int numero, String logradouro, String complemento, String bairro, String cidade, String referencia, String uf, Date criacao, Venda venda, Date ultima_alteracao) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.sexo = sexo;
        this.telFixo = telFixo;
        this.celular = celular;
        this.email = email;
        this.foto = foto;
        this.cep = cep;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.referencia = referencia;
        this.uf = uf;
        this.criacao = criacao;
        this.venda = venda;
        this.ultima_alteracao = ultima_alteracao;
    }

    

    public Cliente(String nome, String cpf, Date dtNasc, char sexo, String telFixo, String celular, String email, byte[] foto, String cep, int numero, String logradouro, String complemento, String bairro, String cidade, String referencia, String uf, Date criacao, Date ultima_alteracao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.sexo = sexo;
        this.telFixo = telFixo;
        this.celular = celular;
        this.email = email;
        this.foto = foto;
        this.cep = cep;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.referencia = referencia;
        this.uf = uf;
        this.criacao = criacao;
        this.ultima_alteracao = ultima_alteracao;
    }

    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtNascFormatada() throws ParseException {
        return control.FuncoesUteis.dateToStr(dtNasc);
    }
    
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

      public Object[] toArray(){
    return new Object[]{this,cpf,celular,cidade};
        
    }
    
}
