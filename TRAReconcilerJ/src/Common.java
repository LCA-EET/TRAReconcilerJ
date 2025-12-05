import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Common {

    public static boolean debug = false;

    public static final Pattern rx = Pattern.compile("@[0-9]+", Pattern.CASE_INSENSITIVE);

    public static boolean FileExists(String path){
        return FileExists(Path.of(path));
    }

    public static boolean FileExists(Path path){
        return Files.exists(path);
    }

    public static boolean DirectoryExists(String path){
        return DirectoryExists(Path.of(path));
    }

    public static boolean DirectoryExists(Path path){
        return Files.isDirectory(path);
    }

    public static String ReadText(String path){
        return ReadText(Path.of(path));
    }

    public static String ReadText(Path path){
        try{
            return Files.readString(path);
        } catch(Exception ex){
            return "";
        }
    }

    public static void PrintDebug(String message){
        if(debug){
            System.out.println(message);
        }
    }
    public static String File_NoExtension(Path filePath){
        String fileName = filePath.getFileName().toString();
        return fileName.split("\\.")[0];
    }
    public static List<String> FindFiles(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(p -> !Files.isDirectory(p))
                    // this is a path, not string,
                    // this only test if path end with a certain path
                    //.filter(p -> p.endsWith(fileExtension))
                    // convert path to string first
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
    }
}
