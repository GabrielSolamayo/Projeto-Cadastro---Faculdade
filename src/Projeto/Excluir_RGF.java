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

public class Excluir_RGF extends JFrame implements ActionListener{

	JButton btnExcluir;
	JLabel  rgf, texto;
	JTextField  txtRGF;

	public Excluir_RGF() {
		setTitle("Excluir Professor");
		setSize(450 , 230);
		getContentPane().setLayout(null);

		texto = criarRotulo("Digite o RGF do professor para excluir.", 10, 5, 500, 35);

		rgf = criarRotulo("RGF: ", 10, 50, 100, 35);
		txtRGF = criarTexto(110, 50, 300, 35);

		//Criando Botões;
		btnExcluir = criarBotao("Excluir", 155, 130, 130, 30, 'E');
		btnExcluir.addActionListener(this);

		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	//Evento dos botoes;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExcluir){
			int resp=JOptionPane.showConfirmDialog(null, "Confirma a exclusao?");
			if(resp == 0) {
				Professor novo = new Professor(txtRGF.getText());
				int a = Integer.parseInt(novo.getRgf());
				if (verificarRGF(a)) {
					excluirDados(novo);
					limparCampos();
				}
			}
		}
	}

	public boolean verificarRGF(int rgf) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");
			PreparedStatement ps = cn.prepareStatement("Select rgf from professores where rgf = " + rgf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "RGF nao encontrado");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	//Gravando os dados no BD "projeto_integrador";
	public void excluirDados(Professor novo) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");

			PreparedStatement ps = cn.prepareStatement("DELETE FROM professores WHERE rgf = ?");
			ps.setString(1, novo.getRgf());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Professor excluido com sucesso.");
			ps.close();
			cn.close();
			System.out.println("Conexao encerrada.");            
		} catch (SQLException e) {
			System.out.println("Falha ao tentar excluir o Professor.");
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
		txtRGF.setText("");
	}
}
