void main() {


    Path path = Path.of("src/data.txt");
    List<Postava> postavy = new ArrayList<>();

    try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
        String radek;
        while ((radek = bufferedReader.readLine()) != null) {
            if (radek.isBlank()) {
                continue;
            }
            Postava postava = new Validation((radek)).validation();
            if (postava != null) {
                postavy.add(postava);
            }
        }
    }
    catch(IOException e){
        System.err.println("Chyba pri praci se souborem.");
    }


    System.out.println();
    System.out.println("=== Platně zadaní vězni (příjmení, rok narození) ===");
    for (Postava postava : postavy) {
        System.out.println(postava.toString());

    }
    System.out.println("Celkem platných záznamů: " + postavy.size());


    System.out.println("\nMladší 18: ");
    for (Postava postava : postavy) {
        if (postava.getDatumNarozeni().isAfter((LocalDate.now().minusYears(18)))){
            System.out.println(postava.toString());
        }
    }




    int totalAmountElf = 0;
    int totalAmountTrpaslik = 0;
    int totalAmountClovek = 0;
    int totalAmountOrk = 0;
    int totalAmountHobit = 0;
    for (Postava postava : postavy) {


        switch(postava.getRasa()) {
            case "Elf":
                totalAmountElf++;
                break;
            case "Trpaslík":
                totalAmountTrpaslik++;
                break;
            case "Člověk":
                totalAmountClovek++;
                break;
            case "Ork":
                totalAmountOrk++;
                break;
            case "Hobit":
                totalAmountHobit++;
                break;
        }
    }

    System.out.println("\nValidní žádosti: ");
    System.out.println("Elf: " + totalAmountElf);
    System.out.println("Trpaslík: " + totalAmountTrpaslik);
    System.out.println("Člověk: " + totalAmountClovek);
    System.out.println("Ork: " + totalAmountOrk);
    System.out.println("Hobit: " + totalAmountHobit);


    int highestLevel = 0;
    Postava highestPostava = null;
    for  (Postava postava : postavy) {
        if (postava.getLevel() > highestLevel) {
            highestLevel = postava.getLevel();
            highestPostava = postava;
        }
    }
    System.out.println("\nPostava s nejvyšším levelem: " +  highestPostava.toString());








}
