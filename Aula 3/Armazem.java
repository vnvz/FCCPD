public class Armazem {
    private int capacidadeMaxima;
    private int estoque;

    public Armazem(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.estoque = 0;
    }

    public synchronized void armazenar(int producao) throws InterruptedException {
        while (estoque + producao > capacidadeMaxima) {
            System.out.println("Armazém cheio. Produtor esperando...");
            wait();
        }
        estoque += producao;
        System.out.println("Produtor produziu " + producao + " sacas. Total no armazém: " + estoque);
        notifyAll();
    }

    public synchronized void consumir(int consumo) throws InterruptedException {
        while (estoque < consumo) {
            System.out.println("Armazém vazio. Consumidor esperando...");
            wait();
        }
        estoque -= consumo;
        System.out.println("Consumidor consumiu " + consumo + " sacas. Total no armazém: " + estoque);
        notifyAll();
    }
}