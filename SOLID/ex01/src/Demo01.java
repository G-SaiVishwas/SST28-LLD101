import java.util.*;

public class Demo01 {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");

        InputParser parser = new InputParser();
        StudentValidator validator = new StudentValidator();
        StudentRepository db = new FakeDb();
        OnboardingService svc = new OnboardingService(db);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        System.out.println("INPUT: " + raw);

        Map<String, String> fields = parser.parse(raw);
        List<String> errors = validator.validate(fields);

        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        StudentRecord rec = svc.register(fields);
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + db.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render(db));
    }
}
