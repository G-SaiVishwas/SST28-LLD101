import java.util.*;

public class EligibilityEngine {
    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules, FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityResult result = evaluate(s);
        ReportPrinter.print(s, result);
        store.save(s.rollNo, result.status);
    }

    public EligibilityResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            String reason = rule.check(s);
            if (reason != null) {
                status = "NOT_ELIGIBLE";
                reasons.add(reason);
                break;
            }
        }

        return new EligibilityResult(status, reasons);
    }
}
