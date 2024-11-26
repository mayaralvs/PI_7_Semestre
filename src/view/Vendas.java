package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.Conexao;

public class Vendas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField; // Campo de pesquisa
    private JTextField textField_1; // Quantidade
    private JTextField textField_2; // Preço
    private JTextField textField_3; // Total
    private JTable table;
    private JComboBox<String> cbProdutos;
    private ArrayList<String> listaProdutos; // Lista para armazenar produtos
    private ArrayList<Double> listaPrecos; // Lista para armazenar preços
    private DefaultTableModel tableModel; // Modelo da tabela
    private JTextField textField_4; // Total da compra
    private double totalCompra = 0.0; // Campo para armazenar o total acumulado

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Vendas frame = new Vendas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Vendas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 752, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(90, 0, 646, 391);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(72, 11, 70, 25);
        panel.add(lblProduto);
        
        textField = new JTextField();
        textField.setBounds(142, 11, 200, 25);
        panel.add(textField);
        
        cbProdutos = new JComboBox<>();
        cbProdutos.setBounds(142, 41, 200, 25);
        panel.add(cbProdutos);
        
        // Adicionar DocumentListener para o JTextField
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarProdutos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarProdutos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarProdutos();
            }
        });

        // Adicionar ActionListener ao JComboBox
        cbProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarPreco();
            }
        });
        
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(72, 81, 100, 25);
        panel.add(lblQuantidade);
        
        textField_1 = new JTextField();
        textField_1.setBounds(172, 81, 50, 25);
        panel.add(textField_1);
        
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(72, 121, 70, 25);
        panel.add(lblPreco);
        
        textField_2 = new JTextField();
        textField_2.setBounds(142, 121, 100, 25);
        textField_2.setEditable(false);
        panel.add(textField_2);
        
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(72, 161, 70, 25);
        panel.add(lblTotal);
        
        textField_3 = new JTextField();
        textField_3.setBounds(142, 161, 100, 25);
        textField_3.setEditable(false);
        panel.add(textField_3);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(391, 158, 100, 30);
        panel.add(btnAdicionar);
        
        JButton btnFinalizar = new JButton("Finalizar Venda");
        btnFinalizar.setBounds(328, 350, 150, 30);
        panel.add(btnFinalizar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 213, 626, 115);
        panel.add(scrollPane);
        
        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Produto", "Quantidade", "Valor", "Total"
            }
        );
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("Total da Compra: ");
        lblNewLabel.setBounds(10, 350, 99, 22);
        panel.add(lblNewLabel);
        
        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setBounds(119, 350, 86, 25);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Fecha a tela atual (CadastroFuncionarios)
		        dispose();
		        
		        // Abre a tela Inicio
		        Inicio inicio = new Inicio(); 
		        inicio.setVisible(true);
        	}
        });
        btSair.setBounds(525, 350, 111, 30);
        panel.add(btSair);
        
        // Carregar produtos ao iniciar a tela
        carregarProdutos();

        // Adicionar um DocumentListener ao campo de quantidade para atualizar o total
        textField_1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calcularTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calcularTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calcularTotal();
            }
        });
        
        // Adicionar ActionListener ao botão "Adicionar"
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        
        // Adicionar ActionListener ao botão "Finalizar Venda"
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarVenda();
            }
        });
    }

    private void carregarProdutos() {
        listaProdutos = new ArrayList<>(); // Inicializa a lista
        listaPrecos = new ArrayList<>(); // Inicializa a lista de preços
        ArrayList<String> produtos = new ArrayList<>();
        try {
            Connection con = Conexao.conexao();
            String sql = "SELECT nome, preco FROM produtos"; // Altere 'preco' para o nome da coluna correta
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                produtos.add(nome);
                listaProdutos.add(nome); // Adiciona o nome do produto à lista
                listaPrecos.add(preco); // Adiciona o preço à lista
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Atualiza o JComboBox
        cbProdutos.setModel(new DefaultComboBoxModel<>(produtos.toArray(new String[0])));
        
        // Verifica se o ComboBox está vazio e desabilita a ação
        if (produtos.isEmpty()) {
            cbProdutos.setEnabled(false);
        }
    }

    private void atualizarPreco() {
        String produtoSelecionado = (String) cbProdutos.getSelectedItem();
        if (produtoSelecionado != null) {
            int index = listaProdutos.indexOf(produtoSelecionado);
            if (index != -1) {
                double preco = listaPrecos.get(index);
                textField_2.setText(String.valueOf(preco));
                calcularTotal(); // Atualiza o total ao selecionar o produto
            }
        }
    }

    private void calcularTotal() {
        try {
            double preco = Double.parseDouble(textField_2.getText());
            int quantidade = Integer.parseInt(textField_1.getText().isEmpty() ? "0" : textField_1.getText());
            double total = preco * quantidade;
            textField_3.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            textField_3.setText("0");
        }
    }

    private void filtrarProdutos() {
        String filtro = textField.getText().toLowerCase();
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        
        // Limpa o modelo antes de adicionar novos produtos filtrados
        for (String produto : listaProdutos) {
            if (produto.toLowerCase().contains(filtro)) {
                modelo.addElement(produto);
            }
        }
        
        cbProdutos.setModel(modelo);
        
        // Seleciona o primeiro item da lista filtrada
        if (modelo.getSize() > 0) {
            cbProdutos.setSelectedIndex(0);
            atualizarPreco(); // Atualiza o preço do produto selecionado
        } else {
            textField_2.setText("");
            textField_3.setText("");
        }
    }

    private void adicionarProduto() {
        String produto = (String) cbProdutos.getSelectedItem();
        int quantidade = Integer.parseInt(textField_1.getText().isEmpty() ? "0" : textField_1.getText());
        double preco = Double.parseDouble(textField_2.getText());
        double total = Double.parseDouble(textField_3.getText());
        
        // Adiciona uma nova linha na tabela
        tableModel.addRow(new Object[]{produto, quantidade, preco, total});
        
        // Atualiza o total da compra
        totalCompra += total;
        textField_4.setText(String.valueOf(totalCompra));
        
        // Limpa os campos após adicionar o produto
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
    }

    private void finalizarVenda() {
        try {
            Connection con = Conexao.conexao();
            String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE nome = ?"; // Altere para o nome da coluna correta
            PreparedStatement stmt = con.prepareStatement(sql);

            // Percorre as linhas da tabela e atualiza a quantidade no banco de dados
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String produto = (String) tableModel.getValueAt(i, 0);
                int quantidade = (int) tableModel.getValueAt(i, 1);
                stmt.setInt(1, quantidade); // Define a quantidade a ser subtraída
                stmt.setString(2, produto);
                stmt.executeUpdate(); // Executa a atualização
            }

            stmt.close();
            con.close();
            // Limpa a tabela após a venda ser finalizada
            tableModel.setRowCount(0);
            totalCompra = 0.0;
            textField_4.setText(String.valueOf(totalCompra));
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a venda!");
        }
    }
}
