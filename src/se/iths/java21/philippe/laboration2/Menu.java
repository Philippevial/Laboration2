package se.iths.java21.philippe.laboration2;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public void runLaboration() {
        Service service = new Service();
        Search search = new Search();
        Save save = new Save();
        service.loadFromFile();
        runLoop(service, search, save);
    }

    private void runLoop(Service service, Search search, Save save) {
        boolean run = true;
        while (run) {
            menuChoices();
            String input = scanner.nextLine();
            run = executeChoice(service, search, save, true, input);
        }
    }

    public void menuChoices() {
        System.out.println("1. Produkthantering");
        System.out.println("2. Sök och Bläddra");
        System.out.println("3. Spara");
        System.out.println("0. Avsluta");
    }

    private boolean executeChoice(Service service, Search search, Save save, boolean run, String input) {
        switch (input) {
            case "1" -> executeProductChoice(service);
            case "2" -> executeSearchChoice(search, service);
            case "3" -> save.saveToFile(service.getProductList());
            case "0" -> run = false;
            default -> System.out.println("Fel val, prova igen!");
        }
        return run;
    }

    private void productMenu() {
        System.out.println("1. Skapa och lägg till ny produkt");
        System.out.println("2. Ändra på produkt");
        System.out.println("3. Radera produkt");
        System.out.println("5. Tillbaka");
    }

    private void executeProductChoice(Service service) {

        boolean run = true;
        while (run) {
            productMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> service.createNewProduct();
                case "2" -> service.modifyProduct();
                case "3" -> service.deleteProductFromList();
                case "5" -> run = false;
                default -> System.out.println("Fel val, prova igen!");
            }
        }
    }

    private void printSearchMenu() {
        System.out.println("1. Sök på produkt");
        System.out.println("2. Sök på dryckens nr");
        System.out.println("3. Visa produkter efter kategori");
        System.out.println("4. Visa produkter över 100kr");
        System.out.println("5. Visa produkter under 100kr");
        System.out.println("0. Tillbaka");
    }

    private void executeSearchChoice(Search search, Service service) {
        boolean run = true;
        while (run) {
            printSearchMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> search.searchForProduct(service.getProductList()).forEach(System.out::println);
                case "2" -> search.searchForProductByProdId(service.getProductList()).forEach(System.out::println);
                case "3" -> search.productsByCategory(service.getProductList()).forEach(System.out::println);
                case "4" -> search.productsOver100Kr(service.getProductList()).forEach(System.out::println);
                case "5" -> search.productsUnder100Kr(service.getProductList()).forEach(System.out::println);
                case "0" -> run = false;
                default -> System.out.println("Fel val, prova igen!");
            }
        }
    }

    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);
        Menu meny = new Menu();
        meny.runLaboration();

    }
}
