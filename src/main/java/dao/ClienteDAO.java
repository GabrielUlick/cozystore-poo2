/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author 1547816
 */
public class ClienteDAO extends GenericDAO {

    private List<Cliente> pesquisar(String pesq, int tipo) {
        List<Cliente> lista = new ArrayList();

        Session sessao = null;
        try {
            sessao = ConexaoHibernate.getSessionFactory().openSession();
            sessao.beginTransaction();

            // CRITERIA
            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery consulta = builder.createQuery(Cliente.class);

            // TABELAS
            Root tabela = consulta.from(Cliente.class);

            //RESTRIÇÕES
            Predicate restricoes = null;
            switch (tipo) {
                // where nomeCliente LIKE 'pesq%'
                case 1:
                    restricoes = builder.like(tabela.get("nome"), pesq + "%");
                    break;
                case 2:
                    restricoes = builder.like(tabela.get("cpf"), pesq + "%");
                    break;
                case 3:
                    restricoes = builder.like(tabela.get("bairro"), pesq + "%");
                    break;
                case 4:
                    Expression mes = builder.function("month", Integer.class, tabela.get("dtNasc"));
                    restricoes = builder.equal(mes, pesq);
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

    public List<Cliente> pesquisarNome(String pesq) {
        return pesquisar(pesq, 1);
    }

    public List<Cliente> pesquisarCPF(String pesq) {
        return pesquisar(pesq, 2);
    }

    public List<Cliente> pesquisarBairro(String pesq) {
        return pesquisar(pesq, 3);
    }

    public List<Cliente> pesquisarMes(String pesq) {
        return pesquisar(pesq, 4);
    }
}
