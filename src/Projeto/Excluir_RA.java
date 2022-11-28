package Projeto;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.event.*;
import javax.swing.*;

public class Excluir_RA extends JFrame implements ActionListener{

	JButton btnExcluir;
	JLabel  ra, texto;
	JTextField  txtRA;

	public Excluir_RA() {
		setTitle("Excluir Aluno");
		setSize(500 , 350);
		getContentPane().setLayout(null);

		texto = criarRotulo("Digite o RA do Aluno para excluir.", 10, 5, 500, 35);

		ra = criarRotulo("RA: ", 10, 50, 100, 35);
		txtRA = criarTexto(110, 50, 300, 35);

		//Criando Botões;
		btnExcluir = criarBotao("Excluir", 155, 230, 130, 30, 'E');
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
				Aluno novo = new Aluno(txtRA.getText());
				try {
					excluirDados(novo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				limparCampos();
			}
		}
	}


	//Gravando os dados no BD "projeto_integrador";
	private void excluirDados(Aluno novo) throws Exception{
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador","root","");
			pstm = cn.prepareStatement("DELETE FROM alunos WHERE ra = ?");
			pstm.setString(1, novo.getRa());
			JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso.");
			pstm.execute();
		}catch(SQLException e) {
			cn.rollback();
			JOptionPane.showInputDialog("Falha ao acessar");
			throw new Exception("Falha ao acessar a base de dados.", e);
		} finally {
			pstm.close();
			cn.close();
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
		txtRA.setText("");
	}
}
