import java.util.*;

public class Demo05 {
    public static void main(String[] args) {
        ExportRequest req = SampleData.weeklyReport();

        List<Exporter> exporters = List.of(new PdfExporter(), new CsvExporter(), new JsonExporter());
        String[] names = {"PDF", "CSV", "JSON"};

        System.out.println("=== Export Demo ===");
        for (int i = 0; i < exporters.size(); i++) {
            ExportResult result = exporters.get(i).export(req);
            if (result.isSuccess()) {
                System.out.println(names[i] + " bytes=" + result.data.length);
            } else {
                System.out.println(names[i] + " ERROR: " + result.error);
            }
        }
    }
}
