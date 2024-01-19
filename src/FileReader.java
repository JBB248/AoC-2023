import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileReader 
{
    public static ArrayList<String> readFile(String path)
    {
        ArrayList<String> result = null;
        try
        {
            result = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        }
        catch(IOException e)
        {
            System.out.println("File not found: " + path);
        }

        return result;
    }    
}
