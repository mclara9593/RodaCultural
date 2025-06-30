package MenuUtils;
import Model.Books;
import Model.Movie;
import Model.Show;
import Model.Media;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import static MenuUtils.MenuUtilities.getLists;


public class SaveFile {

    public static void save(List<Media> midias,String path) {

        Gson gsonAll = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<? extends Media>books= getLists(midias,"B");
        List<? extends Media>movies= getLists(midias,"M");
        List<? extends Media>shows= getLists(midias,"S");

        try {
                String json = gsonAll.toJson(midias);
                FileWriter writer = new FileWriter(path);
                writer.write(json);
                writer.close();


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nenhuma mídia salva.");
        }

    }

    public static List<Media> load(List<Media> midias, String path) {


        JsonDeserializer Deserializer=new JsonDeserializer<Media>() {
            @Override
            public Media deserialize(
                    JsonElement json,
                    Type typeOfT,
                    JsonDeserializationContext context

            ) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String tipo = jsonObject.get("tipo").getAsString();

                // Desserializa conforme o tipo
                switch (tipo) {
                    case "Book":
                        return context.deserialize(json, Books.class);
                    case "Movie":
                        return context.deserialize(json, Movie.class);
                    case "Show":
                        return context.deserialize(json, Show.class);
                    default:
                        throw new JsonParseException("Tipo desconhecido: " + tipo);
                }
            }
        };

        Gson gson = new GsonBuilder().registerTypeAdapter(Media.class,Deserializer).create();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Type listType = new TypeToken<List<Media>>() {}.getType();
            List<Media> loadedMidias = gson.fromJson(br, listType);
            if(loadedMidias!=null){
                midias.addAll(loadedMidias);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return midias;
    }

    // MenuUtils.SaveFile
//    public static List<Media> load(List<Media> currentList, String path) {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
//            List<Media> loadedList = (List<Media>) ois.readObject();
//
//            // Tratar caso o arquivo esteja vazio ou seja a primeira execução
//            if (loadedList == null) {
//                return currentList != null ? currentList : new ArrayList<>();
//            }
//
//            if (currentList == null) {
//                currentList = new ArrayList<>();
//            }
//
//            currentList.addAll(loadedList);
//            return currentList;
//        } catch (FileNotFoundException e) {
//            // Arquivo não existe - retorna lista atual ou nova lista vazia
//            return currentList != null ? currentList : new ArrayList<>();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return currentList != null ? currentList : new ArrayList<>();
//        }
//    }



}
