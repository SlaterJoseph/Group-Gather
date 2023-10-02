package com.groupgather.utils;

import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JsonUtils {
    Set<String> requiredQuotes;

    public JsonUtils(){
        requiredQuotes = new HashSet<>();
        fillRequiredQuotes();
    }

    // Adds the needed values to requires quotes
    private void fillRequiredQuotes() {
        requiredQuotes.add("first_name");
        requiredQuotes.add("last_name");
        requiredQuotes.add("nick_name");
        requiredQuotes.add("username");
        requiredQuotes.add("description");
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
            String colVal = columns.get(i);
            sb.append(colVal);
            sb.append(" = ");

            if(requiredQuotes.contains(colVal)){
                sb.append("'");
                sb.append(values.get(i));
                sb.append("'");
            } else {
                sb.append(values.get(i));
            }
            sb.append(", ");
        }

        sb.delete(sb.toString().length() - 2, sb.toString().length()); // Removes the extra comma
        return sb.toString();
    }


}
