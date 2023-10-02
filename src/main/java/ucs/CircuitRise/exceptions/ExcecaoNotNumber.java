package ucs.CircuitRise.exceptions;

public class ExcecaoNotNumber extends Exception{

	private static final long serialVersionUID = 6548L;

	@Override
	public String getMessage() {
		return "Há uma entrada númerica onde foi inserido outro tipo de dado";
	}
}
