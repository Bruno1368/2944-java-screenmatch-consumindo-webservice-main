package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite um filme para busca:  ");
        var busca = leitura.nextLine();

        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=1c0361f4";

        try{

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            //instancia gson, que transforma json em objeto, e também ajusta para letra maiscula
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            //Titulo filme = gson.fromJson(json, Titulo.class);
            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            //System.out.println(meuTituloOmdb);
            //  try {
            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Meu titulo: " + meuTitulo);
        }catch (NumberFormatException err) {
            System.out.println("Aconteceu um erro de formato: ");
            System.out.println(err.getMessage());
        }catch (IllegalArgumentException err){
            System.out.println("O endereço está escrito em um formato inválido ");
            //System.out.println(err.getMessage());
        }catch (ErroDeConversaoDeAnoException err){
            System.out.println(err.getMessage());
        }

        System.out.println("Programa executado corretamente");
    }

}
