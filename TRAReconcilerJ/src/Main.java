import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        System.out.println("WARNING: It is highly recommended that you " +
                "backup your translation files before running this program.");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("Provide the absolute path to the mod root directory.");
            System.out.print("Mod root path: ");

            String modRoot = br.readLine();
            if(Common.DirectoryExists(modRoot)){
                System.out.println("\nTP2 / TPH Mode?");
                System.out.print("1. Yes \n2. No: ");

                String mode = br.readLine();
                int selectedMode = Integer.parseInt(mode);
                if(selectedMode == 1){
                    TP2Mode(br, modRoot);
                }
                else if (selectedMode == 2){
                    NonTP2Mode(br, modRoot);
                }
                else{
                    System.out.println("\nInvalid mode entry. Exiting.");
                    System.exit(1);
                }
            }
            else{
                System.out.println("\nPath does not resolve to a directory. Exiting.");
                System.exit(1);
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void NonTP2Mode(BufferedReader br, String modRoot) throws IOException {
        System.out.println("\nProvide the absolute path to the native language root directory.");
        System.out.print("Native language TRA root directory path: ");

        String traRoot = br.readLine();
        if(Common.DirectoryExists(traRoot)){
            new FileProcessor(modRoot, traRoot, false);
        }
        else{
            System.out.println("\nPath does not resolve to a directory. Exiting.");
            System.exit(1);
        }
    }

    private static void TP2Mode(BufferedReader br, String modRoot) throws IOException {
        System.out.println("\nProvide the absolute path to the translation file used for your tph and tp2 files.");
        System.out.print("TP2 / TPH TRA file path: ");
        String traPath = br.readLine();
        if (Common.FileExists(traPath)){
            new FileProcessor(modRoot, traPath, true);
        }
        else{
            System.out.println("\nPath does not resolve to a file. Exiting.");
            System.exit(1);
        }
    }
}
