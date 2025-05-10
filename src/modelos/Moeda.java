package modelos;

public class Moeda {
    private String nome;
    private String codigo;
    private double taxaCambio;

    public Moeda(String nome, String codigo, double taxaCambio) {
        this.nome = nome;
        this.codigo = codigo;
        this.taxaCambio = taxaCambio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTaxaCambio() {
        return taxaCambio;
    }

    public void setTaxaCambio(double taxaCambio) {
        this.taxaCambio = taxaCambio;
    }
}
