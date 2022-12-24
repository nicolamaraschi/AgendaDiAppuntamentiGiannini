package test;


import codice.*;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class AgendaTest {
    Appuntamento ap1,ap2,ap3,ap4,ap5,ap6;
    Agenda ag1,ag2, ag3,ag4,ag5;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
         ap1= new Appuntamento(LocalDate.of(2020,3,20), LocalTime.of(2,15),10,"giovanni","lorenteggio");
         ap2= new Appuntamento(LocalDate.of(2019,6,2), LocalTime.of(9,0),30,"paola","spagna");
         ap3= new Appuntamento(LocalDate.of(2022,8,4), LocalTime.of(1,25),20,"ginevra","parigi");
         ap4= new Appuntamento(LocalDate.of(2021,8,26), LocalTime.of(6,4),70,"franco","francia");
         ap5= new Appuntamento(LocalDate.of(2016,1,15), LocalTime.of(4,15),60,"mariana","genova");
         ap6= new Appuntamento(LocalDate.of(2020,12,16), LocalTime.of(7,4),30,"marco","milano");


        ArrayList<Appuntamento> testListaAppuntamenti1;
        testListaAppuntamenti1.add(ap1);
        testListaAppuntamenti1.add(ap2);
        testListaAppuntamenti1.add(ap3);
        testListaAppuntamenti1.add(ap4);

        ArrayList<Appuntamento> testListaAppuntamenti2;
        testListaAppuntamenti2.add(ap5);
        testListaAppuntamenti2.add(ap6);

        ArrayList<Appuntamento> testListaAppuntamenti3;
        testListaAppuntamenti3.add(ap1);
        testListaAppuntamenti3.add(ap2);
        testListaAppuntamenti3.add(ap3);
        testListaAppuntamenti3.add(ap4);
        testListaAppuntamenti3.add(ap5);
        testListaAppuntamenti3.add(ap6);

         ag1= new Agenda(testListaAppuntamenti1,"nicola");
         ag2= new Agenda(testListaAppuntamenti1,"giovanni");
         ag3= new Agenda(testListaAppuntamenti2,"simone");
         ag4= new Agenda(testListaAppuntamenti3,"lorenzo");


    }

    @org.junit.jupiter.api.Test
    void cercaAppuntamentoPerNome() {
            Appuntamento app1= ag1.cercaAppuntamentoPerNome(ag1,"nicola");
            Assert.assertEquals(app1,ap1);
        }

    @org.junit.jupiter.api.Test
    void cercaAppuntamentoPerData() {
        Appuntamento app1 = ag1.cercaAppuntamentoPerData(ag1,LocalDate.of(2020,3,20), LocalTime.of(2,15),10,"giovanni","lorenteggio");
        Assert.assertEquals(app1,ap1);
    }

    @org.junit.jupiter.api.Test
    void modificaAppuntamento() {

    }

    @org.junit.jupiter.api.Test
    void creaAgendaDalNome() {
        ag1.creaAgendaDalNome("terenzio");
        Assert.assertEquals(ag1,ap1);
    }

    @org.junit.jupiter.api.Test
    void creaAgendaDaFile() {
        ag1.creaAgendaDaFile();
        int size=ag1.getListaAgende().size();
        Assert.assertEquals(size,5);
    }

    @org.junit.jupiter.api.Test
    void scritturaAgendaSulFile() {
    // come faccio a fare questo test
    }

    @org.junit.jupiter.api.Test
    void cancellaAgenda() {
        ag1.cancellaAgenda(ag4);
        int size=ag1.getListaAgende().size();
        Assert.assertEquals(size,3);
    }

    @org.junit.jupiter.api.Test
    void inserisciAppuntantoAllAgenda() {
        ag1.inserisciAppuntantoAllAgenda(ag1,LocalDate.of(2020,3,20), LocalTime.of(2,15),10,"giovanni","lorenteggio");
        int size= ag1.getListaAgende().size();
        Assert.assertEquals(size,7);

    }

    @org.junit.jupiter.api.Test
    void elencoAppuntameniPerData() {
            // questo metodo non puo essere fatto è solo una stampa ordinata
    }
}