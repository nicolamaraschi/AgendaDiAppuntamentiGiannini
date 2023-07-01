package codice;

import eccezioni.InputErratiException;
import eccezioni.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;



/**
 *
 */
public class Agenda {

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
        if(dataAppuntamento==null) throw new IllegalArgumentException("\nerrore:nomePersona nulla\n");
        if(agenda==null) throw new IllegalArgumentException("\nerrore:agenda non esistente\n");
        for (Appuntamento iteratore: agenda.listaAppuntamentiDiUnAgenda) {
            if(iteratore.getDataAppuntamento().isEqual(dataAppuntamento)) return iteratore;
        }
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(dataAppuntamento)) throw new AppuntamentoInesistenteException("\nerrore:non c'è nessuna prenotazione per quella data\n");
        return null;
    }

    /**
     * @param agenda
     * @param appuntamentoModificato
     * @throws InputErratiException
     * @throws AppuntamentoInesistenteException
     */

   public  void modificaAppuntamento(Agenda agenda, Appuntamento appuntamentoModificato, Appuntamento appuntamentoVecchio) throws InputErratiException, AppuntamentoInesistenteException, AppuntamentoGiaPresente {

        if(appuntamentoModificato==null|| appuntamentoVecchio==null || agenda==null) throw new InputErratiException("\nerrore:dati inseriti non corretti\n");

        if(!this.listaAppuntamentiDiUnAgenda.contains(appuntamentoVecchio)) throw new AppuntamentoInesistenteException("\nerrore:appuntamento non trovato\n");
        agenda.getListaAppuntamentiDiUnAgenda().remove(appuntamentoVecchio);

       if(agenda.listaAppuntamentiDiUnAgenda.contains(appuntamentoModificato)) throw new AppuntamentoGiaPresente("\nerrore:appuntamento già presente\n");

       agenda.getListaAppuntamentiDiUnAgenda().add(appuntamentoModificato);
   }
    /**
     * questo metodo crea un agenda mediante una stringa nome
     * @param nome
     * @return Agenda
     * @throws InputErratiException
     * @throws AgendaInesistenteException
     */

    public Agenda creaAgendaDalNome(String nome) throws InputErratiException, AgendaInesistenteException {
        if(nome==null) throw new InputErratiException("\nerrore:dati inseriti non corretti\n");
        Agenda agenda= new Agenda(nome);
        for(Agenda iteratore: getListaAgende()){
            if(iteratore.getNomeAgenda().equals(nome)) throw new AgendaInesistenteException("\nerrore:il nome da lei inserito è gia contenuto nella lista delle agende\n");
        }
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
            String[] tokens;
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
        }
    }

    /**
     * questo metodo scrive tutti gli appuntamenti di una agenda dentro il file agenda.txt
     * @param agenda
     * @throws IOException
     */
    public void ScritturaAgendaSulFile(Agenda agenda) throws IOException {

        File file = new File("src/file/agenda.txt");
        // Verificare se il file esiste
        if (file.exists()) {
            try {
                // Aprire il file in modalità di append
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                // Scrivere l'oggetto sul file
                for (Appuntamento iteratore:agenda.listaAppuntamentiDiUnAgenda){
                    bw.write(iteratore.toString());
                    bw.newLine();
                }
                bw.close();
                System.out.println("\nScrittura avvenuta con successo\n");
            } catch (IOException e) {
                System.out.println("\nErrore: scrittura sul file fallita\n");
            }
        } else {
            System.out.println("\nerrore: Il file non esiste, verrà creato\n");
            try {
                // Creare il file
                file.createNewFile();
                System.out.println("\nFile creato con successo\n");
                // Aprire il file in modalità di append
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                // Scrivere l'oggetto sul file
                for (Appuntamento iteratore:agenda.listaAppuntamentiDiUnAgenda){
                    bw.write(iteratore.toString());
                    bw.newLine();
                }
                bw.close();
                System.out.println("\nOggetto scritto sul file con successo\n");
            } catch (IOException e) {
                System.out.println("\nErrore: creazione del file fallita\n");
            }
        }
    }

    /**
     * questo metodo cancella un agenda
     * @param agenda
     * @throws InputErratiException
     */
    public void cancellaAgenda(Agenda agenda) throws InputErratiException, AgendaInesistenteException {
        if(agenda==null) throw new InputErratiException("errore:dati inseriti non corretti");
        if(!this.listaAgende.contains(agenda)) throw new AgendaInesistenteException("errore: vuoi cancellare un agenda che non esiste nella lista delle agende");
        this.listaAgende.remove(agenda);
    }

    /**
     * questo metodo inserisce un appuntamento all'agenda controllando non ci siano sovrapposizione
     * @param agenda
     * @param appuntamento
     * @throws InputErratiException
     * @throws AppuntamentoGiaPresente
     */
    public void inserisciAppuntamentoAllAgenda(Agenda agenda,Appuntamento appuntamento) throws InputErratiException,  SovrapposizioneAppuntamentiException, AppuntamentoGiaPresente {
        if(agenda==null|| appuntamento ==null) throw new InputErratiException("\nerrore:dati inseriti non corretti\n");
        if(agenda.listaAppuntamentiDiUnAgenda.contains(appuntamento)) throw new AppuntamentoGiaPresente("\nerrore: appuntamento già presente il agenda\n");
        if(agenda.listaAppuntamentiDiUnAgenda.size()==0){
            agenda.listaAppuntamentiDiUnAgenda.add(appuntamento);
            return;
        }
        for (Appuntamento iteratore: agenda.getListaAppuntamentiDiUnAgenda()){
            int minutiIteratore=iteratore.orarioAppuntamento.getMinute();
            int minutiTotaliIteratore=minutiIteratore+iteratore.durataAppuntamento;
            int minutiInput=appuntamento.orarioAppuntamento.getMinute();
            int minutiTotaliInput=minutiInput+appuntamento.durataAppuntamento;

            int oreIteratore=iteratore.orarioAppuntamento.getHour();
            int oreTotaliIteratore=iteratore.durataAppuntamento+minutiIteratore;
            int oreInput=appuntamento.orarioAppuntamento.getHour();
            int oreTotaliInput=appuntamento.durataAppuntamento+minutiInput;
            if((iteratore.dataAppuntamento.compareTo(appuntamento.dataAppuntamento))==0){
                if(oreIteratore==oreInput){
                    if(minutiInput<minutiIteratore &&((minutiTotaliInput)<minutiIteratore) ||(minutiInput>minutiTotaliIteratore) &&((minutiTotaliInput)>minutiTotaliIteratore)){
                    }
                    else throw new SovrapposizioneAppuntamentiException("\nerrore: appuntamenti in sovrapposizione\n");
                }
                if((oreInput<oreIteratore)&& (oreInput+(oreTotaliInput/60))<oreIteratore || (oreInput>(oreIteratore+(oreTotaliIteratore/60)))&& (oreInput+(minutiInput+(oreTotaliInput/60)))>(oreIteratore+(oreTotaliIteratore/60))){}
                else throw new SovrapposizioneAppuntamentiException("\nerrore: appuntamenti in sovrapposizione\n");
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
    public ArrayList<Appuntamento> elencoAppuntamentiOrdinatiPerData(Agenda agenda) throws InputErratiException, AgendaInesistenteException {
        if(agenda==null) throw new InputErratiException("errore:dati inseriti non corretti");
        if(!this.listaAgende.contains(agenda)) throw new AgendaInesistenteException("errore: agenda non esistente");
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
