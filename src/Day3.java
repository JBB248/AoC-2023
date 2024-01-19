import java.awt.Rectangle;
import java.util.ArrayList;

public class Day3
{
    static class NumBox
    {
        public int value = 0;
        public boolean counted = false;
        public Rectangle bounds;

        public NumBox(int x, int y, int width, int value)
        {
            this.value = value;
            this.bounds = new Rectangle(x, y, width, 1);
        }
    }

    static final String FILE = "data/day3.dat";

    static ArrayList<String> content;
    static ArrayList<NumBox> boxes;

    public static void main(String[] args)
    {
        content = FileReader.readFile(FILE);
        boxes = setupBoxes();

        System.out.println("Answer1: " + part1());
        System.out.println("Answer2: " + part2());
    }

    private static ArrayList<NumBox> setupBoxes()
    {
        ArrayList<NumBox> boxes = new ArrayList<>();

        // Create boundaries for each number found in the data
        for(int y = 0; y < content.size(); y++)
        {
            String line = content.get(y);
            for(int x = 0; x < line.length(); x++)
            {
                char c = line.charAt(x);
                if(Character.isDigit(c))
                {
                    int i = 1;
                    while(x + i < line.length() && Character.isDigit(line.charAt(x + i))) 
                        i++;
                    boxes.add(new NumBox(x, y, i, Integer.parseInt(line.substring(x, x + i))));
                    x += i;
                }
            }
        }

        return boxes;
    }

    private static int part1()
    {
        // Use boundaries to detect if any special characters are adjacent to a number
        int total = 0;
        for(int y = 0; y < content.size(); y++)
        {
            String line = content.get(y);
            for(int x = 0; x < line.length(); x++)
            {
                char c = line.charAt(x);
                if(c != '.' && !Character.isLetterOrDigit(c))
                {
                    Rectangle bounds = new Rectangle(x - 1, y - 1, 3, 3);
                    for(NumBox box : boxes)
                    {
                        if(box.counted || !box.bounds.intersects(bounds))
                            continue;
                        
                        box.counted = true; // Make sure we don't count the same box twice
                        total += box.value;
                    }
                }
            }
        }

        return total;
    }

    private static int part2()
    {
        // Use boundaries to detect if any special characters are adjacent to a number
        int total = 0;
        for(int y = 0; y < content.size(); y++)
        {
            String line = content.get(y);
            for(int x = 0; x < line.length(); x++)
            {
                char c = line.charAt(x);
                if(c == '*')
                {
                    NumBox box1 = null;
                    NumBox box2 = null;
                    Rectangle bounds = new Rectangle(x - 1, y - 1, 3, 3);
                    for(NumBox box : boxes)
                    {
                        if(!box.bounds.intersects(bounds))
                            continue;
                        
                        if(box1 == null)
                            box1 = box;
                        else if(box2 == null)
                        {
                            box2 = box;
                            break;
                        }
                    }

                    if(box2 != null)
                        total += box1.value * box2.value;
                }
            }
        }

        return total;
    }
}
