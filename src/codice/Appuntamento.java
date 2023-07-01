package codice;

import eccezioni.InputErratiException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;


/**
 *
 */
public class Appuntamento implements Comparable<Appuntamento> {
    LocalDate dataAppuntamento;
    LocalTime orarioAppuntamento;
    int durataAppuntamento;
    String nomePersonaAppuntamento;
    String luogoAppuntamento;
    //GregorianCalendar dataGregoriana = GregorianCalendar.from(this.dataAppuntamento.atStartOfDay(ZoneId.systemDefault()));

    /**
     * Costruttore principale per la classe appuntamento
     * @param dataAppuntamento la data di quando avviene l'appuntamento
     * @param orarioAppuntamento  l'orario di quando si effettua l'appuntamento
     * @param durataAppuntamento   la durata in minuti dell'appuntamento
     * @param nomePersonaAppuntamento il nome della persona da incontrare all'appuntamento
     * @param luogoAppuntamento il luogo dove si ha l'appuntamento
     */
    public Appuntamento(LocalDate dataAppuntamento, LocalTime orarioAppuntamento, int durataAppuntamento, String nomePersonaAppuntamento, String luogoAppuntamento) {
       this.dataAppuntamento=dataAppuntamento;
        this.orarioAppuntamento = orarioAppuntamento;
        this.durataAppuntamento = durataAppuntamento;


        this.nomePersonaAppuntamento = nomePersonaAppuntamento;
        this.luogoAppuntamento = luogoAppuntamento;
    }

    public LocalTime getOrarioAppuntamento() {
        return orarioAppuntamento;
    }

    public int getDurataAppuntamento() {
        return durataAppuntamento;
    }

    public String getLuogoAppuntamento() {
        return luogoAppuntamento;
    }

    /**
     * @return dataAppuntamento
     */
    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    /**
     * @return nomePersonaAppuntamento
     */
    public String getNomePersonaAppuntamento() {
        return nomePersonaAppuntamento;
    }

    /**
     * @return dataAppuntamento+" "+orarioAppuntamento+" "+durataAppuntamento+" "+nomePersonaAppuntamento+" "+luogoAppuntamento+"\n"
     */
    @Override
    public String toString() {
        return dataAppuntamento+" "+orarioAppuntamento+" "+durataAppuntamento+" "+nomePersonaAppuntamento+" "+luogoAppuntamento;
    }

    /**
     * questo metodo ci permette di determinare due agende uguali controllando la data dell'appuntamento, l'orario dell'appuntamento e il luogo dove avviene l'appuntamento
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appuntamento that = (Appuntamento) o;
        return dataAppuntamento.equals(that.dataAppuntamento) && orarioAppuntamento.equals(that.orarioAppuntamento) && luogoAppuntamento.equals(that.luogoAppuntamento);
    }

    /**
     * @param appuntamento the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(Appuntamento appuntamento) {
        return this.dataAppuntamento.compareTo(appuntamento.dataAppuntamento);
    }
}
