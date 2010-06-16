package ee.adit.exception;

public class AditException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AditException(String message) {
		super(message);
	}
	
	public AditException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
