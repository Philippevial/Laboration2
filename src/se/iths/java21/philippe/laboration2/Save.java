package se.iths.java21.philippe.laboration2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;

public class Save {
    public void saveToFile(List<Product> products) {
        Service service = new Service();
        List<String> stringList = products.stream()
                .map(this::getProductFields).toList();

        Path path = Path.of(String.valueOf(service.filePath()));

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
}
