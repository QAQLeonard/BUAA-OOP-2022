import java.io.*;

public class Q3
{
    public static void removeComments(String inputPath,String outPath) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(inputPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
        String line = br.readLine();
        int length;
        boolean double_quotation = false, long_notes = false, single_quotation = false;
        StringBuilder sb = new StringBuilder();
        while (line != null)
        {
            length = line.length();

            for (int i = 0; i < length; i++)
            {

                if (!double_quotation && !long_notes && !single_quotation) //既不在双引号里也不在长注释里也不在单引号里
                {
                    if (line.charAt(i) == '"')
                    {
                        double_quotation = true;
//                        printf("%c", ch[i]);
                    }

                    else if (i<line.length()-1&&line.charAt(i) == '/' && line.charAt(i+1) == '/')
                    {
                        break;
                    } //遇到短注释直接跳过本行

                    else if (i<line.length()-1&&line.charAt(i) == '/' && line.charAt(i+1) == '*')
                    {
                        long_notes = true;
                    }

                    else if (line.charAt(i) == 39)
                    {
//                        printf("%c", ch[i]);
                        sb.append(line.charAt(i));
                        single_quotation = true;
                    }
                    else
                    {
//                        printf("%c", ch[i]);
                        sb.append(line.charAt(i));
                    }
                }

                else if (double_quotation) //双引号里
                {
//                    printf("%c", ch[i]);
                    sb.append(line.charAt(i));
                    if (line.charAt(i) == '"')
                    {
                        double_quotation = false;
                    }
                }

                else if (long_notes) //长注释里
                {
                    if (i<line.length()-1&&line.charAt(i) == '*' && line.charAt(i+1) == '/')
                    {
                        long_notes = false;
                        i++;
                    }
                }

//                else if (single_quotation && !long_notes)
//                {
//                    printf("%c", ch[i]);
//                    if (line.charAt(i) == 39)
//                    {
//                        single_quotation = false;
//                    }
//                }
            }

            if (!long_notes) //换行
            {
//                printf("\n");
                sb.append("\r\n");
                bw.write(sb.toString());
                sb.delete(0, sb.length());
            }
            line = br.readLine();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws IOException
    {
        removeComments("template/solution/Question3/input.txt", "template/solution/Question3/out.txt");
    }
}
