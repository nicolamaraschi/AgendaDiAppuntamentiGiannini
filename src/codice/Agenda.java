package codice;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// problema: non viene mai usato iterable, devi correggere


// non c'è alcuna gerarchia e non esistono classi abstract e la scrittura su file non deve essere binaria ma semplice
public class Agenda implements  Iterable<Agenda>{
    private ArrayList<Appuntamento> listaAppuntamentiDiUnAgenda;
    private ArrayList<Agenda> listaAgende;

    final String nomeAgenda;

    public Agenda(ArrayList<Appuntamento> listaAppuntamentiDiUnAgenda, String nomeAgenda) {
        this.listaAppuntamentiDiUnAgenda = listaAppuntamentiDiUnAgenda;
        this.nomeAgenda = nomeAgenda;
    }

    public ArrayList<Appuntamento> getListaAppuntamentiDiUnAgenda() {
        return listaAppuntamentiDiUnAgenda;
    }

    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public ArrayList<Agenda> getListaAgende() {
        return listaAgende;
    }

    @Override
    public Iterator<Agenda> iterator() {
        return this.iterator();
    }
    // a lei non piace il confronto delle stringhe con == , poi modificalo
    public Appuntamento cercaAppuntamentoPerNome(Agenda agenda, String nomePersona){
        if(nomePersona==null) throw new IllegalArgumentException("errore:nomePersona nulla");
        if(agenda==null) throw new IllegalArgumentException("errore:agenda non esistente");
        for (Appuntamento iteratore: agenda.listaAppuntamentiDiUnAgenda) {
            if(iteratore.getNomePersonaAppuntamento().equals(nomePersona)) return iteratore;
        }
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(nomePersona)) throw new IllegalArgumentException("errore:non c'è nessuna persona con questo nome che ha fatto una prenotazione");
        return null;
    }
    public Appuntamento cercaAppuntamentoPerData(Agenda agenda, LocalDate dataAppuntamento){
        if(dataAppuntamento==null) throw new IllegalArgumentException("errore:nomePersona nulla");
        if(agenda==null) throw new IllegalArgumentException("errore:agenda non esistente");
        for (Appuntamento iteratore: agenda.listaAppuntamentiDiUnAgenda) {
            if(iteratore.getDataAppuntamento().isEqual(dataAppuntamento)) return iteratore;
        }
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(dataAppuntamento)) throw new IllegalArgumentException("errore:non c'è nessuna prenotazione per quella data");
        return null;
    }



    // domanda da farle: ma devo far poter modificare ogni combinazione di campo di appuntamento
   public  void modificaAppuntamento(Agenda agenda, Appuntamento appuntamento){
        if(appuntamento==null) throw new IllegalArgumentException("errore:appuntamento nullo");
        if(!this.listaAppuntamentiDiUnAgenda.contains(appuntamento)) throw new IllegalArgumentException("errore:appuntamento non trovato");
        agenda.getListaAppuntamentiDiUnAgenda().add(appuntamento);
   }


    // IllegalArgumentException non le vanno bene bisogma crearne eccezioni a doc oppure utilizzare qualche eccezione molto piu particolare
    public Agenda creaAgendaDalNome(String nome){
        if(nome==null) throw new IllegalArgumentException("nome inserito nullo");
        if(this.listaAgende.contains(nome)) throw new IllegalArgumentException("errore:il nome da lei inserito è gia contenuto nella lista delle agende");
        Agenda agenda= new Agenda(nome);
        // questa add è da controllare
        this.listaAgende.add(agenda);
        return agenda;
    }

    public void creaAgendaDaFile(Agenda agenda){
        LocalDate dataAppuntamento;
        LocalTime orarioAppuntamento;
        int durataAppuntamento;
        String nomePersonaAppuntamento;
        String luogoAppuntamento;
        int i=0;

        /*
        int index=0;
        InputStream stream = this.getClass().getResourceAsStream("src/file/agenda.txt" + index + ".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        */
        File file = new File("src/file/agenda.txt");
        try{
            Scanner scan = new Scanner(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        try{
            FileInputStream fstream = new FileInputStream("src/file/agenda.txt");
            DataInputStream in      = new DataInputStream(fstream);
            BufferedReader br       = new BufferedReader(new InputStreamReader(in));
            // stringa che contiene tutto il testo letto
            String strLine;
            String[] tokens=null;
            //Arrays.fill(tokens, null);
            while ((strLine = br.readLine()) != null){
                tokens = strLine.split(" ");
            }
            for(String iteratore: tokens){
                boolean ceck=true;
                int indice=0;
                int indiceIncremento=3;
                if(ceck){
                    indice=0;
                    ceck=true;
                }
                else indice=3;
                dataAppuntamento= LocalDate.parse(tokens[indice+indiceIncremento]);
                indice++;
                orarioAppuntamento= LocalTime.parse(tokens[indice+indiceIncremento]);
                indice++;
                durataAppuntamento= Integer.parseInt(tokens[indice+indiceIncremento]);
                indice++;
                nomePersonaAppuntamento=tokens[indice+indiceIncremento];
                indice++;
                luogoAppuntamento=tokens[indice+indiceIncremento];
                indice++;
                Appuntamento appuntamento= new Appuntamento(dataAppuntamento,orarioAppuntamento,durataAppuntamento,nomePersonaAppuntamento,luogoAppuntamento);
                this.listaAppuntamentiDiUnAgenda.add(appuntamento);
            }
            in.close();
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }


    }

    public void ScritturaAgendaSulFile(Agenda agenda) throws IOException {
        try{
            // bisogna vedere se devo gestire nel caso abbia gia un agenda.txt
            FileWriter writer = new FileWriter("src/file/agenda.txt");
            for (Appuntamento iteratore:agenda.listaAppuntamentiDiUnAgenda) {
                writer.write(iteratore.toString());
            }
            writer.close();
        }
        // ma questa cosa si può fare??
        catch (IOException e){
            throw new IOException("errore: l'apertuta del file è fallita");
        }

    }


    public void cancellaAgenda(Agenda agenda){
        if(agenda==null) throw new IllegalArgumentException("l'agenda inserita è null");
        if(!this.listaAgende.contains(agenda)) throw new IllegalArgumentException("errore: vuoi cancellare un agenda che non esiste nella lista delel agende");
        this.listaAgende.remove(agenda);

    }


    public void inserisciAppuntantoAllAgenda(Agenda agenda, LocalDate dataAppuntamento, LocalTime orarioAppuntamento, int durataAppuntamento, String nomePersonaAppuntamento,String luogoAppuntamento){
    if(agenda==null||dataAppuntamento==null||orarioAppuntamento==null||durataAppuntamento==0||nomePersonaAppuntamento==null||luogoAppuntamento==null) throw new IllegalArgumentException();
    Appuntamento appuntamento= new Appuntamento(dataAppuntamento,orarioAppuntamento,durataAppuntamento,nomePersonaAppuntamento,luogoAppuntamento);
    if(agenda.listaAppuntamentiDiUnAgenda.contains(appuntamento)) throw new IllegalArgumentException("errore: appuntamento già presente il agenda");
    agenda.listaAppuntamentiDiUnAgenda.add(appuntamento);
    }

    public ArrayList<Appuntamento> elencoAppuntameniPerData(Agenda agenda, LocalDate dataAppuntamento){
        if(agenda==null) throw new IllegalArgumentException();
        if(dataAppuntamento==null) throw new IllegalArgumentException();
        if(!this.listaAgende.contains(agenda)) throw new IllegalArgumentException("errore: agenda non esistente");
        if(!agenda.listaAppuntamentiDiUnAgenda.contains(dataAppuntamento)) throw new IllegalArgumentException("errore: non ci sono appuntamenti per quella data ");
        ArrayList<Appuntamento> listaAppantumentiOrdinati= new ArrayList<>();
        Collections.sort(this.listaAppuntamentiDiUnAgenda);
        listaAppantumentiOrdinati.addAll(listaAppuntamentiDiUnAgenda);
       return listaAppantumentiOrdinati;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return nomeAgenda.equals(agenda.nomeAgenda);
    }


}
