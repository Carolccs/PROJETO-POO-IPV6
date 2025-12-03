package br.ifpb.edu.poo;

public class Rota {
    private String destino;
    private String mascara;
    private String gateway;
    private InterfaceFisica interfaceFisica;

    public Rota(String destino, String mascara, String gateway, InterfaceFisica interfaceFisica) {
        this.destino = destino;
        this.mascara = mascara;
        this.gateway = gateway;
        this.interfaceFisica = interfaceFisica;
    }

    public String getDestino() { return destino; }
    public String getMascara() { return mascara; }
    public String getGateway() { return gateway; }
    public InterfaceFisica getInterfaceFisica() { return interfaceFisica; }

    @Override
    public String toString() {
        return destino + " / " + mascara + " / " + gateway + " (" + interfaceFisica.getNome() + ")";
    }
}