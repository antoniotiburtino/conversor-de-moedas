package principal;

import excessoes.ConversaoException;
import modelos.ExchangeRateResponse;
import servico.Conversor;
import util.JsonUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Conversor conversor = new Conversor();
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        realizarConversao(sc, conversor);
                    } catch (ConversaoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    continuar = false;
                    System.out.println("Saindo do conversor. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        sc.close();
    }

    public static void exibirMenu() {
        System.out.println("Bem-vindo(a) ao Conversor de Moedas!");

        System.out.println("1. Converter Moeda");
        System.out.println("2. Sair");
        System.out.println("Escolha uma opção: ");
    }

    private static void realizarConversao(Scanner sc, Conversor conversor) throws ConversaoException {
        System.out.println("Selecione a moeda BASE (origem):");
        String moedaBase = escolherMoeda(sc);

        System.out.println("Selecione a moeda ALVO (destino):");
        String moedaAlvo = escolherMoeda(sc);

        ExchangeRateResponse exchangeRateResponse = conversor.obterTaxas(moedaBase);

        System.out.println("Digite o valor a ser convertido:");
        double valor = sc.nextDouble();

        try {
            double valorConvertido = conversor.converter(moedaBase, moedaAlvo, valor);
            System.out.printf("O valor de %.2f %s em %s é: %.2f%n%n", valor, moedaBase, moedaAlvo, valorConvertido);
        } catch (ConversaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        JsonUtil.escreverJsonEmArquivo(exchangeRateResponse, "taxas_cambio.json");
    }

    private static String escolherMoeda(Scanner sc) {
        System.out.println("Moedas disponíveis:");
        System.out.println("1. ARS - Peso Argentino");
        System.out.println("2. BOB - Boliviano");
        System.out.println("3. BRL - Real Brasileiro");
        System.out.println("4. CLP - Peso Chileno");
        System.out.println("5. COP - Peso Colombiano");
        System.out.println("6. USD - Dólar Americano");

        while (true) {
            System.out.println("Digite o número da moeda desejada: ");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1: return "ARS";
                case 2: return "BOB";
                case 3: return "BRL";
                case 4: return "CLP";
                case 5: return "COP";
                case 6: return "USD";
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
