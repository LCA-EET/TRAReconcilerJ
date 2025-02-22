import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class ComponentFile {
    private final TRAFile _associatedTRA;

    public ComponentFile(String path, TRAFile associatedTRA){
        _associatedTRA = associatedTRA;
        if(Common.FileExists(path)){
            Common.PrintDebug("Path exists: " + path);
            ReadComponentFile(path);
        }
    }
    private void ReadComponentFile(String path){
        String fileText = Common.ReadText(path);
        Matcher matcher = Common.rx.matcher(fileText);
        Object[] results = matcher.results().toArray();
        if(results.length > 0){
            for(int i = 0; i < results.length; i++){
                MatchResult result = (MatchResult) results[i];
                int referenceID = Integer.parseInt(fileText.substring(result.start() + 1, result.end()));
                _associatedTRA.AddUsedReference(referenceID);
            }
        }

    }

}
