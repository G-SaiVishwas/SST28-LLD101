import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;

    public OnboardingService(StudentRepository repo) {
        this.repo = repo;
    }

    public StudentRecord register(Map<String, String> fields) {
        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(
                id,
                fields.get("name"),
                fields.get("email"),
                fields.get("phone"),
                fields.get("program")
        );
        repo.save(rec);
        return rec;
    }
}
