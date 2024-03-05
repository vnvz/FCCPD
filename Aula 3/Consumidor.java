public class Consumidor implements Runnable {
    private int consumoPorIntervalo;
    private Armazem armazem;
    private int intervaloEntreConsumos;

    public Consumidor(int consumoPorIntervalo, Armazem armazem, int intervaloEntreConsumos) {
        this.consumoPorIntervalo = consumoPorIntervalo;
        this.armazem = armazem;
        this.intervaloEntreConsumos = intervaloEntreConsumos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(intervaloEntreConsumos * 1000);
                armazem.consumir(consumoPorIntervalo);
            } catch (InterruptedException ignored) {
            }
        }
    }
}