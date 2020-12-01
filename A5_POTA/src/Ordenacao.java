import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ordenacao {

	public JTable insertionSort(int coluna, JTable tabela) { // insertionsort
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();

		for (int i = 1; i < tabela.getRowCount(); i++) {

			char aux = tabela.getValueAt(i, coluna).toString().charAt(0);
			int j = i;

			while ((j > 0) && (tabela.getValueAt(j - 1, coluna).toString().charAt(0) > aux)) {
				model.moveRow(j - 1, j - 1, j);
				j -= 1;
			}
		}

		tabela.setModel(model);
		return tabela;
	}

}
