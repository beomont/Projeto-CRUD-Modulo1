package projeto.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {

	
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
//		listaProdutos();
//
//		List<String> listaProdutos = Files.readAllLines(path);
//		System.out.println("ID: ");
//		int id = sc.nextInt();
//		sc.nextLine();
//
//		String produtoEditavel = cadastraProduto();
//		String stringFinal = "";
//		
//		for(int i = 0; i<listaProdutos.size(); i++) {
//			if (id == i) {
//			String novoConteudo = listaProdutos.get(i).substring(listaProdutos.get(i).indexOf("\\|"), 7) + "conteudo" + listaProdutos.get(i).substring(15);		
//			listaProdutos.remove(i);
//			listaProdutos.add(i, novoConteudo);			
//			}			
//		}
//		
//		Files.writeString(path, listaProdutos);
		
	}
	
	

	public static String excluiProduto() throws IOException {
		listaProdutos();
		System.out.print("\nQual Produto que deseja excluir? ");

		Integer idInput = sc.nextInt();
		sc.nextLine();

		List<String> listaProdutos = Files.readAllLines(path);
		String listaComExclusao = "";

		for (int i = 0; i < listaProdutos.size(); i++) {
			if (idInput != i) {
				listaComExclusao += listaProdutos.get(i) + "\n";
			}
			Files.writeString(path, listaComExclusao);

		}
		System.out.printf("\nEXCLUSÃO DO PRODUTO (%d) REALIZADA!%n%n", idInput);
		return listaComExclusao;
	}
	

	public static void pesquisaProduto() throws IOException {
		System.out.println("Qual produto deseja pesquisar!");
		String pesqusiarProduto = sc.nextLine().toUpperCase();

		List<String> listaProdutos = listaProdutos();
		List<String> produtosFiltrados = new ArrayList<>();

		for (int i = 0; i < listaProdutos.size(); i++) {
			String nomeProduto = listaProdutos.get(i).split("\\|")[0];

			if ((nomeProduto.equals(pesqusiarProduto) || nomeProduto.contains(pesqusiarProduto))) {
				produtosFiltrados.add(listaProdutos.get(i));
			}
		}
		System.out.println(produtosFiltrados);
	}
	
	
	
	
	public static void compraProduto() throws IOException {
		
		/*
		 * List<String> listaProdutos = Files.readAllLines(path); listaProdutos();
		 * System.out.print("Escolha o ID do Produto: "); int id = sc.nextInt();
		 * sc.nextLine();
		 * 
		 * 
		 * 
		 * String produtoEscolhido = "";
		 * 
		 * List<String> listaProdutosEscolhidos = new ArrayList<>();
		 * 
		 * 
		 * produtoEscolhido = listaProdutos.get(id);
		 * 
		 * // System.out.printf(listaProdutos.get(id)); System.out.println();
		 * 
		 * listaProdutosEscolhidos.add(listaProdutos.get(id));
		 * 
		 * // System.out.println(listaProdutosEscolhidos.get(0));
		 * 
		 * 
		 * produtoEscolhido = produtoEscolhido.split("\\|")[2]; Integer
		 * quantidadeProdutoDisponivel = Integer.parseInt(produtoEscolhido);
		 * 
		 * System.out.print("Quantidade do Produto escolhido: "); Integer
		 * quantidadeEscolhida = sc.nextInt();
		 * 
		 * if (quantidadeEscolhida >quantidadeProdutoDisponivel) {
		 * System.out.println("NÃO TEM ESTOQUE SUFICIENTE DESTE PRODUTO!"); } else {
		 * 
		 * Integer quantidadeFinal = quantidadeProdutoDisponivel-quantidadeEscolhida;
		 * 
		 * }
		 * 
		 * System.out.println(produtoEscolhido);
		 */
			}
		
		
		
	
		
		
		
		
	}



//	public static boolean finalizacaoPrograma(boolean cont) {
//		System.out.println("Tem certeza que deseja finalizar o programa?");
//		System.out.println("S - SIM | N - NÃO");
//		String validaSaida = sc.nextLine().trim().toUpperCase();
//
//		if (!(validaSaida == "S")) {
//			return cont = false;
//		} else {
//			return cont = true;
//		}
//
//	}