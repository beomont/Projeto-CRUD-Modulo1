package projeto.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
	/*
	 * ALUNOS TURMA 939:
	 * 
	 * AGAMENON JUNIOR
	 * BRUNO SANTANA LOPES
	 * CHRISTIAN DOS SANTOS SILVA
	 * LUCAS ACIOLE ALBUQUERQUE
	 * MARCELE MONTALVÃO
	 * 
	 * 
	 * */

	static Scanner sc = new Scanner(System.in);
	static Path path = Paths.get("C:\\Users\\Bruno\\eclipse-workspace\\projeto.crud\\produtos.txt");

	public static void main(String[] args) throws IOException {

		boolean continuar = true;
		String opcaoEntrada;

		while (continuar) {
			System.out.println("--------------------------------------------");
			System.out.println("Escolha um das opções abaixo: \n");
			System.out.println("1 - Criar Produto");
			System.out.println("2 - Listar Produto");
			System.out.println("3 - Editar Produto");
			System.out.println("4 - Excluir Produto");
			System.out.println("5 - Pesquisar Produto");
			System.out.println("6 - Comprar Produto");
			System.out.println("0 - Sair");
			System.out.println("--------------------------------------------");
			opcaoEntrada = sc.nextLine();
			switch (opcaoEntrada) {
			case "1" -> cadastraProduto();
			case "2" -> listaProdutos();
			case "3" -> editaProduto();
			case "4" -> excluiProduto();
			case "5" -> pesquisaProduto();
			case "6" -> compraProduto();
			case "0" -> continuar = false; // função pra parar
			default -> System.out.println("""
					********************************************
					************* OPÇÃO INVÁLIDA! **************
					********************************************
					""");
			}
		}

	}

	public static String cadastraProduto() throws IOException {
		System.out.print("Digite o nome do Produto: ");
		String nome = sc.nextLine();

		System.out.print("Digite o preço do Produto: ");
		String preco = sc.nextLine();

		System.out.print("Digite a quantidade do Produto: ");
		String quantidade = sc.nextLine();

		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		Files.writeString(path, nome + "|" + preco + "|" + quantidade + "\n", StandardOpenOption.APPEND);

		String produtoCompleto = nome + ", " + preco + ", " + quantidade;

		System.out.println("\n************* PRODUTO INSERIDO! ************\n");
		return produtoCompleto;
	}

	public static List<String> listaProdutos() throws IOException {
		List<String> listaProdutos = Files.readAllLines(path);
		String nome, preco, quantidade;
		Integer id = 0;

		if (listaProdutos.size() == 0) {
			System.out.println("\nLISTA VAZIA!\n");
		} else {
			System.out.println("\n************************** LISTA PRODUTOS ***************************\n");
			for (String string : listaProdutos) {
				nome = string.split("\\|")[0];
				preco = string.split("\\|")[1];
				quantidade = string.split("\\|")[2];
				System.out.printf("Produto: %d | Nome: %-15s | Preço: %-8s | Quantidade: %-4s\n", id, nome, preco,
						quantidade);
				id++;

			}
			System.out.println("\n*********************************************************************\n");
		}

		return listaProdutos;
	}

	public static void editaProduto() throws IOException {
		listaProdutos();
		List<String> listaProdutos = Files.readAllLines(path);
		System.out.println("Qual produto deseja editar? ");
		int id = sc.nextInt();
		sc.nextLine();
		String listaConcat = "";

		System.out.println("PRODUTO A SER EDITADO: " + listaProdutos.get(id));

		System.out.print("Novo nome do Produto: ");
		String nome = sc.nextLine().toUpperCase();

		System.out.print("Novo preço do Produto: ");
		String preco = sc.nextLine();

		System.out.print("Nova quantidade do Produto: ");
		String quantidade = sc.nextLine();

		String produtoEdit = nome + "|" + preco + "|" + quantidade;

		listaProdutos.set(id, produtoEdit);

		for (int i = 0; i < listaProdutos.size(); i++) {
			listaConcat += listaProdutos.get(i) + "\n";
		}

		Files.writeString(path, listaConcat);
		listaProdutos();

	}

	public static void excluiProduto() throws IOException {
		listaProdutos();
		List<String> listaProdutos = Files.readAllLines(path);
		System.out.print("\nQual Produto que deseja excluir? ");

		int id = sc.nextInt();
		sc.nextLine();
		String listaComExclusao = "";

		listaProdutos.remove(id);

		for (int i = 0; i < listaProdutos.size(); i++) {
			listaComExclusao += listaProdutos.get(i) + "\n";
		}

		Files.writeString(path, listaComExclusao);

		System.out.printf("\nEXCLUSÃO DO PRODUTO (%d) REALIZADA!%n%n", id);
		listaProdutos();
	}

	public static void pesquisaProduto() throws IOException {
		System.out.println("Qual produto deseja pesquisar?");
		String pesquisaProduto = sc.nextLine().toUpperCase().trim();
		String nome, preco, quantidade;
		Integer id = 0;

		List<String> listaProdutos = Files.readAllLines(path);
		List<String> produtosFiltrados = new ArrayList<>();

		for (int i = 0; i < listaProdutos.size(); i++) {
			String nomeProduto = listaProdutos.get(i).split("\\|")[0];

			if ((nomeProduto.equals(pesquisaProduto) || nomeProduto.contains(pesquisaProduto))) {
				produtosFiltrados.add(listaProdutos.get(i));
			}
		}

		System.out.println("\n************************** PRODUTOS PESQUISADOS ***************************\n");
		for (String string : produtosFiltrados) {
			nome = string.split("\\|")[0];
			preco = string.split("\\|")[1];
			quantidade = string.split("\\|")[2];
			System.out.printf("Produto: %d | Nome: %-15s | Preço: %-8s | Quantidade: %-4s\n", id, nome, preco,
					quantidade);
			id++;
		}
		System.out.println("\n*********************************************************************\n");

	}

	public static void compraProduto() throws IOException {
		boolean continuarComprando = true;
		List<String> listaProdutos = Files.readAllLines(path);
		List<String> carrinhoProdutos = new ArrayList<>();
		String nome;
		Double valor;
		Integer quantidadeDisponivel;
		String produtoSolicitado;
		Integer quantidadeSolicitada;

		while (continuarComprando) {
			listaProdutos();
			System.out.print("Informe o id do produto que deseja comprar: ");
			Integer id = sc.nextInt();
			sc.nextLine();

			System.out.print("Informe a quantidade do produto que deseja: ");
			quantidadeSolicitada = sc.nextInt();
			sc.nextLine();

			nome = listaProdutos.get(id).split("\\|")[0];
			valor = Double.parseDouble(listaProdutos.get(id).split("\\|")[1]);
			quantidadeDisponivel = Integer.parseInt(listaProdutos.get(id).split("\\|")[2]);

			System.out.printf("%n.::PRODUTO ESCOLHIDO::. %nProduto: %s | R$%.2f%n%n", nome, valor);

			if (quantidadeSolicitada > quantidadeDisponivel) {
				System.out.println("Não há quantidade suficiente em estoque! =( ");
			} else if (quantidadeSolicitada <= 0) {
				System.out.println("Por favor informar uma quantidade valida!");
			} else {

				produtoSolicitado = nome + "|" + valor + "|" + quantidadeSolicitada;
				carrinhoProdutos.add(produtoSolicitado);

				// Atualiza Estoque Original
				Integer estoqueAtual = quantidadeDisponivel - quantidadeSolicitada;
				String produtoEstoqueAtual = nome + "|" + valor + "|" + estoqueAtual;
				listaProdutos.set(id, produtoEstoqueAtual);

				String atualizaArquivo = "";
				for (int i = 0; i < listaProdutos.size(); i++) {
					atualizaArquivo += listaProdutos.get(i) + "\n";
				}

				Files.writeString(path, atualizaArquivo);

			}
			System.out.println("***PRODUTO ADICIONADO AO CARRINHO!***\n");
			System.out.println("DESEJA CONTINUAR COMPRANDO?");
			System.out.println("S - SIM | N - NÃO");
			String continua = sc.nextLine().toUpperCase().trim();
			if (!continua.equals("S")) {
				continuarComprando = false;
			}
		}

		listaCarrinho(carrinhoProdutos);
	}

	public static void listaCarrinho(List<String> carrinhoProdutos) {
		String nome, preco, quantidade;
		Integer id = 1;
		Double valorTotal = 0.0;

		if (carrinhoProdutos.size() == 0) {
			System.out.println("\nCARRINHO VAZIO!\n");
		} else {
			System.out.println("\n************************** CARRINHO DE COMPRAS ***************************\n");
			for (String string : carrinhoProdutos) {
				nome = string.split("\\|")[0];
				preco = string.split("\\|")[1];
				quantidade = string.split("\\|")[2];
				System.out.printf("Item de Compra: %d | Nome: %-15s | Preço: %-8s | Quantidade: %-4s\n", id, nome,
						preco, quantidade);

				Double precoDouble = Double.parseDouble(preco);
				Integer quantidadeInt = Integer.parseInt(quantidade);
				valorTotal += (precoDouble * quantidadeInt);
				id++;

			}
			System.out.printf("%n%nVALOR TOTAL DA COMPRA: R$%.2f", valorTotal);
			System.out.println("\n*********************************************************************\n");
		}

	}

}