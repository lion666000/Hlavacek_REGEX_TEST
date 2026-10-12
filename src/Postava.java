import java.time.LocalDate;

public class Postava {
    String jmeno;
    String rasa;
    LocalDate datumNarozeni;
    String typZbrane;
    int level;

    public Postava(String jmeno, String rasa, LocalDate datumNarozeni, String typZbrane, int level) {
        this.jmeno = jmeno;
        this.rasa = rasa;
        this.datumNarozeni = datumNarozeni;
        this.typZbrane = typZbrane;
        this.level = level;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getRasa() {
        return rasa;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public String getTypZbrane() {
        return typZbrane;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Postava{" +
                "jmeno='" + jmeno + '\'' +
                ", rasa='" + rasa + '\'' +
                ", datumNarozeni=" + datumNarozeni +
                ", typZbrane='" + typZbrane + '\'' +
                ", level=" + level +
                '}';
    }
}
