package control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 1547816
 */
public class ClienteTableModel extends AbstractTableModel {

    List<Cliente> lista = new ArrayList();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cli = lista.get(rowIndex);

        try {
            switch (columnIndex) {
                case 0:
                    return cli.getNome();
                case 1:
                    return cli.getBairro();
                case 2:
                    return cli.getCidade();
                case 3:
                    return cli.getDtNasc().toString();
                case 4:
                    return cli.getCelular();
                case 5:
                    return cli.getFoto();
                default:
                    return "";
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
            return ""; // or some default value
        }

    }

    @Override
    public String getColumnName(int columnIndex) {
        String colunas[] = {"Nome", "Bairro", "Cidade", "Dt. Nasc.", "Celular", "Foto"};
        return colunas[columnIndex];
    }

    public void adicionar(Cliente cli) {
        lista.add(cli);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public Cliente getCliente(int row) {
        return lista.get(row);
    }

    public void remover(int row) {
        lista.remove(row);
        fireTableRowsDeleted(lista.size() - 1, lista.size() - 1);
    }

    public void setList(List<Cliente> novaLista) {
        lista = novaLista;
        fireTableRowsInserted(0, lista.size() - 1);
    }
}
