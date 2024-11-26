package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Tela de cadastro de produtos. Permite a inserção, exclusão, atualização e
 * busca dos produtos. Os dados dos produtos são armazenados em um banco de dados
 * relacional. Esta classe estende {@link JFrame} e é uma aplicação de interface
 * gráfica.
 */

public class CadastroFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfNome;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextField tfCnpj;
	private JTextField tfLogradouro;
	private JTextField tfCidade;
	private JTextField tfNumero;
	private JTextField tfComplemento;
	private JTextField tfData;
	private JTextField tfCep;
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
					CadastroFornecedor frame = new CadastroFornecedor();
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
	public CadastroFornecedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Dados ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(151, 0, 509, 209);
		contentPane.add(panel_1);
		
		JLabel lblCadastroDeFornecedores = new JLabel("Cadastro de Fornecedores");
		lblCadastroDeFornecedores.setBounds(58, 11, 140, 14);
		panel_1.add(lblCadastroDeFornecedores);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(48, 32, 42, 20);
		panel_1.add(tfID);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(13, 35, 25, 14);
		panel_1.add(lblId);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(113, 35, 34, 14);
		panel_1.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(156, 32, 343, 20);
		panel_1.add(tfNome);
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(60, 63, 86, 20);
		panel_1.add(tfTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 66, 46, 14);
		panel_1.add(lblTelefone);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(166, 66, 46, 14);
		panel_1.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(216, 63, 283, 20);
		panel_1.add(tfEmail);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(13, 97, 46, 14);
		panel_1.add(lblCnpj);
		
		tfCnpj = new JTextField();
		tfCnpj.setColumns(10);
		tfCnpj.setBounds(63, 94, 86, 20);
		panel_1.add(tfCnpj);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(163, 97, 59, 14);
		panel_1.add(lblLogradouro);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(232, 94, 267, 20);
		panel_1.add(tfLogradouro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(135, 125, 46, 14);
		panel_1.add(lblCidade);
		
		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(185, 122, 207, 20);
		panel_1.add(tfCidade);
		
		JLabel lblNum = new JLabel("Número");
		lblNum.setBounds(13, 124, 46, 14);
		panel_1.add(lblNum);
		
		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(66, 122, 59, 20);
		panel_1.add(tfNumero);
		
		JLabel lblTUF = new JLabel("UF");
		lblTUF.setBounds(418, 125, 25, 14);
		panel_1.add(lblTUF);
		
		JComboBox cmbUF = new JComboBox();
		cmbUF.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", 
				"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cmbUF.setBounds(453, 121, 46, 22);
		panel_1.add(cmbUF);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(166, 156, 77, 14);
		panel_1.add(lblComplemento);
		
		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(244, 153, 255, 20);
		panel_1.add(tfComplemento);
		
		JLabel lblDataCad = new JLabel("Data do Cadastro");
		lblDataCad.setBounds(10, 187, 139, 14);
		panel_1.add(lblDataCad);
		
		tfData = new JTextField();
		tfData.setEditable(false);
		tfData.setColumns(10);
		tfData.setBounds(124, 184, 155, 20);
		panel_1.add(tfData);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(13, 156, 42, 14);
		panel_1.add(lblCep);
		
		tfCep = new JTextField();
		tfCep.setColumns(10);
		tfCep.setBounds(63, 153, 86, 20);
		panel_1.add(tfCep);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(151, 207, 509, 137);
		contentPane.add(panel_1_1);
		
		/**
	     * Lista os dados do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnListar = new JButton("Listar dados");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.conexao();
					
					String sql = "SELECT * FROM fornecedor";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id"), rs.getString("nome"), rs.getString("cnpj")});
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListar.setBounds(399, 108, 100, 23);
		panel_1_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 489, 75);
		panel_1_1.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "CNPJ"
			}
			
			
		));
		scrollPane.setViewportView(tbDados);
		
		tbDados.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tbDados.getSelectedRow(); // Obtem a linha selecionada
		        
		        // Recupera o ID da linha selecionada 
		        String selectedId = tbDados.getValueAt(row, 0).toString();
		        
		        try {
		            Connection con = Conexao.conexao();
		            
		            //  buscar os dados completos do fornecedor com base no id
		            String sql = "SELECT * FROM fornecedor WHERE id = ?";
		            PreparedStatement stmt = con.prepareStatement(sql);
		            stmt.setString(1, selectedId);
		            
		            ResultSet rs = stmt.executeQuery();
		            
		            if (rs.next()) {
		                // Preenche os campos com os dados recuperados do banco
		                tfID.setText(rs.getString("id"));
		                tfNome.setText(rs.getString("nome"));
		                tfTelefone.setText(rs.getString("telefone"));
		                tfEmail.setText(rs.getString("email"));
		                tfCnpj.setText(rs.getString("cnpj"));
		                tfLogradouro.setText(rs.getString("logradouro"));
		                tfNumero.setText(rs.getString("numero"));
		                tfCidade.setText(rs.getString("cidade"));
		                cmbUF.setSelectedItem(rs.getString("uf"));
		                tfCep.setText(rs.getString("cep"));
		                tfComplemento.setText(rs.getString("complemento"));
		                tfData.setText(rs.getString("data_cadastro")); 
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(148, 342, 512, 49);
		contentPane.add(panel_2);
		
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
		            String sqlCheckEmail = "SELECT * FROM fornecedor WHERE email = ?";
		            PreparedStatement checkStmt = con.prepareStatement(sqlCheckEmail);
		            checkStmt.setString(1, tfEmail.getText());

		            ResultSet rs = checkStmt.executeQuery();
		            
		            if (rs.next()) {
		                // Se já houver um resultado, significa que o email está em uso
		                JOptionPane.showMessageDialog(null, "Este e-mail já está cadastrado. Por favor, insira um e-mail diferente.");
		                return; // Interrompe o processo de cadastro
		            }

		            rs.close();
		            checkStmt.close();
		            
		            // Se o e-mail não estiver cadastrado, proceder com a inserção
		            String sql = "INSERT INTO clientes(nome, telefone, email, cnpj, logradouro, numero, cidade, uf, cep, complemento) "
		                    + "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        
		            PreparedStatement stmt = con.prepareStatement(sql);
		        
		            stmt.setString(1, tfNome.getText());
		            stmt.setString(2, tfTelefone.getText());
		            stmt.setString(3, tfEmail.getText());
		            stmt.setString(4, tfCnpj.getText());
		            stmt.setString(5, tfLogradouro.getText());
		            stmt.setString(6, tfNumero.getText());
		            stmt.setString(7, tfCidade.getText());
		            stmt.setString(8, cmbUF.getSelectedItem().toString());
		            stmt.setString(9, tfCep.getText());
		            stmt.setString(10, tfComplemento.getText());
		            
		            // Executar a query
		            stmt.execute();
		            
		            stmt.close();
		            con.close();
		        
		            JOptionPane.showMessageDialog(null, "Dados Cadastrados!");

		            // Limpar os campos
		            tfNome.setText("");
		            tfTelefone.setText("");
		            tfEmail.setText("");
		            tfCnpj.setText("");
		            tfLogradouro.setText("");
		            tfNumero.setText("");
		            tfCidade.setText("");
		            cmbUF.setSelectedIndex(0);
		            tfCep.setText("");
		            tfComplemento.setText("");
		            
		        } catch (SQLException e1) {
		            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e1.getMessage());
		            e1.printStackTrace();
		        }
			}
		});
		btnCadastrar.setBounds(10, 15, 89, 23);
		panel_2.add(btnCadastrar);
		
		/**
	     * Realiza o cancelamento de um cadastro de um produto no banco de dados.
	     * Limpa os campos.
	     */
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tfID.setText("");
				tfNome.setText("");
	            tfTelefone.setText("");
	            tfEmail.setText("");
	            tfCnpj.setText("");
	            tfLogradouro.setText("");
	            tfNumero.setText("");
	            tfCidade.setText("");
	            cmbUF.setSelectedIndex(0);
	            tfCep.setText("");
	            tfComplemento.setText("");
			}
		});
		btnCancelar.setBounds(109, 15, 89, 23);
		panel_2.add(btnCancelar);
		
		/**
	     * Realiza a exclusão de um produto no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	                Connection con = Conexao.conexao();

	                // SQL para deletar apenas com base no ID
	                String sql = "DELETE FROM fornecedor WHERE id = ?";

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
	                tfCnpj.setText("");
	                tfLogradouro.setText("");
	                tfNumero.setText("");
	                tfCidade.setText("");
	                cmbUF.setSelectedIndex(0);
	                tfCep.setText("");
	                tfComplemento.setText("");

	            } catch (SQLException e1) {
	                JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e1.getMessage());
	                e1.printStackTrace();
	            }
			}
		});
		btnExcluir.setBounds(208, 15, 89, 23);
		panel_2.add(btnExcluir);
		
		/**
	     * Fecha a tela atual e abre a tela inicial do programa.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Fecha a tela atual (CadastroFuncionarios)
		        dispose();
		        
		        // Abre a tela Inicio
		        Inicio inicio = new Inicio(); 
		        inicio.setVisible(true);
			}
		});
		btnSair.setBounds(413, 15, 89, 23);
		panel_2.add(btnSair);
		
		/**
	     * Realiza a atualização de um produto no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
if(tfID.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Informe o Id");
				
				}else {
					try {
					    Connection con = Conexao.conexao();
					    
					    String sql = "UPDATE fornecedor SET nome = ?, telefone = ?, email = ?, cnpj = ?, logradouro = ?, numero = ?, cidade = ?, uf = ?, cep = ?, complemento = ?"
					               + "WHERE id = ?";
					    
					    PreparedStatement stmt = con.prepareStatement(sql);
					    
					    // Definindo os parâmetros da query
					    stmt.setString(1, tfNome.getText());
					    stmt.setString(2, tfTelefone.getText());
					    stmt.setString(3, tfEmail.getText());
					    stmt.setString(4, tfCnpj.getText());
					    stmt.setString(5, tfLogradouro.getText());
					    stmt.setString(6, tfNumero.getText());
					    stmt.setString(7, tfCidade.getText());
					    stmt.setString(8, cmbUF.getSelectedItem().toString());
					    stmt.setString(9, tfCep.getText());
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
					    tfCnpj.setText("");
					    tfLogradouro.setText("");
					    tfNumero.setText("");
					    tfCidade.setText("");
					    cmbUF.setSelectedIndex(0);
					    tfCep.setText("");
					    tfComplemento.setText("");
					    
					    JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

					} catch (SQLException e1) {
					    JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e1.getMessage());
					    e1.printStackTrace();
					}
				}	
			}
		});
		btnAtualizar.setBounds(307, 15, 89, 23);
		panel_2.add(btnAtualizar);
	}

}
