/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ClienteDAO;
import dao.ConexaoHibernate;
import dao.GenericDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JTable;

import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;

import org.hibernate.HibernateException;

/**
 *
 * @author Belsa
 */
public class GerDominio {

    private GenericDAO genDAO;
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;

    public GerDominio() throws HibernateException {

        ConexaoHibernate.getSessionFactory().openSession();
        genDAO = new GenericDAO();
        clienteDAO = new ClienteDAO();
        produtoDAO = new ProdutoDAO();
        vendaDAO = new VendaDAO();
    }

    // GENÉRICO
    public List listar(Class classe) throws HibernateException {
        return genDAO.listar(classe);
    }

    // GENÉRICO
    public void excluir(Object obj) throws HibernateException {
        genDAO.excluir(obj);

    }

    // Cliente
    public List<Cliente> pesquisarCliente(String pesq, int tipo) throws HibernateException {
        List<Cliente> lista = null;
        switch (tipo) {
            case 0:
                lista = clienteDAO.pesquisarNome(pesq);
                break;
            case 1:
                lista = clienteDAO.pesquisarCPF(pesq);
                break;
            case 2:
                lista = clienteDAO.pesquisarBairro(pesq);
                break;
            case 3:
                lista = clienteDAO.pesquisarMes(pesq);
                break;
        }
        return lista;
    }

    public int InserirCliente(
            String nome, String cpf, Date dtNasc, char sexo, String telFixo,
            String celular, String email, Icon foto, String cep, int numero,
            String logradouro, String complemento, String bairro, String cidade,
            String referencia, String uf) throws HibernateException {

        Date criacao = new Date();
        Date ultima_alteracao = criacao;

        Cliente cliente = new Cliente(
                nome, cpf, dtNasc, sexo, telFixo, celular, email, FuncoesUteis.IconToBytes(foto),
                cep, numero, logradouro, complemento, bairro, cidade, referencia, uf,
                criacao, ultima_alteracao
        );

        clienteDAO.inserir(cliente);
        return cliente.getIdCliente();
    }

    public int alterarCliente(Cliente cli, String nome, String cpf, Date dtNasc, char sexo, String telFixo,
            String celular, String email, Icon foto, String cep, int numero,
            String logradouro, String complemento, String bairro, String cidade,
            String referencia, String uf) throws HibernateException {

        cli.setNome(nome);
        cli.setCpf(cpf);
        cli.setDtNasc(dtNasc);
        cli.setSexo(sexo);
        cli.setCep(cep);
        cli.setLogradouro(logradouro);
        cli.setBairro(bairro);
        cli.setNumero(numero);
        cli.setComplemento(complemento);
        cli.setReferencia(referencia);
        cli.setTelFixo(telFixo);
        cli.setCelular(celular);
        cli.setEmail(email);
        cli.setFoto(FuncoesUteis.IconToBytes(foto));
        cli.setCidade(cidade);
        Date ultima_alteracao = new Date();
        cli.setUltima_alteracao(ultima_alteracao);

        clienteDAO.alterar(cli);
        return cli.getIdCliente();

    }

    // ----------------------------------------
    public List<Produto> pesquisarProduto(String pesq, int tipo) throws HibernateException {
        List<Produto> lista = null;
        switch (tipo) {
            case 0:
                lista = produtoDAO.pesquisarNome(pesq);
                break;
            case 1:
                lista = produtoDAO.pesquisarMarca(pesq);
                break;
        }
        return lista;
    }

    public int InserirProduto(String nome, String marca, String codigo_barra, double valor, int qtde, Icon foto)
            throws HibernateException {

        Date criacao = new Date();
        Date ultima_alteracao = criacao;

        Produto produto = new Produto(nome, marca, codigo_barra, valor, qtde, FuncoesUteis.IconToBytes(foto), criacao, ultima_alteracao);
        produtoDAO.inserir(produto);
        return produto.getIdProduto();
    }

    public int alterarProduto(Produto prod, String nome, String marca, String codigo_barra, double valor, int qtde, Icon foto)
            throws HibernateException {

        prod.setNome(nome);
        prod.setMarca(marca);
        prod.setCodigo_barra(codigo_barra);
        prod.setValor(valor);
        prod.setQtde(qtde);
        prod.setFoto(FuncoesUteis.IconToBytes(foto));
        Date ultima_alteracao = new Date();
        prod.setUltima_alteracao(ultima_alteracao);

        produtoDAO.alterar(prod);

        return prod.getIdProduto();
    }

    // ----------------------------------------
    // Venda
    public List<Venda> pesquisarVenda(String pesq, int tipo) throws HibernateException {
        List<Venda> lista = null;
        switch (tipo) {
            case 0:
                lista = vendaDAO.pesquisarNomeCliente(pesq);
                break;
            case 1:
                lista = vendaDAO.pesquisarCPFCliente(pesq);
                break;
            case 2:
                lista = vendaDAO.pesquisarBairroCliente(pesq);
                break;

        }
        return lista;
    }

    public int alterarVenda(Venda venda, String formaPg, JTable tabelaVenda, String data) throws HibernateException {

    double valorTotal = 0;
    venda.setDataVenda(data);
    venda.setFormaPagamento(formaPg);

    List<ItemVenda> itensVenda = new ArrayList<>(); // Lista para armazenar os itens da venda

    int tam = tabelaVenda.getRowCount();

    if (tam > 0) {
        for (int lin = 0; lin < tam; lin++) {
            int col = 0;
            Produto produto = (Produto) tabelaVenda.getValueAt(lin, col++);
            int qtde = (int) tabelaVenda.getValueAt(lin, col++);
            double valorunit = (double) tabelaVenda.getValueAt(lin, col++);
            double valortotal = (double) tabelaVenda.getValueAt(lin, col++);

            ItemVenda item = new ItemVenda(produto, venda, qtde, valorunit);
            itensVenda.add(item);
            valorTotal += valortotal;
        }

        venda.setItensVenda(itensVenda);
        venda.setValorTotalVenda(valorTotal);

        produtoDAO.alterar(venda); 

        return venda.getIdVenda();
    }
    return -1; 
}

    
    public int inserirVenda(Cliente cliente, String formaPg, JTable tabelaVenda, String data) throws HibernateException {

        Venda venda;
        double valorTotal = 0;
        venda = new Venda(0, data, cliente, formaPg);
        List lista = venda.getItensVenda();
        int tam = tabelaVenda.getRowCount();

        if (tam > 0) {
            for (int lin = 0; lin < tam; lin++) {
                int col = 0;
                Produto produto = (Produto) tabelaVenda.getValueAt(lin, col++);
                int qtde = (int) tabelaVenda.getValueAt(lin, col++);
                double valorunit = (double) tabelaVenda.getValueAt(lin, col++);
                double valortotal = (double) tabelaVenda.getValueAt(lin, col++);

                ItemVenda item = new ItemVenda(produto, venda, qtde, valorunit);
                lista.add(item);
                valorTotal = valortotal;
            }
            venda.setValorTotalVenda(valorTotal);
            genDAO.inserir(venda);
            return venda.getIdVenda();
        } else {
            return -1;
        }
    }
    
    public List<ItemVenda> carregarItensPedido(Venda vend) throws HibernateException {
        return vendaDAO.carregarListaItens(vend);
    }
}
