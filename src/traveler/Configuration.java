/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traveler;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ebasso
 */
public class Configuration {
    
    public final String TOOL_NAME = "\nTraveler Status";
    public final String TOOL_VERSION = "Version 2018-03-02-01\n";
    public final String TITLE = "Traveler Statistics";
    public Date DNOW;
    public Long LNOW;
    public String NOW;
    
    public String INIFILE;
    public String wwwRoot;
    
    public String server;
    public String username;
    public String password;
    
    public Configuration(String inifile) {
        this.INIFILE = inifile;
        
        this.DNOW = new Date();
        this.LNOW = this.DNOW.getTime();
        Timestamp tsTimestamp = new Timestamp(this.LNOW);
        this.NOW = tsTimestamp.toString();
    }
            
}
