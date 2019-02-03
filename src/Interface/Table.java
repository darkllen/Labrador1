package Interface;

import Model.Faculty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Table {



    public static void tableParameters(int WIDTH,JTable table, Vector<String> columns, DefaultTableModel model, ArrayList<Faculty> array) {

        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setForeground(Color.black);
        table.setBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.red);
        table.setSelectionBackground(Color.yellow);
        table.setRowHeight(100);
//        for(int i=0;i<columns.size();i++){
//           TableColumn tableColumn= table.getColumnModel().getColumn(i);
//        tableColumn.setPreferredWidth(100);
//        }
    }
}
