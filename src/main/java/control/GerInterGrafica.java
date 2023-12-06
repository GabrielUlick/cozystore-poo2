package control;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import View.Home;
import View.DlgCadCliente;
import View.DlgCadProduto;
import View.DlgCaixa;
import View.DlgEstoque;
import View.DlgHistVendas;
import View.DlgPesquisarCliente;
import View.DlgPesquisarProduto;
import View.DlgRelatorio;
import View.DlgVenda;
import model.Cliente;
import model.Produto;
import model.Venda;

/**
 *
 * @author 1547816
 */
public class GerInterGrafica {

    // SINGLETON
    private final static GerInterGrafica unicaInstancia = new GerInterGrafica();
    private GerDominio gerDom;

    private GerInterGrafica() {
        gerDom = new GerDominio();

    }

    public static GerInterGrafica getInstance() {
        return unicaInstancia;
    }
    // FIM DO SINGLETON

    public GerDominio getGerDom() {
        return gerDom;
    }
    // FIM DO SINGLETON

    // ATRIBUTOS
    private Home home = null;
    private DlgCadCliente janelaCadCliente = null;
    private DlgCadProduto janelaCadProduto = null;
    private DlgCaixa janelaCaixa = null;
    private DlgVenda janelaVenda = null;
    private DlgRelatorio janelaRelatorio = null;
    private DlgEstoque janelaEstoque = null;
    private DlgPesquisarProduto janelaPesqProd = null;
    private DlgPesquisarCliente janelaPesqCli = null;
    private DlgHistVendas janelaHistVendas = null;

    private JDialog abrirJanela(JDialog objDlg, Class classe) {

        try {
            if (objDlg == null) {
                objDlg = (JDialog) classe.getConstructor(Frame.class, boolean.class).newInstance(home, true);
            }
            objDlg.setVisible(true);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            objDlg = null;
            JOptionPane.showMessageDialog(home, ex.getMessage());
        }
        return objDlg;

    }

    public void abrirJanPrincipal() {
        if (home == null) {
            home = new Home();
        }
        home.setVisible(true);
    }

    public void abrirJanCadCliente() {
        abrirJanela(janelaCadCliente, DlgCadCliente.class);

    }

    public void abrirJanelaCadProduto() {
        abrirJanela(janelaCadProduto, DlgCadProduto.class);

    }

    public void abrirJanelaCaixa() {
        abrirJanela(janelaCaixa, DlgCaixa.class);

    }

    public void abrirJanelaVenda() {
        abrirJanela(janelaVenda, DlgVenda.class);

    }

    public Venda abrirJanelaHistVendas() {
        janelaHistVendas = (DlgHistVendas) abrirJanela(janelaHistVendas, DlgHistVendas.class);
        return janelaHistVendas.getVendaSelecionada();

    }

    public void abrirJanelaRelatorio() {
        abrirJanela(janelaRelatorio, DlgRelatorio.class);

    }

    public void abrirJanelaEstoque() {
        abrirJanela(janelaEstoque, DlgEstoque.class);

    }

    public Produto abrirJanelaPesqProduto() {
        janelaPesqProd = (DlgPesquisarProduto) abrirJanela(janelaPesqProd, DlgPesquisarProduto.class);
        return janelaPesqProd.getProdSelecionado();
    }

    public Cliente abrirJanelaPesqCliente() {
        janelaPesqCli = (DlgPesquisarCliente) abrirJanela(janelaPesqCli, DlgPesquisarCliente.class);
        return janelaPesqCli.getCliSelecionado();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            /*
            FlatDarkLaf dark = new FlatDarkLaf();
            javax.swing.UIManager.setLookAndFeel( dark );
             */
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // TRADUÇÃO
        InterDominio.getConexão();
        GerInterGrafica gerIG = GerInterGrafica.getInstance();
        gerIG.abrirJanPrincipal();
    }
}
