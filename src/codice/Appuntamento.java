package codice;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appuntamento implements Comparable<Appuntamento> {
    LocalDate dataAppuntamento;
    LocalTime orarioAppuntamento;
    int durataAppuntamento;
    String nomePersonaAppuntamento;
    String luogoAppuntamento;

    public Appuntamento(LocalDate dataAppuntamento, LocalTime orarioAppuntamento, int durataAppuntamento, String nomePersonaAppuntamento, String luogoAppuntamento) {
        this.dataAppuntamento = dataAppuntamento;
        this.orarioAppuntamento = orarioAppuntamento;
        this.durataAppuntamento = durataAppuntamento;
        if(durataAppuntamento<0 || durataAppuntamento>=60) throw new IllegalArgumentException();
        this.nomePersonaAppuntamento = nomePersonaAppuntamento;
        this.luogoAppuntamento = luogoAppuntamento;
    }

    public Appuntamento setDataAppuntamento(LocalDate dataAppuntamento) {
        this.dataAppuntamento = dataAppuntamento;
        return this;
    }

    public Appuntamento setOrarioAppuntamento(LocalTime orarioAppuntamento) {
        this.orarioAppuntamento = orarioAppuntamento;
        return this;
    }

    public Appuntamento setDurataAppuntamento(int durataAppuntamento) {
        this.durataAppuntamento = durataAppuntamento;
        return this;
    }

    public Appuntamento setNomePersonaAppuntamento(String nomePersonaAppuntamento) {
        this.nomePersonaAppuntamento = nomePersonaAppuntamento;
        return this;
    }

    public Appuntamento setLuogoAppuntamento(String luogoAppuntamento) {
        this.luogoAppuntamento = luogoAppuntamento;
        return this;
    }

    public LocalDate getDataAppuntamento() {
        return dataAppuntamento;
    }

    public LocalTime getOrarioAppuntamento() {
        return orarioAppuntamento;
    }

    public int getDurataAppuntamento() {
        return durataAppuntamento;
    }

    public String getNomePersonaAppuntamento() {
        return nomePersonaAppuntamento;
    }

    public String getLuogoAppuntamento() {
        return luogoAppuntamento;
    }


    @Override
    public String toString() {
        return dataAppuntamento+" "+orarioAppuntamento+" "+durataAppuntamento+" "+nomePersonaAppuntamento+" "+luogoAppuntamento;
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
