import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite o nome do usuário:");
            String username = scanner.nextLine();
            Student student = new Student(username);

            System.out.println("Digite a opção de tópico (1 - Avisos Gerais, 2 - Atividades Extracurriculares, 3 - Ambos):");
            int topicOption = scanner.nextInt();
            scanner.nextLine();

            Topic generalNotices = new Topic("Avisos Gerais");
            Topic extracurricularActivities = new Topic("Atividades Extracurriculares");

            switch (topicOption) {
                case 1:
                    student.subscribe(generalNotices.getName());
                    break;
                case 2:
                    student.subscribe(extracurricularActivities.getName());
                    break;
                case 3:
                    student.subscribe(generalNotices.getName());
                    student.subscribe(extracurricularActivities.getName());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    System.exit(0);
            }

            new Thread(student::receiveMessage).start();

            Server server = new Server();

            while (true) {
                Message message1 = new Message(generalNotices.getName(), "Aviso geral " + LocalDateTime.now());
                Message message2 = new Message(extracurricularActivities.getName(), "Atividade extracurricular " + LocalDateTime.now());
                server.sendMessage(message1, generalNotices);
                server.sendMessage(message2, extracurricularActivities);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}