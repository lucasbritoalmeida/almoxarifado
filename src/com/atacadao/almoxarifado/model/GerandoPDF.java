/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atacadao.almoxarifado.model;

import com.atacadao.almoxarifado.entidade.Entrada;
import com.atacadao.almoxarifado.entidade.Equipamento;
import com.atacadao.almoxarifado.entidade.Saida;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfBody;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class GerandoPDF {
    public void pdfDeSaida(ArrayList<Equipamento> equipamentos, String solicitante, String autorizante, String responsavel,String numeroSaida){
        Document documento = new Document();
        
        try {
            
            Path path = Paths.get("\\files\\saidas.pdf");
            
            if (!Files.isDirectory(path.getParent())) {
                Files.createDirectory(path.getParent());
                Files.createFile(path);
            }

            
            PdfWriter pdf;
            pdf = PdfWriter.getInstance(documento, new FileOutputStream("\\files\\saidas.pdf"));

            documento.open();
            documento.addTitle("SOLICITAÇÃO DE EQUIPAMENTOS E PRODUTOS");
            
            /**
             * Responsavel pelo cabeçalho do documento
             */
            Image imagem = Image.getInstance("atacadao.jpg");   
            imagem.setAlignment(Element.ALIGN_CENTER);
            documento.add(imagem);
            
            Paragraph titulo = new Paragraph("SOLICITAÇÃO DE EQUIPAMENTOS E PRODUTOS"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 16, 0, BaseColor.BLACK));
           
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            
            documento.setMargins(0, 0, 18, 0);
            
            Date datas = new Date();
            Locale local = new Locale("pt", "BR");
            SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy", local);
            
            Paragraph espaco = new Paragraph("\n\nDeclaro para os devidos fins que eu " + solicitante
                +" recebi na " +sdf.format(datas) + " os equipamentos abaixo relacionados da empresa "
                    + "Atacadão dos Pisos por " + autorizante+" e autorizado por "+ responsavel+".\n"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, 0, BaseColor.BLACK));
            
            documento.add(espaco);
            
            documento.add(new Paragraph("\n Numero de registro : " + numeroSaida+"\n\n"));
            /**
             * Responsavel por cria a tabela da saída dos equipamentos
             */
            PdfPTable pdfT = new PdfPTable(4);
            PdfPCell celulas = new PdfPCell(new Paragraph("Relação de equipamentos solicitados para seus devidos fins."
                    + " Favor caso haja devolução manter o maximo possível do estado atual dos mesmos. Grato !!!"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 11, 0, BaseColor.GRAY)));
            celulas.setColspan(4);
            
            
            PdfPCell patrimonio = new PdfPCell(new Paragraph("PATRIMONIO"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, 0, BaseColor.WHITE)));
            patrimonio.setBackgroundColor(BaseColor.GRAY);
            patrimonio.setPadding((float) 1);
            
            PdfPCell Nome = new PdfPCell(new Paragraph("NOME"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, 0, BaseColor.WHITE)));
            Nome.setBackgroundColor(BaseColor.GRAY);
            Nome.setPadding((float) 1);
            
            PdfPCell Situacao = new PdfPCell(new Paragraph("SITUAÇÃO"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, 0, BaseColor.WHITE)));
            Situacao.setBackgroundColor(BaseColor.GRAY);
            Situacao.setPadding((float) 1);
            
            PdfPCell valor = new PdfPCell(new Paragraph("DESTINO"
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, 0, BaseColor.WHITE)));
            valor.setBackgroundColor(BaseColor.GRAY);
            valor.setPadding((float) 1);
            
            pdfT.addCell(celulas);
            pdfT.addCell(patrimonio);pdfT.addCell(Nome);pdfT.addCell(Situacao);pdfT.addCell(valor);
            
            for (Equipamento equipamento : equipamentos) {
                
                PdfPCell patrimonios = new PdfPCell(new Paragraph(equipamento.getPatrimonio()
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 10, 0, BaseColor.BLACK)));
            patrimonios.setBackgroundColor(BaseColor.WHITE);
            Situacao.setPadding((float) 0.8);
                pdfT.addCell(patrimonios);
                
                PdfPCell nomes = new PdfPCell(new Paragraph(equipamento.getNome()
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 10, 0, BaseColor.BLACK)));
            nomes.setBackgroundColor(BaseColor.WHITE);
            nomes.setPadding((float) 0.8);
                pdfT.addCell(nomes);
                
                PdfPCell situacoes = new PdfPCell(new Paragraph(equipamento.getSituacao()
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 10, 0, BaseColor.BLACK)));
            situacoes.setBackgroundColor(BaseColor.WHITE);
            situacoes.setPadding((float) 0.8);
                pdfT.addCell(situacoes);
                
                PdfPCell valores = new PdfPCell(new Paragraph(equipamento.getCodigo()
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 10, 0, BaseColor.BLACK)));
            valores.setBackgroundColor(BaseColor.WHITE);
            valores.setPadding((float) 0.8);
                pdfT.addCell(valores);
                
            }
            documento.add(pdfT);
            
            /**
             * Cria tabela para assinatura do solicitante e autorizado
             */
            PdfPTable pdfTs = new PdfPTable(5);
          
            Paragraph sol = new Paragraph("\n\n" + solicitante
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, Font.ITALIC));
            sol.setAlignment(Element.ALIGN_CENTER);
            PdfPCell ass = new PdfPCell(sol);
            ass.setBorder(0);
            ass.setBorderWidthTop(1);
            ass.setColspan(2);
            
            PdfPCell espacos = new PdfPCell();
            espacos.setBorder(0);
            
            
            Paragraph auth = new Paragraph("\n\n" + autorizante
                    ,new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 12, Font.ITALIC));
            auth.setAlignment(Element.ALIGN_CENTER);
            PdfPCell ass2 = new PdfPCell(auth);
            ass2.setBorder(0);
            ass2.setBorderWidthTop(1);
            ass2.setColspan(2);
            
            
            pdfTs.addCell(ass);
            pdfTs.addCell(espacos);
            pdfTs.addCell(ass2);
            
            
            documento.add(new Paragraph("\n\n\n"));
            documento.add(pdfTs);
            
            documento.close();            
            ImpressaoDeDocumentos imprimir = new ImpressaoDeDocumentos("\\files\\saidas.pdf");
            
