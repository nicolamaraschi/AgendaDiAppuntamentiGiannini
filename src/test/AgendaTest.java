package test;


import codice.*;
import eccezioni.*;

import org.junit.Test;
import org.junit.Assert;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;




class AgendaTest {
    Appuntamento ap1,ap2,ap3,ap4,ap5,ap6,ap7,ap8,ap9,ap10;
    Agenda ag1,ag2, ag3,ag4;

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
    void cercaAppuntamentoPerNome() throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException,AppuntamentoGiaPresente {
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);
            Appuntamento app1= ag1.cercaAppuntamentoPerNome(ag1,"giovanni");
            Assert.assertEquals("giovanni",app1.getNomePersonaAppuntamento());
        }

    @org.junit.jupiter.api.Test
    void cercaAppuntamentoPerData() throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException,AppuntamentoGiaPresente {

        ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap2);
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap4);
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap5);
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap6);

        Appuntamento app1 = ag1.cercaAppuntamentoPerData(ag1,LocalDate.of(2021,8,26));
        Assert.assertEquals(app1.getDataAppuntamento(),LocalDate.of(2021,8,26));
        Assert.assertEquals(app1.getNomePersonaAppuntamento(),"franco");
        Assert.assertEquals(app1.getOrarioAppuntamento(),LocalTime.of(6,4));
        Assert.assertEquals(app1.getLuogoAppuntamento(),"francia");


        Throwable e1 = assertThrows(AppuntamentoInesistenteException.class, () -> ag1.cercaAppuntamentoPerData(ag1,LocalDate.of(2025,3,20)));
        assertEquals(e1.getMessage(), "\nerrore:non c'è nessuna prenotazione per quella data\n");

        Throwable e2 = assertThrows(IllegalArgumentException.class, () -> ag1.cercaAppuntamentoPerData(ag1,null));
        assertEquals(e2.getMessage(), "\nerrore:nomePersona nulla\n");

        Throwable e3 = assertThrows(IllegalArgumentException.class, () -> ag1.cercaAppuntamentoPerData(null,LocalDate.of(2025,3,20)));
        assertEquals(e3.getMessage(), "\nerrore:agenda non esistente\n");

    }

    @org.junit.jupiter.api.Test
    void modificaAppuntamento() throws AgendaInesistenteException, InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException, AppuntamentoGiaPresente {
        ag1.creaAgendaDalNome("terenzio");
        ag1.inserisciAppuntamentoAllAgenda(ag1,ap1);

        assertEquals(ag1.getListaAppuntamentiDiUnAgenda().get(0).getDataAppuntamento(),LocalDate.of(2020,3,20));

        Throwable e1 = assertThrows(AppuntamentoInesistenteException.class, () -> ag1.modificaAppuntamento(ag1,ap1,ap2));
        assertEquals(e1.getMessage(), "\nerrore:appuntamento non trovato\n");

        ag1.modificaAppuntamento(ag1,ap2,ap1);
        assertEquals(ag1.getListaAppuntamentiDiUnAgenda().get(0).getDataAppuntamento(),LocalDate.of(2019,6,2));

        Throwable e2 = assertThrows(InputErratiException.class, () -> ag1.modificaAppuntamento(ag1,null,ap2));
        assertEquals(e2.getMessage(), "\nerrore:dati inseriti non corretti\n");

        ag1.inserisciAppuntamentoAllAgenda(ag2,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap2);
        Throwable e3 = assertThrows(AppuntamentoGiaPresente.class, () -> ag1.modificaAppuntamento(ag2,ap3,ap2));
        assertEquals(e3.getMessage(), "\nerrore:appuntamento già presente\n");
    }

    @org.junit.jupiter.api.Test
    void creaAgendaDalNome() throws InputErratiException, AgendaInesistenteException {
        ag1.creaAgendaDalNome("terenzio");

        Assert.assertEquals(1,ag1.getListaAgende().size());
        ag1.creaAgendaDalNome("luca");

        Assert.assertEquals(2,ag1.getListaAgende().size());


        Throwable e1 = assertThrows(InputErratiException.class, () -> ag1.creaAgendaDalNome(null));
        assertEquals(e1.getMessage(), "\nerrore:dati inseriti non corretti\n");

        Throwable e2 = assertThrows(AgendaInesistenteException.class, () -> ag1.creaAgendaDalNome("luca"));
        assertEquals(e2.getMessage(), "\nerrore:il nome da lei inserito è gia contenuto nella lista delle agende\n");

    }

    @org.junit.jupiter.api.Test
    void creaAgendaDaFile() {
        ag1.creaAgendaDaFile(ag2);
        assertEquals(ag1.getListaAgende().size(),0);
        ag1.creaAgendaDaFile(ag3);

    }

    @org.junit.jupiter.api.Test
    void scritturaAgendaSulFile() throws IOException {
        ag2.getListaAppuntamentiDiUnAgenda().add(ap1);
        ag1.ScritturaAgendaSulFile(ag2);
        File file = new File("src/file/agenda.txt");
        int contatoreLinea = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                contatoreLinea++;
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(contatoreLinea,1);
        System.out.println("Line count: " + contatoreLinea);
    }

    @org.junit.jupiter.api.Test
    void cancellaAgenda() throws InputErratiException, AgendaInesistenteException{

        ag1.getListaAgende().add(ag2);
        ag1.getListaAgende().add(ag3);
        ag1.getListaAgende().add(ag4);
        assertEquals(ag1.getListaAgende().size(),3);
        ag1.cancellaAgenda(ag4);
        Assert.assertEquals(ag1.getListaAgende().size(),2);

    }

    @org.junit.jupiter.api.Test
    void inserisciAppuntantoAllAgenda() throws InputErratiException, AppuntamentoGiaPresente, SovrapposizioneAppuntamentiException {

        ag1.inserisciAppuntamentoAllAgenda(ag2,ap1);

        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),1);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap2);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap4);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),4);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap7);
        Assert.assertEquals(ag2.getListaAppuntamentiDiUnAgenda().size(),5);

        ag1.inserisciAppuntamentoAllAgenda(ag2,ap6);
        ap8= new Appuntamento(LocalDate.of(2020,12,16), LocalTime.of(7,0),60,"marco","milano");
        Throwable e1 = assertThrows(SovrapposizioneAppuntamentiException.class, () -> ag1.inserisciAppuntamentoAllAgenda(ag2,ap8));
        assertEquals(e1.getMessage(), "\nerrore: appuntamenti in sovrapposizione\n");

        ap9= new Appuntamento(LocalDate.of(2020,12,16), LocalTime.of(6,0),120,"marco","milano");
        Throwable e2 = assertThrows(SovrapposizioneAppuntamentiException.class, () -> ag1.inserisciAppuntamentoAllAgenda(ag2,ap9));
        assertEquals(e2.getMessage(), "\nerrore: appuntamenti in sovrapposizione\n");

        ap10= new Appuntamento(LocalDate.of(2020,12,16), LocalTime.of(5,0),180,"marco","milano");
        Throwable e3 = assertThrows(SovrapposizioneAppuntamentiException.class, () -> ag1.inserisciAppuntamentoAllAgenda(ag2,ap10));
        assertEquals(e3.getMessage(), "\nerrore: appuntamenti in sovrapposizione\n");

        Throwable e4 = assertThrows(AppuntamentoGiaPresente.class, () -> ag1.inserisciAppuntamentoAllAgenda(ag2,ap7));
        assertEquals(e4.getMessage(), "\nerrore: appuntamento già presente il agenda\n");

        Throwable e5 = assertThrows(InputErratiException.class, () -> ag1.inserisciAppuntamentoAllAgenda(null,ap7));
        assertEquals(e5.getMessage(), "\nerrore:dati inseriti non corretti\n");

        Exception eccezione1= assertThrows(InputErratiException.class,()->{throw new InputErratiException("errore");});
        assertEquals(eccezione1.getMessage(),"errore");



    }

    @Test
    void elencoAppuntameniOrdinatiPerData() throws InputErratiException, AppuntamentoGiaPresente, AgendaInesistenteException, SovrapposizioneAppuntamentiException {

        ag1.getListaAgende().add(ag2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap1);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap2);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap3);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap4);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap5);
        ag1.inserisciAppuntamentoAllAgenda(ag2,ap6);

        ArrayList<Appuntamento> appuntamentiOrdinati = ag1.elencoAppuntamentiOrdinatiPerData(ag2);
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