package Projeto;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.event.*;
import javax.swing.*;


public class Principal extends JFrame implements ActionListener{
	ImageIcon imagem;
	JMenuBar menu;
	JMenu cadastro, alteracao, exclusao, encerrar;
	JMenuItem professor, prof, aluno, alu, pRGF, pRA, sair;

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
		encerrar = new JMenu("Encerrar");
		menu.add(cadastro);
		menu.add(alteracao);
		menu.add(exclusao);
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
		pRA = new JMenuItem("Por RA");
		exclusao.add(pRGF);
		exclusao.add(pRA);

		//Criando os itens de Encerrar;
		sair = new JMenuItem("Sair");
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
		}
	}
	
}