package br.com.alura.screenmatch.modelos;

//usando record
public record TituloOmdb(String title, String year, String runtime) {

    @Override
    public String toString() {
        return "Titulo: " + title + ", ano de lançamento: " + year + ", duração do filme: " + runtime;
    }
}
