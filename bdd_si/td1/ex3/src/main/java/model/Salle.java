package model;

public class Salle {
    private int idSalle;
    private String nomSalle;

    public Salle(int idSalle, String nomSalle) {
        this.idSalle = idSalle;
        this.nomSalle = nomSalle;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", nomSalle='" + nomSalle + '\'' +
                '}';
    }
}
