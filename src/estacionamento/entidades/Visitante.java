package estacionamento.entidades;

import estacionamento.enumerados.MotivosDaVisita;
import estacionamento.enumerados.TiposDePessoas;

public class Visitante extends Pessoa {
	
	private Integer telefone;
	private MotivosDaVisita motivo;
	
	public Visitante(String nome, String cpf, TiposDePessoas tipo, Integer telefone, MotivosDaVisita motivo) {
		super(nome, cpf, tipo);
		this.telefone = telefone;
		this.motivo = motivo;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public MotivosDaVisita getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivosDaVisita motivo) {
		this.motivo = motivo;
	}
	
	@Override
	public String toString() {
		return super.toString() +
				"\nTelefone: " + telefone +
				"\nMotivo da visita: " + motivo + "\n";
	}
	

}