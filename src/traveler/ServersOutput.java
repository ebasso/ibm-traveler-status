package traveler;

import java.util.List;

/**
 *
 * @author ebasso
 */
public class ServersOutput {

    String title;
    String lastRun;
    List<Server> servers;

    public ServersOutput(String title, String lastRun, List<Server> servers) {
        this.title = title;
        this.lastRun = lastRun;
        this.servers = servers;

    }

    public String toJson() {
        String output = String.format("{\"title\": \"%s\", \"lastRun\": \"%s\", \"servers\": [", this.title, this.lastRun);

        for (Server server : this.servers) {
            output += String.format("{\"status\": %d, "
                    + "\"ai\": %d, "
                    + "\"name\": \"%s\", "
                    + "\"devices\": %d,"
                    + "\"users\": %d},",
                    server.server_status,
                    server.availability_index,
                    server.domino_name,
                    server.devices,
                    server.number_of_users);
        }
        output = output.substring(0, output.length() - 1) + "]";
        return output + "}";
    }

}
