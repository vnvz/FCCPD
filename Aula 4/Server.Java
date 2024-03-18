import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    public void sendMessage(Message message, Topic topic) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.0");
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            try (MulticastSocket socket = new MulticastSocket()) {
                socket.joinGroup(new InetSocketAddress(group, 4446), networkInterface);
                byte[] msg = message.toString().getBytes();
                DatagramPacket packet = new DatagramPacket(msg, msg.length, group, 4446);
                socket.send(packet);
                System.out.println("Mensagem enviada: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runServer() {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, String> topicCodes = new HashMap<>();
            topicCodes.put(1, "Avisos Gerais");
            topicCodes.put(2, "Atividades Extracurriculares");
            topicCodes.put(3, "Ambos");

            while (true) {
                System.out.println("Códigos dos tópicos:");
                for (Map.Entry<Integer, String> entry : topicCodes.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }

                System.out.println("Digite o código do tópico da mensagem:");
                int topicCode = scanner.nextInt();
                scanner.nextLine();

                String topicName = topicCodes.get(topicCode);
                if (topicName == null) {
                    System.out.println("Código do tópico inválido. Tente novamente.");
                    continue;
                }

                Topic topic = new Topic(topicName);

                System.out.println("Digite a mensagem:");
                String messageBody = scanner.nextLine();
                Message message = new Message(topic.getName(), messageBody);

                if (topicCode == 3) {
                    sendMessage(message, new Topic(topicCodes.get(1)));
                    sendMessage(message, new Topic(topicCodes.get(2)));
                } else {
                    sendMessage(message, topic);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }
}