package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JsonUtil {
    public static void escreverJsonEmArquivo(Object objeto, String caminhoArquivo){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(caminhoArquivo)){
            gson.toJson(objeto, writer);
            System.out.println("Arquivo JSON gerado com sucesso em: " + caminhoArquivo + "\n");
            System.out.println("***************************************\n");
        } catch (IOException e){
            System.out.println("Erro ao escrever o arquivo JSON: " + e.getMessage());
        }
    }
}
