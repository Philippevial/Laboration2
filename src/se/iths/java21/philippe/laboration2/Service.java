package se.iths.java21.philippe.laboration2;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Service {
    private List<Product> products;
    static Scanner scanner = new Scanner(System.in);
    private static final Pattern pattern = Pattern.compile(",");

    public void loadFromFile() {
        if (Files.exists(filePath())) {
            fileLoader(filePath());
        } else
            fileLoader(defaultPath());
    }

    private void fileLoader(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            products = lines.map(Service::createAndSplitProduct)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path defaultPath() {
        return Path.of("resources", "products.csv");
    }

    @NotNull
    Path filePath() {
        String homePath = System.getProperty("user.home");
        Path path = Path.of(homePath, "Philippes produkter", "products.csv");
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
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

    public void deleteProductFromList() {
        System.out.println("Ange produkt: ");
        String name = scanner.nextLine();
        Product toRemove = products.stream()
                .filter(product -> product.name().contains(name))
                .findFirst()
                .orElse(new Product("Empty", 0, 0, "Empty"));
        System.out.println(toRemove);
        products.remove(toRemove);
    }

    private String inputRead(String input) {
        System.out.println(input);
        return scanner.nextLine();
    }

    public void createNewProduct() {
        Product product = new Product("Tom", 0, 0, "Tom");
        product.setName(inputRead("Ange namn: "));
        product.setPrice(Float.parseFloat(inputRead("Ange pris: ")));
        product.setProdID(Integer.parseInt(inputRead("Ange produktID: ")));
        product.setCategory(inputRead("Ange kategori: "));
        products.add(product);
    }

    public List<Product> getProductList() {
        return products;
    }
}
