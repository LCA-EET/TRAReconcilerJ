import java.nio.file.Path;
import java.util.HashSet;
import java.util.Hashtable;
import java.nio.file.Files;

public class TRAFile {
    private HashSet<Integer> _usedReferences;
    private Hashtable<Integer, TRAReference> _references;
    private Path _filePath;

    public TRAFile(String path){
        _filePath = Path.of(path);
        _usedReferences = new HashSet<>();
        _references = new Hashtable<>();
        if(Files.exists(_filePath)){
            ReadTRA();
        }
        if(traFile.exists() && !traFile.isDirectory()){
            ReadTRA(traFile);
        }
    }

    private void ReadTRA(){
        try {
            byte[] encoded = Files.readAllBytes(_filePath);

            String content = Files.readAll(_filePath);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
