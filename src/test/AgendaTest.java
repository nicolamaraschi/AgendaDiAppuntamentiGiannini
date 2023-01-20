package test;


import codice.*;
import eccezioni.AgendaInesistenteException;
import eccezioni.AppuntamentoInesistenteException;
import eccezioni.InputErratiException;
import eccezioni.SovrapposizioneAppuntamentiException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//TODO fare tutti i test
class AgendaTest {
    Appuntamento ap1,ap2,ap3,ap4,ap5,ap6,ap7;
    Agenda ag1,ag2, ag3,ag4,ag5;
    ArrayList<Appuntamento> testListaAppuntamenti1,testListaAppuntamenti2, testListaAppuntamenti3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

         ap1= new Appuntamento(LocalDate.of(2020,3,20), LocalTime.of(2,15),10,"giovanni","lorenteggio");
         ap7= new Appuntamento(LocalDate.of(2020,3,20), LocalTime.of(7,4),30,"marco","milano");
         ap2= new Appuntamento(LocalDate.of(2019,6,2), LocalTime.of(9,0),30,"paola","spagna");
         ap3= new Appuntamento(LocalDate.of(2022,8,4), LocalTime.of(1,25),20,"ginevra","parigi");
         ap4= new Appuntamento(LocalDate.of(2021,8,26), LocalTime.of(6,4),70,"franco","francia");
         ap5= new Appuntamento(LocalDate.of(2016,1,15), LocalTime.of(4,15),60,"mariana","genova");
         ap6= new Appuntamento(LocalDate.of(2020,12,16), LocalTime.of(7,4),30,"marco","milano");

         ag1= new Agenda("nicola");
         ag2= new Agenda("giovanni");
         ag3= new Agenda( "simone");
         ag4= new Agenda( "lorenzo");
    }

    @org.junit.jupiter.api.Test
    void cercaAppuntamentoPerNome() throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException {
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);
            Appuntamento app1= ag1.cercaAppuntamentoPerNome(ag1,"giovanni");
            Assert.assertEquals("giovanni",app1.getNomePersonaAppuntamento());
        }

    @org.junit.jupiter.api.Test
    void cercaAppuntamentoPerData() throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException {
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);
        Appuntamento app1 = ag1.cercaAppuntamentoPerData(ag1,LocalDate.of(2020,3,20));
        Assert.assertEquals(app1.getDataAppuntamento(),LocalDate.of(2020,3,20));
        Throwable e = assertThrows(AppuntamentoInesistenteException.class, () -> ag1.cercaAppuntamentoPerData(ag1,LocalDate.of(2025,3,20)));
        assertEquals(e.getMessage(), "\nerrore:non c'è nessuna prenotazione per quella data\n");

    }

    @org.junit.jupiter.api.Test
    void modificaAppuntamento() throws AgendaInesistenteException, InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException {
            ag1.creaAgendaDalNome("terenzio");
            ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);
            assertEquals(ag1.getListaAppuntamentiDiUnAgenda().get(0).getDataAppuntamento(),LocalDate.of(2020,3,20));
            Throwable e = assertThrows(AppuntamentoInesistenteException.class, () -> ag1.modificaAppuntamento(ag1,ap1,ap2));
            assertEquals(e.getMessage(), "\nerrore:appuntamento non trovato\n");
            ag1.modificaAppuntamento(ag1,ap2,ap1);
            assertEquals(ag1.getListaAppuntamentiDiUnAgenda().get(0).getDataAppuntamento(),LocalDate.of(2019,6,2));
    }

    @org.junit.jupiter.api.Test
    void creaAgendaDalNome() throws InputErratiException, AgendaInesistenteException {
        ag1.creaAgendaDalNome("terenzio");
        //System.out.println(ag1.getListaAgende().size());
        Assert.assertEquals(1,ag1.getListaAgende().size());
        ag1.creaAgendaDalNome("luca");
        //System.out.println(ag1.getListaAgende().size());
        Assert.assertEquals(2,ag1.getListaAgende().size());
        //ag1.creaAgendaDalNome("luca");
        //Assert.assertThrows(AgendaInesistenteException,"");

    }

    @org.junit.jupiter.api.Test
    void creaAgendaDaFile() {
        ag1.creaAgendaDaFile(ag2);
        Assert.assertEquals(ag1.getListaAgende().size(),0);
        ag1.creaAgendaDaFile(ag3);

    }

    @org.junit.jupiter.api.Test
    void scritturaAgendaSulFile() {
    // come faccio a fare questo test
    }

    @org.junit.jupiter.api.Test
    void cancellaAgenda() throws InputErratiException, AgendaInesistenteException{

        ag1.getListaAgende().add(ag2);
        ag1.getListaAgende().add(ag3);
        ag1.getListaAgende().add(ag4);
        Assert.assertEquals(ag1.getListaAgende().size(),3);
        ag1.cancellaAgenda(ag4);
        Assert.assertEquals(ag1.getListaAgende().size(),2);

    }

    @org.junit.jupiter.api.Test
    void inserisciAppuntantoAllAgenda() throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException {
        //ag1.inserisciAppuntamentoAllAgenda(ag1,LocalDate.of(3,4,2020), LocalTime.of(2,15),10,"giovanni","lorenteggio");
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap1);

        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),1);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap2);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap4);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),4);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap7);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),5);
    }

    @Test
    void elencoAppuntameniOrdinatiPerData() throws InputErratiException, AppuntamentoInesistenteException, AgendaInesistenteException, SovrapposizioneAppuntamentiException {
            // questo metodo non puo essere fatto è solo una stampa ordinata
        ag1.getListaAgende().add(ag2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap1);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap4);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap5);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap6);

        ArrayList<Appuntamento> appuntamentiOrdinati=new ArrayList<>();
        appuntamentiOrdinati=ag1.elencoAppuntamentiOrdinatiPerData(ag2);
        for (Appuntamento iteratore:appuntamentiOrdinati) {
            System.out.println(iteratore.toString());
        }
        Assert.assertEquals(appuntamentiOrdinati.get(0).getDataAppuntamento(),LocalDate.of(2016,1,15));
        Assert.assertEquals(appuntamentiOrdinati.get(1).getDataAppuntamento(),LocalDate.of(2019,6,2));
        Assert.assertEquals(appuntamentiOrdinati.get(2).getDataAppuntamento(),LocalDate.of(2020,3,20));
        Assert.assertEquals(appuntamentiOrdinati.get(3).getDataAppuntamento(),LocalDate.of(2020,12,16));
        Assert.assertEquals(appuntamentiOrdinati.get(4).getDataAppuntamento(),LocalDate.of(2021,8,26));
        Assert.assertEquals(appuntamentiOrdinati.get(5).getDataAppuntamento(),LocalDate.of(2022,8,4));
    }
}