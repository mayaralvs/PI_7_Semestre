package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

/**
 * Tela de início. Permite a navegação no sistema. Esta classe estende
 * {@link JFrame} e é uma aplicação de interface gráfica.
 */

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame1 = new Inicio();
					frame1.setVisible(true);
					frame1.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construtor da tela de início.
	 */

	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "In\u00EDcio", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 633, 371);
		contentPane.add(panel);

		/**
		 * Cria um botão com ícone e ação associada.
		 * 
		 * @param acao Abrir a tela de cadastro de funcionario.
		 * @return JButton configurado.
		 */

		JButton btnCadFun = new JButton("Cadastrar Funcionário");
		btnCadFun.setBounds(69, 62, 139, 87);
		btnCadFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroFuncionarios exibir = new CadastroFuncionarios();
				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});
		panel.setLayout(null);
		btnCadFun.setIcon(new ImageIcon("C:\\Users\\mvlln\\OneDrive\\Documents\\PI 3o Semestre\\Ícones\\usuario.png"));
		btnCadFun.setVerticalTextPosition(JButton.BOTTOM); // Texto embaixo
		btnCadFun.setHorizontalTextPosition(JButton.CENTER); // Centraliza o texto
		panel.add(btnCadFun);

		/**
		 * Cria um botão com ícone e ação associada.
		 * 
		 * @param acao Abrir a tela de cadastro de clientes.
		 * @return JButton configurado.
		 */

		JButton btnCadCliente = new JButton("Cadastro de Clientes");
		btnCadCliente.setBounds(232, 62, 139, 87);
		btnCadCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroClientes exibir = new CadastroClientes();

				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});

		btnCadCliente.setIcon(
				new ImageIcon("C:\\Users\\mvlln\\OneDrive\\Documents\\PI 3o Semestre\\Ícones\\usuarios-alt.png"));
		btnCadCliente.setVerticalTextPosition(JButton.BOTTOM);
		btnCadCliente.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnCadCliente);

		/**
		 * Cria um botão com ícone e ação associada.
		 * 
		 * @param acao Abrir a tela de cadastro de produtos.
		 * @return JButton configurado.
		 */

		JButton btnCadProduto = new JButton("Cadastro de Produtos");
		btnCadProduto.setBounds(391, 62, 139, 87);
		btnCadProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroProdutos exibir = new CadastroProdutos();
				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCadProduto
				.setIcon(new ImageIcon("C:\\Users\\mvlln\\OneDrive\\Documents\\PI 3o Semestre\\Ícones\\caixas.png"));
		btnCadProduto.setVerticalTextPosition(JButton.BOTTOM);
		btnCadProduto.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnCadProduto);

		/**
		 * Cria um botão com ícone e ação associada.
		 * 
		 * @param acao Abrir a tela de cadastro de fornecedores.
		 * @return JButton configurado.
		 */

		JButton btnCadForn = new JButton("Cadastro de Fornecedores");
		btnCadForn.setBounds(69, 186, 144, 87);
		btnCadForn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroFornecedor exibir = new CadastroFornecedor();
				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnCadForn.setIcon(
				new ImageIcon("C:\\Users\\mvlln\\OneDrive\\Documents\\PI 3o Semestre\\Ícones\\lado-do-caminhao.png"));
		btnCadForn.setVerticalTextPosition(JButton.BOTTOM);
		btnCadForn.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnCadForn);

		/**
		 * Cria um botão com ícone e ação associada.
		 * 
		 * @param acao Abrir a tela de vendas.
		 * @return JButton configurado.
		 */

		JButton btnVendas = new JButton("Vendas");
		btnVendas.setBounds(232, 186, 139, 87);
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vendas exibir = new Vendas();
				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});

		btnVendas.setIcon(new ImageIcon(
				"C:\\Users\\mvlln\\OneDrive\\Documents\\PI 3o Semestre\\Ícones\\carrinho-de-compras.png"));
		btnVendas.setVerticalTextPosition(JButton.BOTTOM);
		btnVendas.setHorizontalTextPosition(JButton.CENTER);
		panel.add(btnVendas);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\mvlln\\Downloads\\Logotipo_confeitaria_bolos_gradiente_rosa_marrom-removebg-preview (2).png"));
		lblNewLabel.setBounds(512, 249, 114, 100);
		panel.add(lblNewLabel);

		JButton btnVendas_1 = new JButton("Compras");
		btnVendas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Compras exibir = new Compras();
				exibir.setVisible(true);
				exibir.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVendas_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVendas_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVendas_1.setBounds(391, 186, 139, 87);
		panel.add(btnVendas_1);
	}
}
