package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;
import model.Acoes;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import javax.swing.border.EtchedBorder;

/**
 * Tela de cadastro de funcionarios. Permite a inserção, exclusão, atualização e
 * busca de funcionarios. Os dados dos funcioanrios são armazenados em um banco de dados
 * relacional. Esta classe estende {@link JFrame} e é uma aplicação de interface
 * gráfica.
 */

public class CadastroFuncionarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JTextField tfBuscar;
	private JTable tbDados;

	/**
	 * Método principal da aplicação. Inicia a interface gráfica.
	 *
	 * @param 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionarios frame = new CadastroFuncionarios();
					frame.setVisible(true);
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
	public CadastroFuncionarios() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 740, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(-4, 407, 718, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/**
	     * Realiza o cadastro de um novo funcionario no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				String usuario = tfUsuario.getText();
				String senha = new String(pfSenha.getPassword());

				if (usuario.equals("") || senha.equals("")) {
					JOptionPane.showMessageDialog(null, "Usuário e senha devem ser informados");
				} else {

					try {
						Connection con = Conexao.conexao();
						String sql = "INSERT INTO dados_senhas(usuario, senha) VALUE (?, ?)";

						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setString(1, tfUsuario.getText());
						stmt.setString(2, new String(pfSenha.getPassword()));

						stmt.execute();

						stmt.close();
						con.close();

						tfUsuario.setText("");
						pfSenha.setText("");

						JOptionPane.showMessageDialog(null, "Dados Cadastrados!");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnSalvar.setBounds(10, 21, 102, 23);
		panel.add(btnSalvar);
		
		/**
	     * Realiza a atualização de um  funcionario no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfId.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Informe o Id");

				} else {
					try {
						Connection con = Conexao.conexao();

						String sql = "UPDATE dados_senhas SET usuario = ?, senha = ? WHERE id = ?";

						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setString(1, tfUsuario.getText());
						stmt.setString(2, new String(pfSenha.getPassword()));
						stmt.setString(3, tfId.getText());

						stmt.execute();
						stmt.close();
						con.close();

						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnAtualizar.setBounds(139, 21, 102, 23);
		panel.add(btnAtualizar);
		
		/**
	     * Realiza a exclusão de um funcionario no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
				} else {

					try {
						Connection con = Conexao.conexao();

						String sql = "DELETE FROM dados_senhas  WHERE id = ?";

						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setString(1, tfId.getText());
						stmt.execute();

						stmt.close();
						con.close();

						tfId.setText("");
						tfUsuario.setText("");
						pfSenha.setText("");

						JOptionPane.showMessageDialog(null, "Excluido!");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnExcluir.setBounds(269, 21, 102, 23);
		panel.add(btnExcluir);
		

		/**
	     * Realiza o cancelamento de um cadastro de funcionario no banco de dados.
	     * Limpa os campos que estão com dados.
	     */

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Fecha a tela atual (CadastroFuncionarios)
				dispose();

				// Abre a tela Inicio
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
			}
		});
		btnVoltar.setBounds(400, 21, 102, 23);
		panel.add(btnVoltar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(-4, 328, 718, 46);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		tfBuscar = new JTextField();
		tfBuscar.setBounds(113, 15, 86, 20);
		panel_1.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID ou nome");
				} else {
					Connection con = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;
					try {
						con = Conexao.conexao();

						String sql = "SELECT id, usuario FROM dados_senhas WHERE id LIKE ? OR usuario LIKE ?";
						stmt = con.prepareStatement(sql);

						String searchText = "%" + tfBuscar.getText() + "%";

						stmt.setString(1, searchText); // Buscar por ID
						stmt.setString(2, searchText); // Buscar por nome

						rs = stmt.executeQuery();

						if (rs.next()) {
							// Atualiza os campos com os valores retornados
							tfId.setText(rs.getString("id"));
							tfUsuario.setText(rs.getString("usuario"));
						} else {
							// Se não encontrar o ID, exibe mensagem
							JOptionPane.showMessageDialog(null, "ID não existe.");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						// Fecha os recursos no bloco finally
						try {
							if (rs != null)
								rs.close();
							if (stmt != null)
								stmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnAbrir.setBounds(10, 14, 89, 23);
		panel_1.add(btnAbrir);
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		JButton btnListar = new JButton("Listar dados");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = Conexao.conexao();

					String sql = "SELECT id, usuario FROM dados_senhas";

					PreparedStatement stmt = con.prepareStatement(sql);

					ResultSet rs = stmt.executeQuery();

					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					while (rs.next()) {
						modelo.addRow(new Object[] { rs.getString("id"), rs.getString("usuario") });
					}

					rs.close();
					con.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListar.setBounds(311, 14, 103, 23);
		panel_1.add(btnListar);
		
		 /**
	     * Configura os botões de ação na interface.
	     *
	     * @param panel o painel principal onde os botões serão adicionados.
	     */

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(0, 0, 718, 103);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(288, 62, 142, 20);
		panel_2.add(pfSenha);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(232, 62, 46, 14);
		panel_2.add(lblSenha);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(56, 62, 142, 20);
		panel_2.add(tfUsuario);
		tfUsuario.setColumns(10);

		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(10, 62, 46, 14);
		panel_2.add(lblUsurio);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 31, 46, 14);
		panel_2.add(lblNewLabel);

		tfId = new JTextField();
		tfId.setBounds(56, 31, 86, 20);
		panel_2.add(tfId);
		tfId.setEditable(false);
		tfId.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Listar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(0, 115, 718, 192);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 698, 156);
		panel_3.add(scrollPane);

		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Id", "Usuario" }) {
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
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */

		tbDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = tbDados.getSelectedRow(); // Obtém a linha selecionada
				String selectedId = tbDados.getValueAt(row, 0).toString(); // Obtém o ID da linha selecionada

				try {
					Connection con = Conexao.conexao();

					// Consulta para buscar os dados do funcionário baseado no ID selecionado
					String sql = "SELECT id, usuario FROM dados_senhas WHERE id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, selectedId);

					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {
						// Preenche os campos de texto com os dados retornados
						tfId.setText(rs.getString("id"));
						tfUsuario.setText(rs.getString("usuario"));
						// Não preenche o campo de senha
						pfSenha.setText(""); // Limpa o campo de senha
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
