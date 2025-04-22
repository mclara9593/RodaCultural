package test.java;

import Midias.*;
import Others.Gender;
import Others.Pessoa;
import Others.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MediaTest {
    private List<Media> midias;
    private List<Pessoa> atores;
    private Media filme;
    private Media livro;

    @BeforeEach
    void setUp() {
        midias = new ArrayList<>();
        atores = new ArrayList<>();

        // Configurar um filme de teste
        filme = new Movie("Matrix", true, 1999,
                new Pessoa(), Gender.FICCAO_CIENTIFICA, new Review(),
                new ArrayList<>(), "Cinema", 136, new Pessoa(), "Roteiro");

        // Configurar um livro de teste
        livro = new Books("1984", true, 1949,
                new Pessoa(), new Review(), Gender.DISTOPICO, 12345, true, "Editora");

        midias.add(filme);
        midias.add(livro);
    }

    @Test
    void testRegisterMovie() {
        Media novaMidia = Media.register(midias, atores);
        assertNotNull(novaMidia);
        assertTrue(novaMidia instanceof Movie);
    }

    @Test
    void testSearchByTitle() {
        Media encontrada = Media.search(midias, atores);
        assertNotNull(encontrada);
        assertEquals("MATRIX", encontrada.getTitle().toUpperCase());
    }

    @Test
    void testSearchByGenre() {
        Media encontrada = Media.search(midias, atores);
        assertEquals(Gender.FICCAO_CIENTIFICA, encontrada.getGender());
    }

    @Test
    void testDoReview() {
        Media avaliada = Media.doReview(midias, atores);
        assertNotNull(avaliada);
        assertTrue(avaliada.isStatus());
        assertNotNull(avaliada.getReview());
    }

    @Test
    void testSetAndGetTitle() {
        filme.setTitle("Matrix Reloaded");
        assertEquals("Matrix Reloaded", filme.getTitle());
    }

    @Test
    void testSetAndGetStatus() {
        filme.setStatus(false);
        assertFalse(filme.isStatus());
    }

//    @Test
//    void testFilterByYear() {
//        List<Media> filtered = midias.stream()
//                .filter(m -> m.getRelease_date() == 1999)
//                .toList();
//
//        assertEquals(1, filtered.size());
//        assertEquals("Matrix", filtered.get(0).getTitle());
//    }
}

class PessoaTest {
    @Test
    void testPessoaCreation() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Keanu Reeves");
        pessoa.setFunção("Ator");

        assertEquals("Keanu Reeves", pessoa.getNome());
        assertEquals("Ator", pessoa.getFunção());
    }
}

class ReviewTest {
    @Test
    void testReviewCreation() {
        Review review = new Review();
        review.setNote("Excelente!");
        review.setStars(5);

        assertEquals("Excelente!", review.getNote());
        assertEquals(5, review.getStars());
    }
}