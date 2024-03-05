public class Produtor implements Runnable {
    private int producaoPorColheita;
    private Armazem armazem;
    private int intervaloEntreColheitas;

    public Produtor(int producaoPorColheita, Armazem armazem, int intervaloEntreColheitas) {
        this.producaoPorColheita = producaoPorColheita;
        this.armazem = armazem;
        this.intervaloEntreColheitas = intervaloEntreColheitas;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(intervaloEntreColheitas * 1000);
                armazem.armazenar(producaoPorColheita);
            } catch (InterruptedException ignored) {
            }
        }
    }
}