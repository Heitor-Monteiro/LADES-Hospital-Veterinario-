/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisUrinarioMamaria;

/**
 *
 * @author thiberius
 */
public class VisualizarConsulta extends AbstractBean {

    public FormsExames viewCONSULTA(String pkConsulta) {
        FormsExames exames = new FormsExames();

        exames.getControlAnamnese().setAnamnese((Anamnese) getDaoGenerico().list("select a from Anamnese a where a.id.pkAnamnese=" + pkConsulta).get(0));
        exames.getControlExameFisico().setExameFisico((ExameFisico) getDaoGenerico().list("select s from ExameFisico s where s.id.pkExameFisico=" + pkConsulta).get(0));
        exames.getControlSisDigestorio().setSisDigestorio((SisDigestorio) getDaoGenerico().list("select s from SisDigestorio s where s.id.pkSisDigestorio=" + pkConsulta).get(0));
        exames.getControlSisRespCardio().setSisRespCardio((SisRespCardio) getDaoGenerico().list("select s from SisRespCardio s where s.id.pkSisRespCardio=" + pkConsulta).get(0));
        exames.getControlSisUrinarioMamaria().setSisUrinarioMamaria((SisUrinarioMamaria) getDaoGenerico().list("select s from SisUrinarioMamaria s where s.id.pkSisUrinarioMamaria=" + pkConsulta).get(0));
        exames.getControleSisTegumentar().setSisTegumentar((SisTegumentar) getDaoGenerico().list("select s from SisTegumentar s where s.id.pkSisTegumentar=" + pkConsulta).get(0));
        exames.getControleSisNeurologico().setSisNeurologico((SisNeurologico) getDaoGenerico().list("select s from SisNeurologico s where s.id.pkSisNeurologico=" + pkConsulta).get(0));
        exames.getControleSisOftalmico().setSisOftalmico((SisOftalmico) getDaoGenerico().list("select s from SisOftalmico s where s.id.pkSisOftalmico=" + pkConsulta).get(0));
        exames.getControleSisMuscEsque().setSisMuscEsque((SisMuscEsque) getDaoGenerico().list("select s from SisMuscEsque s where s.id.pkSisMuscEsque=" + pkConsulta).get(0));

        System.out.println("pkConsulta " + pkConsulta + " ====================================\n");
        System.out.println("Anamnese: " + exames.getControlAnamnese().getAnamnese().getQueixaPrincipal());

        return exames;
    }
}
