import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader _br;
    public static void main(String[] args){
        Common.CompilePattern();
        Common.debug = true;
        System.out.println("WARNING: It is highly recommended that you " +
                "backup your translation files before running this program.");

        _br = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("Provide the absolute path to the mod root directory.");
            System.out.print("Mod root path: ");

            String modRoot = _br.readLine();
            if(Common.DirectoryExists(modRoot)){
                System.out.println("\nTP2 / TPH Mode?");
                System.out.print("1. Yes \n2. No: ");

                String mode = _br.readLine();
                int selectedMode = Integer.parseInt(mode);
                if(selectedMode == 1){
                    TP2Mode(modRoot);
                }
                else if (selectedMode == 2){
                    NonTP2Mode(modRoot);
                }
                else{
                    System.out.println("\nInvalid mode entry. Exiting.");
                    System.exit(0);
                }
            }
            else{
                System.out.println("\nPath does not resolve to a directory. Exiting.");
                System.exit(0);
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void NonTP2Mode(String modRoot){
        try{
            System.out.println("\nProvide the absolute path to the native language root directory.");
            System.out.print("Native language TRA root directory path: ");

            String traRoot = _br.readLine();
            if(Common.DirectoryExists(traRoot)){
                FileProcessor processor = new FileProcessor(modRoot, traRoot, false);
            }
            else{
                System.out.println("\nPath does not resolve to a directory. Exiting.");
                System.exit(0);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void TP2Mode(String modRoot){
        try{
            System.out.println("\nProvide the absolute path to the translation file used for your tph and tp2 files.");
            System.out.print("TP2 / TPH TRA file path: ");
            String traPath = _br.readLine();
            if (Common.FileExists(traPath)){
                FileProcessor processor = new FileProcessor(modRoot, traPath, true);
            }
            else{
                System.out.println("\nPath does not resolve to a file. Exiting.");
                System.exit(0);
            }
        }
        catch (Exception e){

        }

    }


}
