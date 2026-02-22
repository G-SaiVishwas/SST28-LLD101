public class CsvExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String csv = "title,body\n" + req.title + "," + req.body.replace("\n", " ") + "\n";
        return ExportResult.ok(csv.getBytes());
    }
}
