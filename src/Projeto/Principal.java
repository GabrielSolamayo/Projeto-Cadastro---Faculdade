package Projeto;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.event.*;
import javax.swing.*;


public class Principal extends JFrame implements ActionListener{
	ImageIcon imagem;
	JMenuBar menu;
	JMenu cadastro, alteracao, exclusao, encerrar, imprimir;
	JMenuItem professor, prof, aluno, alu, professores, alunos, pRGF, pRA, sair;

	public static void main(String[] args) {
		new Principal();
	}

	public Principal() {
		setTitle("Faculdade");
		setSize(700, 500); 

		menu = new JMenuBar(); //Criando o Menu;
		setJMenuBar(menu);

		//Criando o conteudo do Menu;
		cadastro = new JMenu("Cadastro");
		alteracao = new JMenu("Alteracão");
		exclusao = new JMenu("Exclusão");
		imprimir = new JMenu("Imprimir");
		encerrar = new JMenu("Encerrar");
		menu.add(cadastro);
		menu.add(alteracao);
		menu.add(exclusao);
		menu.add(imprimir);
		menu.add(encerrar);

		//Criando os items de Cadastro;
		professor = new JMenuItem("Professor");
		professor.addActionListener(this);
		aluno = new JMenuItem("Aluno");
		aluno.addActionListener(this);
		cadastro.add(professor);
		cadastro.add(aluno);

		//Criando os itens de Alteracao;
		prof = new JMenuItem("Professores");
		prof.addActionListener(this);
		alu = new JMenuItem("Alunos");
		alu.addActionListener(this);
		alteracao.add(prof);
		alteracao.add(alu);

		//Criando os itens de Exclusao;
		pRGF = new JMenuItem("Por RGF");
		pRGF.addActionListener(this);
		pRA = new JMenuItem("Por RA");
		pRA.addActionListener(this);
		exclusao.add(pRGF);
		exclusao.add(pRA);

		//Criando os itens de Imprimir;
		professores = new JMenuItem("Professores");
		professores.addActionListener(this);
		alunos = new JMenuItem("Alunos");
		alunos.addActionListener(this);
		imprimir.add(professores);
		imprimir.add(alunos);



		//Criando os itens de Encerrar;
		sair = new JMenuItem("Sair");
		sair.addActionListener(this);
		encerrar.add(sair);

		imagem = new ImageIcon(getClass().getResource("Pinguim.jpg"));
		JLabel jl = new JLabel(imagem);
		add(jl);

		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == aluno) {
			new Cadastro_Aluno();
		}else if(e.getSource() == professor) {
			new Cadastro_Prof();
		}else if (e.getSource() == prof) {
			new Alterar_Prof();					
		}else if (e.getSource() == alu) {
			new Alterar_Aluno();	
		}else if (e.getSource() == pRGF) {
			new Excluir_RGF();
		}else if (e.getSource() == pRA) {
			new Excluir_RA();
		}else if(e.getSource() == professores) {
			System.out.println("\nLista de Professores: \n");
			consultarProfessores();
		}else if (e.getSource() == alunos) {
			System.out.println("\nLista de Alunos: \n");
			consultarAlunos();
		}else if (e.getSource() == sair) {
			int resp = JOptionPane.showConfirmDialog(null,"Confirma o encerramento?");
			if(resp==0)
				System.exit(0);
		}
	}

	public void consultarAlunos() {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador", "root", "");
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT nome_aluno, rg_aluno FROM alunos");
			while(rs.next()) {
				Aluno novo = new Aluno();
				novo.setNome(rs.getString("nome_aluno"));
				novo.setRg(rs.getString("rg_aluno"));
				System.out.println(novo);
			}
			rs.close();
			st.close();
			cn.close();
		}catch(SQLException e) {
			System.out.println("Falha ao realizar a operação");
			e.getMessage();
		}
	}

	public void consultarProfessores() {
		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_integrador", "root", "");
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("SELECT nome_prof, rg_prof FROM professores");
			while(rs.next()) {
				Aluno novo = new Aluno();
				novo.setNome(rs.getString("nome_prof"));
				novo.setRg(rs.getString("rg_prof"));
				System.out.println(novo);
			}
			rs.close();
			st.close();
			cn.close();
		}catch(SQLException e) {
			System.out.println("Falha ao realizar a operação");
			e.getMessage();
		}
	}





}