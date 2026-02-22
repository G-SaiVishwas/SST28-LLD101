public class SampleData {
    public static ExportRequest weeklyReport() {
        return new ExportRequest("Weekly Report", longBody());
    }

    public static String longBody() {
        return "Name,Score\nAyaan,82\nRiya,91\n";
    }
}
