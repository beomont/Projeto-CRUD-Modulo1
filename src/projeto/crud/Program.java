package projeto.crud;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Program {

	static Scanner sc = new Scanner(System.in);
	static Path path = Paths.get("C:\\Users\\Bruno\\eclipse-workspace\\projeto.crud\\arquivo.txt");

	public static void main(String[] args) throws IOException {

		boolean continuar = true;
		String opcaoEntrada;

		while (continuar) {
			System.out.println("Escolha um das opções abaixo");
			System.out.println("1 - Criar Produto");
			System.out.println("2 - Lstar Produto");
			System.out.println("3 - Editar Produto");
			System.out.println("4 - Pesquisar Produto");
			System.out.println("5 - Comprar Produto");
			System.out.println("0 - Sair");
			opcaoEntrada = sc.nextLine();
			switch (opcaoEntrada) {
			case "1" -> cadastraProduto();
			case "2" -> listaProdutos();
			case "3" -> editaProduto();
			case "0" -> continuar = false; //função pra parar
			default -> System.out.println("Opção inválida");
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
		
		String produtoCompleto = nome + ", " + preco + ", " +quantidade;
		
		return produtoCompleto;
	}

	public static void listaProdutos() throws IOException {
		List<String> listaProdutos = Files.readAllLines(path);
		String nome, preco, quantidade;
		Integer id = 1;

		for (String string : listaProdutos) {
			nome = string.split("\\|")[0];
			preco = string.split("\\|")[1];
			quantidade = string.split("\\|")[2];
			System.out.printf("Produto: %d | Nome: %s | Preço: %s | Quantidade: %s\n", id, nome, preco,
					quantidade);
			id++;
		}

	}
	
	
	public static void editaProduto() throws IOException {
		
		listaProdutos();
		System.out.print("Qual o Id que deseja editar? ");		
		
		
	}
	
	public static void excluirProduto() throws IOException {
		listaProdutos();
		System.out.print("Qual o Id que deseja excluir? ");
		
		//lógica exclusão por ID
//		
//		
//		List<String> listaProdutos = Files.readAllLines(path);
		
		
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

}
