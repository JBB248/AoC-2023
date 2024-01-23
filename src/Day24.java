import java.util.ArrayList;

public class Day24 
{
    private static final String FILE = "data/day24.dat";

    private static final long MINPOS = 200000000000000l;
    private static final long MAXPOS = 400000000000000l;

    private static long[][] data;

    private static boolean debug = false;

    public static void main(String[] args)
    {
        data = parseContent();

        System.out.println("Answer1: " + part1());
        System.out.println("Answer2: " + part2());
    }

    private static long[][] parseContent()
    {
        ArrayList<String> content = FileReader.readFile(FILE);

        long[][] data = new long[content.size()][6];
        for(int i = 0; i < content.size(); i++)
        {
            long[] numbers = new long[6];
            String[] values = content.get(i).split("@|,");
            for(int ii = 0; ii < 6; ii++)
            {
                numbers[ii] = Long.parseLong(values[ii].trim());
            }
            data[i] = numbers;
        }
        return data;
    }

    /**
     * ** 1-Determine Equation **
     * 
     * ax + by = c
     * 
     * px = sx + t * vx
     * py = sy + t * vy
     * 
     * t = (px - sx) / vx
     * t = (py - sy) / vy
     * 
     * (px - sx) / vx = (py - sy) / vy
     * 
     * vy * px - vx * py = vy * sx - vx * sy
     * 
     * a = vy
     * b = -vx
     * c = vy * sx - vx * sy
     * 
     * a1x + b1y = c1
     * a2x + b2y = c2
     * 
     * ** 2-Check for parallel **
     * 
     * a1 / a2 = b1 / b2
     * a1b2 = a2b1
     * 
     * ** 3-Solve for X and Y **
     * 
     * --Solve for x--
     * a1b2x + b1b2y = b2c1
     * a2b1x + b1b2y = b1c2
     * 
     * a1b2x - a2b1x = b2c1 - b1c2
     * x = (b2c1 - b1c2) / (a1b2 - a2b1)
     * 
     * --Solve for y--
     * a1a2x + a2b1y = a2c1
     * a1a2x + a1b2y = a1c2
     * 
     * a2b1y - a1b2y = a2c1 - a1c2
     * y = (a2c1 - a1c2) / (a2b1 - a1b2)
     * 
     * ** 4-Check if point is in the past **
     * 
     * --check if (point - start) has the same sign as velocity--
     * px - sx ~ vx
     * py - sy ~ vy
     * 
     * ** 5-Check if the intersection is within the given range **
     */
    private static int part1()
    {
        int total = 0;
        for(int i = 0; i < data.length; i++)
        {
            long[] A = data[i];
            for(int ii = 0; ii < i; ii++)
            {
                long[] B = data[ii];

                /* 1 */
                double a1 = A[4], b1 = -A[3], c1 = A[4] * A[0] - A[3] * A[1];
                double a2 = B[4], b2 = -B[3], c2 = B[4] * B[0] - B[3] * B[1];

                /* 2 */
                if(a1 * b2 == a2 * b1) 
                {
                    if(debug)
                        System.out.printf("[%d %d] [%d %d]%nPaths are parallel%n", A[0], A[1], B[0], B[1]);
                    continue;
                }

                /* 3 */
                double px = (b2 * c1 - b1 * c2) / (a1 * b2 - a2 * b1);
                double py = (a2 * c1 - a1 * c2) / (a2 * b1 - a1 * b2);

                if(debug)
                    System.out.printf("[%d %d] [%d %d]%n(%.3f, %.3f)%n", A[0], A[1], B[0], B[1], px, py);

                /* 4 */
                if((px - A[0]) * A[3] < 0 || (px - B[0]) * B[3] < 0 || (py - A[1]) * A[4] < 0 || (py - B[1]) * B[4] < 0)
                {
                    if(debug)
                        System.out.println("Paths crossed in the past\n");
                    continue;
                }
                    
                /* 5 */
                if(MINPOS < px && px < MAXPOS && MINPOS < py && py < MAXPOS)
                {
                    if(debug)
                        System.out.println("Crossed inside test area\n");
                    total++;
                }
                else if(debug)
                    System.out.println("Crossed outside test area\n");
            }
        }

        return total;
    }

    private static int part2()
    {
        int total = 0;

        return total;
    }
}
