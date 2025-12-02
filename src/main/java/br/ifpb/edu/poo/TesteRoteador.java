package br.ifpb.edu.poo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TesteRoteador {

    public static void main(String[] args) {

        String entradaSimulada =
                // 1 – Cadastrar Interface Física
                "1\neth0\n192.168.1.1\n" +

                // CADASTRO DAS ROTAS DOS EXEMPLOS
                // Rota 1: 0.0.0.0/0
                "2\n0.0.0.0\n0.0.0.0\n1.1.1.1\neth0\n" +

                // Rota 2: 10.0.0.0/8
                "2\n10.0.0.0\n255.0.0.0\n10.0.0.254\neth0\n" +

                // Rota 3: 10.1.0.0/16
                "2\n10.1.0.0\n255.255.0.0\n10.1.0.254\neth0\n" +

                // Rota 4: 10.1.2.0/24
                "2\n10.1.2.0\n255.255.255.0\n10.1.2.254\neth0\n" +

                // Rota 5: 192.168.1.0/24
                "2\n192.168.1.0\n255.255.255.0\n192.168.1.254\neth0\n" +

                // LISTAR ROTAS
                "3\n" +

                // EXEMPLO 1 → Roteamento 10.1.2.45 (esperado: 10.1.2.0/24)
                "5\n10.1.2.45\n" +

                // EXEMPLO 2 → Roteamento 10.3.5.7 (esperado: 10.0.0.0/8)
                "5\n10.3.5.7\n" +

                // EXEMPLO 3 → Roteamento 8.8.8.8 (esperado: rota default 0.0.0.0/0)
                "5\n8.8.8.8\n" +

                // UC06 – Configurar exibição para CIDR
                "8\n2\n" +

                // LISTAR ROTAS agora em CIDR
                "3\n" +

                // UC08 – Resetar tabela de rotas
                "9\ns\n" +

                // Listar novamente → deve estar vazia
                "3\n" +

                // Listar interfaces
                "4\n" +

                // SAIR
                "0\n";

        InputStream input = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(input);

        System.out.println("===== INICIANDO TESTE COMPLETO DO ROTEADOR =====\n");
        Main.main(new String[]{});
        System.out.println("\n===== FIM DO TESTE =====");
    }
}
