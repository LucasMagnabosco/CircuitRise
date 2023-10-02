package ucs.CircuitRise.exceptions;

public class ExcecaoObjetoJaCadastrado extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2584L;

	@Override
	public String getMessage() {
		return "O objeto nao foi inserido no sistema pois ja esta cadastrado";
	}
	
}
