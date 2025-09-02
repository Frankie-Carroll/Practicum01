import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;


public class PersonGenerator {
    public static void main(String[] args) {

        String ID;
        String firstName;
        String lastName;
        String title;
        String CSVPersonRec;
        int yearOfBrith;

        Scanner in = new Scanner(System.in);

        ArrayList<String> csvPerson = new ArrayList<>();

        boolean done;

        do{
            ID = SafeInput.getNonZeroLenString(in, "Enter ID");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            yearOfBrith = SafeInput.getRangedInt(in, "What year were you born",1000, 2025 );
            CSVPersonRec = ID + "," + firstName + "," + lastName + "," + title + "," + yearOfBrith + "\n";

            csvPerson.add(CSVPersonRec);

            done = SafeInput.getYNConfirm(in, "Are you done adding persons?");

        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\person_data.txt");

        try{

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String p: csvPerson){
                writer.write(p, 0, p.length());
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
