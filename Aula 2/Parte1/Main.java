import java.util.Scanner;

class Produtor implements Runnable {
    private int codigo;
    private int producaoPorColheita;
    private int intervaloEntreColheitas;
    private int totalSacas = 0;

    public Produtor(int codigo, int producaoPorColheita, int intervaloEntreColheitas) {
        this.codigo = codigo;
        this.producaoPorColheita = producaoPorColheita;
        this.intervaloEntreColheitas = intervaloEntreColheitas;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            totalSacas += producaoPorColheita;
            System.out.println("Produtor " + codigo + " produziu um total de " + totalSacas + " sacas.");
            try {
                Thread.sleep(intervaloEntreColheitas * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o intervalo entre colheitas e a produção por colheita para o produtor 111:");
        int intervalo111 = scanner.nextInt();
        int producao111 = scanner.nextInt();
        System.out.println("Digite o intervalo entre colheitas e a produção por colheita para o produtor 222:");
        int intervalo222 = scanner.nextInt();
        int producao222 = scanner.nextInt();

        Produtor produtor111 = new Produtor(111, producao111, intervalo111);
        Produtor produtor222 = new Produtor(222, producao222, intervalo222);

        Thread thread111 = new Thread(produtor111);
        Thread thread222 = new Thread(produtor222);

        thread111.start();
        thread222.start();
        scanner.close();
    }
}