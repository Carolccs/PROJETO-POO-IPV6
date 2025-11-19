package br.ifpb.edu.poo;

import java.util.ArrayList;
import java.util.List;

public class TabelaRotas {
    private List<Rota> rotas = new ArrayList<>();

    public void adicionarRota(Rota rota) {
        // Verifica duplicata
        for (Rota r : rotas) {
            if (r.getDestino().equals(rota.getDestino()) &&
                r.getMascara().equals(rota.getMascara()) &&
                r.getGateway().equals(rota.getGateway())) {
                System.out.println("❌ Rota já existe!");
                return;
            }
        }
        rotas.add(rota);
        System.out.println("✅ Rota adicionada com sucesso!");
    }

    public void listarRotas() {
        if (rotas.isEmpty()) {
            System.out.println("⚠️ Tabela de rotas vazia.");
        } else {
            System.out.println("🧭 Tabela de Rotas:");
            for (int i = 0; i < rotas.size(); i++) {
                System.out.println((i + 1) + " - " + rotas.get(i));
            }
        }
    }
}
