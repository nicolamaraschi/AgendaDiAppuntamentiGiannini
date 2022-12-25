package codice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;


public class Appuntamento implements Comparable<Appuntamento> {
    LocalDate dataAppuntamento;
    LocalTime orarioAppuntamento;
    int durataAppuntamento;
    String nomePersonaAppuntamento;
    String luogoAppuntamento;
    //GregorianCalendar dataGregoriana = GregorianCalendar.from(this.dataAppuntamento.atStartOfDay(ZoneId.systemDefault()));

    public Appuntamento(LocalDate dataAppuntamento, LocalTime orarioAppuntamento, int durataAppuntamento, String nomePersonaAppuntamento, String luogoAppuntamento) {
       this.dataAppuntamento=dataAppuntamento;
        this.orarioAppuntamento = orarioAppuntamento;
        this.durataAppuntamento = durataAppuntamento;
        if(durataAppuntamento<0 || durataAppuntamento>=60) throw new IllegalArgumentException();
        this.nomePersonaAppuntamento = nomePersonaAppuntamento;
        this.luogoAppuntamento = luogoAppuntamento;
    }

    /*
     public GregorianCalendar getDataGregoriana() {

        return dataGregoriana;
    }
    */

    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    public String getNomePersonaAppuntamento() {
        return nomePersonaAppuntamento;
    }



    @Override
    public String toString() {
        return dataAppuntamento+" "+orarioAppuntamento+" "+durataAppuntamento+" "+nomePersonaAppuntamento+" "+luogoAppuntamento+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appuntamento that = (Appuntamento) o;
        return dataAppuntamento.equals(that.dataAppuntamento) && orarioAppuntamento.equals(that.orarioAppuntamento) && luogoAppuntamento.equals(that.luogoAppuntamento);
    }


    @Override
    public int compareTo(Appuntamento appuntamento) {
        return this.dataAppuntamento.compareTo(appuntamento.dataAppuntamento);
    }
}
