import java.util.regex.Matcher;

public class ComponentFile {
    private final TRAFile _associatedTRA;

    public ComponentFile(String path, TRAFile associatedTRA){
        _associatedTRA = associatedTRA;
        if(Common.FileExists(path)){
            ReadComponentFile(path);
        }
    }
    private void ReadComponentFile(String path){
        String fileText = Common.ReadText(path);
        Matcher matcher = Common.rx.matcher(fileText);
        while(matcher.find()){
            int usedReference = Integer.parseInt(matcher.group(1).substring(1));
            _associatedTRA.AddUsedReference(usedReference);
        }
    }

}
