package MenuUtils;
import Midias.Books;
import Midias.Movie;
import Midias.Show;
import Midias.Media;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.io.FileWriter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import static MenuUtils.MenuUtilities.getLists;


public class SaveFile {

    public static void save(List<Media> midias,String pathLivros,String pathMovies,String pathShows,String path) {

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
            midias.addAll(loadedMidias);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return midias;
    }






}



