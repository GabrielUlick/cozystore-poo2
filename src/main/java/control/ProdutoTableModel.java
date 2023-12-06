package control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Produto;

public class ProdutoTableModel extends AbstractTableModel {

    private final List<Produto> lista = new ArrayList<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8; // Número de colunas na tabela
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = lista.get(rowIndex);

        try {
            switch (columnIndex) {
                case 0:
                    return produto.getIdProduto();
                case 1:
                    return produto.getNome();
                case 2:
                    return produto.getMarca();
                case 3:
                    return produto.getCodigo_barra();
                case 4:
                    return produto.getQtde();
                case 5:
                    return produto.getFoto();
                case 6:
                    return produto.getCriacao().toString(); 
                case 7:
                    return produto.getUltima_alteracao().toString(); 
                default:
                    return "";
            }
        } catch (Exception ex) {
            Logger.getLogger(ProdutoTableModel.class.getName()).log(Level.SEVERE, null, ex);
            return ""; // ou algum valor padrão
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colunas[] = {"ID", "Nome", "Marca", "Código de Barras", "Quantidade", "Foto", "Criação", "Última Alteração"};
        return colunas[columnIndex];
    }

    public void adicionar(Produto produto) {
        lista.add(produto);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public Produto getProduto(int row) {
        return lista.get(row);
    }

    public void remover(int row) {
        lista.remove(row);
        fireTableRowsDeleted(lista.size() - 1, lista.size() - 1);
    }

    public void setList(List<Produto> novaLista) {
        lista.clear();
        lista.addAll(novaLista);
        fireTableDataChanged();
    }
}
