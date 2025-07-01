package Model.Others;

/**
 * Classe que representa avaliação
 * @return stars avaliação de 1 a 5
 * @return note crítica
 */
public class Review {
    public static String note;
    public static int stars;

    public static int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
