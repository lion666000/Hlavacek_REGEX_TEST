import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final int POCET_UDAJU = 5;

    private static final Pattern JMENO =Pattern.compile("^\\p{Lu}\\p{L}*$");

    private static final Pattern RASA =Pattern.compile("^(Elf|Trpaslík|Člověk|Ork|Hobit)$");

    private static final Pattern DATUM =Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
    private static final DateTimeFormatter DATUM_FORMAT =
            DateTimeFormatter.ofPattern("d.M.uuuu").withResolverStyle(ResolverStyle.STRICT);

    private static final Pattern TYP =Pattern.compile("^(Meč|Kuše|Hůl|Sekera|Dýka)$");

    private static final Pattern LEVEL =Pattern.compile("^(100|[1-9]\\d{0,1}|0)$");

    private final String radek;

    Validation(String radek) {
        this.radek = radek;
    }

    public Postava validation(){
        System.out.println("\nRadek: " + radek);

        String[] data = radek.split(";", -1);

        if (data.length != POCET_UDAJU) {
            System.out.println("   Žádost není validní - Chybějící údaj (nalezeno " + data.length
                    + ", očekáváno " + POCET_UDAJU + ")");
            return null;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
        }

        List<String> chyby = new ArrayList<>();

        if (!JMENO.matcher(data[0]).matches()) {
            chyby.add("Neplatné jméno");
        }

        //Špatná rasa
        if (!RASA.matcher(data[1]).matches()) {
            chyby.add("Neznámá rasa");
        }

        LocalDate datum = null;
        if (DATUM.matcher(data[2]).matches()) {
            try {
                datum = LocalDate.parse(data[2], DATUM_FORMAT);
            } catch (DateTimeParseException e) {
                chyby.add("Neplatný datum narození");
            }
        } else if (data[2].isEmpty()) {
            chyby.add("Prázdný údaj: Datum");
        } else if (datum.isAfter(LocalDate.now())) {
            chyby.add("Datum narození nesmí být v budoucnosti");
        }
        else {
            chyby.add("Neplatný datum narození");
        }

        if (!TYP.matcher(data[3]).matches()) {
            chyby.add("Neplatný typ zbraně");
        }

        int level = 0;
            if (LEVEL.matcher(data[4]).matches()) {
            level = Integer.parseInt(data[4]);
        } else if (data[4].isEmpty()) {
                chyby.add("Prázdný údaj: Level");
            } else {
            chyby.add("Level mimo rozsah");
        }

        if (!chyby.isEmpty()) {
            System.out.println("   Žádost není validní - " + String.join(", ", chyby));
            return null;
        }

        System.out.println("   Žádost postavy " + data[0] + " je validní");
        return new Postava(data[0], data[1], datum, data[3], level);
    }
}
