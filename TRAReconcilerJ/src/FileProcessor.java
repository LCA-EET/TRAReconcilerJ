import java.nio.file.Path;
import java.util.List;

public class FileProcessor {
    private final Path _traPath;

    public FileProcessor(String traPath){
        _traPath = Path.of(traPath);
    }
    public void ProcessTRAFiles(){
        try{
            List<String> traFiles = Common.FindFiles(_traPath, ".tra");
            for(int i = 0; i < traFiles.size(); i++){
                System.out.println(traFiles.get(i));
            }
        } catch (Exception ex){

        }

    }
}
