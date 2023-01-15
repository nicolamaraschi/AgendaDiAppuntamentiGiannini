package eccezioni;

/**
 *
 */
public class AgendaInesistenteException extends Exception{
    /**
     * @param errorMessage
     */
    public AgendaInesistenteException(String errorMessage) {
        super(errorMessage);
    }
}
