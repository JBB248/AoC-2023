import java.util.ArrayList;

public class Day1 
{
    static final String FILE = "data/day1.dat";
    public static void main(String[] args)
    {
        System.out.println("Answer1: " + part1());
        System.out.println("Answer2: " + part2());
    }

    private static int part1()
    {
        ArrayList<String> content = FileReader.readFile(FILE);

        int total = 0;
        for(String line : content)
        {
            int left = 0;
            // Starting from the left, find the first index of a numerical digit
            for(int i = 0; i < line.length(); i++)
            {
                if(Character.isDigit(line.charAt(i)))
                {
                    left = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            int right = 0;
            // Starting from the right, find the last index of a numerical digit
            for(int i = line.length() - 1; i > -1; i--)
            {
                if(Character.isDigit(line.charAt(i)))
                {
                    right = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            total += (left * 10 + right);
        }

        return total;
    }

    static String[] numbers = new String[] {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    private static int part2()
    {
        ArrayList<String> content = FileReader.readFile(FILE);

        int total = 0;
        for(String line : content)
        {
            int left = 0;
            int numIndex = -1;
            // Starting from the left, find the first index of a written word
            for(int i = 1; i < numbers.length; i++)
            {
                int index = line.indexOf(numbers[i]);
                if((numIndex == -1 && index > -1) || (index > -1 && index < numIndex))
                {
                    numIndex = index;
                    left = i;
                }
            }
            // Starting from the left find the first index of a numerical digit
            // If it is earlier than the first index of a written word, use it instead
            for(int i = 0; i < (numIndex > -1 ? numIndex : line.length()); i++)
            {
                char c = line.charAt(i);
                if(Character.isDigit(c))
                {
                    left = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            int right = 0;
            numIndex = -1;
            // Starting from the right, find the last index of a written word
            for(int i = 1; i < numbers.length; i++)
            {
                int index = line.lastIndexOf(numbers[i]);
                if((numIndex == -1 && index > -1) || (index > -1 && numIndex < index))
                {
                    numIndex = index;
                    right = i;
                }
            }
            // Starting from the right find the last index of a numerical digit
            // If it is later than the last index of a written word, use it instead
            for(int i = line.length() - 1; i > numIndex; i--)
            {
                char c = line.charAt(i);
                if(Character.isDigit(c))
                {
                    right = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            // The concat the left number with the right number and add it to the total
            total += (left * 10 + right);
        }

        return total;
    }
}
