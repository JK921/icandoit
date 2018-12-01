import java.io.File;
import java.io.FileWriter;

public class TestGenerator {

    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();
        File file = new File("./input/testcase.txt");

        sb.append("500\n");
        for (int i = 0; i < 500; i++) {
            for (int j = 1; j <= 500; j++) {
                sb.append(j + 500 * i + " ");
                // System.out.print(j + 500 * i + " ");
            }
            sb.append("\n");
            // System.out.println("");
        }

        FileWriter fw = new FileWriter(file);
        fw.write(sb.toString());
        fw.close();
    }

}
