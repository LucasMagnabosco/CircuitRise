package ucs.CircuitRise.controller;
import javax.swing.table.AbstractTableModel;

import ucs.CircuitRise.model.Pilot;
import java.util.List;

public class PilotTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private List<Pilot> pilots;
    private final String[] columnNames = {"Num", "Nome", "Pontos", "Viórias", "Pódios"};

    public PilotTableModel(List<Pilot> p) {
        this.pilots = p;
    }

    public void addPilot(Pilot pilot) {
        pilots.add(pilot);
        fireTableRowsInserted(pilots.size() - 1, pilots.size() - 1);
    }

    @Override
    public int getRowCount() {
        return pilots.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pilot pilot = pilots.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pilot.getNumber();
            case 1:
                return pilot.getName();
            case 2:
            	return pilot.getPoints();
            case 3:
            	return pilot.getWins();
            case 4:
            	return pilot.getPodiums();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
