import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;

public class FileProcessor {
    private final Path _traPath, _modPath;
    private boolean _tphMode;
    private Hashtable<Path, TRAFile> _traFiles;
    private TRAFile _lastProcessed;

    public FileProcessor(String modPath, String traPath, boolean tphMode){
        _modPath = Path.of(modPath);
        _tphMode = tphMode;
        _traPath = Path.of(traPath);
        _traFiles = new Hashtable<>();
        if(_tphMode){
            ProcessTPH();
        }
        else{
            ProcessNonTPH();
        }
    }
    private void ProcessTRAFile(Path traFilePath){
        _lastProcessed = new TRAFile(traFilePath);
        _traFiles.put(traFilePath.getFileName(), _lastProcessed);
    }
    private void ProcessTPH(){
        ProcessTRAFile(_traPath);
        ProcessComponentFiles(".tph");
        ProcessComponentFiles(".tp2");
        System.out.println(("Writing reconciled TRA..."));
        //_lastProcessed.WriteTRAFile();
    }
    private void ProcessNonTPH(){
        ProcessTRAFiles();
        ProcessComponentFiles(".baf");
        ProcessComponentFiles(".d");
        System.out.println(("Writing reconciled TRAs..."));
        for(TRAFile traFile : _traFiles.values()){
            //traFile.WriteTRAFile();
        }
    }
    private void ProcessTRAFiles(){
        try{
            List<String> traFiles = Common.FindFiles(_traPath, ".tra");
            for(int i = 0; i < traFiles.size(); i++){
                ProcessTRAFile(Path.of(traFiles.get(i)));
            }
        } catch (Exception ex){

        }

    }
    private void ProcessComponentFiles(String extension){
        try {
            List<String> files = Common.FindFiles(_modPath, extension);
            for(int i = 0; i < files.size(); i++){
                String stringPath = files.get(i);
                Path fileName = Path.of(stringPath).getFileName();
                System.out.println("Processing component file: " + stringPath);
                if(!_tphMode){
                    if(_traFiles.contains(fileName)){
                        ComponentFile cf = new ComponentFile(stringPath, _traFiles.get(fileName));
                    }
                }
                else{
                    ComponentFile cf = new ComponentFile(stringPath, _lastProcessed);
                }
            }
        } catch (Exception e){

        }


    }
}
