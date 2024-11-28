package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

/**
 * Tela de cadastro de clientes. Permite a inserção, exclusão, atualização e
 * busca de clientes. Os dados dos clientes são armazenados em um banco de dados
 * relacional. Esta classe estende {@link JFrame} e é uma aplicação de interface
 * gráfica.
 */

public class CadastroClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfNome;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextField tfCPF;
	private JTextField tfLogradouro;
	private JTextField tfCidade;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JTable tbDados;
	private JTextField tfDtCad;
	private JTextField tfCEP;

	/**
	 * Método principal da aplicação. Inicia a interface gráfica.
	 *
	 * @param 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClientes frame2 = new CadastroClientes();
					frame2.setVisible(true);
					frame2.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
     * Construtor da tela de Cadastro de Clientes.
     * Define os componentes da interface e adiciona eventos aos botões.
     */
	public CadastroClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(144, 0, 642, 188);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTitulo = new JLabel("Cadastro de Clientes");
		lblTitulo.setBounds(58, 11, 140, 14);
		panel_1.add(lblTitulo);

		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(48, 32, 42, 20);
		panel_1.add(tfID);
		tfID.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(13, 35, 25, 14);
		panel_1.add(lblId);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(113, 35, 34, 14);
		panel_1.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(156, 32, 343, 20);
		panel_1.add(tfNome);
		tfNome.setColumns(10);

		tfTelefone = new JTextField();
		tfTelefone.setBounds(546, 60, 86, 20);
		panel_1.add(tfTelefone);
		tfTelefone.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(496, 63, 46, 14);
		panel_1.add(lblTelefone);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 66, 46, 14);
		panel_1.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(60, 63, 283, 20);
		panel_1.add(tfEmail);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(363, 66, 34, 14);
		panel_1.add(lblCPF);

		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(392, 63, 86, 20);
		panel_1.add(tfCPF);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(7, 97, 59, 14);
		panel_1.add(lblLogradouro);

		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(76, 94, 233, 20);
		panel_1.add(tfLogradouro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(443, 97, 46, 14);
		panel_1.add(lblCidade);

		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(493, 94, 139, 20);
		panel_1.add(tfCidade);

		JLabel lblNum = new JLabel("Número");
		lblNum.setBounds(331, 96, 42, 14);
		panel_1.add(lblNum);

		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(373, 94, 53, 20);
		panel_1.add(tfNumero);

		JLabel lblTUF = new JLabel("UF");
		lblTUF.setBounds(13, 127, 25, 14);
		panel_1.add(lblTUF);

		JComboBox cmbUF = new JComboBox();
		cmbUF.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cmbUF.setBounds(48, 123, 46, 22);
		panel_1.add(cmbUF);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(277, 128, 77, 14);
		panel_1.add(lblComplemento);

		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(355, 125, 255, 20);
		panel_1.add(tfComplemento);

		JLabel lblDataCad = new JLabel("Data do Cadastro");
		lblDataCad.setBounds(10, 159, 96, 14);
		panel_1.add(lblDataCad);

		tfDtCad = new JTextField();
		tfDtCad.setEditable(false);
		tfDtCad.setColumns(10);
		tfDtCad.setBounds(124, 156, 155, 20);
		panel_1.add(tfDtCad);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(124, 128, 25, 14);
		panel_1.add(lblCep);

		tfCEP = new JTextField();
		tfCEP.setColumns(10);
		tfCEP.setBounds(159, 125, 86, 20);
		panel_1.add(tfCEP);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(144, 402, 642, 49);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		/**
	     * Realiza o cadastro de um novo cliente no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.conexao();

					if (con != null) {
						System.out.println("Conexão estabelecida com sucesso.");
					} else {
						System.out.println("Falha ao conectar ao banco de dados.");
					}

					// Verificar se o email já está cadastrado
					String sqlCheckEmail = "SELECT * FROM clientes WHERE email = ?";
					PreparedStatement checkStmt = con.prepareStatement(sqlCheckEmail);
					checkStmt.setString(1, tfEmail.getText());

					ResultSet rs = checkStmt.executeQuery();

					if (rs.next()) {
						// Se já houver um resultado, significa que o email está em uso
						JOptionPane.showMessageDialog(null,
								"Este e-mail já está cadastrado. Por favor, insira um e-mail diferente.");
						return; // Interrompe o processo de cadastro
					}

					rs.close();
					checkStmt.close();

					// Se o e-mail não estiver cadastrado, proceder com a inserção
					String sql = "INSERT INTO clientes(nome, telefone, email, cpf, logradouro, numero, cidade, estado, cep, complemento) "
							+ "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, tfNome.getText());
					stmt.setString(2, tfTelefone.getText());
					stmt.setString(3, tfEmail.getText());
					stmt.setString(4, tfCPF.getText());
					stmt.setString(5, tfLogradouro.getText());
					stmt.setString(6, tfNumero.getText());
					stmt.setString(7, tfCidade.getText());
					stmt.setString(8, cmbUF.getSelectedItem().toString());
					stmt.setString(9, tfCEP.getText());
					stmt.setString(10, tfComplemento.getText());

					// Exibir valores antes de executar
					System.out.println("Nome: " + tfNome.getText());
					System.out.println("Telefone: " + tfTelefone.getText());
					System.out.println("Email: " + tfEmail.getText());
					System.out.println("CPF: " + tfCPF.getText());
					System.out.println("Estado: " + cmbUF.getSelectedItem().toString());

					// Executar a query
					stmt.execute();

					stmt.close();
					con.close();

					JOptionPane.showMessageDialog(null, "Dados Cadastrados!");

					// Limpar os campos
					tfNome.setText("");
					tfTelefone.setText("");
					tfEmail.setText("");
					tfCPF.setText("");
					tfLogradouro.setText("");
					tfNumero.setText("");
					tfCidade.setText("");
					cmbUF.setSelectedIndex(0);
					tfCEP.setText("");
					tfComplemento.setText("");

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		btnCadastrar.setBounds(32, 15, 89, 23);
		panel_2.add(btnCadastrar);
		
		/**
	     * Realiza o cancelamento de um cadastro de cliente no banco de dados.
	     * Limpa os campos que estão com dados.
	     */

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Limpar dados
				tfID.setText("");
				tfNome.setText("");
				tfTelefone.setText("");
				tfEmail.setText("");
				tfCPF.setText("");
				tfLogradouro.setText("");
				tfNumero.setText("");
				tfCidade.setText("");
				cmbUF.setSelectedIndex(0);
				tfCEP.setText("");
				tfComplemento.setText("");
			}
		});
		btnCancelar.setBounds(153, 15, 89, 23);
		panel_2.add(btnCancelar);
		
		/**
	     * Realiza a exclusão de um cliente no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
				} else {
					try {
						Connection con = Conexao.conexao();

						// SQL para deletar apenas com base no ID
						String sql = "DELETE FROM clientes WHERE id = ?";

						PreparedStatement stmt = con.prepareStatement(sql);

						// Definindo apenas o parâmetro ID, já que estamos deletando com base nele
						stmt.setString(1, tfID.getText());

						// Executar a query
						stmt.executeUpdate(); // use executeUpdate para operações de inserção, atualização e exclusão

						stmt.close();
						con.close();

						JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");

						// Limpar os campos após a exclusão
						tfID.setText("");
						tfNome.setText("");
						tfTelefone.setText("");
						tfEmail.setText("");
						tfCPF.setText("");
						tfLogradouro.setText("");
						tfNumero.setText("");
						tfCidade.setText("");
						cmbUF.setSelectedIndex(0);
						tfCEP.setText("");
						tfComplemento.setText("");

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});

		btnExcluir.setBounds(274, 15, 89, 23);
		panel_2.add(btnExcluir);
		
		/**
	     * Fecha a tela aual e abre a tela de início do sistema.
	     */

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Fecha a tela atual (CadastroClientes)
				dispose();

				// Abre a tela Inicio
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
			}
		});
		btnSair.setBounds(516, 15, 89, 23);
		panel_2.add(btnSair);
		
		/**
	     * Realiza a atualização de um cadastro de um cliente no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfID.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Informe o Id");

				} else {
					try {
						Connection con = Conexao.conexao();

						String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, cpf = ?, logradouro = ?, numero = ?, cidade = ?, estado = ?, cep = ?, complemento = ? "
								+ "WHERE id = ?";

						PreparedStatement stmt = con.prepareStatement(sql);

						// Definindo os parâmetros da query
						stmt.setString(1, tfNome.getText());
						stmt.setString(2, tfTelefone.getText());
						stmt.setString(3, tfEmail.getText());
						stmt.setString(4, tfCPF.getText());
						stmt.setString(5, tfLogradouro.getText());
						stmt.setString(6, tfNumero.getText());
						stmt.setString(7, tfCidade.getText());
						stmt.setString(8, cmbUF.getSelectedItem().toString());
						stmt.setString(9, tfCEP.getText());
						stmt.setString(10, tfComplemento.getText());
						stmt.setString(11, tfID.getText());

						// Executar a query
						stmt.execute();

						stmt.close();
						con.close();

						// Limpar os campos
						tfNome.setText("");
						tfTelefone.setText("");
						tfEmail.setText("");
						tfCPF.setText("");
						tfLogradouro.setText("");
						tfNumero.setText("");
						tfCidade.setText("");
						cmbUF.setSelectedIndex(0);
						tfCEP.setText("");
						tfComplemento.setText("");

						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e1.getMessage());
						e1.printStackTrace();
					}

				}
			}
		});
		btnAtualizar.setBounds(395, 15, 89, 23);
		panel_2.add(btnAtualizar);
		
		   /**
	     * Configura os botões de ação na interface.
	     *
	     * @param panel o painel principal onde os botões serão adicionados.
	     */

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(144, 199, 642, 201);
		panel.add(panel_1_1);
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnListar = new JButton("Listar dados");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = Conexao.conexao();

					String sql = "SELECT * FROM clientes";

					PreparedStatement stmt = con.prepareStatement(sql);

					ResultSet rs = stmt.executeQuery();

					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					while (rs.next()) {
						modelo.addRow(new Object[] { rs.getString("id"), rs.getString("nome") });
					}

					rs.close();
					con.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnListar.setBounds(514, 167, 100, 23);
		panel_1_1.add(btnListar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 622, 134);
		panel_1_1.add(scrollPane);

		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Id", "Nome" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbDados);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mvlln\\Downloads\\JEMA VIVI 022-04 (4).jpg"));
		lblNewLabel_1.setBounds(0, 0, 142, 461);
		panel.add(lblNewLabel_1);

		tbDados.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = tbDados.getSelectedRow(); // Obtem a linha selecionada

				// Recupera o ID da linha selecionada
				String selectedId = tbDados.getValueAt(row, 0).toString();

				try {
					Connection con = Conexao.conexao();

					// buscar os dados completos do cliente com base no id
					String sql = "SELECT * FROM clientes WHERE id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, selectedId);

					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {
						// Preenche os campos com os dados recuperados do banco
						tfID.setText(rs.getString("id"));
						tfNome.setText(rs.getString("nome"));
						tfTelefone.setText(rs.getString("telefone"));
						tfEmail.setText(rs.getString("email"));
						tfCPF.setText(rs.getString("cpf"));
						tfLogradouro.setText(rs.getString("logradouro"));
						tfNumero.setText(rs.getString("numero"));
						tfCidade.setText(rs.getString("cidade"));
						cmbUF.setSelectedItem(rs.getString("estado"));
						tfCEP.setText(rs.getString("cep"));
						tfComplemento.setText(rs.getString("complemento"));
						tfDtCad.setText(rs.getString("dt_cadastro"));
					}

					rs.close();
					stmt.close();
					con.close();

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

	}
}