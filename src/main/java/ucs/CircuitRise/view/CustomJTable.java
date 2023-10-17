package ucs.CircuitRise.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomJTable extends JTable {
    public CustomJTable(DefaultTableModel model) {
        super(model);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        component.setBackground(Color.RED); 

        if (isRowSelected(row) && isColumnSelected(column)) {
            component.setBackground(Color.BLUE);
        }

        return component;
    }
}
