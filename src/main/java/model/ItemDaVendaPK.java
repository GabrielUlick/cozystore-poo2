
package model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Belsa
 */
@Embeddable
public class ItemDaVendaPK implements Serializable{
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "idVenda")
    private Venda venda;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "idProduto")    
    private Produto produto;

    public ItemDaVendaPK() {
    }

    public ItemDaVendaPK(Venda venda, Produto produto) {
        this.venda = venda;
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

   
    
    
}

   
