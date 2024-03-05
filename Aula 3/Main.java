import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a capacidade máxima para o armazém:");
        int capacidadeMaxima = scanner.nextInt();

        Armazem armazem = new Armazem(capacidadeMaxima);

        System.out.println("Digite a produção por colheita e o intervalo entre colheitas para o produtor:");
        int producao = scanner.nextInt();
        int intervaloProducao = scanner.nextInt();
        System.out.println("Digite o consumo por intervalo e o intervalo entre consumos para o consumidor:");
        int consumo = scanner.nextInt();
        int intervaloConsumo = scanner.nextInt();
        scanner.close();

        Produtor produtor = new Produtor(producao, armazem, intervaloProducao);
        Consumidor consumidor = new Consumidor(consumo, armazem, intervaloConsumo);

        new Thread(produtor).start();
        new Thread(consumidor).start();
    }
}