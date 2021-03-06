/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.entidade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */

public class Saida {
    
    private String registro;
    private String solicitador;
    private String autorizador;
    private String patrimonio;
    private String nome;
    private Date validade;
    private String situacao;
    private String codigo;
    private String tipo;
    private Double valor;
    private Date datasaida;

    public Saida(String registro, String solicitador, String autorizador, String patrimonio, String nome, Date validade, String situacao, String codigo, String tipo, Double valor) {
        this.registro = registro;
        this.solicitador = solicitador;
        this.autorizador = autorizador;
        this.patrimonio = patrimonio;
        this.nome = nome;
        this.validade = validade;
        this.situacao = situacao;
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Saida(String registro, String solicitador, String autorizador, String patrimonio, String nome, Date validade, String situacao, String codigo, String tipo, Double valor, Date datasaida) {
        this.registro = registro;
        this.solicitador = solicitador;
        this.autorizador = autorizador;
        this.patrimonio = patrimonio;
        this.nome = nome;
        this.validade = validade;
        this.situacao = situacao;
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
        this.datasaida = datasaida;
    }
    
    

    public Saida() {
    }

    @Override
    public String toString() {
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", local);
        return "Saida{" + "registro=" + registro + ", solicitador=" + solicitador + ", autorizador=" + autorizador + ", patrimonio=" + patrimonio + ", nome=" + nome + ", validade=" + validade + ", situacao=" + situacao + ", codigo=" + codigo + ", tipo=" + tipo + ", valor=" + valor + ", datasaida=" + sdf.format(datasaida) + '}';
    }   

    
    public Date getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(Date datasaida) {
        this.datasaida = datasaida;
    }
    
    
    
    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


    
    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    
    public String getSolicitador() {
        return solicitador;
    }

    public void setSolicitador(String solicitador) {
        this.solicitador = solicitador;
    }

    
    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }
}
