package traveler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebasso
 */
public class ServersStatusDeserializer implements JsonDeserializer<ServersStatus> {

    @Override
    public ServersStatus deserialize(JsonElement jElem, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {

        JsonObject jo = jElem.getAsJsonObject();
        
        int code = jo.get("code").getAsInt();
        String href = jo.get("href").getAsString();
        String message = jo.get("message").getAsString();
        int totalRecords = jo.get("totalRecords").getAsInt();
        
        // next is null on last PushStatus response
        String next = "";
        try {
            next = jo.get("next").getAsString();
        } catch (java.lang.NullPointerException npe) {
            // do nothing
        }
        
        // convert "data" in a List of Devices
        JsonArray jarray = jo.get("data").getAsJsonArray();
        List<Server> data = new ArrayList<>();
        for (JsonElement je2 : jarray) {
            Server server = new Gson().fromJson(je2, Server.class);
            data.add(server);
        }
       
        return new ServersStatus(code, data, href, message, next, totalRecords);
    }
    
}
