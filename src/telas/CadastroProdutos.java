package telas;

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

import classes.Produto;
import conexao.Conexao;
import conexao.ProdutoDAO;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Produtos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(240, 0, 171, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblCod = new JLabel("Código:");
		lblCod.setBounds(27, 69, 46, 14);
		contentPane.add(lblCod);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(104, 63, 55, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(201, 66, 46, 14);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(252, 63, 253, 20);
		contentPane.add(tfNome);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(27, 100, 67, 14);
		contentPane.add(lblFabricante);
		
		tfFabricante = new JTextField();
		tfFabricante.setColumns(10);
		tfFabricante.setBounds(104, 94, 136, 20);
		contentPane.add(tfFabricante);
		
		JLabel lblLote = new JLabel("Lote:");
		lblLote.setBounds(287, 97, 46, 14);
		contentPane.add(lblLote);
		
		tfLote = new JTextField();
		tfLote.setColumns(10);
		tfLote.setBounds(338, 94, 86, 20);
		contentPane.add(tfLote);
		
		JLabel lblCusto = new JLabel("Custo:");
		lblCusto.setBounds(470, 100, 46, 14);
		contentPane.add(lblCusto);
		
		tfCusto = new JTextField();
		tfCusto.setColumns(10);
		tfCusto.setBounds(521, 97, 86, 20);
		contentPane.add(tfCusto);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(27, 131, 67, 14);
		contentPane.add(lblCategoria);
		
		tfCategoria = new JTextField();
		tfCategoria.setColumns(10);
		tfCategoria.setBounds(104, 125, 136, 20);
		contentPane.add(tfCategoria);
		
		JLabel lblPublico = new JLabel("Público:");
		lblPublico.setBounds(287, 125, 46, 14);
		contentPane.add(lblPublico);
		
		tfPublico = new JTextField();
		tfPublico.setColumns(10);
		tfPublico.setBounds(338, 122, 86, 20);
		contentPane.add(tfPublico);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(27, 159, 67, 14);
		contentPane.add(lblFornecedor);
		
		tfFornecedor = new JTextField();
		tfFornecedor.setColumns(10);
		tfFornecedor.setBounds(104, 153, 136, 20);
		contentPane.add(tfFornecedor);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(30, 184, 638, 137);
		contentPane.add(panel_1_1);
		
		/**
	     * Exibe a lista dos ítens do banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnListar = new JButton("Listar dados");
		btnListar.setBounds(528, 108, 100, 23);
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
		scrollPane.setBounds(10, 22, 618, 75);
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
		panel_2.setBounds(27, 319, 641, 49);
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
		btnCadastrar.setBounds(10, 15, 89, 23);
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
		btnCancelar.setBounds(109, 15, 89, 23);
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
		btnExcluir.setBounds(208, 15, 89, 23);
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
		btnAtualizar.setBounds(307, 15, 89, 23);
		panel_2.add(btnAtualizar);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(287, 156, 46, 14);
		contentPane.add(lblValor);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(338, 153, 86, 20);
		contentPane.add(tfValor);
		
		JLabel lblQtde = new JLabel("Quantidade:");
		lblQtde.setBounds(475, 159, 67, 14);
		contentPane.add(lblQtde);
		
		tfQtde = new JTextField();
		tfQtde.setColumns(10);
		tfQtde.setBounds(552, 153, 86, 20);
		contentPane.add(tfQtde);
	}
}
