/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.persistencia;

import com.atacadao.almoxarifado.conectividade.Connections;
import com.atacadao.almoxarifado.entidade.Entrada;
import com.atacadao.almoxarifado.entidade.Equipamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class entradaConexao {
    
    public static void Cadastrar(Entrada entrada){
          Connection conn = Connections.getConnection();    
        String sql = "insert into entrada (numeronota,datadacompra,custo,tipoproduto,fornecedor) values(?,?,?,?,?);";
        PreparedStatement prepare = null;
        try {
             prepare = conn.prepareStatement(sql);
             prepare.setString(1, entrada.getNumeroNota());
             prepare.setDate(2, new Date(entrada.getDataCompra().getTime()));
             prepare.setDouble(3, entrada.getCusto());
             prepare.setString(4, entrada.getProdutos().toString());
             prepare.setString(5, entrada.getFornecedor());
             
             prepare.execute();
             
             prepare.close();
             conn.close();
             
        } catch (SQLException ex) {
            try {
                conn.close();
            } catch (SQLException ex1) {
                System.out.println("erro de conexão");
            }
            System.out.println("erro de conexão");
        }
    }
    
    
}
