import java.util.Scanner;

class Armazem {
    private int capacidadeMaxima;
    private int estoque;

    public Armazem(int capacidadeMaxima, int estoque) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.estoque = estoque;
    }

    public synchronized boolean armazenar(int producao) {
        if (estoque + producao <= capacidadeMaxima) {
            estoque += producao;
            return true;
        } else {
            System.out.println("Não há espaço suficiente no armazém para a produção.");
            return false;
        }
    }

    public void imprimirCapacidade() {
        System.out.println("Capacidade atual do armazém: " + estoque + "/" + capacidadeMaxima);
    }
}

class Produtor implements Runnable {
    private int codigo;
    private int producaoPorColheita;
    private int totalSacas = 0;
    private Armazem armazem;
    private int intervaloEntreColheitas;

    public Produtor(int codigo, int producaoPorColheita, Armazem armazem, int intervaloEntreColheitas) {
        this.codigo = codigo;
        this.producaoPorColheita = producaoPorColheita;
        this.armazem = armazem;
        this.intervaloEntreColheitas = intervaloEntreColheitas;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            totalSacas += producaoPorColheita;
            System.out.println("Produtor " + codigo + " produziu um total de " + totalSacas + " sacas.");
            if (!armazem.armazenar(producaoPorColheita)) {
                System.out.println("Armazém está cheio. Encerrando produção.");
                System.exit(0);
            }
            try {
                Thread.sleep(intervaloEntreColheitas * 1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o estoque e a capacidade máxima para o armazém:");
        int estoque = scanner.nextInt();
        int capacidadeMaxima = scanner.nextInt();

        Armazem armazem = new Armazem(capacidadeMaxima, estoque);
        armazem.imprimirCapacidade();

        System.out.println("Digite a produção por colheita e o intervalo entre colheitas para o produtor 111:");
        int producao111 = scanner.nextInt();
        int intervalo111 = scanner.nextInt();
        System.out.println("Digite a produção por colheita e o intervalo entre colheitas para o produtor 222:");
        int producao222 = scanner.nextInt();
        int intervalo222 = scanner.nextInt();
        scanner.close();

        Produtor produtor111 = new Produtor(111, producao111, armazem, intervalo111);
        Produtor produtor222 = new Produtor(222, producao222, armazem, intervalo222);

        new Thread(produtor111).start();
        new Thread(produtor222).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ignored) {
                    }
                    armazem.imprimirCapacidade();
                }
            }
        }).start();
    }
}