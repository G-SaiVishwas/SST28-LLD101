import java.util.*;

public class AuditLog {
    private final List<String> entries = new ArrayList<>();

    public void add(String entry) {
        entries.add(entry);
    }

    public List<String> all() {
        return Collections.unmodifiableList(entries);
    }
}
