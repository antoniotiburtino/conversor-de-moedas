package servico;

import com.google.gson.Gson;
import excessoes.ConversaoException;
import modelos.ExchangeRateResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/2d10f8a124d321ebe81659e7/latest/";

    public ExchangeRateResponse obterTaxas(String moedaBase) throws ConversaoException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + moedaBase))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200){
                throw new ConversaoException("Erro ao obter taxas e câmbio: " + response.body());
            }

            Gson gson = new Gson();
            return gson.fromJson(response.body(), ExchangeRateResponse.class);
        } catch (Exception e) {
            throw new ConversaoException("Erro ao acessar a API: " + e.getMessage());
        }
    }

    public double obterTaxaCambio(String moedaBase, String moedaAlvo) throws ConversaoException{
        ExchangeRateResponse response = obterTaxas(moedaBase);
        if (response != null && response.getRates().containsKey(moedaAlvo)){
            return response.getRates().get(moedaAlvo);
        }
        throw new ConversaoException("Moeda alvo não encontrada: " + moedaAlvo);
    }

    public double converter(String moedaBase, String moedaAlvo, double valor) throws ConversaoException {
        double taxaCambio = obterTaxaCambio(moedaBase, moedaAlvo);
        return valor * taxaCambio; // Retorna o valor convertido
    }
}
