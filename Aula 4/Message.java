import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String topic;
    private String body;
    private LocalDateTime timestamp;

    public Message(String topic, String body) {
        this.topic = topic;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return "[" + timestamp.format(formatter) + "] " + topic + " : " + body;
    }
}