package MenuUtils;
import Midias.Books;
import Midias.Movie;
import Midias.Show;
import Midias.Media;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

import Others.Gender;
import com.google.gson.*;
import static MenuUtils.MenuUtilities.getLists;


public class SaveFile {

    public static void save(List<Media> midias,String pathLivros,String pathMovies,String pathShows,String path) {
        Gson gsonAll = new GsonBuilder()
                .registerTypeAdapter(Gender.class, new JsonSerializer<Gender>() {
                    @Override
                    public JsonElement serialize(Gender src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.getDescricao()); // Usa a descrição do enum
                    }
                })
                .setPrettyPrinting().create();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Gender.class, new JsonSerializer<Gender>() {
                    @Override
                    public JsonElement serialize(Gender src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.getDescricao());
                    }
                })
                .setPrettyPrinting().create();


        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<? extends Media>books= getLists(midias,"B");
        List<? extends Media>movies= getLists(midias,"M");
        List<? extends Media>shows= getLists(midias,"S");

        try {
                String json = gsonAll.toJson(midias);
                FileWriter writer = new FileWriter(path);
                writer.write(json);
                writer.close();

                String json1 = gson.toJson(books);
                FileWriter writerLivros = new FileWriter(pathLivros);
                writerLivros.write(json1);
                writerLivros.close();

                String json2 = gson.toJson(movies);
                FileWriter writerMovies = new FileWriter(pathMovies);
                writerMovies.write(json2);
                writerMovies.close();

                String json3 = gson.toJson(shows);
                FileWriter writerShows = new FileWriter(pathShows);
                writerShows.write(json3);
                writerShows.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nenhuma mídia salva.");
        }

    }

    public static List<Media> load(List<Media> midias,String path) {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<List<Media>>() {}.getType();

            List<Media> loadedMidias = gson.fromJson(br, listType);
            midias.addAll(loadedMidias);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return midias;
    }




}



