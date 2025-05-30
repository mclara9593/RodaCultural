//package test.java;
//
//import Midias.Books;
//import Midias.*;
//import Midias.Movie;
//import Midias.Show;
//import Others.Gender;
//import Others.Pessoa;
//import Others.Review;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.List;
//
//import static MenuUtils.MenuUtilities.getLists;
//import static Midias.Media.order;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class orderTest {
//        private List<Media> midias;
//        private List<Pessoa> atores;
//        private Media filme;
//        private Media livro;
//        private Media show;
//        private Media filme2;
//
//        @BeforeEach
//        void setUp() {
//            midias = new ArrayList<>();
//            atores = new ArrayList<>();
//
//            // Configurar um filme de teste
//            Review review =new Review();
//            review.setNote("Legal");
//            review.setStars(5);
//
//            Review review2 =new Review();
//            review.setNote("Massa");
//            review.setStars(4);
//
//            filme = new Movie("Matrix", true, 1999,
//                    new Pessoa("joão","Matrix"), Gender.FICCAO_CIENTIFICA, review,
//                    new ArrayList<>(), "Cinema", 136, new Pessoa("p1","Matrix"), "Roteiro");
//
//            filme2 = new Movie("Narnia", true, 1995,
//                    new Pessoa("seila","Narnia"), Gender.FICCAO_CIENTIFICA, review2,
//                    new ArrayList<>(), "Cinema", 136, new Pessoa("p1","Matrix"), "Roteiro");
//
//            // Configurar um livro de teste
//            livro = new Books("1984", true, 1949,
//                    new Pessoa("George Orwell","1984"), new Review(), Gender.DISTOPICO, 12345, true, "Editora");
//
//            show = new Show("TBBT", true, 2007,
//                    2009, new Pessoa("sheldon","TBBT"),Gender.DISTOPICO, new Review(),
//                    new ArrayList<>(),"streaming",7);
//
//
//            midias.add(filme2);
//            midias.add(show);
//            midias.add(filme);
//            midias.add(livro);
//        }
//
//        @Test
//        void testRegisterMovie() {
//            Media novaMidia = Media.register(midias, atores);
//            assertNotNull(novaMidia);
//            assertTrue(novaMidia instanceof Movie);
//        }
//
//
//        @Test
//        void testRegisterShow() {
//            Media novaMidia = Media.register(midias, atores);
//            assertNotNull(novaMidia);
//            assertTrue(novaMidia instanceof Show);
//        }
//
//        @Test
//        void testRegisterBooks() {
//            Media novaMidia = Media.register(midias, atores);
//            assertNotNull(novaMidia);
//            assertTrue(novaMidia instanceof Books);
//        }
//
//    @Test
//    void testgetList(){
//        getLists(midias,"Movie");
//
//    }
//
////        @Test
////        void testOrder(){
////            order(midias);
////        }
//
//
//
//}
