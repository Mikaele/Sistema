/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pessoa;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import sistema.Util;

/**
 *
 * @author Mikaele
 */
public class PessoaController {
    public void inserirPessoa(Pessoa p) throws SQLException {
        try {
            
            Util util = new Util();
             Connection conexao = util.conecta();
            String sql = "INSERT INTO pessoa (nome, endereco, genero, nascimento ,cpf, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);// note que agora criamos um Statement de forma diferente
			statement.setString(1, p.getNome());
			statement.setString(2, p.getEndereco());
			statement.setString(3, p.getGenero());
			statement.setString(4, p.getNascimento());
			statement.setString(5, p.getCpf());
			statement.setString(6, p.getCargo());
			 
			int rowsInserted = statement.executeUpdate(); // Executa a inserção e retorna valor != 0 se inseriu (ID de inserção do banco)
			if (rowsInserted > 0) {
				System.out.println("Novo usuário inserido com sucesso");
			}
        statement.close();
        conexao.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
    }
public ArrayList getAll()throws SQLException {
        try {
            String sql = "SELECT * FROM pessoa";
            
                                                                Util util= new  Util();
			Connection conexao = util.conecta();
			Statement statement = conexao.createStatement();
			ResultSet result = statement.executeQuery(sql);
                        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
			int count = 0;
			while (result.next()){
                             Pessoa p = new  Pessoa(result.getString("nome"),  result.getString("genero"), result.getString("nascimento"),result.getString("endereco"),result.getString("cpf"),result.getString("cargo"));// inicializa pessoa
                            lista.add(p);
				}
                                
                                statement.close();
                                conexao.close();
	        return  lista;
		
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
                return null;
        } 
    }

public Pessoa getPessoaByCPF( String cpf) throws SQLException{
   String sql= "SELECT * FROM pessoa WHERE cpf like '%"+cpf +"%'"; //Consulta SQL
   Util util= new  Util(); //inicializando minha classe q faz conexão com banco de dados
   Connection conexao = util.conecta(); //faz a conexão com banco
   Statement statement = conexao.createStatement();//usa da conqxão para pegar a credencial para acesso ao banco
   ResultSet result = statement.executeQuery(sql);//executa a consulta SQL e agora retoena valores, por isso ResultSet
   Pessoa p=null;  //declaração de variavel  pessoa 
   while(result.next()){
         p = new  Pessoa(result.getString("nome"),  result.getString("genero"), result.getString("nascimento"),result.getString("endereco"),result.getString("cpf"),result.getString("cargo"));// inicializa pessoa
   }
 return p;

}
	

}
