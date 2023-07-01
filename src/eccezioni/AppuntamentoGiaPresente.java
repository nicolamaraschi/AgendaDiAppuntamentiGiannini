package eccezioni;

public class AppuntamentoGiaPresente extends Exception {

    public AppuntamentoGiaPresente(String errorMessage) {
        super(errorMessage);
    }
}
