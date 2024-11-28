package view;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Compras extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cbProdutos; // ComboBox para produtos

    private JTextField textFieldQuantidade; // Campo para quantidade
    private JTable table; // Tabela para exibir os produtos adicionados
    private DefaultTableModel tableModel; // Modelo da tabela
    private ArrayList<String> listaProdutos; // Lista para armazenar produtos
    private ArrayList<Double> listaPrecos; // Lista para armazenar preços
    private ArrayList<Integer> listaIds; // Lista para armazenar IDs dos produtos
    
    private ArrayList<String> listaFabricantes;
    private ArrayList<String> listaCategorias;
    private ArrayList<String> listaFornecedores;
    private ArrayList<String> listaPublicos;
    
    private JTextField textField_8;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_15;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Compras frame = new Compras();
                frame.setVisible(true);
				frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Compras() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Compras", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(143, 0, 641, 411);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(30, 30, 70, 25);
        panel.add(lblProduto);

        cbProdutos = new JComboBox<>();
        cbProdutos.setBounds(100, 30, 200, 25);
        panel.add(cbProdutos);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(226, 147, 70, 25);
        panel.add(lblQuantidade);

        textFieldQuantidade = new JTextField();
        textFieldQuantidade.setBounds(299, 147, 64, 25);
        panel.add(textFieldQuantidade);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(531, 218, 100, 30);
        panel.add(btnAdicionar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 259, 597, 141);
        panel.add(scrollPane);

        table = new JTable();
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Produto", "Quantidade", "Preço", "Total" });
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        JLabel lblCod = new JLabel("Código:");
        lblCod.setBounds(341, 36, 46, 14);
        panel.add(lblCod);

        textField_8 = new JTextField();
        textField_8.setEditable(false); // Não editável
        textField_8.setBounds(418, 30, 55, 20);
        panel.add(textField_8);
        
        JLabel lblLote = new JLabel("Lote:");
        lblLote.setBounds(30, 89, 46, 14);
        panel.add(lblLote);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(81, 86, 86, 25);
        panel.add(textField);
        
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(30, 147, 46, 14);
        panel.add(lblValor);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(81, 142, 86, 25);
        panel.add(textField_1);
        
        JLabel lblCusto = new JLabel("Custo:");
        lblCusto.setBounds(226, 92, 46, 14);
        panel.add(lblCusto);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(277, 89, 86, 25);
        panel.add(textField_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mvlln\\Downloads\\JEMA VIVI 022-04 (4).jpg"));
        lblNewLabel.setBounds(0, 0, 142, 461);
        contentPane.add(lblNewLabel);
        
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Fecha a tela atual (Compras)
				dispose();

				// Abre a tela Inicio
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
        	}
        });
        btnSair.setBounds(674, 422, 100, 30);
        contentPane.add(btnSair);

        // Carregar produtos no ComboBox ao iniciar
        carregarProdutos();
        carregarFabricantes();
        carregarCategorias();
        carregarFornecedores();
        carregarPublicos();

        cbProdutos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = cbProdutos.getSelectedIndex();
                    if (index >= 0) {
                        textField_8.setText(String.valueOf(listaIds.get(index)));
                    }
                }
            }
        });

        btnAdicionar.addActionListener(e -> adicionarProduto());
    }
    
    private void limparCampos() {
        textFieldQuantidade.setText("");
        textField.setText("");       // Campo Lote
        textField_1.setText("");     // Campo Valor
        textField_2.setText("");     // Campo Custo
        // Adicione outros campos, se necessário
    }


    private void carregarProdutos() {
        listaProdutos = new ArrayList<>();
        listaPrecos = new ArrayList<>();
        listaIds = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT id, nome, preco FROM produtos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                listaProdutos.add(nome);
                listaPrecos.add(preco);
                listaIds.add(id);
            }
            

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        cbProdutos.setModel(new DefaultComboBoxModel<>(listaProdutos.toArray(new String[0])));
    }

    private void carregarFabricantes() {
        listaFabricantes = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT fabricante FROM produtos"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaFabricantes.add(rs.getString("fabricante"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar fabricantes!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarCategorias() {
        listaCategorias = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT categoria FROM produtos"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaCategorias.add(rs.getString("categoria"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar categorias!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarFornecedores() {
        listaFornecedores = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT fornecedor FROM produtos"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaFornecedores.add(rs.getString("fornecedor"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar fornecedores!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarPublicos() {
        listaPublicos = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT DISTINCT publico FROM produtos"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                listaPublicos.add(rs.getString("publico"));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar públicos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarProduto() {
        String produto = (String) cbProdutos.getSelectedItem();
        int quantidade = Integer.parseInt(textFieldQuantidade.getText());
        double preco = listaPrecos.get(cbProdutos.getSelectedIndex());
        double total = quantidade * preco;
        
        // Obter os valores de lote, valor e custo
        String lote = textField.getText();
        double preco1 = Double.parseDouble(textField_1.getText());
        double custo = Double.parseDouble(textField_2.getText());

        // Adiciona à tabela
        tableModel.addRow(new Object[] { produto, quantidade, preco1, total });

        // Atualiza o estoque no banco de dados
        int produtoId = listaIds.get(cbProdutos.getSelectedIndex());
        atualizarEstoque(produtoId, quantidade, lote, preco, custo);
        
        limparCampos();
    }

    private void atualizarEstoque(int produtoId, int quantidadeComprada, String lote, double preco, double custo) {
        try {
            // Conectar ao banco de dados
            Connection con = Conexao.conexao();

            // SQL para buscar a quantidade atual do produto
            String sqlBuscaEstoque = "SELECT quantidade, lote, preco, custo FROM produtos WHERE id = ?";
            PreparedStatement stmtBuscaEstoque = con.prepareStatement(sqlBuscaEstoque);
            stmtBuscaEstoque.setInt(1, produtoId);
            ResultSet rs = stmtBuscaEstoque.executeQuery();

            if (rs.next()) {
            	// double preco = Double.parseDouble(textField_1.getText());
                int quantidadeAtual = rs.getInt("quantidade");
                int novaQuantidade = quantidadeAtual + quantidadeComprada;
                
                // Verifica se há estoque suficiente
                if (novaQuantidade >= 0) {
                    // SQL para atualizar a quantidade, lote, valor e custo
                    String sqlAtualizarEstoque = "UPDATE produtos SET quantidade = ?, lote = ?, preco = ?, custo = ? WHERE id = ?";
                    PreparedStatement stmtAtualizarEstoque = con.prepareStatement(sqlAtualizarEstoque);
                    stmtAtualizarEstoque.setInt(1, novaQuantidade);
                    stmtAtualizarEstoque.setString(2, lote);  // Atualizando o lote
                    stmtAtualizarEstoque.setDouble(3, preco); // Atualizando o valor
                    stmtAtualizarEstoque.setDouble(4, custo); // Atualizando o custo
                    stmtAtualizarEstoque.setInt(5, produtoId); // ID do produto
                    stmtAtualizarEstoque.executeUpdate();
                    
                    stmtAtualizarEstoque.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Estoque insuficiente para a quantidade solicitada!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            rs.close();
            stmtBuscaEstoque.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar estoque!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
