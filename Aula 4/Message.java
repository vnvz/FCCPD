import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String body;
    private LocalDateTime sendTime;
    private String topic;

    public Message(String body, LocalDateTime sendTime, String topic) {
        this.body = body;
        this.sendTime = sendTime;
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return "[" + sendTime.format(formatter) + "] " + topic + " : " + body;
    }
}