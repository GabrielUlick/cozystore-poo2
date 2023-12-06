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
import model.Produto;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author 1547816
 */
public class ProdutoDAO extends GenericDAO {

    private List<Produto> pesquisar(String pesq, int tipo) {
        List<Produto> lista = new ArrayList();

        Session sessao = null;
        try {
            sessao = ConexaoHibernate.getSessionFactory().openSession();
            sessao.beginTransaction();

            // CRITERIA
            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery consulta = builder.createQuery(Produto.class);

            // TABELAS
            Root tabela = consulta.from(Produto.class);

            //RESTRIÇÕES
            Predicate restricoes = null;
            switch (tipo) {
                // where nomeCliente LIKE 'pesq%'
                case 1:
                    restricoes = builder.like(tabela.get("nome"), pesq + "%");
                    break;
                case 2:
                    restricoes = builder.like(tabela.get("marca"), pesq + "%");
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

    public List<Produto> pesquisarNome(String pesq) {
        return pesquisar(pesq, 1);
    }

    public List<Produto> pesquisarMarca(String pesq) {
        return pesquisar(pesq, 2);
    }

}
