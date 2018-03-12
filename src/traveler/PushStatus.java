package traveler;

import java.util.List;

/**
 *
 * @author ebasso
 *
 */
public class PushStatus {

    private int code;         //": "200",
    private List<Device> data;      //": "",
    private String href;      // "\/api\/traveler\/pushstatus?page=1&pm=1",
    private String message;   // "OK",
    private String next;      // "\/api\/traveler\/pushstatus?page=2&pm=1",
    private int totalRecords; // "6657"

    public PushStatus(int code, List<Device> data, String href, String message, String next, int totalRecords) {
        this.code = code;
        this.data = data;
        this.href = href;
        this.message = message;
        this.next = next;
        this.totalRecords = totalRecords;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Device> getData() {
        return data;
    }

    public void setData(List<Device> data) {
        this.data = data;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    @Override
    public String toString() {
        
        return String.format("\nPushStatus{\n\tcode=%d, \n\tdata=%s, \n\thref=%s, \n\tmessage=%s, \n\tnext=%s, \n\ttotalRecords=%s'\n}",
                code,data,href,message,next,totalRecords);
        
        //return "PushStatus{" + "code=" + code + ", data=" + data + ", href=" + href + ", message=" + message + ", next=" + next + ", totalRecords=" + totalRecords + '}';
    }   

}
