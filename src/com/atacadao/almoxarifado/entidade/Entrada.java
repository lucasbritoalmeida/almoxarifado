/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author lucas
 */
public class Entrada {
    private String numeroNota;
    private String fornecedor;
    private Date dataCompra;
    private Double custo;
    private String nomeequipamento;
    private String codigoequip;
    private Double valorequip;
    private ArrayList<Equipamento> Produtos;

    @Override
    public String toString() {
        Locale local = new Locale("pt","BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy", local);
        return "entrada{" + "numeroNota=" + numeroNota +  ", dataCompra=" + sdf.format(dataCompra) + ", custo=" + custo + ", Produtos" + Produtos + '}';
    }

    public Entrada() {
    }

    public Entrada(String numeroNota, String fornecedor, Date dataCompra, Double custo, String nomeequipamento, String codigoequip, Double valorequip) {
        this.numeroNota = numeroNota;
        this.fornecedor = fornecedor;
        this.dataCompra = dataCompra;
        this.custo = custo;
        this.nomeequipamento = nomeequipamento;
        this.codigoequip = codigoequip;
        this.valorequip = valorequip;
    }
    
    

    public Entrada(String numeroNota, String fornecedor, Date dataCompra, Double custo, ArrayList<Equipamento> Produtos) {
        this.numeroNota = numeroNota;
        this.fornecedor = fornecedor;
        this.dataCompra = dataCompra;
        this.custo = custo;
        this.Produtos = Produtos;
    }

    
    
    public Entrada(String numeroNota, Integer quantidade, Date dataCompra, Double custo, ArrayList<Equipamento> Produtos) {
        this.numeroNota = numeroNota;       
        this.dataCompra = dataCompra;
        this.custo = custo;
        this.Produtos = Produtos;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }
    
    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ArrayList<Equipamento> getProdutos() {
        return Produtos;
    }

    public void setProdutos(ArrayList<Equipamento> Produtos) {
        this.Produtos = Produtos;
    }

    public String getNomeequipamento() {
        return nomeequipamento;
    }

    public void setNomeequipamento(String nomeequipamento) {
        this.nomeequipamento = nomeequipamento;
    }

    public String getCodigoequip() {
        return codigoequip;
    }

    public void setCodigoequip(String codigoequip) {
        this.codigoequip = codigoequip;
    }

    public Double getValorequip() {
        return valorequip;
    }

    public void setValorequip(Double valorequip) {
        this.valorequip = valorequip;
    }
    
}
