package test.java;
import Midias.*;
import Others.Gender;
import Others.Pessoa;
import Others.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static MenuUtils.SaveFile.save;
import static org.junit.jupiter.api.Assertions.*;

class SaveFileTeste {
    private List<Media> midias;
    private List<Pessoa> atores;
    private Media filme;
    private Media livro;
    private Media show;

    @BeforeEach
    void setUp() {
        midias = new ArrayList<>();
        atores = new ArrayList<>();

        // Configurar um filme de teste
        filme = new Movie("Matrix", true, 1999,
                new Pessoa("joão","Matrix"), Gender.FICCAO_CIENTIFICA, new Review(),
                new ArrayList<>(), "Cinema", 136, new Pessoa("p1","Matrix"), "Roteiro");

        // Configurar um livro de teste
        livro = new Books("1984", true, 1949,
                new Pessoa("George Orwell","1984"), new Review(), Gender.DISTOPICO, 12345, true, "Editora");

        show = new Show("TBBT", true, 2007,
                new Pessoa("sheldon","TBBT"),Gender.DISTOPICO, new Review(),
                new ArrayList<>(),"streaming",7);


        midias.add(show);
        midias.add(filme);
        midias.add(livro);
    }

    @Test
    void testRegisterMovie() {
        Media novaMidia = Media.register(midias, atores);
        assertNotNull(novaMidia);
        assertTrue(novaMidia instanceof Movie);
    }

    void testRegisterShow() {
        Media novaMidia = Media.register(midias, atores);
        assertNotNull(novaMidia);
        assertTrue(novaMidia instanceof Show);
    }

    void testRegisterBooks() {
        Media novaMidia = Media.register(midias, atores);
        assertNotNull(novaMidia);
        assertTrue(novaMidia instanceof Books);
    }

    @Test
    void testsaveMovie() {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\MenuUtils.json";
        save(midias,null,null,null,path);
    }

}