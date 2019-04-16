/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.RacesId;
import com.lades.sihv.model.Species;
import com.lades.sihv.model.SpeciesId;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class EditAnimalSmallData extends AbstractBean {

    private AnimalDataGroup item;
    private final List<Species> listObjSpecies;
    private List<Races> listObjRaces;
    private boolean statusRace;

    public EditAnimalSmallData() {
        System.out.println("►►►►►►►►►►►►► "
                + "EditAnimalSmallData > private EditAnimalSmallData");
        listObjSpecies = new ArrayList();
        instantiateObjects();
        new LoadRegisteredSpeciesDogAndCat()
                .methodLoadRegisteredSpeciesDogAndCat(listObjSpecies);
    }

    private void instantiateObjects() {
        System.out.println("►►►►►►►►►►►►► "
                + "EditAnimalSmallData > private void instantiateObjects()");
        listObjRaces = new ArrayList();
        item = new AnimalDataGroup();
        item.setAnimal(new Animals());
        item.setOldRGHV("0");
        item.setAnimalNameTemp("x");
        item.setSelectTextSpecies("x");
        item.getAnimal().setAnimalName("x");
        item.getAnimal().setGenderAnimal("F");
        item.getAnimal().setAnimalAge("x");
        item.setSpecies(new Species());
        item.getSpecies().setId(new SpeciesId());
    }

    public void methodToSelectSmallAnimalData(AnimalDataGroup selectAnimalDataGroup) {
        System.out.println("►►►►►►►►►►►►► "
                + "EditAnimalSmallData > public void methodToSelectSmallAnimalData");
        item = selectAnimalDataGroup;
        new BreedListing().methodBreedListing(listObjRaces, item);
        statusRace = false;
    }

    public void loadRegisteredBreedsDogAndCat() {
        new BreedListing().methodBreedListing(listObjRaces, item);
        item.setSelectTextBreed("");
    }

    public void methodCheckBreeds() {
        System.out.println("►►►►►►►►►►►►► "
                + "EditAnimalSmallData > public void methodCheckBreeds");
        statusRace = new CheckBreeds().methodCheckBreeds(listObjRaces, item, statusRace);
    }

    public void methodToUpdateSmallAnimalData(List<AnimalDataGroup> listAnimal) {
        System.out.println("►►►►►►►►►►►►► "
                + "EditAnimalSmallData > public void methodToUpdateSmallAnimalData");
        try {
            item.getAnimal().setAnimalName(item.getAnimalNameTemp() + " - " + item.getOldRGHV());
            getDaoGenerico().update(item.getAnimal());
            for (Species specie : listObjSpecies) {
                if (specie.getNameSpecies().equals(item.getSelectTextSpecies())) {
                    item.setSpecies(specie);
                    break;
                }
            }
            if (statusRace) {
                RacesId id = new RacesId();
                id.setSpeciesGenreOrderClassAnimalPkClassAnimal(listObjRaces.get(0).getId().getSpeciesGenreOrderClassAnimalPkClassAnimal());
                id.setSpeciesGenreOrderPkOrder(listObjRaces.get(0).getId().getSpeciesGenreOrderPkOrder());
                id.setSpeciesGenrePkGenre(listObjRaces.get(0).getId().getSpeciesGenrePkGenre());
                id.setSpeciesPkSpecies(item.getSpecies().getId().getPkSpecies());
                item.getRace().setId(id);
                getDaoGenerico().save(item.getRace());
            }

            item.getSmallAnimal().setRaces(item.getRace());
            getDaoGenerico().update(item.getSmallAnimal());

            int num = listAnimal.indexOf(item);
            listAnimal.remove(num);
            listAnimal.add(num, item);
            getObjMessage().info("Os dados foram atualizados com sucesso!", "");
            instantiateObjects();
            RequestContext.getCurrentInstance().execute("PF('dlg2').hide();");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodToUpdateDeathData(): " + e.toString());
            new ModuleToCollectError().erroPage500("DeclareDeathOfSmallAnimal > methodToUpdateDeathData", e.toString());
        }
    }

    // GETs & SETs ------------------------------------------------------------
    public AnimalDataGroup getItem() {
        return item;
    }

    public List<Species> getListObjSpecies() {
        return listObjSpecies;
    }

    public List<Races> getListObjRaces() {
        return listObjRaces;
    }

}
