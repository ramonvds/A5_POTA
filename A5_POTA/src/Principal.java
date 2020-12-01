import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JRadioButton;

public class Principal extends MouseAdapter {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private static Formatter output;
	private ButtonGroup bg = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setSize(1200, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Carregar Dados");
		lblNewLabel.setBounds(0, 0, 1174, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Arquivo: ");
		lblNewLabel_1.setBounds(10, 50, 56, 23);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(54, 50, 1044, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 1164, 307);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "CPF", "Nome", "Sobrenome", "Sexo", "Data de admissao", "Pais", "Diagnostico" }));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		//String[][] dados = new String [][];
		scrollPane.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setToolTipText("Clique para ordenar de acordo com a coluna selecionada."); 
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				int index = table.columnAtPoint(mouseEvent.getPoint());
				if (index >= 0) {
					long start = System.nanoTime();
					Ordenacao sort = new Ordenacao();  
					table = sort.insertionSort(index, table);
					//table = sort.mergeSort(index, table, 0, index-1);
					model.fireTableDataChanged();
					long elapsedTime = System.nanoTime() - start;
					textField_1.setText(Long.toString(elapsedTime));
				}
			};
		});

		Button button = new Button("Procurar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("."));
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.showOpenDialog(frame);
				File f = fc.getSelectedFile();
				textField.setText(f.getPath());
				Scanner entrada = null;

				try {
					entrada = new Scanner(Paths.get(f.getPath()));

					if (f != null) {
						entrada.nextLine();
						while (entrada.hasNext()) {
							String linha = entrada.nextLine();
							String[] textoSeparado = linha.split(",");
							System.out.println(textoSeparado);
							model.addRow(textoSeparado);
							
						}

					}

				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NoSuchElementException elementException) {
					System.err.println("Arquivo formatado inadequadamente. Terminando.");
				} catch (IllegalStateException stateException) {
					System.err.println("Erro ao ler do arquivo. Terminando.");
				}

			}
		});
		button.setBounds(1104, 50, 70, 23);
		frame.getContentPane().add(button);

		textField_1 = new JTextField();
		textField_1.setBounds(173, 427, 86, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Contador Ordenacao (ns):");
		lblNewLabel_2.setBounds(10, 427, 153, 23);
		frame.getContentPane().add(lblNewLabel_2);

		Button button_1 = new Button("Salvar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvarRegistros(table);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				fecharArquivo();
			}
		});
		button_1.setBounds(1104, 430, 70, 22);
		frame.getContentPane().add(button_1);
		
		Button button_2 = new Button("Adc Registro");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[]{model.getRowCount()+1,null,null,null,null,null,null,null});
				table.changeSelection(model.getRowCount()-1, 0, false, false);
			}
		});
		button_2.setActionCommand("");
		button_2.setBounds(10, 87, 100, 23);
		frame.getContentPane().add(button_2);
		
		Button button_3 = new Button("Rmv Registro");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
			}
		});
		button_3.setActionCommand("");
		button_3.setBounds(116, 88, 100, 23);
		frame.getContentPane().add(button_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Insertion Sort");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(290, 427, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Merge Sort");
		rdbtnNewRadioButton_1.setEnabled(false);
		rdbtnNewRadioButton_1.setBounds(414, 427, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("ABB");
		rdbtnNewRadioButton_2.setEnabled(false);
		rdbtnNewRadioButton_2.setBounds(538, 427, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_2);
		
	}

	public static void salvarRegistros(JTable tabela) throws FileNotFoundException {

		try {
			File arquivoordenado = new File(JOptionPane.showInputDialog("Informe o nome do arquivo: ")+".csv");
			if (arquivoordenado.createNewFile() && arquivoordenado.getName() != null) {
				//System.out.println("Arquivo Criado: " + arquivoordenado.getName());
				JOptionPane.showMessageDialog(tabela, "Arquivo criado: " + arquivoordenado.getName());
			} else {
				//System.out.println("Arquivo ja existe.");
				JOptionPane.showMessageDialog(tabela, "Tente criar o arquivo novamente.");
			}
			
			output = new Formatter(arquivoordenado.getName());
			
			output.format("%s%n", "id,cpf,nome,sobrenome,sexo,data de admissao,pais,diagnostico");
			
			for(int row = 0;row < tabela.getRowCount();row++) {
				String linha = "";
			    for(int col = 0;col < tabela.getColumnCount();col++) {
			    	linha += tabela.getValueAt(row, col).toString()+",";
			    }
			    output.format("%s%n", linha);
			}
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Erro escrevendo no arquivo. Terminando.");
		} catch (NoSuchElementException elementException) {
			System.err.println("Entrada invalida. Por favor tente novamente.");
		} catch (IOException e) {
			System.out.println("Erro.");
			e.printStackTrace();
		}
	}

	public static void fecharArquivo()
 {
		if (output != null)
			output.close();
	}
}
