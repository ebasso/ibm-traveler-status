package traveler;

import java.util.List;

/**
 *
 * @author ebasso
 *
 */
public class ServersStatus {

    public int code;         //": "200",
    public List<Server> data;      //": "",
    public String href;      // "\/api\/traveler\/servers?page=1&pm=1",
    public String message;   // "OK",
    public String next;      // "",
    public int totalRecords; // "6657"

    public ServersStatus(int code, List<Server> data, String href, String message, String next, int totalRecords) {
        this.code = code;
        this.data = data;
        this.href = href;
        this.message = message;
        this.next = next;
        this.totalRecords = totalRecords;
    }

    public List<Server> getData() {
        return data;
    }

    public String getNext() {
        if (next == null && !"".equals(next)) {
            return "";
        }
        
        return next;
    }
    
    
    @Override
    public String toString() {
        
        return String.format("\nServerStatus{\n\tcode=%d, \n\tdata=%s, \n\thref=%s, \n\tmessage=%s, \n\tnext=%s, \n\ttotalRecords=%s'\n}",
                code,data,href,message,next,totalRecords); 
    }   

}
