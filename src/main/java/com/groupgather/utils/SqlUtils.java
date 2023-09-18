package com.groupgather.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class SqlUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);
    private final HashSet<String> stringCols = new HashSet<>();

    public SqlUtils(){

        stringCols.add("name");
        stringCols.add("list_of_people");
        stringCols.add("description");
        stringCols.add("location");
    }

    public String createSQLActivity(int userId, List<String> columns, List<String> values){
        StringBuilder sb = new StringBuilder("INSERT INTO activities ");

        StringBuilder cols = new StringBuilder("(host_id, ");
        StringBuilder vals = new StringBuilder("(");
        vals.append(userId);
        vals.append(", ");

        for(int i = 0; i < columns.size(); i++){
            String col = columns.get(i);
            String val = values.get(i);

            if(stringCols.contains(col)){
                val = "'" + val + "'";
            }

            cols.append(col);
            cols.append(", ");
            vals.append(val);
            vals.append(", ");
        }

        cols.delete(cols.length() - 2, cols.length());
        cols.append(") VALUES ");
        vals.delete(vals.length() - 2, vals.length());
        vals.append(");");
        sb.append(cols);
        sb.append(vals);

        LOGGER.debug("SQL statement is {}", sb);

        return sb.toString();
    }
}
