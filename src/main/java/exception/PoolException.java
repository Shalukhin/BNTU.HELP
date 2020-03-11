package exception;

public class PoolException extends Exception {

	private static final long serialVersionUID = 7531318976113815994L;

	public PoolException() {
		super();
	}

	public PoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public PoolException(String message) {
		super(message);
	}

	public PoolException(Throwable cause) {
		super(cause);
	}
}
