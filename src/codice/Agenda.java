package codice;

import eccezioni.InputErratiException;
import eccezioni.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
//import java.time.ZoneId;
import java.util.*;


// problema: non viene mai usato iterable, devi correggere

/**
 *
 */
// non c'è alcuna gerarchia e non esistono classi abstract e la scrittura su file non deve essere binaria ma semplice
public class Agenda implements  Iterable<Agenda>{

    /*TODO a Inserire un nuovo appuntamento nell’agenda. In tal caso è necessario verificare correttezza
       e completezza dei dati inseriti e che non ci siano sovrapposizioni con altri appuntamenti
        già definiti per quello stesso orario, tenendo ancheconto della durata.*/


    private ArrayList<Appuntamento> listaAppuntamentiDiUnAgenda;
    private ArrayList<Agenda>       listaAgende;
    final   String                  nomeAgenda;


    /**
     * costruttore agenda dato il nome dell'agenda
     * @param nomeAgenda
     */
    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
        this.listaAppuntamentiDiUnAgenda = new ArrayList<>();
        this.listaAgende= new ArrayList<>();
    }

    /**
     * getter della ArrayList deli appuntamenti di un'agenda
     * @return ArrayList Appuntamento>
     */
    public ArrayList<Appuntamento> getListaAppuntamentiDiUnAgenda() {
        return listaAppuntamentiDiUnAgenda;
    }

    /**
     * getter del nome dell' agenda
     * @return String
     */
    public String getNomeAgenda() {
        return nomeAgenda;
    }

    /**
     * getter dell ArrayList dell agenda
     * @return ArrayList Agenda
     */
    public ArrayList<Agenda> getListaAgende() {
        return listaAgende;
    }

    /**
     * oggetto iterator per agenda
     * @return Iterator Agenda
     */
    @Override
    public Iterator<Agenda> iterator() {
        return this.iterator();
    }

    // a lei non piace il confronto delle stringhe con == , poi modificalo

    /**
     * il metodo cerca un appuntamento di un agenda mediante il nome della persona da incontrare
     * @param agenda
     * @param nomePersona
     * @return Appuntamento
     */
    public Appuntamento cercaAppuntamentoPerNome(Agenda agenda, String nomePersona) throws InputErratiException, AppuntamentoInesistenteException {
        if(nomePersona==null|| agenda==null) throw new InputErratiException("errore:nomePersona o agenda nulla");
        for (Appuntamento iteratore: agenda.listaAppuntamentiDiUnAgenda) {
            if(iteratore.getNomePersonaAppuntamento().equals(nomePersona)) return iteratore;
        }
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(nomePersona)) throw new AppuntamentoInesistenteException("errore:non c'è nessuna persona con questo nome che ha fatto una prenotazione");
        return null;
    }

    /**
     * @param agenda
     * @param dataAppuntamento
     * @return
     * @throws AppuntamentoInesistenteException
     */
    public Appuntamento cercaAppuntamentoPerData(Agenda agenda, LocalDate dataAppuntamento) throws AppuntamentoInesistenteException {
        if(dataAppuntamento==null) throw new IllegalArgumentException("errore:nomePersona nulla");
        if(agenda==null) throw new IllegalArgumentException("errore:agenda non esistente");
        for (Appuntamento iteratore: agenda.listaAppuntamentiDiUnAgenda) {
            if(iteratore.getDataAppuntamento().isEqual(dataAppuntamento)) return iteratore;
        }
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(dataAppuntamento)) throw new AppuntamentoInesistenteException("\nerrore:non c'è nessuna prenotazione per quella data\n");
        return null;
    }

    /**
     * @param agenda
     * @param appuntamento
     * @throws InputErratiException
     * @throws AppuntamentoInesistenteException
     */
    // domanda da farle: ma devo far poter modificare ogni combinazione di campo di appuntamento
   public  void modificaAppuntamento(Agenda agenda, Appuntamento appuntamento) throws InputErratiException, AppuntamentoInesistenteException {
        if(appuntamento==null|| agenda==null) throw new InputErratiException("errore:dati inseriri non corretti");
        if(!this.listaAppuntamentiDiUnAgenda.contains(appuntamento)) throw new AppuntamentoInesistenteException("errore:appuntamento non trovato");
        agenda.getListaAppuntamentiDiUnAgenda().add(appuntamento);
   }


    /**
     * questo metodo crea un agendaa mediante una stringa nome
     * @param nome
     * @return Agenda
     * @throws InputErratiException
     * @throws AgendaInesistenteException
     */
    // IllegalArgumentException non le vanno bene bisogna crearne eccezioni a doc oppure utilizzare qualche eccezione molto piu particolare
    public Agenda creaAgendaDalNome(String nome) throws InputErratiException, AgendaInesistenteException {
        if(nome==null) throw new InputErratiException("errore:dati inseriri non corretti");
        Agenda agenda= new Agenda(nome);
        for(Agenda iteratore: getListaAgende()){
            if(iteratore.getNomeAgenda().equals(nome)) throw new AgendaInesistenteException("errore:il nome da lei inserito è gia contenuto nella lista delle agende");
        }
        //if(getListaAgende().contains(nome))

        // questa add è da controllare
        getListaAgende().add(agenda);
        return agenda;
    }

    /**
     * questo metodo crea riempie un agenda leggendo tutti gli appuntamenti dal file agenda.txt
     * @param agenda
     */
    public void creaAgendaDaFile(Agenda agenda){
        Appuntamento appuntamento;
        LocalDate   dataAppuntamento=null;
        LocalTime   orarioAppuntamento=null;
        int         durataAppuntamento=0;
        String      nomePersonaAppuntamento="";
        String      luogoAppuntamento="";

        try{
            FileInputStream fileInputStream = new FileInputStream("src/file/agenda.txt");
            DataInputStream in              = new DataInputStream(fileInputStream);
            BufferedReader br               = new BufferedReader(new InputStreamReader(in));
            // stringa che contiene tutto il testo letto
            String strLine;
            String[] tokens=null;
            //Arrays.fill(tokens, null);
            while ((strLine = br.readLine()) != null){
                tokens = strLine.split(" ");
                int i=5;
                for(String iteratore: tokens){
                    if(i%5==0) dataAppuntamento = LocalDate.parse(iteratore);
                    if(i%6==0) orarioAppuntamento= LocalTime.parse(iteratore);
                    if(i%7==0) durataAppuntamento= Integer.parseInt(iteratore);
                    if(i%8==0) nomePersonaAppuntamento=iteratore;
                    if(i%9==0) {
                        luogoAppuntamento=iteratore;
                        appuntamento= new Appuntamento(dataAppuntamento,orarioAppuntamento,durataAppuntamento,nomePersonaAppuntamento,luogoAppuntamento);
                        agenda.listaAppuntamentiDiUnAgenda.add(appuntamento);
                        i=4;
                    }
                    i++;
                }
            }
            in.close();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("errore: Index out of bounds");
        }
        catch (NumberFormatException ex){
            System.err.println("errore: errore parsing");
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            System.err.println("TEST");
        }
    }

    /**
     * questo metodo scrive tutti gli appuntamenti di una agenda dentro il file agenda.txt
     * @param agenda
     * @throws IOException
     */
    public void ScritturaAgendaSulFile(Agenda agenda) throws IOException {
        try{
            // bisogna vedere se devo gestire nel caso abbia gia un agenda.txt
            FileWriter writer = new FileWriter("src/file/agenda.txt");
            for (Appuntamento iteratore:agenda.listaAppuntamentiDiUnAgenda) {
                writer.write(iteratore.toString());
            }
            writer.close();
        }
        // ma questa cosa, si può fare?
        catch (IOException e){
            throw new IOException("errore: apertura del file è fallita");
        }
    }

    /**
     * questo metodo cancella un agenda
     * @param agenda
     * @throws InputErratiException
     */
    public void cancellaAgenda(Agenda agenda) throws InputErratiException {
        if(agenda==null) throw new InputErratiException("errore:dati inseriri non corretti");
        //if(!this.listaAgende.contains(agenda)) throw new AgendaInesistenteException("errore: vuoi cancellare un agenda che non esiste nella lista delle agende");
        this.listaAgende.remove(agenda);
    }

    /**
     * questo metodo inserisce un appuntamento all'agenda controllando non ci siano sovrapposizione
     * @param agenda
     * @param appuntamento
     * @throws InputErratiException
     * @throws AppuntamentoInesistenteException
     */
    public void inserisciAppuntamentoAllAgenda(Agenda agenda,Appuntamento appuntamento) throws InputErratiException, AppuntamentoInesistenteException, SovrapposizioneAppuntamentiException {
        boolean ceck=false;
        if(agenda==null|| appuntamento ==null) throw new InputErratiException("errore:dati inseriri non corretti");
        if(agenda.listaAppuntamentiDiUnAgenda.contains(appuntamento)) throw new AppuntamentoInesistenteException("errore: appuntamento già presente il agenda");
        if(agenda.listaAppuntamentiDiUnAgenda.size()==0){
            agenda.listaAppuntamentiDiUnAgenda.add(appuntamento);
            return;
        }
        for (Appuntamento iteratore: agenda.getListaAppuntamentiDiUnAgenda()){
            if((iteratore.dataAppuntamento.compareTo(appuntamento.dataAppuntamento))==0){
                if(iteratore.orarioAppuntamento.getHour()==appuntamento.orarioAppuntamento.getHour()){
                    int minutiIteratore=iteratore.orarioAppuntamento.getMinute();
                    int minutiTotaliIteratore=minutiIteratore+iteratore.durataAppuntamento;
                    int minutiInput=appuntamento.orarioAppuntamento.getMinute();
                    int minutiTotaliInput=minutiInput+appuntamento.durataAppuntamento;
                    if(appuntamento.orarioAppuntamento.getMinute()<iteratore.orarioAppuntamento.getMinute() &&((appuntamento.orarioAppuntamento.getMinute()+appuntamento.durataAppuntamento)<iteratore.orarioAppuntamento.getMinute()) ||(appuntamento.orarioAppuntamento.getMinute()>(iteratore.orarioAppuntamento.getMinute()+iteratore.durataAppuntamento)) &&((appuntamento.orarioAppuntamento.getMinute()+appuntamento.durataAppuntamento)>(iteratore.orarioAppuntamento.getMinute()+iteratore.durataAppuntamento))){
                    }
                    else throw new SovrapposizioneAppuntamentiException("");

                }

                if((appuntamento.orarioAppuntamento.getHour()<iteratore.orarioAppuntamento.getHour())&& (appuntamento.orarioAppuntamento.getHour()+((appuntamento.durataAppuntamento+appuntamento.orarioAppuntamento.getMinute())/60))<iteratore.orarioAppuntamento.getHour()
                        || (appuntamento.orarioAppuntamento.getHour()>(iteratore.orarioAppuntamento.getHour()+((iteratore.durataAppuntamento+iteratore.orarioAppuntamento.getMinute())/60)))&& (appuntamento.orarioAppuntamento.getHour()+(appuntamento.orarioAppuntamento.getMinute()+((appuntamento.durataAppuntamento+appuntamento.orarioAppuntamento.getMinute())/60)))>(iteratore.orarioAppuntamento.getHour()+((iteratore.durataAppuntamento+iteratore.orarioAppuntamento.getMinute())/60))){}
                else throw new SovrapposizioneAppuntamentiException("TEST");
            }

        }
        agenda.listaAppuntamentiDiUnAgenda.add(appuntamento);
    }

    /**
     * questo metodo stampa gli appuntamenti in ordine cronologico in base alla data
     * @param agenda
     * @return ArrayList Appuntamento
     * @throws InputErratiException
     */
    public ArrayList<Appuntamento> elencoAppuntamentiOrdinatiPerData(Agenda agenda) throws InputErratiException {
        if(agenda==null) throw new InputErratiException("errore:dati inseriri non corretti");
        //if(agenda.listaAgende.contains(agenda)) throw new AgendaInesistenteException("errore: agenda non esistente");
       // if(!agenda.listaAppuntamentiDiUnAgenda.contains(dataAppuntamento)) throw new AppuntamentoInesistenteException("errore: non ci sono appuntamenti per quella data ");
        Collections.sort(agenda.listaAppuntamentiDiUnAgenda);
       return agenda.listaAppuntamentiDiUnAgenda;
    }

    /**
     * questo metodo controlla se due agende sono uguali mediante il nome
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return nomeAgenda.equals(agenda.nomeAgenda);
    }


}
