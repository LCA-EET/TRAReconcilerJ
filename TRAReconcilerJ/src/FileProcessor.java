import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;

public class FileProcessor {
    private final Path _traPath, _modPath;
    private final boolean _tphMode;
    private final Hashtable<String, TRAFile> _traFiles;
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
        _traFiles.put(Common.File_NoExtension(traFilePath), _lastProcessed);
    }
    private void ProcessTPH(){
        ProcessTRAFile(_traPath);
        ProcessComponentFiles(".tph");
        ProcessComponentFiles(".tp2");
        System.out.println(("Writing reconciled TRA..."));
        _lastProcessed.WriteTRAFile();
    }
    private void ProcessNonTPH(){
        ProcessTRAFiles();
        ProcessComponentFiles(".baf");
        ProcessComponentFiles(".d");
        System.out.println(("Writing reconciled TRAs..."));
        for(TRAFile traFile : _traFiles.values()){
            traFile.WriteTRAFile();
        }
    }
    private void ProcessTRAFiles(){
        try {
            for(String traFile : Common.FindFiles(_traPath, ".tra")) {
                ProcessTRAFile(Path.of(traFile));
            }
        } catch (Exception ex){

        }
    }
    private void ProcessComponentFiles(String extension){
        try {
            for(String stringPath : Common.FindFiles(_modPath, extension)) {
                String fileID = Common.File_NoExtension(Path.of(stringPath));
                if(!_tphMode){
                    if(_traFiles.containsKey(fileID)){
                        ComponentFile cf = new ComponentFile(stringPath, _traFiles.get(fileID));
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
