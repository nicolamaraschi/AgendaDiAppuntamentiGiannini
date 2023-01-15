package eccezioni;

/**
 * questa classe di eccezione viene usata nel caso un appuntamento non sia esistente in un agenda
 */
public class AppuntamentoInesistenteException extends Exception{
    /**
     * @param errorMessage
     */
    public AppuntamentoInesistenteException(String errorMessage) {
        super(errorMessage);
    }
}
