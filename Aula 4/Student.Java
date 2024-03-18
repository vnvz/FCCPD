import java.util.ArrayList;
import java.util.List;

public class Student {
    private String username;
    private List<String> topics;

    public Student(String username) {
        this.username = username;
        this.topics = new ArrayList<>();
    }

    public void subscribe(String topic) {
        this.topics.add(topic);
    }

    public String getUsername() {
        return username;
    }

    public List<String> getTopics() {
        return topics;
    }
}