package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;
import controller.ProdutoDAO;
import model.Produto;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;

/**
 * Tela de cadastro de produtos. Permite a inserção, exclusão, atualização e
 * busca de produtos. Os dados dos produtos são armazenados em um banco de dados
 * relacional. Esta classe estende {@link JFrame} e é uma aplicação de interface
 * gráfica.
 */

public class CadastroProdutos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfNome;
	private JTextField tfFabricante;
	private JTextField tfLote;
	private JTextField tfCusto;
	private JTextField tfCategoria;
	private JTextField tfPublico;
	private JTextField tfFornecedor;
	private JTextField tfValor;
	private JTextField tfQtde;
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
					CadastroProdutos frame = new CadastroProdutos();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
     * Construtor da tela de Cadastro de Produtos.
     * Define os componentes da interface e adiciona eventos aos botões.
     */
	public CadastroProdutos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(146, 184, 627, 206);
		contentPane.add(panel_1_1);
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnListar = new JButton("Listar dados");
		btnListar.setBounds(517, 172, 100, 23);
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.conexao();
					
					String sql = "SELECT * FROM produtos";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					
					modelo.setNumRows(0);
					
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id"), rs.getString("nome"), 
								rs.getString("fabricante"), rs.getString("lote"), rs.getString("custo"), rs.getString("categoria"),
								rs.getString("publico"), rs.getString("fornecedor"), rs.getString("preco"), rs.getString("quantidade")});
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 607, 139);
		panel_1_1.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Fabricante", "Lote", "Custo", "Categoria", "P\u00FAblico", "Fornecedor", "preco", "Quantidade"
			})
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	scrollPane.setViewportView(tbDados);
	
	/**
     * Exibe a lista dos ítens do banco de dados.
     * Exibe mensagens de erro em caso de falha.
     */
	
	tbDados.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	        int row = tbDados.getSelectedRow(); // Obtem a linha selecionada
	        
	        // Recupera o ID da linha selecionada 
	        String selectedId = tbDados.getValueAt(row, 0).toString();
	        
	        try {
	            Connection con = Conexao.conexao();
	            
	            //  buscar os dados completos do cliente com base no id
	            String sql = "SELECT * FROM produtos WHERE id = ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, selectedId);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                // Preenche os campos com os dados recuperados do banco
	            tfID.setText(rs.getString("id"));
		            tfNome.setText(rs.getString("nome"));
		            tfFabricante.setText(rs.getString("fabricante"));
		            tfLote.setText(rs.getString("lote"));
		            tfCusto.setText(rs.getString("custo"));
		            tfCategoria.setText(rs.getString("categoria"));
		            tfPublico.setText(rs.getString("publico"));
		            tfFornecedor.setText(rs.getString("fornecedor"));
		            tfValor.setText(rs.getString("preco"));
		            tfQtde.setText(rs.getString("quantidade"));
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

				
				
		scrollPane.setViewportView(tbDados);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(146, 401, 627, 49);
		contentPane.add(panel_2);
		
		/**
	     * Realiza o cadastro de um novo produto no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
			            Produto produto = new Produto(
			                0, // ID gerado automaticamente pelo banco
			                tfNome.getText(),
			                tfFabricante.getText(),
			                tfLote.getText(),
			                Double.parseDouble(tfCusto.getText()),
			                tfCategoria.getText(),
			                tfPublico.getText(),
			                tfFornecedor.getText(),
			                Double.parseDouble(tfValor.getText()),
			                Integer.parseInt(tfQtde.getText())
			            );

			            ProdutoDAO produtoDAO = new ProdutoDAO();
			            produtoDAO.cadastrar(produto);

			            JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
			            // Limpar campos
			            tfNome.setText("");
			            tfFabricante.setText("");
			            tfLote.setText("");
			            tfCusto.setText("");
			            tfCategoria.setText("");
			            tfPublico.setText("");
			            tfFornecedor.setText("");
			            tfValor.setText("");
			            tfQtde.setText("");

			        } catch (SQLException ex) {
			            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + ex.getMessage());
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Erro de formato: " + ex.getMessage());
			        }
			}
		});
		btnCadastrar.setBounds(32, 15, 89, 23);
		panel_2.add(btnCadastrar);
		

		/**
	     * Realiza o cancelamento de um produto de cliente no banco de dados.
	     * Limpa os campos que estão com dados.
	     */
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Limpar os campos
				tfID.setText("");
	            tfNome.setText("");
	            tfFabricante.setText("");
	            tfLote.setText("");
	            tfCusto.setText("");
	            tfCategoria.setText("");
	            tfPublico.setText("");
	            tfFornecedor.setText("");
	            tfValor.setText("");
	            tfQtde.setText("");
			}
		});
		btnCancelar.setBounds(153, 15, 89, 23);
		panel_2.add(btnCancelar);
		
		/**
	     * Realiza a exclusão de um produto no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfID.getText().equals("")) {
		            JOptionPane.showMessageDialog(null, "Informe o ID do produto a ser excluído.");
		        } else {
		            try {
		                int id = Integer.parseInt(tfID.getText());

		                ProdutoDAO produtoDAO = new ProdutoDAO();
		                produtoDAO.excluir(id);

		                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

		                // Limpar os campos
		                tfNome.setText("");
			            tfFabricante.setText("");
			            tfLote.setText("");
			            tfCusto.setText("");
			            tfCategoria.setText("");
			            tfPublico.setText("");
			            tfFornecedor.setText("");
			            tfValor.setText("");
			            tfQtde.setText("");

		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage());
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
				
				// Fecha a tela atual (CadastroFuncionarios)
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
	     * Realiza a atualização de um produto no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfID.getText().equals("")) {
		            JOptionPane.showMessageDialog(null, "Informe o ID do produto a ser atualizado.");
		        } else {
		            try {
		                Produto produto = new Produto(
		                    Integer.parseInt(tfID.getText()),
		                    tfNome.getText(),
		                    tfFabricante.getText(),
		                    tfLote.getText(),
		                    Double.parseDouble(tfCusto.getText()),
		                    tfCategoria.getText(),
		                    tfPublico.getText(),
		                    tfFornecedor.getText(),
		                    Double.parseDouble(tfValor.getText()),
		                    Integer.parseInt(tfQtde.getText())
		                );

		                ProdutoDAO produtoDAO = new ProdutoDAO();
		                produtoDAO.atualizar(produto);

		                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

		                // Limpar os campos
		                tfNome.setText("");
			            tfFabricante.setText("");
			            tfLote.setText("");
			            tfCusto.setText("");
			            tfCategoria.setText("");
			            tfPublico.setText("");
			            tfFornecedor.setText("");
			            tfValor.setText("");
			            tfQtde.setText("");

		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
		            }
		        }

			}
		});
		btnAtualizar.setBounds(395, 15, 89, 23);
		panel_2.add(btnAtualizar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastro de Produtos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(147, 0, 627, 173);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfQtde = new JTextField();
		tfQtde.setBounds(528, 125, 63, 20);
		panel.add(tfQtde);
		tfQtde.setColumns(10);
		
		tfCusto = new JTextField();
		tfCusto.setBounds(505, 66, 86, 20);
		panel.add(tfCusto);
		tfCusto.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setBounds(260, 32, 253, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setBounds(454, 69, 46, 14);
		panel.add(lblCusto);
		
		JLabel lblQtde = new JLabel("Quantidade:");
		lblQtde.setBounds(459, 128, 67, 14);
		panel.add(lblQtde);
		
		tfValor = new JTextField();
		tfValor.setBounds(346, 122, 86, 20);
		panel.add(tfValor);
		tfValor.setColumns(10);
		
		tfPublico = new JTextField();
		tfPublico.setBounds(346, 91, 86, 20);
		panel.add(tfPublico);
		tfPublico.setColumns(10);
		
		tfLote = new JTextField();
		tfLote.setBounds(346, 63, 86, 20);
		panel.add(tfLote);
		tfLote.setColumns(10);
		
		JLabel lblLote = new JLabel("Lote:");
		lblLote.setBounds(295, 66, 46, 14);
		panel.add(lblLote);
		
		JLabel lblPublico = new JLabel("Público:");
		lblPublico.setBounds(295, 94, 46, 14);
		panel.add(lblPublico);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(295, 125, 46, 14);
		panel.add(lblValor);
		
		tfFornecedor = new JTextField();
		tfFornecedor.setBounds(112, 122, 136, 20);
		panel.add(tfFornecedor);
		tfFornecedor.setColumns(10);
		
		tfCategoria = new JTextField();
		tfCategoria.setBounds(112, 94, 136, 20);
		panel.add(tfCategoria);
		tfCategoria.setColumns(10);
		
		tfFabricante = new JTextField();
		tfFabricante.setBounds(112, 63, 136, 20);
		panel.add(tfFabricante);
		tfFabricante.setColumns(10);
		
		tfID = new JTextField();
		tfID.setBounds(112, 32, 55, 20);
		panel.add(tfID);
		tfID.setEditable(false);
		tfID.setColumns(10);
		
		JLabel lblCod = new JLabel("Código:");
		lblCod.setBounds(35, 38, 46, 14);
		panel.add(lblCod);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(35, 69, 67, 14);
		panel.add(lblFabricante);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(35, 100, 67, 14);
		panel.add(lblCategoria);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(35, 128, 67, 14);
		panel.add(lblFornecedor);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(209, 35, 46, 14);
		panel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mvlln\\Downloads\\JEMA VIVI 022-04 (4).jpg"));
		lblNewLabel.setBounds(0, 0, 142, 461);
		contentPane.add(lblNewLabel);
	}
}
