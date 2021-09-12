package br.com.uniesp.estacionamento.service;

import java.util.List;
import java.util.Scanner;

import br.com.uniesp.estacionamento.entidade.Pessoa;
import br.com.uniesp.estacionamento.entidade.Veiculo;
import br.com.uniesp.estacionamento.entidade.enums.Marcas;
import br.com.uniesp.estacionamento.entidade.enums.TiposDeVeiculos;
import br.com.uniesp.estacionamento.repositorio.PessoaRepositorio;

public class VeiculoService {

	private Pessoa donoDoCarro;
	private Veiculo veiculo;
	private PessoaService ps = new PessoaService();
	
	Scanner sc = new Scanner(System.in);
	
	public void cadastraVeiculo(PessoaRepositorio dados) {
		System.out.print("Tipo: "
				+ "\n	1  - Carro"
				+ "\n	2  - Moto"
				+ "\n	3  - Onibus"
				+ "\n	4  - Van"
				+ "\nInforme a op��o: ");
		int tipo = sc.nextInt();
		System.out.print("Informe a placa do ve�culo: ");
		String placa = sc.next();
		System.out.print("Marca: "
				+ "\n	1  - Audi"
				+ "\n	2  - BMW"
				+ "\n	3  - Chevrolet"
				+ "\n	4  - Citroen"
				+ "\n	5  - FIAT"
				+ "\n	6  - Ford"
				+ "\n	7  - Hyundai"
				+ "\n	8  - Honda"
				+ "\n	9  - Jaguar"
				+ "\n	10 - Land Rover"
				+ "\n	11 - Peugeot"
				+ "\n	12 - Toyota"
				+ "\n	13 - Volkswagen"
				+ "\n	14 - Volvo"
				+ "\n	15 - Outro"
				+ "\nInforme a op��o: ");
		int marca = sc.nextInt();
		String respostaCadastro;
		do {
			System.out.print("O dono tem cadastro no sistema (s/n): ");
			respostaCadastro = sc.next();
		} while(!respostaCadastro.equalsIgnoreCase("s") && !respostaCadastro.equalsIgnoreCase("n"));
			
		if(respostaCadastro.equalsIgnoreCase("n")) {
			System.out.println("\nPreencha dados pessoais do dono:");
			ps.cadastraPessoa(dados.getListaPessoasCadastradas());
			donoDoCarro = ps.getPessoa();
			veiculo = new Veiculo(TiposDeVeiculos.procurarOpcao(tipo), Marcas.procurarOpcao(marca), placa.toUpperCase(), donoDoCarro);
			dados.adicionarAoVeiculoAoBandoDeDados(veiculo);
		}
		if(respostaCadastro.equalsIgnoreCase("s")) {
			System.out.print("Informe o nome: ");
			String nome = sc.next();
			ps.consultaPessoa(dados.getListaPessoasCadastradas(), nome.toUpperCase());
			donoDoCarro = ps.getPessoa();
			if (donoDoCarro == null) {
				System.out.println("Preencha dados pessoais do dono:");
				ps.cadastraPessoa(dados.getListaPessoasCadastradas());
				donoDoCarro = ps.getPessoa();
				veiculo = new Veiculo(TiposDeVeiculos.procurarOpcao(tipo), Marcas.procurarOpcao(marca), placa.toUpperCase(), donoDoCarro);
				dados.adicionarAoVeiculoAoBandoDeDados(veiculo);
				return;
			}
			veiculo = new Veiculo(TiposDeVeiculos.procurarOpcao(tipo), Marcas.procurarOpcao(marca),placa.toUpperCase(), donoDoCarro);
			dados.adicionarAoVeiculoAoBandoDeDados(veiculo);
		}
	}

	public void consultaVeiculo(List<Veiculo> lista, String placa) {
		for (Veiculo v : lista) {
			if (placa.equals(v.getPlacaDoVeiculo())) {
				System.out.println("\nVe�culo encontrado!");
				veiculo = v;
				System.out.println(v);
				return;
			}
		}
		System.out.println("Ve�culo n�o encontrado!\n");
	}
	
	public void editaVeiculo(List<Veiculo> lista, String placaDoVeculo) {
		for (Veiculo v : lista) {
			if (placaDoVeculo.equals(v.getPlacaDoVeiculo())) {	
				System.out.println("\nVe�culo encontrado!\n");
				System.out.println(v);
				
				String respostaEditor;
				do {
					System.out.print("\nTem certeza que deseja editar os dados do ve�culo (s/n): ");
					respostaEditor = sc.nextLine();
				} while(!respostaEditor.equalsIgnoreCase("s") && !respostaEditor.equalsIgnoreCase("n"));
				
				if (respostaEditor.equalsIgnoreCase("s")) {
					System.out.print("Tipo: "
							+ "\n	1  - Carro"
							+ "\n	2  - Moto"
							+ "\n	3  - Onibus"
							+ "\n	4  - Van"
							+ "\nInforme a op��o: ");
					int novoTipo = sc.nextInt();
					System.out.print("Nova placa: ");
					String novoPlaca = sc.next();
					System.out.print("Marca: "
							+ "\n	1  - Audi"
							+ "\n	2  - BMW"
							+ "\n	3  - Chevrolet"
							+ "\n	4  - Citroen"
							+ "\n	5  - FIAT"
							+ "\n	6  - Ford"
							+ "\n	7  - Hyundai"
							+ "\n	8  - Honda"
							+ "\n	9  - Jaguar"
							+ "\n	10 - Land Rover"
							+ "\n	11 - Peugeot"
							+ "\n	12 - Toyota"
							+ "\n	13 - Volkswagen"
							+ "\n	14 - Volvo"
							+ "\n	15 - Outro"
							+ "\nInforme a op��o: ");
					int novaMarca = sc.nextInt();
					v.setTipo(TiposDeVeiculos.procurarOpcao(novoTipo));
					v.setPlacaDoVeiculo(novoPlaca);
					v.setMarca(Marcas.procurarOpcao(novaMarca));
					String respostaEditorDono;
					do {
						System.out.print("Deseja editar os dados pessoais do dono (s/n): ");
						respostaEditorDono = sc.next();
					} while(!respostaEditorDono.equalsIgnoreCase("s") && !respostaEditorDono.equalsIgnoreCase("n"));
					
					if (respostaEditorDono.equalsIgnoreCase("s")) {
						ps.editaPessoa(v);
						System.out.println("Dados do ve�culo e do dono editados!");
						return;
					}
					if (respostaEditorDono.equalsIgnoreCase("n")) {
						System.out.println("Dados do ve�culo editados!");
						return;
					}	
				}
				if (respostaEditor.equalsIgnoreCase("n")) {
					return;
				}	
			}
			System.out.println("Ve�culo n�o encontrado!");
		}
	}
	
	public void excluiVeiculo(List<Veiculo> lista, String placaDoVeiculo) {
		for (Veiculo v : lista) {
			if (placaDoVeiculo.equals(v.getPlacaDoVeiculo())) {
				System.out.println("\nVe�culo encontrado!\n");
				System.out.println(v);
				String resposta;
				do {
					System.out.print("\nTem certeza que deseja excluir (s/n): ");
					resposta = sc.nextLine();
				} while(!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("n"));
				
				if (resposta.equalsIgnoreCase("s")) {
					System.out.println("Ve�culo excluido!");
					lista.remove(v);
					return;
				}
				if (resposta.equalsIgnoreCase("n")) {
					return;
				}	
			}
		}
		System.out.println("Ve�culo n�o encontrado!\n");
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public Pessoa getDonoDoCarro() {
		return donoDoCarro;
	}
}
