/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ConexaoHibernate;

/**
 *
 * @author 2020122760307
 */
public class InterDominio {

    private static InterDominio gerenciador;

    private InterDominio() {
        ConexaoHibernate.getSessionFactory();
       
    }

    public static InterDominio getConexão() {
        if (gerenciador == null) {
            gerenciador = new InterDominio();
        }
        return gerenciador;
    }
}
