package br.ifpb.edu.poo;

public class InterfaceFisica {
    private String nome;
    private String enderecoIP;

    public InterfaceFisica(String nome, String enderecoIP) {
        this.nome = nome;
        this.enderecoIP = enderecoIP;
    }

    public String getNome() {
        return nome;
    }

    public String getEnderecoIP() {
        return enderecoIP;
    }

    @Override
    public String toString() {
        return nome + " (" + enderecoIP + ")";
    }
