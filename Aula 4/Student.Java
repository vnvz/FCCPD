import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

public class Student {
    @SuppressWarnings("unused")
    private String username;
    private List<String> topics;

    public Student(String username) {
        this.username = username;
        this.topics = new ArrayList<>();
    }

    public void subscribe(String topic) {
        this.topics.add(topic);
        System.out.println("Você se inscreveu com sucesso no tópico: " + topic);
    }

    public void receiveMessage() {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            try (MulticastSocket socket = new MulticastSocket(4446)) {
                socket.joinGroup(new InetSocketAddress(group, 4446), networkInterface);
                while (true) {
                    byte[] buffer = new byte[1000];
                    DatagramPacket message = new DatagramPacket(buffer, buffer.length);
                    socket.receive(message);
                    String receivedMessage = new String(message.getData(), 0, message.getLength());
                    String topicName = receivedMessage.split(":")[0];
    
                    System.out.println("Tópico recebido: " + topicName + ", Tópicos inscritos: " + this.topics);
    
                    if (this.topics.contains(topicName)) {
                        System.out.println("Mensagem recebida: " + receivedMessage);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}