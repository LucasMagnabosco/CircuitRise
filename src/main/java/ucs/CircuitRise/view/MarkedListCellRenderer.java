package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MarkedListCellRenderer extends DefaultListCellRenderer {
    private static int manipulatedRow = -1;

    public static void markRowAsManipulated(int row) {
        manipulatedRow = row;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (index == manipulatedRow) {
            renderer.setForeground(Color.RED); 
        } else {
            renderer.setForeground(list.getForeground());
        }

        return renderer;
    }
}
