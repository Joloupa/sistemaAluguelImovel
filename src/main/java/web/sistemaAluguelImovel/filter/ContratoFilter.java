package web.sistemaAluguelImovel.filter;

public class ContratoFilter {

     private String proprietario;
    private String locatario;
    private String cidade;

     public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getLocatario() {
        return locatario;
    }

    public void setLocatario(String locatario) {
        this.locatario = locatario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "proprietario: " + proprietario + "\nlocatario: " + locatario + "\ncidade: " + cidade;
    }

    
}
