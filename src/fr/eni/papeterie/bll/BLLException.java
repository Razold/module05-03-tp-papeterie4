package fr.eni.papeterie.bll;

public class BLLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public BLLException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BLLException(String message, Throwable exc) {
		super(message, exc);
		// TODO Auto-generated constructor stub
	}



	public BLLException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return "Une erreur est survenue dans La couche BLL(Business Logic Layer). \n";
	}

}
