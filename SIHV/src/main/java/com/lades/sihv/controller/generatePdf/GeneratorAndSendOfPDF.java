/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.generatePdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.lades.sihv.controller.ModuleToCollectError;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thiberius
 */
public class GeneratorAndSendOfPDF {

    public Document methodGeneratorPDF(String documentPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(documentPath));
            return document;
        } catch (DocumentException | IOException e) {
            System.err.println("►►►►►►►►►►►►► ERRO public Document methodGeneratorPDF(): " + e.toString());
            new ModuleToCollectError().erroPage500("GeneratorPDF > methodGeneratorPDF", e.toString());
            return null;
        }
    }

    public void sendPDF(boolean shippingType, String documentPath, String dataClient) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String currentDate = df.format(c.getTime());
            String shippingTypeText = (shippingType) ? "attachment" : "inline";
            File file = new File(documentPath);
            byte[] contentPdf = new byte[(int) file.length()];
            try (InputStream is = new FileInputStream(file)) {
                is.read(contentPdf);
            }
            HttpServletResponse res = (HttpServletResponse) FacesContext.
                    getCurrentInstance().
                    getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", shippingTypeText + ";filename=" + currentDate + "-" + dataClient + ".pdf");
            res.getOutputStream().write(contentPdf);
//            res.getOutputStream().flush();
//            res.getOutputStream().close();
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void sendPDF(): " + e.toString());
            new ModuleToCollectError().erroPage500("GeneratorPDF > sendPDF", e.toString());
        }
    }
}
