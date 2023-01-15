package eccezioni;

/**
 *classe di eccezione che descrive due appuntamenti in sovrapposizione tra di loro
 */
public class SovrapposizioneAppuntamentiException extends Exception{
    /**
     *
     * @param errorMessage
     */
    public SovrapposizioneAppuntamentiException(String errorMessage) {
        super(errorMessage);
    }
}
