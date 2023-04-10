public class TwoColorBall
{
    public static void main(String[] args)
    {
        int[] redBall = new int[6];
        int blueBall = 0;
        int temp = 0;
        for (int i = 0; i < 6; i++)
        {
            redBall[i] = (int) (Math.random() * 33 + 1);
            for (int j = 0; j < i; j++)
            {
                if (redBall[i] == redBall[j])
                {
                    i--;
                    break;
                }
            }
        }
        blueBall = (int) (Math.random() * 16 + 1);
        for (int i = 0; i < 6; i++)
        {
            for (int j = i + 1; j < 6; j++)
            {
                if (redBall[i] > redBall[j])
                {
                    temp = redBall[i];
                    redBall[i] = redBall[j];
                    redBall[j] = temp;
                }
            }
        }
        System.out.print("The red ball is: ");
        for (int i = 0; i < 6; i++)
        {
            System.out.print(redBall[i] + " ");
        }
        System.out.println();
        System.out.println("The blue ball is: " + blueBall);
    }
}
