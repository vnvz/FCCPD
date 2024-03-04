public class PingPongT extends Thread{
	private String palavra;
	private int delay;
	
	public PingPongT(String oQueFalar, int tempoDelay){
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
		PingPongT ping = new PingPongT("ping", 500);
		PingPongT pong = new PingPongT("PONG", 1000);
		ping.start();
		pong.start();
	}
}