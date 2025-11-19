package br.ifpb.edu.poo;

public class Rota {
    private String destino;
    private String gateway;
    private String mascara;
    private InterfaceFisica interfaceFisica; // agora é um objeto, não uma String

    public Rota(String destino, String gateway, String mascara, InterfaceFisica interfaceFisica) {
        this.destino = destino;
        this.gateway = gateway;
        this.mascara = mascara;
        this.interfaceFisica = interfaceFisica;
    }

    public String getDestino() { return destino; }
    public String getMascara() { return mascara; }
    public String getGateway() { return gateway; }
    public InterfaceFisica getInterfaceFisica() { return interfaceFisica; }

    @Override
    public String toString() {
        return destino + " / " + mascara + " → " + gateway + " (" + interfaceFisica.getNome() + ")";
    }
}