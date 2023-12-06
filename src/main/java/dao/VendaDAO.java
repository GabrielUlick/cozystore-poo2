/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.ItemVenda;
import model.Venda;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Belsa
 */
public class VendaDAO extends GenericDAO {

    private List<Venda> pesquisar(String pesq, int tipo) {
        List<Venda> lista = new ArrayList();

        Session sessao = null;
        try {
            sessao = ConexaoHibernate.getSessionFactory().openSession();
            sessao.beginTransaction();

            // CRITERIA
            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery consulta = builder.createQuery(Venda.class);

            // TABELAS
            Root tabela = consulta.from(Venda.class);

            //RESTRIÇÕES
            Predicate restricoes = null;
            switch (tipo) {
                // where nomeCliente LIKE 'pesq%'
                case 1:
                    restricoes = builder.like(tabela.get("cliente").get("nome"), pesq + "%");
                    break;
                case 2:
                    restricoes = builder.like(tabela.get("cliente").get("cpf"), pesq + "%");
                    break;
                case 3:
                    restricoes = builder.like(tabela.get("cliente").get("bairro"), pesq + "%");
                    break;
            }

            consulta.where(restricoes);

            lista = sessao.createQuery(consulta).getResultList();

            sessao.getTransaction().commit();
            sessao.close();
        } catch (HibernateException erro) {
            if (sessao != null) {
                sessao.getTransaction().rollback();
                sessao.close();
            }
            throw new HibernateException(erro);
        }

        return lista;

    }

    public List<Venda> pesquisarNomeCliente(String pesq) {
        return pesquisar(pesq, 1);
    }

    public List<Venda> pesquisarCPFCliente(String pesq) {
        return pesquisar(pesq, 2);
    }

    public List<Venda> pesquisarBairroCliente(String pesq) {
        return pesquisar(pesq, 3);
    }
    
      public List<ItemVenda> carregarListaItens (Venda vend) {
        Session sessao = null;
        List<ItemVenda> lista = new ArrayList();
        try {
            sessao = ConexaoHibernate.getSessionFactory().openSession();
            sessao.beginTransaction();
            
            // Verifica se a lista JÁ FOI CARREGADA
            if ( ! Hibernate.isInitialized( vend.getItensVenda()  ) ) {
                
                // Carrega o PROXY para outro objeto
                Venda vendTemp = sessao.get(Venda.class, vend.getIdVenda() );

                // Carrega a lista de PEDIDOS
                lista = vendTemp.getItensVenda();
                
                // Carrega os dados do banco
                lista.size();

                // Atualiza a lista no OBJETO principal (parâmetro)
                vend.setItensVenda(lista);
            }
            
            sessao.getTransaction().commit();
            sessao.close();
        } catch ( HibernateException erro ) {
            if ( sessao != null) {
                sessao.getTransaction().rollback();
                sessao.close();
            }
            throw new HibernateException(erro);  
        }
        return lista;
    }
    
}
