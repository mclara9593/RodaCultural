package Midias;

import Others.Gender;
import Others.Pessoa;

import java.time.LocalDate;

public class Books extends Media {
        private int ISBN;
        private Boolean copy;
        private String publisher;



        public Books(String title, boolean status, LocalDate release_date, Pessoa author, Gender gender, int ISBN, Boolean copy, String publisher) {
            //construtor de Media
            super(title, status, release_date, gender, author);
        }
}

