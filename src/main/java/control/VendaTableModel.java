package control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Venda;

public class VendaTableModel extends AbstractTableModel {

    private final List<Venda> lista = new ArrayList<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // Número de colunas na tabela (ajuste conforme necessário)
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda venda = lista.get(rowIndex);

        try {
            switch (columnIndex) {
                case 0:
                    return venda.getIdVenda();
                case 1:
                    return venda.getValorTotalVenda();
                case 2:
                    return venda.getDataVenda();
                case 3:
                    return venda.getCliente().getNome();
                case 4:
                    return venda.getFormaPagamento();
                default:
                    return "";
            }
        } catch (Exception ex) {
            Logger.getLogger(VendaTableModel.class.getName()).log(Level.SEVERE, null, ex);
            return ""; 
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colunas[] = {"ID", "Valor Total", "Data Venda", "Cliente", "Forma Pagamento"};
        return colunas[columnIndex];
    }

    public void adicionar(Venda venda) {
        lista.add(venda);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public Venda getVenda(int row) {
        return lista.get(row);
    }

    public void remover(int row) {
        lista.remove(row);
        fireTableRowsDeleted(lista.size() - 1, lista.size() - 1);
    }

    public void setList(List<Venda> novaLista) {
        lista.clear();
        lista.addAll(novaLista);
        fireTableDataChanged();
    }
}
