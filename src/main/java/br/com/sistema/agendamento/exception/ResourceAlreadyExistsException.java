package br.com.sistema.agendamento.exception;

public class ResourceAlreadyExistsException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException() {
    }
 
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
