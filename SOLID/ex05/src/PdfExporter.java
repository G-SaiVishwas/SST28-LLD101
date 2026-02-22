public class PdfExporter implements Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body.length() > 20) {
            return ExportResult.fail("PDF cannot handle content > 20 chars");
        }
        byte[] bytes = ("[PDF] " + req.title + "\n" + req.body).getBytes();
        return ExportResult.ok(bytes);
    }
}
