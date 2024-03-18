import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome de usuário:");
        String username = scanner.nextLine();

        Student student = new Student(username);

        System.out.println("Escolha um tópico para se inscrever:");
        System.out.println("1. Avisos Gerais");
        System.out.println("2. Atividades Extracurriculares");
        System.out.println("3. Os dois tópicos");

        int option = scanner.nextInt();

        Topic generalNotices = new Topic("Avisos Gerais");
        Topic extracurricularActivities = new Topic("Atividades Extracurriculares");

        switch (option) {
            case 1:
                student.subscribe(generalNotices.getName());
                generalNotices.addStudent(student);
                break;
            case 2:
                student.subscribe(extracurricularActivities.getName());
                extracurricularActivities.addStudent(student);
                break;
            case 3:
                student.subscribe(generalNotices.getName());
                generalNotices.addStudent(student);
                student.subscribe(extracurricularActivities.getName());
                extracurricularActivities.addStudent(student);
                break;
        }

        Server server = new Server();
        Message message = new Message("Teste", LocalDateTime.now(), generalNotices.getName());
        server.sendMessage(message, generalNotices);
    }
}