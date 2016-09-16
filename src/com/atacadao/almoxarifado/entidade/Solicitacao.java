/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.entidade;

import java.util.Date;

/**
 *
 * @author lucas
 */
public class Solicitacao {
    private int id;
    private String produto;
    private Date dataSolicitacao;
    private Date dataLiberacao;
    private String situacao;
    private String responsavel;

    public Solicitacao(String produto, Date dataSolicitacao, String responsavel) {
        this.produto = produto;
        this.dataSolicitacao = dataSolicitacao;
        this.responsavel = responsavel;
    }

    
    
    public Solicitacao(int id, String produto, Date dataSolicitacao, Date dataLiberacao, String situacao, String responsavel) {
        this.id = id;
        this.produto = produto;
        this.dataSolicitacao = dataSolicitacao;
        this.dataLiberacao = dataLiberacao;
        this.situacao = situacao;
        this.responsavel = responsavel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(Date dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
