package br.com.alura.screenmatch.modelos;

public class Titulo implements Comparable<Titulo> {
        // notação

    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOmdb tituloOmdb){
        this.nome = tituloOmdb.title();
        String runtime = tituloOmdb.runtime();
        if(tituloOmdb.year().length() >= 4){
            this.anoDeLancamento = Integer.valueOf(tituloOmdb.year().substring(0, 4));
        }
        if(runtime != null && !runtime.equals("N/A")){
            this.duracaoEmMinutos = Integer.valueOf(tituloOmdb.runtime().substring(0, 2));
        }else{
            this.duracaoEmMinutos = 0;
        }

    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota){
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia(){
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }

    @Override
    public String toString() {
      return "(Nome: " + this.nome + ", ano de lançamento: " + this.anoDeLancamento + ", duração do filme: " + this.duracaoEmMinutos + " minutos) ";
    }
}
