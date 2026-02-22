public class JsonExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String json = "{\"title\":\"" + req.title + "\",\"body\":\"" + req.body + "\"}";
        return ExportResult.ok(json.getBytes());
    }
}
