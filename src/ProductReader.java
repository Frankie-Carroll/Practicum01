import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String product;

        try
        {
            File workingDir = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDir);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                BufferedReader reader = Files.newBufferedReader(file);

                System.out.printf("%-6s %-15s %-15s %-5s%n",
                        "ID#", "Name", "Description", "Cost");
                System.out.println("-----------------------------------------------------");

                while((product = reader.readLine()) != null)
                {
                    String[] format = product.split(",");

                    if (format.length == 4)
                    {
                        System.out.printf("%-6s %-15s %-15s %-5s%n",
                                format[0].trim(), format[1].trim(), format[2].trim(),
                                format[3].trim());
                    }
                }
                reader.close();
                System.out.println("\n\nData file read!");
        }
    }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
