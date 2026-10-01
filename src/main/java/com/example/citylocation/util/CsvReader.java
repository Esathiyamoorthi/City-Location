package com.example.citylocation.util;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class CsvReader {

    public List<String> readCsv(String filePath) {

        List<String> ms = new ArrayList<String>();
        try  {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine())!= null) {
             if (!line.trim().isEmpty()){
                 String location = line.replace("\"", "").trim();
                 System.out.println("CSV Location: " + location);
                 ms.add(line);
             }
            }
            br.close();
        }catch (Exception e){System.out.println(e);}
        return ms;
    }
}

