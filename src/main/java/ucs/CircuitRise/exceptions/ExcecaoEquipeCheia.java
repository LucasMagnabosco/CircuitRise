package ucs.CircuitRise.exceptions;

public class ExcecaoEquipeCheia extends Exception{


	private static final long serialVersionUID = 5478L;

	@Override
	public String getMessage() {
		return "A equipe selecionada já possui dois pilotos cadastrados";
	}
}