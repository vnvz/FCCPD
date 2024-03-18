import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {
    public void sendMessage(Message message, Topic topic) {
        try {
            MulticastSocket socket = new MulticastSocket();
            byte[] buffer = message.toString().getBytes();
            InetAddress group = InetAddress.getByName("230.0.0.0");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}