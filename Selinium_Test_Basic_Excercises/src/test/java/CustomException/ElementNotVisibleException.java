package CustomException;
public class ElementNotVisibleException extends Exception {
    private static final long serialVersionUID = 1L; // Recommended serialVersionUID

    public ElementNotVisibleException(String message) {
        super(message);
    }
}

