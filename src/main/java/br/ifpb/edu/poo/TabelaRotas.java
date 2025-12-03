package br.ifpb.edu.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TabelaRotas {

    private String modoExibicao = "MASCARA"; // padrão
    private List<Rota> rotas = new ArrayList<>();

    public void setModoExibicao(String modo) {
        this.modoExibicao = modo;
    }

    public void adicionarRota(Rota rota) {
        for (Rota r : rotas) {
            if (r.getDestino().equals(rota.getDestino()) &&
                r.getMascara().equals(rota.getMascara()) &&
                r.getGateway().equals(rota.getGateway())) {
                System.out.println(" Rota já existe!");
                return;
            }
        }
        rotas.add(rota);
        System.out.println(" Rota adicionada com sucesso!");
    }

    public void listarRotas() {
        if (rotas.isEmpty()) {
            System.out.println("Tabela de rotas vazia.");
            return;
        }

        System.out.println("Tabela de Rotas:");
        for (int i = 0; i < rotas.size(); i++) {
            Rota r = rotas.get(i);

            String destino;
            if (modoExibicao.equals("CIDR")) {
                destino = r.getDestino() + "/" + calcularPrefixo(r.getMascara());
            } else {
                destino = r.getDestino() + " / " + r.getMascara();
            }

            System.out.println((i + 1) + " - " 
                    + destino + " / " + r.getGateway()
                    + " (" + r.getInterfaceFisica().getNome() + ")");
        }
    }

    public void excluirRota(Scanner scanner) {
        if (rotas.isEmpty()) {
            System.out.println(" Nenhuma rota cadastrada para excluir.");
            return;
        }

        listarRotas();
        System.out.print("Digite o número da rota que deseja excluir: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice < 0 || indice >= rotas.size()) {
            System.out.println(" Rota inválida!");
            return;
        }

        System.out.print("Confirmar exclusão? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            rotas.remove(indice);
            System.out.println(" Rota removida!");
        }
    }

    // ---------------- LONGEST MATCH ----------------

    public void rotear(String ipDestino) {

        if (rotas.isEmpty()) {
            System.out.println(" Tabela de rotas vazia!");
            return;
        }

        Rota melhor = null;
        int melhorPrefixo = -1;

        for (Rota r : rotas) {
            if (ipPertenceARede(ipDestino, r.getDestino(), r.getMascara())) {
                int prefixo = calcularPrefixo(r.getMascara());

                if (prefixo > melhorPrefixo) {
                    melhorPrefixo = prefixo;
                    melhor = r;
                }
            }
        }

        if (melhor == null) {
            System.out.println("Nenhuma rota encontrada (nem default).");
        } else {
            System.out.println(" Melhor rota:");
            System.out.println(melhor);
        }
    }

    private int calcularPrefixo(String mascara) {
        int bits = 0;
        for (String parte : mascara.split("\\.")) {
            bits += Integer.bitCount(Integer.parseInt(parte));
        }
        return bits;
    }

    private boolean ipPertenceARede(String ip, String destino, String mascara) {
        int ipInt = ipParaInt(ip);
        int destInt = ipParaInt(destino);
        int maskInt = ipParaInt(mascara);
        return (ipInt & maskInt) == (destInt & maskInt);
    }

    private int ipParaInt(String ip) {
        int n = 0;
        for (String p : ip.split("\\.")) {
            n = (n << 8) + Integer.parseInt(p);
        }
        return n;
    }

    public void alterarRota(Scanner scanner) {
        if (rotas.isEmpty()) {
            System.out.println(" Nenhuma rota cadastrada para alterar.");
            return;
        }

        listarRotas();
        System.out.print("Digite o número da rota que deseja alterar: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        if (indice < 0 || indice >= rotas.size()) {
            System.out.println(" Rota inválida!");
            return;
        }

        Rota rotaAtual = rotas.get(indice);

        System.out.print("Novo destino (atual: " + rotaAtual.getDestino() + "): ");
        String novoDestino = scanner.nextLine();
        System.out.print("Nova máscara (atual: " + rotaAtual.getMascara() + "): ");
        String novaMascara = scanner.nextLine();
        System.out.print("Novo gateway (atual: " + rotaAtual.getGateway() + "): ");
        String novoGateway = scanner.nextLine();

        InterfaceFisica interfaceAtual = rotaAtual.getInterfaceFisica();
        Rota novaRota = new Rota(
                novoDestino.isEmpty() ? rotaAtual.getDestino() : novoDestino,
                novaMascara.isEmpty() ? rotaAtual.getMascara() : novaMascara,
                novoGateway.isEmpty() ? rotaAtual.getGateway() : novoGateway,
                interfaceAtual
        );

        rotas.set(indice, novaRota);
        System.out.println(" Rota alterada com sucesso!");
    }

    public void resetar() {
        rotas.clear();
        System.out.println("Tabela de rotas resetada.");
    }
}
