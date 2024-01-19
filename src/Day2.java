import java.util.ArrayList;

public class Day2 
{
    static final String FILE = "data/day2.dat";
    public static void main(String[] args)
    {
        System.out.println("Answer1: " + part1());
        System.out.println("Answer2: " + part2());
    }

    static final int MAX_RED = 12;
    static final int MAX_GREEN = 13;
    static final int MAX_BLUE = 14;

    private static int part1()
    {
        ArrayList<String> content = FileReader.readFile(FILE);

        int total = 0;
        for(int game = 1; game <= content.size(); game++)
        {
            String line = content.get(game - 1).replaceAll("\\s+", ""); // Remove whitespace

            int red = 0;
            int green = 0;
            int blue = 0;
            // Get the sets of dice grabbed from the bag
            String[] sets = line.split(":")[1].split(";");
            for(String set : sets)
            {
                // Find the highest number of each color from each group
                String[] groups = set.split(",");
                for(String group : groups)
                {
                    if(group.endsWith("red"))
                        red = Math.max(red, Integer.parseInt(group.split("r")[0]));
                    if(group.endsWith("green"))
                        green = Math.max(green, Integer.parseInt(group.split("g")[0]));
                    if(group.endsWith("blue"))
                        blue = Math.max(blue, Integer.parseInt(group.split("b")[0]));
                }
            }

            // Check if the game is valid and add its ID if so
            if(red <= MAX_RED && green <= MAX_GREEN && blue <= MAX_BLUE)
                total += game;
        }

        return total;
    }

    private static int part2()
    {
        ArrayList<String> content = FileReader.readFile(FILE);

        int total = 0;
        for(String line : content)
        {
            line = line.replaceAll("\\s+", ""); // Remove whitespace

            int red = 0;
            int green = 0;
            int blue = 0;
            // Get the sets of dice grabbed from the bag
            String[] sets = line.split(":")[1].split(";");
            for(String set : sets)
            {
                // Find the highest number of each color from each group
                String[] groups = set.split(",");
                for(String group : groups)
                {
                    if(group.endsWith("red"))
                        red = Math.max(red, Integer.parseInt(group.split("r")[0]));
                    if(group.endsWith("green"))
                        green = Math.max(green, Integer.parseInt(group.split("g")[0]));
                    if(group.endsWith("blue"))
                        blue = Math.max(blue, Integer.parseInt(group.split("b")[0]));
                }
            }

            // Get their power and add it to the total
            total += red * green * blue;
        }

        return total;
    }
}
