package br.ifpb.edu.poo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TabelaRotas tabela = new TabelaRotas();
        GerenciadorInterfaces gerenciador = new GerenciadorInterfaces();
        int opcao;

        do {
            System.out.println("\n1. Cadastrar Interface Física");
            System.out.println("2. Cadastrar Rota");
            System.out.println("3. Listar Rotas");
            System.out.println("4. Listar Interfaces");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da interface: ");
                    String nome = sc.nextLine();
                    System.out.print("Endereço IP: ");
                    String ip = sc.nextLine();
                    gerenciador.cadastrarInterface(nome, ip);
                }
                case 2 -> {
                    System.out.print("Destino: ");
                    String dest = sc.nextLine();
                    System.out.print("Máscara: ");
                    String mask = sc.nextLine();
                    System.out.print("Gateway: ");
                    String gate = sc.nextLine();
                    System.out.print("Interface (nome): ");
                    String nomeInt = sc.nextLine();
                    InterfaceFisica iface = gerenciador.buscarPorNome(nomeInt);
                    if (iface == null) {
                        System.out.println("❌ Interface não encontrada!");
                    } else {
                        tabela.adicionarRota(new Rota(dest, mask, gate, iface));
                    }
                }
                case 3 -> tabela.listarRotas();
                case 4 -> gerenciador.listarInterfaces();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}