import java.io.*;

public class Q4
{
    public static void addLineNo(String inputPath,String outPath) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(inputPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        int lineNo = 1;
        while (line != null)
        {
            sb.append(lineNo);
            sb.append(" ");
            sb.append(line);
            sb.append("\r\n");
            bw.write(sb.toString());
            sb.delete(0, sb.length());
            line = br.readLine();
            lineNo++;
//            System.out.println(line);
        }
        br.close();
        bw.close();
    }
    public static void main(String[] args) throws IOException
    {
        addLineNo("template/solution/Question4/input.txt", "template/solution/Question4/out.txt");
    }
}
