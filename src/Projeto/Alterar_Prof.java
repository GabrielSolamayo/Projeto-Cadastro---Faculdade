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

public class Alterar_Prof extends JFrame implements ActionListener{

	JButton btnAlterar;
	JLabel nome, rg;
	JTextField txtNome, txtRG;
	private int a;

	public Alterar_Prof() {
		a = Integer.parseInt(JOptionPane.showInputDialog("Insira o RGF do professor que deseja alterar: "));
		if(verificarRGF(a)) {
			setTitle("Alterar Professor");
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

	//Evento dos botoes;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAlterar){
			int resp=JOptionPane.showConfirmDialog(null, "Confirma a alteracao?");
			if(resp == 0) {
				Professor novo = new Professor(txtNome.getText(), txtRG.getText());
				alterarDados(novo);
				limparCampos();
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
	public void alterarDados(Professor novo) {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root", "");

			PreparedStatement ps = cn.prepareStatement("UPDATE professores SET nome_prof = ?, rg_prof = ? WHERE rgf = ?");
			ps.setString(1, novo.getNome());
			ps.setString(2, novo.getRg());
			ps.setInt(3, a);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Professor alterado com sucesso.");
			ps.close();
			cn.close();
			System.out.println("Conexao encerrada.");            
		} catch (SQLException e) {
			System.out.println("Falha ao tentar alterar o Professor.");
			JOptionPane.showMessageDialog(null, "Falha ao tentar alterar Professor - RGR nao encontrado.");
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
