package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Método principal da aplicação. Inicia a interface gráfica.
	 *
	 * @param 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
     * Construtor da tela de Login.
     * Define os componentes da interface e adiciona eventos aos botões.
     */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBounds(190, 265, 75, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSenha.setBounds(190, 305, 75, 14);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Verdana", Font.PLAIN, 16));
		tfUsuario.setBounds(275, 266, 113, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(275, 305, 113, 20);
		contentPane.add(pfSenha);
		
		/**
	     * Realiza o login após verificar se os dados então no banco de dados.
	     * Exibe mensagens de erro em caso de falha.
	     */
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = Conexao.conexao();
					
					String sql = "select * from dados_senhas where usuario=? and senha=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String (pfSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						Inicio exibir = new Inicio();
						exibir.setVisible(true);
						setVisible(false);
					} else {
						
						JOptionPane.showMessageDialog(null, "Esse usuário/senha incorretos");
						
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setBackground(SystemColor.activeCaption);
		btnEntrar.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnEntrar.setBounds(230, 353, 113, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mvlln\\Downloads\\Logotipo_confeitaria_bolos_gradiente_rosa_marrom-removebg-preview (1).png"));
		lblNewLabel_1.setBounds(157, 0, 265, 263);
		contentPane.add(lblNewLabel_1);
	}
}
