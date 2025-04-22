package test.java;

import Midias.*;
import Others.Gender;
import Others.Pessoa;
import Others.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Midias.Media.doReview;
import static Midias.Media.review;
import static org.junit.jupiter.api.Assertions.*;

class MediaTest {

    private Media media;
    private Pessoa autor;
    private List<Media> midias;
    private List<Pessoa> atores;

    @BeforeEach
    void setUp() {
        autor = new Pessoa();
        autor.setNome("Autor Teste");
        media = new Media("Título Teste", false, 2020, Gender.ACAO, autor);
        midias = new ArrayList<>();
        atores = new ArrayList<>();
    }

    @Test
    void testConstrutorEGetters() {
        assertEquals("Título Teste", media.getTitle());
        assertFalse(media.isStatus());
        assertEquals(2020, media.getRelease_date());
        assertEquals(Gender.ACAO, media.getGender());
        assertEquals("Autor Teste", media.getAuthor().getNome());
    }

    @Test
    void testSetters() {
        media.setTitle("Novo Título");
        media.setStatus(true);
        media.setRelease_date(2021);
        media.setGender(Gender.COMEDIA);

        Pessoa novoAutor = new Pessoa();
        novoAutor.setNome("Novo Autor");
        media.setAuthor(novoAutor);

        Review review = new Review();
        review.setNote("Excelente");
        review.setStars(5);
        media.setReview(review);

        assertEquals("Novo Título", media.getTitle());
        assertTrue(media.isStatus());
        assertEquals(2021, media.getRelease_date());
        assertEquals(Gender.COMEDIA, media.getGender());
        assertEquals("Novo Autor", media.getAuthor().getNome());
        assertEquals("Excelente", media.getReview().getNote());
    }

    @Test
    void testToString() {
        String expected = "Título: Título Teste\n" +
                "Status: Não concluído\n" +
                "Ano lançamento: 2020\n" +
                "Gênero: ACAO\n" +
                "Autor: Autor Teste";
                "Avaliação: 5" +
                "Nota: oioioi";
        assertEquals(expected, media.toString());
    }

    // Teste para métodos estáticos (requer mock do Scanner)
    @Test
    void testGetStatusInput() {
        assertDoesNotThrow(() -> Media.getStatusInput());
    }

//    @Test
//    void testGetGenderInput() {
//        assertDoesNotThrow(() -> Media.getGenderInput());
//    }

    @Test
    void testRegister() {
        Media result = Media.register(midias, atores);
        assertDoesNotThrow(() -> Media.register(midias, atores));
    }

    @Test
    void testSearch() {
        midias.add(media);
        List<? extends Media> encontrada = Media.search(midias, atores);
        assertDoesNotThrow(() -> Media.search(midias, atores));
    }

    @Test
    void testReview() {
        midias.add(media);
        assertDoesNotThrow(() -> doReview(midias, atores));
    }

    @Test
    void testChangeStatus() {
        midias.add(media);
        assertDoesNotThrow(() -> Media.changeStatus(midias, atores));
    }

    @Test
    void order() {
        midias.add(media);
        assertDoesNotThrow(() -> Media.order(midias));
    }
}