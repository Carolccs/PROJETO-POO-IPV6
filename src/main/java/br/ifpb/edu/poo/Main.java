package br.ifpb.edu.poo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TabelaRotas tabela = new TabelaRotas();
        GerenciadorInterfaces gerenciador = new GerenciadorInterfaces();
        int opcao;

        do {
            System.out.println("1. Cadastrar Interface Física");
            System.out.println("2. Cadastrar Rota");
            System.out.println("3. Listar Rotas");
            System.out.println("4. Listar Interfaces");
            System.out.println("5. Roteamento de IP (Longest Match)");
            System.out.println("6. Alterar Rota");
            System.out.println("7. Excluir Rota");
            System.out.println("8. Configurar exibição das rotas");
            System.out.println("9. Resetar tabela de rotas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

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
                        System.out.println("Interface não encontrada!");
                    } else {
                        tabela.adicionarRota(new Rota(dest, mask, gate, iface));
                    }
                }

                case 3 -> tabela.listarRotas();

                case 4 -> gerenciador.listarInterfaces();

                case 5 -> {
                    System.out.print("Digite o IP para rotear: ");
                    String ipRota = sc.nextLine();
                    tabela.rotear(ipRota);
                }

                case 6 -> tabela.alterarRota(sc);

                case 7 -> tabela.excluirRota(sc);

                case 8 -> {
                    System.out.println("Escolha o formato:");
                    System.out.println("1. Máscara de subrede");
                    System.out.println("2. Notação CIDR");
                    System.out.print("Opção: ");

                    int tipo = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 1) {
                        tabela.setModoExibicao("MASCARA");
                        System.out.println("Exibição configurada para máscara de subrede.");
                    } else if (tipo == 2) {
                        tabela.setModoExibicao("CIDR");
                        System.out.println("Exibição configurada para notação CIDR.");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }

                case 9 -> {
                    System.out.print("Deseja realmente resetar a tabela? (s/n): ");
                    String conf = sc.nextLine();
                    if (conf.equalsIgnoreCase("s")) {
                        tabela.resetar();
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                }

                case 0 -> System.out.println("Encerrando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}