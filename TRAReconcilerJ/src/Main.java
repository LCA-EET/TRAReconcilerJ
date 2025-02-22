import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        Common.CompilePattern();
        System.out.println("WARNING: It is highly recommended that you " +
                "backup your translation files before running this program.");
        System.out.println("Provide the absolute path to the mod root directory.");
        System.out.print("Mod root path: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = br.readLine();
            if(Common.DirectoryExists(input)){
                FileProcessor processor = new FileProcessor(input);
                processor.ProcessTRAFiles();
            }
            else{
                System.out.println("\nPath does not resolve to a directory. Exiting.");
            }
        } catch(Exception ex){

        }

    }


}
