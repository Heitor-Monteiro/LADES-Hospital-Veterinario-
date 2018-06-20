/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.controller.consulta.ControllerConsulta;
import com.lades.sihv.controller.consulta.ControllerAnamnese;
import com.lades.sihv.controller.consulta.ControllerExameFisico;
import com.lades.sihv.controller.consulta.ControllerSisDigestorio;
import com.lades.sihv.controller.consulta.ControllerSisRespCardio;
import com.lades.sihv.controller.consulta.ControllerSisUrinarioMamaria;
import com.lades.sihv.controller.consulta.ControllerSisTegumentar;
import com.lades.sihv.controller.consulta.ControllerSisNeurologico;
import com.lades.sihv.controller.consulta.ControllerSisOftalmico;
import com.lades.sihv.controller.consulta.ControllerSisMuscEsque;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class FormsExames implements Serializable {

    private ControllerConsulta controlConsulta;
    private ControllerAnamnese controlAnamnese;
    private ControllerExameFisico controlExameFisico;
    private ControllerSisDigestorio controlSisDigestorio;
    private ControllerSisRespCardio controlSisRespCardio;
    private ControllerSisUrinarioMamaria controlSisUrinarioMamaria;
    private ControllerSisTegumentar controleSisTegumentar;
    private ControllerSisNeurologico controleSisNeurologico;
    private ControllerSisOftalmico controleSisOftalmico;
    private ControllerSisMuscEsque controleSisMuscEsque;

    /*O método verifica quais sistema
    de anamnese foram afetados.*/
    public String sistemasAfetados() {
        String sisAfetados = "";

//        sisAfetados += ("Sim".equals(sisDigestorio.getSistemaAfetado())) ? "Sistema digestório e glândulas anexas, " : "";
//        sisAfetados += ("Sim".equals(sisRespCardio.getSistemaAfetado())) ? "Sistema respiratório e cardiovascular, " : "";
//        sisAfetados += ("Sim".equals(sisUrinarioMamaria.getSistemaAfetado())) ? "Sistema gênito-urinário e glândulas mamárias, " : "";
//        sisAfetados += ("Sim".equals(sisTegumentar.getSistemaAfetado())) ? "Sistema tegumentar, " : "";
//        sisAfetados += ("Sim".equals(sisNeurologico.getSistemaAfetado())) ? "Sistema neurológico, " : "";
//        sisAfetados += ("Sim".equals(sisOftalmico.getSistemaAfetado())) ? "Sistema oftálmico, " : "";
//        sisAfetados += ("Sim".equals(sisMuscEsque.getSistemaAfetado())) ? "Sistema músculo-esquelético, " : "";
        sisAfetados += ("".equals(sisAfetados)) ? "Não houve sistemas afetados" : "";

        return sisAfetados;
    }

//  GETs & SETs
    public ControllerConsulta getControlConsulta() {
        if (controlConsulta == null) {
            controlConsulta = new ControllerConsulta();
        }
        return controlConsulta;
    }

    public ControllerAnamnese getControlAnamnese() {
        if (controlAnamnese == null) {
            controlAnamnese = new ControllerAnamnese();
        }
        return controlAnamnese;
    }

    public ControllerExameFisico getControlExameFisico() {
        if (controlExameFisico == null) {
            controlExameFisico = new ControllerExameFisico();
        }
        return controlExameFisico;
    }

    public ControllerSisDigestorio getControlSisDigestorio() {
        if (controlSisDigestorio == null) {
            controlSisDigestorio = new ControllerSisDigestorio();
        }
        return controlSisDigestorio;
    }

    public ControllerSisRespCardio getControlSisRespCardio() {
        if (controlSisRespCardio == null) {
            controlSisRespCardio = new ControllerSisRespCardio();
        }
        return controlSisRespCardio;
    }

    public ControllerSisUrinarioMamaria getControlSisUrinarioMamaria() {
        if (controlSisUrinarioMamaria == null) {
            controlSisUrinarioMamaria = new ControllerSisUrinarioMamaria();
        }
        return controlSisUrinarioMamaria;
    }

    public ControllerSisTegumentar getControleSisTegumentar() {
        if (controleSisTegumentar == null) {
            controleSisTegumentar = new ControllerSisTegumentar();
        }
        return controleSisTegumentar;
    }

    public ControllerSisNeurologico getControleSisNeurologico() {
        if (controleSisNeurologico == null) {
            controleSisNeurologico = new ControllerSisNeurologico();
        }
        return controleSisNeurologico;
    }

    public ControllerSisOftalmico getControleSisOftalmico() {
        if (controleSisOftalmico == null) {
            controleSisOftalmico = new ControllerSisOftalmico();
        }
        return controleSisOftalmico;
    }

    public ControllerSisMuscEsque getControleSisMuscEsque() {
        if (controleSisMuscEsque == null) {
            controleSisMuscEsque = new ControllerSisMuscEsque();
        }
        return controleSisMuscEsque;
    }
}
