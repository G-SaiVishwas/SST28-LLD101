public class ExportResult {
    public final byte[] data;
    public final String error;

    private ExportResult(byte[] data, String error) {
        this.data = data;
        this.error = error;
    }

    public static ExportResult ok(byte[] data) {
        return new ExportResult(data, null);
    }

    public static ExportResult fail(String error) {
        return new ExportResult(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }
}
