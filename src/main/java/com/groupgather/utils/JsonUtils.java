package com.groupgather.utils;

import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonUtils {

    public JsonUtils(){
    }

    // Creates a List of 2 Lists, the first settings, 2nd corresponding values
    public List<List<String>> mapToLists(Map<String, String> payload){
        List<String> keys = new ArrayList<>(payload.keySet());
        List<String> values = new ArrayList<>();
        double longitude = 0;
        double latitude = 0;

        for(int i = 0; i < keys.size(); i++){
            String key = keys.get(i);

            if (key.equals("longitude")) {
                longitude = Double.parseDouble(payload.get(key));
                keys.remove(i--);
            } else if (key.equals("latitude")){
                latitude = Double.parseDouble(payload.get(key));
                keys.remove(i--);
            } else {
                values.add(payload.get(key));
            }
        }

        if(longitude != 0 && latitude != 0){
            keys.add("location");
            values.add(new PGgeometry(new Point(longitude, latitude)).toString());
        }

        List<List<String>> lists = new ArrayList<>();
        lists.add(keys);
        lists.add(values);
        return lists;
    }

    // Used for dynamically making SQL statements
    public String dynamicSQL(List<String> columns, List<String> values){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < columns.size(); i++){
            sb.append(columns.get(i));
            sb.append(" = ");
            sb.append(values.get(i));
            sb.append(",");
        }

        sb.delete(sb.toString().length() - 2, sb.toString().length()); // Removes the extra comma
        return sb.toString();
    }


}
