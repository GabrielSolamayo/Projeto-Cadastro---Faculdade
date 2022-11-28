package Projeto;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.event.*;
import javax.swing.*;

public class Cadastro_Prof extends JFrame implements ActionListener{

	JButton btnGravar;
	JLabel nome, rg, rgf;
	JTextField txtNome, txtRG, txtRGF;

	public Cadastro_Prof() {
		setTitle("Cadastro de Professor");
		setSize(450 , 250);
		getContentPane().setLayout(null);

		nome = criarRotulo("Nome: ", 10, 10, 100, 35);
		txtNome = criarTexto(90, 10, 300, 35);

		rg = criarRotulo("RG: ", 10, 60, 100, 35);
		txtRG = criarTexto(90, 60, 300, 35);

		//Criando Botões;
		btnGravar = criarBotao("Gravar", 155, 130, 130, 30, 'G');
		btnGravar.addActionListener(this);

		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	//Evento dos botoes;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGravar){
			int resp=JOptionPane.showConfirmDialog(null, "Confirma a gravação?");
			if(resp == 0) {
				Professor novo = new Professor(txtNome.getText(), txtRG.getText());
				gravarDados(novo);
				limparCampos();
			}
		}
	}



	//Gravando os dados no BD "projeto_integrador";
	public void gravarDados(Professor novo) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");

			PreparedStatement ps = cn.prepareStatement("INSERT INTO professores(nome_prof, rg_prof) VALUES (?, ?)");
			ps.setString(1, novo.getNome());
			ps.setString(2, novo.getRg());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso.");
			ps.close();
			cn.close();
			System.out.println("Conexão encerrada.");            
		} catch (SQLException e) {
			System.out.println(
					"Falha ao tentar gravar o Professor.");
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
		}
}