public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Powerable pj = (Powerable) reg.get("Projector");
        pj.powerOn();
        ((InputConnectable) reg.get("Projector")).connectInput("HDMI-1");

        ((BrightnessControl) reg.get("LightsPanel")).setBrightness(60);

        ((TemperatureControl) reg.get("AirConditioner")).setTemperatureC(24);

        Scannable scan = (Scannable) reg.get("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Powerable) reg.get("Projector")).powerOff();
        ((Powerable) reg.get("LightsPanel")).powerOff();
        ((Powerable) reg.get("AirConditioner")).powerOff();
    }
}
