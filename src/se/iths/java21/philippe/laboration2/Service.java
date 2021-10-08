package se.iths.java21.philippe.laboration2;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Service {
    private List<Product> products;
    static Scanner scanner = new Scanner(System.in);
    private static final Pattern pattern = Pattern.compile(",");


    public void loadFromFile() {
        if (Files.exists(getPath())) {
            fileLoader(getPath());
        }else
            fileLoader(getDefaultPath());

    }

    private void fileLoader(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            products = lines.map(Service::createAndSplitProduct)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getDefaultPath() {
        return Path.of("resources","products.csv");
    }

    @NotNull
    private Path getPath() {
        String homePath = System.getProperty("user.home");
        return Path.of(homePath, "Lagersaldo", "products.csv");
    }

    private static Product createAndSplitProduct(String line) {
        String[] arr = pattern.split(line);
        if (arr.length == 4)
            return new Product(arr[0], Float.parseFloat(arr[1]), Integer.parseInt(arr[2]), arr[3]);
        else
            return new Product("Empty", 0, 0, "Empty");
    }

    public void modifyProduct() {
        deleteProductFromList();
        createNewProduct();
    }

    private void deleteProductFromList() {
        System.out.println("Ange produkt: ");
        String name = scanner.nextLine();
        Product toRemove = products.stream()
                .filter(product -> product.name().contains(name))
                .findFirst()
                .orElse(new Product("Empty", 0, 0, "Empty"));
        System.out.println(toRemove);
        products.remove(toRemove);
    }

    public List<Product> searchForProduct() {
        System.out.println("Ange produktnamn: ");
        String name = scanner.nextLine();
        return products.stream()
                .filter(product -> product.name().contains(name))
                .collect(Collectors.toList());
    }

    public void createNewProduct() {

        Product product = new Product("Tom", 0, 0, "Tom");
        //products.add(new Product("Tom",0,0,"Tom"));
        System.out.println("Ange produktnamn: ");
        String newProd = scanner.nextLine();
        product.setName(newProd);

        System.out.println("Ange pris: ");
        newProd = scanner.nextLine();
        product.setPrice(Float.parseFloat(newProd));

        System.out.println("Ange produktID: ");
        newProd = scanner.nextLine();
        product.setProdID(Integer.parseInt(newProd));

        System.out.println("Ange kategori: ");
        newProd = scanner.nextLine();
        product.setCategory(newProd);

        String prod = product.name() + "," + product.price() + "," + product.prodID() + "," + product.category();

        try {
            Files.writeString(getPath(), prod, CREATE,APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        List<String> stringList = getProductList().stream().map(this::getProductFields).toList();
        String homePath = System.getProperty("user.home");
        Path path = Path.of(homePath, "Lagersaldo", "products.csv");

        try {
            Files.write(path, stringList, CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProductFields(Product product) {
        return product.name() + "," + product.price() + "," +
                product.prodID() + "," + product.category();
    }

    private List<Product> getProductList() {
        return products;
    }

    public List<Product> searchForProductByProdId(int prodID) {
        System.out.println("Ange produktnr: ");
        return products.stream()
                .filter(product -> product.prodID() == (prodID))
                .collect(Collectors.toList());
    }

    public List<Product> productsByCategory(String category) {
        System.out.println("Ange kategori(Öl, Vin, Sprit): ");
        return products.stream()
                .filter(product -> product.category().contains(category))
                .collect(Collectors.toList());
    }

    public List<Product> productsUnder100Kr() {
        return products.stream()
                .filter(product -> product.price() < 100)
                .collect(Collectors.toList());
    }

    public List<Product> productsOver100Kr() {
        return products.stream()
                .filter(product -> product.price() > 100)
                .collect(Collectors.toList());
    }
}
