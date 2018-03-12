package traveler;

import java.sql.Timestamp;

/**
 *
 * @author ebasso
 *
 * 1. username – The user's complete domino name. 2. deviceid– The device's id
 * that we are reporting on. 3. notification_type – The network protocol in
 * which Traveler will send notifications to the device. 4. manufacturer_address
 * – A unique address for the device given by the manufacturer. 5.
 * manufacturer_provider_id – An id representing what manufacturer the device
 * belongs to. Example: “GCM” 6. applications_to_synchronize – The applications
 * that the device has subscribed to sync. 7. change_flags – The applications
 * and folders subscribed to by the device in which have changes that are not
 * yet synced to device. 8. connection – The current connection information for
 * the Traveler server and the device. 9. connection_time – The connection time
 * for either when the connection was connected or the time of disconnection.
 * 10. offline_time – The time in which Traveler has considered the device to be
 * offline. 11. last_message_received – The epoch for the last time a message
 * was received on the Traveler server from the device. 12. last_message_sent –
 * The epoch for the last time a message was sent from the Traveler server to
 * the device. 13. last_sync_time – The epoch for the last time a device sync
 * was completed.
 */
public class Device {

    String deviceid;
    //"Android_69b371bab20a154f", 
    //": "IBM_IOS_com.zenprise_68A90897020E47D78B1CB3F890D9199E", "ApplDMPJ32BEDJ8T",

    String username; //": "F0091379 Adao Milton Ribeiro da Silva\/BancodoBrasil"
    String applications_to_synchronize; //  ": "mail , calendar, journal, task, serviceability, security
    String change_flags; //  ": "mail   :add(292244152:add:8), serviceability:configGet",
    String last_sync_time; //": "1519914403000",
    String pending_sync; //": "true",
    String push_enabled; //": "false",

    String connection;  //": "false",
    String connection_time; //": "0",
    String last_message_received; //": "0",
    String last_message_sent; //": "0",
    String manufacturer_address; //": "",
    String manufacturer_provided_id; //": "APNS_APPLE_TODO_PRODUCTION",
    String notification_type; //": "HTTP","ActiveSync",
    String offline_time; //": "0",
    
    String lastSyncTimeStatus;

    @Override
    public String toString() {
        return "Device{" + "deviceid=" + deviceid + ", username=" + username + '}';
    }

    public String getUsername() {
        return this.username.substring(0, this.username.indexOf("/"));
    }

    public String getLastSynctimeAsString() {
        if (this.last_sync_time == null) {
            return "00000000000000";
        }
        if (this.last_sync_time.equals("0")) {
            return "00000000000000";
        }
        return this.convertToCommonDate(this.last_sync_time);
    }

    public Long getLastSyncTimeAsLong() {
        return Long.parseLong(this.last_sync_time);
    }

    public String getComments() {
        return "";
    }

    public String getLt() {
        return "";
    }

    public String getHs() {
        return "";
    }

    public String getLastPush() {
        return "";
    }

    public String getDeviceProvider() {
        return "";
    }

    private String convertToCommonDate(String str) {

        str = str.trim();
        Long lts = Long.valueOf(str);
        Timestamp ts = new Timestamp(lts);
        //System.out.println("tsTimestamp: " + tsTimestamp.toString());
        return ts.toString();
    }

}