//            Desktop.getDesktop().open(new File("\\files\\saidas.pdf"));
            
        } catch (DocumentException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pdfRelatorioSaida(ArrayList<Saida> saidas){
        Double total = 0.0;
        Document documento = new Document(PageSize.A4.rotate(),0,0,15,15);

        try {
            PdfWriter pdf = PdfWriter.getInstance(documento, new FileOutputStream("rSaidas.pdf"));

            Paragraph titulo = new Paragraph("Relatório de Saída\n\n", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, Font.BOLD));
            titulo.setAlignment(Element.ALIGN_CENTER);
            
            documento.open();
            documento.add(titulo);
            
            PdfPTable table = new PdfPTable(8);
            table.setTotalWidth(new float[] {10,(float) 13.5,(float) 11.5,(float) 13.5,(float) 13.5,(float) 13.5,(float) 12.5,10});
            PdfPCell cSaida = new PdfPCell(new Phrase("N° Saída",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13,Font.BOLD, BaseColor.WHITE)));
            cSaida.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cNome = new PdfPCell(new Phrase("Nome",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cNome.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cPatrimonio = new PdfPCell(new Phrase("Patrimonio",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12, Font.BOLD, BaseColor.WHITE)));
            cPatrimonio.setBackgroundColor(BaseColor.DARK_GRAY);
           
            PdfPCell cSolic = new PdfPCell(new Phrase("Solicitante",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cSolic.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cAtut = new PdfPCell(new Phrase("Autorizado",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cAtut.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cCodigo = new PdfPCell(new Phrase("Codigo",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cCodigo.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cDtSaida = new PdfPCell(new Phrase("Dt Saída",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cDtSaida.setBackgroundColor(BaseColor.DARK_GRAY);
             PdfPCell cValor = new PdfPCell(new Phrase("Valor",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cValor.setBackgroundColor(BaseColor.DARK_GRAY);
            
            table.addCell(cSaida);
            table.addCell(cNome);
            table.addCell(cPatrimonio);
            
            table.addCell(cSolic);
            table.addCell(cAtut);
            table.addCell(cCodigo);
            table.addCell(cDtSaida);
            table.addCell(cValor);
            
            
            for (Saida saida : saidas) {
              DecimalFormat df = new DecimalFormat("#.##");
              PdfPCell sai = new PdfPCell(new Phrase(saida.getRegistro(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell nomes = new PdfPCell(new Phrase(saida.getNome(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell patrimonios = new PdfPCell(new Phrase(saida.getPatrimonio(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              
              PdfPCell solicitantes = new PdfPCell(new Phrase(saida.getSolicitador(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell autorizados = new PdfPCell(new Phrase(saida.getAutorizador(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell codigos = new PdfPCell(new Phrase(saida.getCodigo(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, 0, BaseColor.BLACK)));
              PdfPCell datas = new PdfPCell(new Phrase(FormatosDeData.formatarLongParaDatas(saida.getDatasaida().getTime()),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell valores = new PdfPCell(new Phrase("R$ " + String.valueOf(df.format(saida.getValor())),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              
              nomes.setVerticalAlignment(Element.ALIGN_CENTER);
              nomes.setHorizontalAlignment(Element.ALIGN_CENTER);
              nomes.setUseBorderPadding(true);
              
              solicitantes.setVerticalAlignment(Element.ALIGN_CENTER);
              solicitantes.setHorizontalAlignment(Element.ALIGN_CENTER);
              solicitantes.setUseBorderPadding(true);
              
              autorizados.setVerticalAlignment(Element.ALIGN_CENTER);
              autorizados.setHorizontalAlignment(Element.ALIGN_CENTER);
              autorizados.setUseBorderPadding(true);
              
              codigos.setVerticalAlignment(Element.ALIGN_CENTER);
              codigos.setHorizontalAlignment(Element.ALIGN_CENTER);
              
              table.addCell(sai);table.addCell(nomes);table.addCell(patrimonios);
              table.addCell(solicitantes);table.addCell(autorizados);table.addCell(codigos);table.addCell(datas);
              table.addCell(valores);
              
              total += saida.getValor();
            }
    
            Paragraph pTotal = new Paragraph("TOTAL",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 13, 0, BaseColor.WHITE));
            pTotal.setAlignment(Element.ALIGN_LEFT);
            PdfPCell cTotal = new PdfPCell(pTotal);            
            cTotal.setBackgroundColor(BaseColor.DARK_GRAY);
            cTotal.setColspan(6);
            table.addCell(cTotal);
            
            DecimalFormat decValores = new DecimalFormat("#.##");
            PdfPCell cValores = new PdfPCell(new Paragraph("R$ " + String.valueOf(decValores.format(total)),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK)));
            cValores.setColspan(2);
            table.addCell(cValores);
            
            documento.add(table);
            
            
            documento.close();
            Desktop.getDesktop().open(new File("rSaidas.pdf"));
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }    
    
    public void pdfRelatorioEntrada(ArrayList<Entrada> entradas){
        Double total = 0.0;
        Document documentos = new Document(PageSize.A4.rotate(),0,0,15,15);

        try {
            PdfWriter pdf = PdfWriter.getInstance(documentos, new FileOutputStream("rEntradas.pdf"));

            Paragraph titulos = new Paragraph("Relatório de Entradas\n\n", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, Font.BOLD));
            titulos.setAlignment(Element.ALIGN_CENTER);
            
            documentos.open();
            documentos.add(titulos); 
            
            PdfPTable tables = new PdfPTable(7);
            tables.setTotalWidth(new float[] {10,(float) 13.5,(float) 11.5,(float) 13.5,(float) 13.5,(float) 13.5,10});
            PdfPCell cSaida = new PdfPCell(new Phrase("N° Nota",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13,Font.BOLD, BaseColor.WHITE)));
            cSaida.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cNome = new PdfPCell(new Phrase("Nome",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cNome.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cPatrimonio = new PdfPCell(new Phrase("Destino",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12, Font.BOLD, BaseColor.WHITE)));
            cPatrimonio.setBackgroundColor(BaseColor.DARK_GRAY);
           
            PdfPCell cSolic = new PdfPCell(new Phrase("Valor Equip",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cSolic.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cAtut = new PdfPCell(new Phrase("Fornecedor",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cAtut.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cCodigo = new PdfPCell(new Phrase("Dt Compra",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cCodigo.setBackgroundColor(BaseColor.DARK_GRAY);
            PdfPCell cDtSaida = new PdfPCell(new Phrase("Valor",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 13, Font.BOLD, BaseColor.WHITE)));
            cDtSaida.setBackgroundColor(BaseColor.DARK_GRAY);
            
            tables.addCell(cSaida);
            tables.addCell(cNome);
            tables.addCell(cPatrimonio);
            
            tables.addCell(cSolic);
            tables.addCell(cAtut);
            tables.addCell(cCodigo);
            tables.addCell(cDtSaida);
            
            for (Entrada entrada : entradas) {
              DecimalFormat df = new DecimalFormat("#.##");
              PdfPCell sai = new PdfPCell(new Phrase(entrada.getNumeroNota(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell nomes = new PdfPCell(new Phrase(entrada.getNomeequipamento(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell patrimonios = new PdfPCell(new Phrase(entrada.getCodigoequip(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              
              PdfPCell solicitantes = new PdfPCell(new Phrase(String.valueOf(df.format(entrada.getValorequip())),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell autorizados = new PdfPCell(new Phrase(entrada.getFornecedor(),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              PdfPCell codigos = new PdfPCell(new Phrase(FormatosDeData.formatarLongParaDatas(entrada.getDataCompra().getTime()),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, 0, BaseColor.BLACK)));
              PdfPCell datas = new PdfPCell(new Phrase(String.valueOf(df.format(entrada.getCusto())),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, 0, BaseColor.BLACK)));
              
              
              nomes.setVerticalAlignment(Element.ALIGN_CENTER);
              nomes.setHorizontalAlignment(Element.ALIGN_CENTER);
              nomes.setUseBorderPadding(true);
              
              solicitantes.setVerticalAlignment(Element.ALIGN_CENTER);
              solicitantes.setHorizontalAlignment(Element.ALIGN_CENTER);
              solicitantes.setUseBorderPadding(true);
              
              autorizados.setVerticalAlignment(Element.ALIGN_CENTER);
              autorizados.setHorizontalAlignment(Element.ALIGN_CENTER);
              autorizados.setUseBorderPadding(true);
              
              codigos.setVerticalAlignment(Element.ALIGN_CENTER);
              codigos.setHorizontalAlignment(Element.ALIGN_CENTER);
              
              tables.addCell(sai);tables.addCell(nomes);tables.addCell(patrimonios);
              tables.addCell(solicitantes);tables.addCell(autorizados);tables.addCell(codigos);tables.addCell(datas);
              
              total += entrada.getCusto();
            }
            Paragraph pTotal = new Paragraph("TOTAL",new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 13, 0, BaseColor.WHITE));
            pTotal.setAlignment(Element.ALIGN_LEFT);
            PdfPCell cTotal = new PdfPCell(pTotal);            
            cTotal.setBackgroundColor(BaseColor.DARK_GRAY);
            cTotal.setColspan(5);
            tables.addCell(cTotal);
            
            DecimalFormat decValores = new DecimalFormat("#.##");
            PdfPCell cValores = new PdfPCell(new Paragraph("R$ " + String.valueOf(decValores.format(total)),new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.BLACK)));
            cValores.setColspan(2);
            tables.addCell(cValores);
            
            documentos.add(tables);
            
            
            documentos.close();
             Desktop.getDesktop().open(new File("rEntradas.pdf"));
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void pdfImpressaoBarraDeCodigo(String codigo){
         Document documento =  new Document(new Rectangle(90, 65));
         documento.setMargins(0, 0, 0, 0);
         
        PdfWriter pdf;
            
        try {
            pdf = PdfWriter.getInstance(documento, new FileOutputStream("codigodebarras.pdf"));
             documento.open();
            PdfContentByte contB = pdf.getDirectContent();
            Barcode128 barCode = new Barcode128();
            barCode.setCode(codigo);
            barCode.setCodeType(Barcode128.CODE128);
            
            Image image = barCode.createImageWithBarcode(contB, BaseColor.BLACK, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("ATCADÃO DOS PISOS\n", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 5));
            titulo.setPaddingTop(0);
            titulo.setAlignment(Element.ALIGN_CENTER);
            
            float scaler = ((documento.getPageSize().getWidth() - documento.leftMargin()
               - documento.rightMargin() - 0) / image.getWidth()) * 60;

            image.scalePercent(scaler);
            image.setPaddingTop(0);
            image.setAlignment(Element.ALIGN_CENTER);
            
            documento.add(titulo);
            documento.add(image);
            
            documento.close();
            
            Desktop.getDesktop().open(new File("codigodebarras.pdf"));
            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerandoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
