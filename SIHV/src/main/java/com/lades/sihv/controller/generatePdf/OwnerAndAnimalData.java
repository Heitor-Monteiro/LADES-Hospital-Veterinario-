/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.generatePdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.animal.VariablesAnimal;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.Scheduling;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

/**
 *
 * @author thiberius
 */
public class OwnerAndAnimalData extends AbstractBean {

    public void printOwnerAndAnimalData(Scheduling schedule, VariablesPerson varPerson,
            AddressControl addressControl, VariablesAnimal varAnimal,
            PhonesControl phonesControl) {
        try {
            Document document = getGeneratorAndSendOfPDF().
                    methodGeneratorPDF("/opt/SIHVdocuments/pdfOfOwnerData.pdf");
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            document.open();
            Image image1 = Image.getInstance("/opt/SIHVdocuments/UFPA.png");
            image1.scalePercent(10);
            image1.setAbsolutePosition(37f, 753f);
            document.add(image1);
            PdfPTable table = new PdfPTable(1);
            table.setHorizontalAlignment(400);
            table.setWidthPercentage(58);
            table.deleteBodyRows();
            //----------------------------------------------------------------------
            Phrase ph = new Phrase();
            ph.setFont(boldFont);
            ph.add("\n              UNIVERSIDADE FEDERAL DO PARÁ\n"
                    + "              CAMPUS UNIVERSITÁRIO DE CASTANHAL\n"
                    + "              INSTITUTO DE MEDICINA VETERINÁRIA\n"
                    + "              HOSPITAL VETERINÁRIO\n\n");
            //----------------------------------------------------------------------
            PdfPCell cell1 = new PdfPCell(ph);
            cell1.setBorder(0);
            table.addCell(cell1);
            document.add(table);
            //----------------------------------------------------------------------
            ph.clear();
            ph.setFont(boldFont);
            ph.add("Procedimento: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(schedule.getTypeService());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Data: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            Calendar c = Calendar.getInstance();
            c.setTime(getObjData());
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            ph.add(df.format(c.getTime()));
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("\nNome do animal: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getAnimal().getAnimalName().replaceAll("[1234567890-]", ""));
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   RGHV: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getOldRGHV());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   RGHV do sistema: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getTempRGHV());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Sexo: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getAnimal().getGenderAnimal());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("\nIdade: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getAnimal().getAnimalAge());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Espécie: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getSelectObjSpecies().getNameSpecies());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Raça: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varAnimal.getSelectTextRaces());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Peso: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add("____________");
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Pelagem: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add("____________");
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("\nProprietário: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(varPerson.getPerson().getNamePerson());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            if (!varPerson.getObjCpf().getCpf().isEmpty()) {
                ph.setFont(boldFont);
                ph.add("   CPF/CNPJ: ");
                document.add(ph);
                ph.clear();
                ph.setFont(normalFont);
                ph.add(varPerson.getObjCpf().getCpf());
                document.add(ph);
                ph.clear();
            }
            //----------------------------------------------------------------------
            if (!varPerson.getObjRg().getRg().isEmpty()) {
                ph.setFont(boldFont);
                ph.add("   RG: ");
                document.add(ph);
                ph.clear();
                ph.setFont(normalFont);
                ph.add(varPerson.getObjRg().getRg());
                document.add(ph);
                ph.clear();
            }
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("\nUF: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(addressControl.getVar().getSelectUF().getUf());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Cidade: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(addressControl.getVar().getSelectCity());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Bairro: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(addressControl.getVar().getSelectNeighborhood());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Logradouro: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(addressControl.getVar().getSelectStreet());
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("   Nº: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(addressControl.getVar().getHouse().getNumberHouse() + "\n");
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            if (!addressControl.getVar().getHouse().getComplement().isEmpty()) {
                ph.setFont(boldFont);
                ph.add("Complemento: ");
                document.add(ph);
                ph.clear();
                ph.setFont(normalFont);
                ph.add(addressControl.getVar().getHouse().getComplement());
                document.add(ph);
                ph.clear();
            }
            //----------------------------------------------------------------------
            if (!varPerson.getPerson().getEmail().isEmpty()) {
                ph.setFont(boldFont);
                ph.add("   E-mail: ");
                document.add(ph);
                ph.clear();
                ph.setFont(normalFont);
                ph.add(varPerson.getPerson().getEmail());
                document.add(ph);
                ph.clear();
            }
            //----------------------------------------------------------------------
            if (!addressControl.getVar().getHouse().getNumberCep().isEmpty()) {
                ph.setFont(boldFont);
                ph.add("   CEP: ");
                document.add(ph);
                ph.clear();
                ph.setFont(normalFont);
                ph.add(addressControl.getVar().getHouse().getNumberCep());
                document.add(ph);
                ph.clear();
            }
            //----------------------------------------------------------------------
            ph.setFont(boldFont);
            ph.add("\nFone: ");
            document.add(ph);
            ph.clear();
            ph.setFont(normalFont);
            ph.add(phonesControl.getPhone1().getNumberPhone());
            if (phonesControl.getPhone2().getNumberPhone() != null) {
                ph.add(", " + phonesControl.getPhone2().getNumberPhone());
            }
            if (phonesControl.getPhone3().getNumberPhone() != null) {
                ph.add(", " + phonesControl.getPhone3().getNumberPhone());
            }
            document.add(ph);
            ph.clear();
            //----------------------------------------------------------------------
            document.close();
            getGeneratorAndSendOfPDF().sendPDF(true, "/opt/SIHVdocuments/pdfOfOwnerData.pdf",
                    "RGHV-" + varAnimal.getOldRGHV()
                    + "-" + varAnimal.getAnimal().getAnimalName().
                            replaceAll("[^QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm ]", ""));
        } catch (DocumentException | IOException e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void printOwnerAndAnimalData(): " + e.toString());
            new ModuleToCollectError().erroPage500("OwnerAndAnimalData > printOwnerAndAnimalData", e.toString());
        }
    }
}
