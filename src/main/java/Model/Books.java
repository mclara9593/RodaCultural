package Model;

import Model.Others.Gender;
import Model.Others.Pessoa;
import Model.Others.Review;

public class Books extends Media {
        private int ISBN;
        private Boolean copy;
        private String publisher;

        public int getISBN() {
                return ISBN;
        }
        public Boolean getCopy() { // Remova 'static'
                return copy;
        }
        public String getPublisher() { // Remova 'static'
                return publisher;
        }

        public void setISBN(int ISBN) {
                this.ISBN = ISBN;
        }
        public void setCopy(Boolean copy) {
                this.copy = copy;
        }
        public void setPublisher(String publisher) {
                this.publisher = publisher;
        }

        public String toString() {
                return super.toString() + "\n" +
                        "ISBN: " + ISBN + "\n" +
                        "Cópia física: " + (copy ? "Sim" : "Não") + "\n" +
                        "Editora: " + publisher;
        }


        //Construtor
        public Books(String title,
                     boolean status,
                     int release_date,
                     Pessoa author,
                     Review review, Gender gender,
                     int ISBN,
                     Boolean copy,
                     String publisher) {
            //construtor de Media
            super(title, status, release_date, gender, author,review);
            this.copy = copy;
            this.publisher = publisher;
            this.ISBN = ISBN;
            this.tipo="Book";
        }

        public Books() {
                super(); // certifique-se de que Media também tem um construtor vazio
                this.tipo = "Book";
        }
}

