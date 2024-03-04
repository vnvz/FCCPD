public class PingPongR implements Runnable{
	private String palavra;
	private int delay;
	
	public PingPongR(String oQueFalar, int tempoDelay){
		palavra = oQueFalar;
		delay = tempoDelay;
	}
	
	public void run(){
		try{
			for(;;){
				System.out.print(palavra + " ");
				Thread.sleep(delay);
			}
		}catch(Exception e){
			System.out.print("Erro");
			return;
		}
	}
	public static void main(String[] argos){
		PingPongR ping = new PingPongR("ping", 500);
		PingPongR pong = new PingPongR("PONG", 1000);
		new Thread(ping).start();
		new Thread(pong).start();
	}
}