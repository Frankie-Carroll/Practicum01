
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;


public class PersonReader {


    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String p;

        try
        {
            File workingDir = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDir);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                BufferedReader reader = Files.newBufferedReader(file);

                System.out.printf("%-6s %-15s %-15s %-15s %-5s%n",
                        "ID#", "First Name", "Last Name", "Title", "YOB");
                System.out.println("-----------------------------------------------------");

                while((p = reader.readLine()) != null)
                {
                    String[] format = p.split(",");

                    if (format.length == 5)
                    {
                        System.out.printf("%-6s %-15s %-15s %-15s %-5s%n",
                                format[0].trim(), format[1].trim(), format[2].trim(),
                                format[3].trim(), format[4].trim());
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
