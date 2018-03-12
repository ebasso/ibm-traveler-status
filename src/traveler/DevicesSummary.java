package traveler;

import java.util.List;

/**
 *
 * @author ebasso
 */
public class DevicesSummary {

    public String title;
    public String lastRun;
    public List<Device> devices;
    public String httpCodes;
    public String deviceClients;

    public int countDevices;
    public int countDevicesGreen;
    public int countDevicesYellow;
    public int countDevicesRed;
    public int countDevicesGray;
    public int percDevicesGreen;
    public int percDevicesYellow;
    public int percDevicesRed;
    public int percDevicesGray;
    public int percDevicesGreen2;
    public int percDevicesYellow2;
    public int percDevicesRed2;

    private int countApple;
    private int countAppleVerse;
    private int countAndroid;
    private int percApple;
    private int percAppleVerse;
    private int percAndroid;
    
    private Configuration config;

    public DevicesSummary(Configuration config, List<Device> devices) {

        this.config = config;
        this.devices = devices;

        this.summaryDevices();
        this.deviceClients = this.summaryDeviceClients();
        this.httpCodes = this.summaryHttpCodes();
    }

    public String summaryHttpCodes() {
        return "[{\"code\": \"200 OK\", \"qtd\": 0, \"perc\": 0},"
                + "{\"code\": \"499 Conflict\",\"qtd\": 0,\"perc\": 0},"
                + "{\"code\": \"403 Acesso proibido\",\"qtd\": 0,\"perc\": 0},"
                + "{\"code\": \"409 Sync resume\",\"qtd\": 0,\"perc\": 0},"
                + "{\"code\": \"449 Conflict\",\"qtd\": 0,\"perc\": 0},"
                + "{\"code\": \"503 Servidor Ocupado\",\"qtd\": 0,\"perc\": 0},"
                + "{\"code\": \"000 Null\",\"qtd\": 0,\"perc\": 0}]";
    }

    private String summaryDeviceClients() {
        return String.format("[{\"client\": \"Android\",\"qtd\": %d,\"perc\": %d},"
                + "{\"client\": \"Apple\",\"qtd\": %d,\"perc\": %d},"
                + "{\"client\": \"Apple (Verse)\",\"qtd\": %d,\"perc\": %d}]",
                countAndroid, percAndroid, countApple, percApple, countAppleVerse, percAppleVerse);
    }

    private void summaryDevices() {

        long lessThan2h = this.config.LNOW - 7200000; //(2 * 60 * 60 * 1000); 
        long lessThan12h = this.config.LNOW - 43200000;//(12 * 60 * 60 * 1000); // Hrs*Min*Sec*Mills
        long lessThan72h = this.config.LNOW - 259200000; //(3 * 24 * 60 * 60 * 1000); // Dias * Hrs*Min*Sec*Mills

        countDevices = 0;
        countDevicesGreen = 0;
        countDevicesYellow = 0;
        countDevicesRed = 0;
        countDevicesGray = 0;
        countApple = 0;
        countAppleVerse = 0;
        countAndroid = 0;
        
        devices.forEach((Device device) -> {

            countDevices++;
            
            long tsSync = device.getLastSyncTimeAsLong();

            if (tsSync > lessThan2h) {
                device.lastSyncTimeStatus = "lessThan2h";
                countDevicesGreen++;
            } else if (tsSync > lessThan12h) {
                device.lastSyncTimeStatus = "lessThan12h";
                countDevicesYellow++;
            } else if (tsSync > lessThan72h) { //tsSync < lOlderThan72h
                device.lastSyncTimeStatus = "lessThan72h";
                countDevicesRed++;
            } else {
                device.lastSyncTimeStatus = "moreThan72h";
                countDevicesGray++;
            }

            if (device.deviceid.contains("IBM_IOS")) {
                countAppleVerse++;
            } else if (device.deviceid.contains("Android_")) {
                countAndroid++;
            } else if (device.deviceid.contains("Appl")) {
                countApple++;
            } else if (device.notification_type.equals("ActiveSync")) {
                countApple++;
            }

        
        });

        if (countDevices == 0) {
            countDevices = 1;
        }

        percDevicesGreen = countDevicesGreen * 100 / countDevices;
        percDevicesYellow = countDevicesYellow * 100 / countDevices;
        percDevicesRed = countDevicesRed * 100 / countDevices;
        percDevicesGray = countDevicesGray * 100 / countDevices;

        percApple = countApple * 100 / countDevices;
        percAppleVerse = countAppleVerse * 100 / countDevices;
        percAndroid = countAndroid * 100 / countDevices;

    }

    public String toJson() {
        String output = String.format("{\"title\": \"%s\", \"lastRun\": \"%s\", ", config.TITLE, config.NOW);

        output += "\"countDevices\": " + this.countDevices + ",";
        output += "\"countDevicesGreen\": " + this.countDevicesGreen + ",";
        output += "\"countDevicesYellow\": " + this.countDevicesYellow + ",";
        output += "\"countDevicesRed\": " + this.countDevicesRed + ",";
        output += "\"countDevicesGray\": " + this.countDevicesGray + ",";
        output += "\"percDevicesGreen\": " + this.percDevicesGreen + ",";
        output += "\"percDevicesYellow\": " + this.percDevicesYellow + ",";
        output += "\"percDevicesRed\": " + this.percDevicesRed + ",";
        output += "\"percDevicesGray\": " + this.percDevicesGray + ",";

        output += "\"deviceClients\": " + deviceClients + ",";
        output += "\"httpCodes\": " + httpCodes + ",";
        output += "\"devices\": [ ";  // dont remove the last space

        for (Device device : this.devices) {
            output += "{";
            output += "\"deviceid\": \"" + device.deviceid + "\", ";
            output += "\"username\": \"" + device.getUsername() + "\", ";
            output += "\"dp\": \"" + device.getDeviceProvider() + "\", ";
            output += "\"ls\": \"" + device.getLastSynctimeAsString() + "\", ";
            output += "\"ls_status\": \"" + device.lastSyncTimeStatus + "\", ";
            output += "\"lp\": \"" + device.getLastPush() + "\", ";
            output += "\"hs\": \"" + device.getHs() + "\", ";
            output += "\"pe\": \"" + device.push_enabled + "\", ";
            output += "\"ps\": \"" + device.pending_sync + "\", ";
            output += "\"ap2sy\": \"" + device.applications_to_synchronize + "\", ";
            output += "\"cf\": \"" + device.change_flags + "\", ";
            output += "\"lt\": \"" + device.getLt() + "\", ";
            output += "\"cmts\": [" + device.getComments() + "]";
            output += "},";
        }

        output = output.substring(0, output.length() - 1) + "]";
        return output + "}";
    }

}
