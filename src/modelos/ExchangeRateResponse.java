package modelos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateResponse {
    @SerializedName("base_code")
    private String base;

    private String date;

    @SerializedName("conversion_rates")
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> filtrarMoedas(String[] codigosMoedas){
        Map<String, Double> moedasFiltradas = new HashMap<>();
        for (String codigo : codigosMoedas){
            if (rates.containsKey(codigo)){
                moedasFiltradas.put(codigo, rates.get(codigo));
            }
        }
        return moedasFiltradas;
    }
}
