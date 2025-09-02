import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    public static void main(String[] args) {
        // ID (a String as before in Person)
        // Name (a String)
        // Description (a String a short sentence)
        // Cost (This is currency so it will be a Java double)

        String id;
        String name;
        String description;
        double cost;
        String csvProduct;
        Scanner in = new Scanner(System.in);
        ArrayList<String> productTele = new ArrayList<>();

        boolean done;

        do {

            id = SafeInput.getNonZeroLenString(in, "Enter ID");
            name = SafeInput.getNonZeroLenString(in, "Enter the name of the product");
            description = SafeInput.getNonZeroLenString(in, "Enter a description for your product");
            cost = SafeInput.getDouble(in, "Enter the cost of the product");
            csvProduct = id + "," + name + "," + description + "," + cost + "\n";

            productTele.add(csvProduct);

            done = SafeInput.getYNConfirm(in, "Are you done adding products?");

        }while(!done);


        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\product_data.txt");

        try{

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String product : productTele){
                writer.write(product, 0, product.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written");

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
