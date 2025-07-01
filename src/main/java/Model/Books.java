package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;

/**
 * Classe que representa um livro, uma subclasse de Media.
 * Armazena informações específicas de livros como ISBN, cópia física e editora.
 */
public class Books extends Media {
        private int ISBN;
        private Boolean copy;
        private String publisher;

        /**
         * Obtém o ISBN do livro.
         * @return ISBN do livro
         */
        public int getISBN() {
                return ISBN;
        }

        /**
         * Verifica se o livro possui cópia física.
         * @return true se possuir cópia física, false caso contrário
         */
        public Boolean getCopy() {
                return copy;
        }

        /**
         * Obtém a editora do livro.
         * @return Nome da editora
         */
        public String getPublisher() {
                return publisher;
        }

        /**
         * Define o ISBN do livro.
         * @param ISBN Número do ISBN
         */
        public void setISBN(int ISBN) {
                this.ISBN = ISBN;
        }

        /**
         * Define se o livro possui cópia física.
         * @param copy true para cópia física, false para digital
         */
        public void setCopy(Boolean copy) {
                this.copy = copy;
        }

        /**
         * Define a editora do livro.
         * @param publisher Nome da editora
         */
        public void setPublisher(String publisher) {
                this.publisher = publisher;
        }

        /**
         * Retorna uma representação em string do livro.
         * @return String formatada com informações do livro
         */
        public String toString() {
                return super.toString() + "\n" +
                        "ISBN: " + ISBN + "\n" +
                        "Cópia física: " + (copy ? "Sim" : "Não") + "\n" +
                        "Editora: " + publisher;
        }

        /**
         * Construtor completo para um livro.
         * @param title Título do livro
         * @param status Status de conclusão
         * @param release_date Ano de lançamento
         * @param author Autor do livro
         * @param review Avaliação do livro
         * @param gender Gênero do livro
         * @param ISBN ISBN do livro
         * @param copy Indica se possui cópia física
         * @param publisher Editora do livro
         */
        public Books(String title,
                     boolean status,
                     int release_date,
                     Pessoa author,
                     Review review, Gender gender,
                     int ISBN,
                     Boolean copy,
                     String publisher) {
                super(title, status, release_date, gender, author, review);
                this.copy = copy;
                this.publisher = publisher;
                this.ISBN = ISBN;
                this.tipo = "Book";
        }

        /**
         * Construtor vazio para um livro.
         */
        public Books() {
                super();
                this.tipo = "Book";
        }
}