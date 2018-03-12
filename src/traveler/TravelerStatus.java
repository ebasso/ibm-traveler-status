package traveler;

/**
 *
 * @author ebasso
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebasso
 */
public class TravelerStatus {

    private static String server;

    private static String username;
    private static String password;

    static String INIFILE = "TravelerStatus.properties";
    static String wwwRoot;

    public static void main(String[] args) {

        try {

            if (args.length != 0) {
                for (String arg : args) {
                    if (arg.startsWith("-u=")) {
                        username = arg.substring(3);
                    }
                    if (arg.startsWith("-p=")) {
                        password = arg.substring(3);
                    }
                    if (arg.startsWith("-s=")) {
                        server = arg.substring(3);
                    }
                    if (arg.startsWith("-out=")) {
                        wwwRoot = arg.substring(5);
                    }
                }
            } else {
                System.out.println("Traveler Status");
                System.out.println("");
                System.out.println("To run:");
                System.out.println("java -jar TravelerStatus.jar -u= -p= -out= -s=");
                System.out.println("");
                System.out.println("Where:");
                System.out.println(" -u : Username");
                System.out.println(" -p : Password");
                System.out.println(" -s : server");
                System.out.println(" -out : Output Directory");
                System.exit(0);
            }
            TravelerStatus app = new TravelerStatus();

            traveler.Configuration config = new traveler.Configuration(INIFILE);
            config.server = server;
            config.username = username;
            config.password = password;
            config.wwwRoot = wwwRoot;

            System.out.println("Retrieve Servers Status");
            app.doServersStatus(config);

            System.out.println("Retrieve Devices Status");
            app.doDevicesStatus(config);
            System.out.println("End.");
        } catch (IOException e) {
        }
    }

    private void doServersStatus(traveler.Configuration config) throws IOException {

        String href = "/api/traveler/servers";

        traveler.RestClient rc = new traveler.RestClient();
        List<traveler.Server> servers = new ArrayList<>();
        while (!href.equals("")) {

            String content = rc.fetchContent(config.server + href, config.username, config.password);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(traveler.PushStatus.class, new traveler.ServersStatusDeserializer())
                    .serializeNulls()
                    .create();
            traveler.ServersStatus ss = gson.fromJson(content, traveler.ServersStatus.class);

            servers.addAll(ss.getData());
            //System.out.println(ss);
            href = ss.getNext();
        }

        traveler.ServersOutput so = new traveler.ServersOutput(config.TITLE, config.NOW, servers);

        this.writeToFile(config.wwwRoot + "traveler-server-data.json", so.toJson());
    }

    private void doDevicesStatus(traveler.Configuration config) throws IOException {

        String href = "/api/traveler/pushstatus";
        // Page Multipler= 2000 devices/page = 20 dev * 100 multiplier
        String pageSize = "?pm=100";

        int count = 0;

        traveler.RestClient rc = new traveler.RestClient();
        List<traveler.Device> devices = new ArrayList<>();

        while (!href.equals("")) {

            String content = rc.fetchContent(config.server + href, config.username, config.password);
            //System.out.println(content);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(traveler.PushStatus.class, new traveler.PushStatusDeserializer())
                    .serializeNulls()
                    .create();
            traveler.PushStatus ps = gson.fromJson(content, traveler.PushStatus.class);

            devices.addAll(ps.getData());
            //System.out.println(ps);
            href = ps.getNext();
            count++;
        }

        traveler.DevicesSummary ds = new traveler.DevicesSummary(config, devices);

        this.writeToFile(config.wwwRoot + "traveler-devices-data.json", ds.toJson());
    }

    private void writeToFile(String filename, String output) {

        FileOutputStream fos;
        PrintStream ps;

        try {
            fos = new FileOutputStream(filename);
            ps = new PrintStream(fos);

            ps.println(output);

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
