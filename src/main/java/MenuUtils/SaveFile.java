//package MenuUtils;
//import Model.Books;
//import Model.Movie;
//import Model.Others.Pessoa;
//import Model.Show;
//import Model.Media;
//import java.io.*;
//import java.lang.reflect.Type;
//
//import java.util.List;
//import java.io.FileWriter;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//
//import static MenuUtils.Utilitie.getLists;
//
///**
// * Classe que salva e carrega a lista de objetos completa
// */
//public class SaveFile {
//
//    /**
//     * Salva as mídias cadastradas no momento da execução
//     *
//     * @param midias Lista de atores
//     * @param path path caminho de salvamento
//     */
//    public static void save(List<Media> midias,String path) {
//
//        Gson gsonAll = new GsonBuilder().setPrettyPrinting().create();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        List<? extends Media>books= getLists(midias,"B");
//        List<? extends Media>movies= getLists(midias,"M");
//        List<? extends Media>shows= getLists(midias,"S");
//
//        try {
//            String json = gsonAll.toJson(midias);
//            FileWriter writer = new FileWriter(path);
//            writer.write(json);
//            writer.close();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Nenhuma mídia salva.");
//        }
//
//    }
//
//    /**
//     * Salva atores cadastrados no momento da execução
//     *
//     * @param atores Lista de atores
//     * @param pathActors path caminho de salvamento
//     */
//    public static void saveActors(List<Media> atores,String pathActors) {
//
//        Gson gsonAll = new GsonBuilder().setPrettyPrinting().create();
//
//        try {
//                String json = gsonAll.toJson(atores);
//                FileWriter writer = new FileWriter(pathActors);
//                writer.write(json);
//                writer.close();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Nenhuma mídia salva.");
//        }
//
//    }
//
//    /**
//     * Carrega as mídias salvas de outros usos do aplicativo
//     *
//     * @param midias Lista de mídias
//     * @param midias path caminho de salvamento
//     */
//    public static List<Media> load(List<Media> midias, String path) {
//        JsonDeserializer Deserializer=new JsonDeserializer<Media>() {
//            @Override
//            public Media deserialize(
//                    JsonElement json,
//                    Type typeOfT,
//                    JsonDeserializationContext context
//
//            ) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//                String tipo = jsonObject.get("tipo").getAsString();
//
//                // Desserializa conforme o tipo
//                switch (tipo) {
//                    case "Book":
//                        return context.deserialize(json, Books.class);
//                    case "Movie":
//                        return context.deserialize(json, Movie.class);
//                    case "Show":
//                        return context.deserialize(json, Show.class);
//                    default:
//                        throw new JsonParseException("Tipo desconhecido: " + tipo);
//                }
//            }
//        };
//
//        Gson gson = new GsonBuilder().registerTypeAdapter(Media.class,Deserializer).create();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            Type listType = new TypeToken<List<Media>>() {}.getType();
//            List<Media> loadedMidias = gson.fromJson(br, listType);
//            if(loadedMidias!=null){
//                midias.addAll(loadedMidias);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return midias;
//    }
//
//    /**
//     * Carrega as atores salvos de outros usos do aplicativo
//     *
//     * @param atores Lista de mídias
//     * @param pathActors caminho de salvamento
//     */
//    public static List<Pessoa> loadActors(List<Pessoa> atores, String pathActors) {
//        // Criar um deserializer personalizado para Pessoa
//        JsonDeserializer<Pessoa> deserializer = new JsonDeserializer<Pessoa>() {
//            @Override
//            public Pessoa deserialize(
//                    JsonElement json,
//                    Type typeOfT,
//                    JsonDeserializationContext context
//            ) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//                // Extrair dados do JSON
//                String nome = jsonObject.get("nome").getAsString();
//                String obra = jsonObject.has("obra") ?
//                        jsonObject.get("obra").getAsString() :
//                        null;
//
//                // Criar nova instância de Pessoa
//                return new Pessoa(nome, obra);
//            }
//        };
//
//        // Configurar Gson com o deserializer personalizado
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Pessoa.class, deserializer)
//                .create();
//
//        try (BufferedReader brActors = new BufferedReader(new FileReader(pathActors))) {
//            // Definir o tipo da lista
//            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
//
//            // Carregar dados do arquivo
//            List<Pessoa> loadedAtores = gson.fromJson(brActors, listType);
//
//            // Adicionar à lista existente se não for nula
//            if (loadedAtores != null) {
//                atores.addAll(loadedAtores);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return atores;
//    }
//
//
//
//}


package MenuUtils;
import Model.Books;
import Model.Movie;
import Model.Others.Pessoa;
import Model.Show;
import Model.Media;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import static MenuUtils.Utilitie.getLists;

public class SaveFile {

    public static void save(List<Media> midias, String path) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(midias);

            // Garante que o diretório existe
            new File(path).getParentFile().mkdirs();

            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar mídias: " + e.getMessage());
        }
    }

    // Método corrigido para salvar atores
    public static void saveActors(List<Pessoa> atores, String pathActors) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(atores);

            // Garante que o diretório existe
            new File(pathActors).getParentFile().mkdirs();

            FileWriter writer = new FileWriter(pathActors);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao salvar atores: " + e.getMessage());
        }
    }

    public static List<Media> load(List<Media> midias, String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Arquivo de mídias não encontrado. Será criado um novo ao salvar.");
            return midias;
        }

        JsonDeserializer<Media> deserializer = new JsonDeserializer<Media>() {
            @Override
            public Media deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String tipo = jsonObject.get("tipo").getAsString();

                switch (tipo) {
                    case "Book": return context.deserialize(json, Books.class);
                    case "Movie": return context.deserialize(json, Movie.class);
                    case "Show": return context.deserialize(json, Show.class);
                    default: throw new JsonParseException("Tipo desconhecido: " + tipo);
                }
            }
        };

        Gson gson = new GsonBuilder().registerTypeAdapter(Media.class, deserializer).create();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Type listType = new TypeToken<List<Media>>() {}.getType();
            List<Media> loadedMidias = gson.fromJson(br, listType);

            if (loadedMidias != null) {
                midias.addAll(loadedMidias);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar mídias: " + e.getMessage());
        }
        return midias;
    }

    public static List<Pessoa> loadActors(List<Pessoa> atores, String pathActors) {
        File file = new File(pathActors);

        // Se o arquivo não existe, retorna a lista original
        if (!file.exists()) {
            System.out.println("Arquivo de atores não encontrado. Será criado um novo ao salvar.");
            return atores;
        }

        JsonDeserializer<Pessoa> deserializer = new JsonDeserializer<Pessoa>() {
            @Override
            public Pessoa deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String nome = jsonObject.get("nome").getAsString();
                String obra = jsonObject.has("obra") ? jsonObject.get("obra").getAsString() : null;
                return new Pessoa(nome, obra);
            }
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Pessoa.class, deserializer)
                .create();

        try (BufferedReader brActors = new BufferedReader(new FileReader(file))) {
            Type listType = new TypeToken<List<Pessoa>>() {}.getType();
            List<Pessoa> loadedAtores = gson.fromJson(brActors, listType);

            if (loadedAtores != null) {
                atores.addAll(loadedAtores);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar atores: " + e.getMessage());
        }

        return atores;
    }
}
