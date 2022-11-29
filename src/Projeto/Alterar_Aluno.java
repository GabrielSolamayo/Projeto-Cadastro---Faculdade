package Projeto;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.*;
import javax.swing.*;

public class Alterar_Aluno extends JFrame implements ActionListener{

	JButton btnAlterar;
	JLabel nome, rg;
	JTextField txtNome, txtRG, txtRA;
	private int a;
	public Alterar_Aluno() {
		a = Integer.parseInt(JOptionPane.showInputDialog("Insira o RA do aluno que deseja alterar: "));
		if(verificarRA(a)) {
			setTitle("Alterar Aluno");
			setSize(500 , 220);
			getContentPane().setLayout(null);

			nome = criarRotulo("Nome: ", 10, 10, 100, 35);
			txtNome = criarTexto(110, 10, 300, 35);

			rg = criarRotulo("RG: ", 10, 60, 100, 35);
			txtRG = criarTexto(110, 60, 300, 35);

			//Criando Botões;
			btnAlterar = criarBotao("Alterar", 155, 120, 130, 30, 'A');
			btnAlterar.addActionListener(this);

			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}else {

		}
	}

	public boolean verificarRA(int ra) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");
			PreparedStatement ps = cn.prepareStatement("Select ra from alunos where ra = " + ra);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "RA nao encontrado");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//Evento dos botoes;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAlterar){
			int resp=JOptionPane.showConfirmDialog(null, "Confirma a alteracao?");
			if(resp == 0) {
				Aluno novo = new Aluno(txtNome.getText(), txtRG.getText());
				String a = novo.getRg();
				if(validarRG(a)) {
					alterarDados(novo);
					limparCampos();
				}
			}
		}
	}

	public boolean validarRG(String A) {
		int a = A.charAt(0) - '0';
		int b = A.charAt(1) - '0';
		int c = A.charAt(2) - '0';
		int d = A.charAt(3) - '0';
		int e = A.charAt(4) - '0';
		int f = A.charAt(5) - '0';
		int g = A.charAt(6) - '0';
		int h = A.charAt(7) - '0';
		int i = A.charAt(8) - '0';
		int result = (2*a) + (3*b) + (4*c) + (5*d) + (6*e) + (7*f) + (8*g) + (9*h) ;
		int abc = 11 -(result % 11);
		if(abc == i) {
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "RG não valido");
			return false;
		}

	}


	//Gravando os dados no BD "projeto_integrador";
	public void alterarDados(Aluno novo) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");
			PreparedStatement ps = cn.prepareStatement("UPDATE alunos SET nome_aluno = ?, rg_aluno = ? WHERE ra = ?");
			ps.setString(1, novo.getNome());
			ps.setString(2, novo.getRg());
			ps.setInt(3, a);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso.");
			ps.close();
			cn.close();
			System.out.println("Conexao encerrada.");            
		} catch (SQLException e) {
			System.out.println("Falha ao tentar alterar o Professor.");
			e.printStackTrace();
		}
	}
	//Metodo para cirar Rotulos;
	private JLabel criarRotulo(String texto, int x, int y, int w, int h) {
		JLabel l1 = new JLabel(texto);
		l1.setBounds(x, y, w, h);
		l1.setFont(new Font("Helvetica", Font.BOLD, 20));
		add(l1);
		return l1;
	}

	//Metodo para criar Textos;
	private JTextField criarTexto(int x, int y, int w, int h) {
		JTextField t1 = new JTextField();
		t1.setBounds(x, y, w, h);
		t1.setFont(new Font("Helvetica", Font.BOLD, 18));        
		//t1.addActionListener(this);
		t1.setHorizontalAlignment(SwingConstants.LEFT);
		add(t1);
		return t1;
	}

	//Metodo para criar botao;
	private JButton criarBotao(String texto, int x, int y, int w, int h, char c) {
		JButton b1 = new JButton(texto);
		b1.setBounds(x, y, w, h);
		b1.setFont(new Font("Helvetica", Font.BOLD, 18));
		b1.setHorizontalAlignment(SwingConstants.CENTER);
		b1.setVerticalAlignment(SwingConstants.CENTER);
		b1.setMnemonic(c); 
		add(b1);
		return b1;
	}

	//Limpando as caixas de textos;
	private void limparCampos() {
		txtNome.setText("");	
		txtRG.setText("");
		txtRA.setText("");
	}
}
