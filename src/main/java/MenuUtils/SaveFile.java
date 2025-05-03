package MenuUtils;
import Midias.Media;
import Midias.Media.*;
import Controller.RodaCultural.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.google.gson.Gson;

public class SaveFile {
    public static void save(List<Media> midias) {
        Gson gson = new Gson();
        String json = gson.toJson(midias);

        try {
            FileWriter writer = new FileWriter("C:\\file.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nenhuma mídia salva.");
        }

        System.out.println(json);

    }
}



