/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Produto;

/**
 *
 * @author Aluno
 */
public class ProdutoDAO {
    
     public void inserir(Produto p) throws SQLException {
        Connection conexao = FabricaConexao.getConnection();
        try {
            String query = "insert into produto(pronome,provalor) values(?, ?)";
            PreparedStatement st = conexao.prepareStatement(query);
            st.setString(1, p.getNome());
            st.setDouble(2, p.getValor());
            st.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    public void delete(Produto p) throws SQLException{
        Connection conexao = FabricaConexao.getConnection();
        try {
            String query = "delete from produto where id=?";
            PreparedStatement st = conexao.prepareStatement(query);
            st.setInt(1, p.getCodigo());
            st.execute();
        } catch (SQLException ex) {
            System.out.println("não conectado");
        }
    }
    
    public List<Produto> listarRegistros() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        Connection conexao = FabricaConexao.getConnection();
        
        try {
            //ABRE CONEXAO COM BANCO DE DADOS

            // CRIA O COMANDO SQL PARA SELECIONAR TODOS OS DADOS DO BANCO
            String sql = "SELECT proid,pronome,provalor FROM produto";
            //PREPARA O COMANDO PARA UMA INSTRUCAO ACEITAVEL DO SQL
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            //EXECULTA E ALOCA O RESULTADO EM UM OBJETO RESULTSET
            ResultSet resultados = instrucao.executeQuery();
            
            while (resultados.next()) {
                Produto pro = new Produto();
                pro.setCodigo(resultados.getInt("proid"));
                
                pro.setNome(resultados.getString("pronome"));
                
                pro.setValor(resultados.getDouble("provalor"));

                produtos.add(pro);
            }
        } catch (SQLException ex) {

        } 

        return produtos;
    }
    
    
     
     
    
    
    
}