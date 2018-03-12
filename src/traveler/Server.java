/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveler;

/**
 *
 * @author ebasso
 */
public class Server {

    int availability_index; //: 100;
    String build_level; //: "9.0.1.20.201711091501_20";
    String domino_name; //": "SRCBSA01086\/BancodoBrasil",
    String heart_beat; //": "1520544455297",
    String hostname; //": "srcbsa01086.bb.com.br",
    String ip_address; //": "192.168.253.37",
    int number_of_users; //": "26",
    int port; //": "50125",
    int server_status; //": "1"
    int devices;

    public Server(int availability_index, String build_level, String domino_name, String heart_beat, String hostname, String ip_address, int number_of_users, int server_status) {
        this.availability_index = availability_index;
        this.build_level = build_level;
        this.domino_name = domino_name;
        this.heart_beat = heart_beat;
        this.hostname = hostname;
        this.ip_address = ip_address;
        this.number_of_users = number_of_users;
        this.server_status = server_status;
        
        this.devices = -1;
    }
    
    @Override
    public String toString() {
        return "{" + "ai=" + availability_index + ", name=" + domino_name + ", hb=" + heart_beat + ", users=" + number_of_users + ", status=" + server_status + '}';
    }

    
}
