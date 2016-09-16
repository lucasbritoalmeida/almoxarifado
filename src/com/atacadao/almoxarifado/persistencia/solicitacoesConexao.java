package com.atacadao.almoxarifado.persistencia;


import com.atacadao.almoxarifado.conectividade.Connections;
import com.atacadao.almoxarifado.entidade.Equipamento;
import com.atacadao.almoxarifado.entidade.Solicitacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class solicitacoesConexao {
    public static void cadastrar(Solicitacao solicitacaos, String usuario){
        Connection conn = Connections.getConnection();
        String sql = "INSERT INTO solicitacoes (produto,datasol,situacao,responsavel,usuario) " +
                    "VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, solicitacaos.getProduto());
            preparedStatement.setDate(2, new Date(solicitacaos.getDataSolicitacao().getTime()));
            preparedStatement.setString(3, "PENDENTE");
            preparedStatement.setString(4, solicitacaos.getResponsavel());
            preparedStatement.setString(5, usuario);
            
            preparedStatement.execute();
            
            if (!conn.isClosed()) {
                preparedStatement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(solicitacoesConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Solicitacao> buscar(String usuario){
        Connection conn = Connections.getConnection();
        String sql = "select * from solicitacoes where usuario LIKE ?";
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, usuario);
            
            ResultSet executeQuery = preparedStatement.executeQuery();
            while (executeQuery.next()) {
                
                solicitacoes.add(new Solicitacao(executeQuery.getInt("id")
                        , executeQuery.getString("produto"), new java.util.Date(executeQuery.getDate("datasol").getTime())
                        , new java.util.Date(executeQuery.getDate("datalib").getTime()), 
                        executeQuery.getString("situacao"), executeQuery.getString("responsavel")));
            }
            
            if (!conn.isClosed()) {
                preparedStatement.close();
                conn.close();
                return solicitacoes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(solicitacoesConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
        public static ArrayList<Solicitacao> buscarTodos(String situacao, java.util.Date dataSrc, java.util.Date dataFinal){
        Connection conn = Connections.getConnection();
        String sql = "SELECT * FROM solicitacoes "
                + "WHERE situacao LIKE ?"
                + "AND datasol BETWEEN ? AND ? ";
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%"+situacao+"%");
            
            if (dataSrc != null) {
                preparedStatement.setDate(2, new Date(dataSrc.getTime()));
            }else{
                preparedStatement.setDate(2, new java.sql.Date(999999999999L));
            }
            
            if (dataFinal != null) {
                preparedStatement.setDate(3, new Date(dataFinal.getTime()));
            }else{
                preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
            }
            
            ResultSet executeQuery = preparedStatement.executeQuery();
            while (executeQuery.next()) {
                
                solicitacoes.add(new Solicitacao(executeQuery.getInt("id")
                        , executeQuery.getString("produto"), new java.util.Date(executeQuery.getDate("datasol").getTime())
                        ,new java.util.Date(executeQuery.getDate("datalib").getTime())
                        ,executeQuery.getString("situacao"), executeQuery.getString("responsavel")));
            }
            
            if (!conn.isClosed()) {
                preparedStatement.close();
                conn.close();
                return solicitacoes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(solicitacoesConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
