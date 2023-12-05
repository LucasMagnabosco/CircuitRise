package ucs.CircuitRise.controller;
import javax.swing.table.AbstractTableModel;

import ucs.CircuitRise.model.Team;

import java.util.List;

public class TeamTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private List<Team> teams;
    private final String[] columnNames = {"Nome", "Pontos", "Viórias", "Pódios"};

    public TeamTableModel(List<Team> t) {
        this.teams = t;
    }

    public void addTeam(Team team) {
        teams.add(team);
        fireTableRowsInserted(teams.size() - 1, teams.size() - 1);
    }

    @Override
    public int getRowCount() {
        return teams.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team team = teams.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return team.getName();
            case 1:
                return team.getPoints();
            case 2:
            	return team.getWins();
            case 3:
            	return team.getPodiums();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

