package ucs.CircuitRise.exceptions;

public class ExcecaoEspacoVazio extends Exception{


	private static final long serialVersionUID = 1856L;

	@Override
	public String getMessage() {
		return "Um dos campos estava vazio, preencha-o";
	}
}

