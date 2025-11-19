package br.ifpb.edu.poo;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorInterfaces {
    private List<InterfaceFisica> interfaces = new ArrayList<>();

    public void cadastrarInterface(String nome, String ip) {
        for (InterfaceFisica i : interfaces) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                System.out.println("❌ Já existe uma interface com este nome!");
                return;
            }
        }
        interfaces.add(new InterfaceFisica(nome, ip));
        System.out.println("✅ Interface cadastrada com sucesso!");
    }

    public InterfaceFisica buscarPorNome(String nome) {
        for (InterfaceFisica i : interfaces) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return null;
    }

    public void listarInterfaces() {
        if (interfaces.isEmpty()) {
            System.out.println("⚠️ Nenhuma interface cadastrada.");
        } else {
            System.out.println("🌐 Interfaces Físicas:");
            for (InterfaceFisica i : interfaces) {
                System.out.println("- " + i);
            }
        }
    }
}
