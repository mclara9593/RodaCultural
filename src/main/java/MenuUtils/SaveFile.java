package MenuUtils;
import Midias.Media;

import java.io.*;
import java.util.List;
import java.io.FileWriter;


import com.google.gson.Gson;

public class SaveFile {
    public static void save(List<Media> midias,String path) {
        Gson gson = new Gson();
        String json = gson.toJson(midias);
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();
            System.out.println("Mídia salva em :" + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nenhuma mídia salva.");
        }
        
    }

    public static List<Media> load(List<Media> midias,String path) {
        Gson gson = new Gson();
        try {

            BufferedReader br = new BufferedReader(new FileReader(path));

            //Converte String JSON para objeto Java
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<List<Media>>() {}.getType();
            List<Media> loadedMidias = gson.fromJson(br, listType);
            midias.addAll(loadedMidias);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return midias;
    }

}



