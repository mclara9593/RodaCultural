package Midias;

import Others.Gender;
import Others.Pessoa;
import Others.Review;

import java.util.Scanner;

import static MenuUtils.MenuUtilities.getStringInput;

public class Books extends Media {
        private static int ISBN;
        private static Boolean copy;
        private static String publisher;


        public static int getISBN() {
                return ISBN;
        }
        public static Boolean getCopy() {
                return copy;
        }
        public static String getPublisher() {
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
        }
}

