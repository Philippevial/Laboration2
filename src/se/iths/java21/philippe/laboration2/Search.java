package se.iths.java21.philippe.laboration2;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Search {
    static Scanner scanner = new Scanner(System.in);
    //Service service = new Service();
   // List<Product> products = service.getProductList();

    public List<Product> searchForProduct(List<Product> products) {
        System.out.println("Ange produktnamn: ");
        String name = scanner.nextLine();
        return products.stream()
                .filter(product -> product.name().contains(name))
                .collect(Collectors.toList());
    }

    public List<Product> searchForProductByProdId(List<Product> products) {
        System.out.println("Ange produktnr: ");
        int prodID = Integer.parseInt(scanner.nextLine());
        return products.stream()
                .filter(product -> product.prodID() == (prodID))
                .collect(Collectors.toList());
    }

    public List<Product> productsByCategory(List<Product> products) {
        System.out.println("Ange kategori(Öl, Vin, Sprit): ");
        String category = scanner.nextLine();
        return products.stream()
                .filter(product -> product.category().contains(category))
                .collect(Collectors.toList());
    }

    public List<Product> productsUnder100Kr(List<Product> products) {
        return products.stream()
                .filter(product -> product.price() < 100)
                .collect(Collectors.toList());
    }

    public List<Product> productsOver100Kr(List<Product> products) {
        return products.stream()
                .filter(product -> product.price() > 100)
                .collect(Collectors.toList());
    }
}
