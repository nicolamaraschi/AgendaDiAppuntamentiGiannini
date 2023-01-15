package eccezioni;

/**
 *   questa classe di eccezione descrive un errore dovuto a un passaggio di dati errati da parte del chiamante
 */
public class InputErratiException extends Exception {

    /**
     * @param errorMessage
     */
    public InputErratiException(String errorMessage) {
        super(errorMessage);
    }
}
