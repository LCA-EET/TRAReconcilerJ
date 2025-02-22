import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class TRAFile {
    private final HashSet<Integer> _usedReferences;
    private final Hashtable<Integer, TRAReference> _references;
    private final Path _filePath;

    public TRAFile(Path path){
        _filePath = path;
        _usedReferences = new HashSet<>();
        _references = new Hashtable<>();
        if(Common.FileExists(_filePath)){
            ReadTRA();
        }
    }

    private void ReadTRA(){
        try {
            String content = Common.ReadText(_filePath);
            Matcher matcher = Common.rx.matcher(content);
            Object[] results = matcher.results().toArray();
            if(results.length > 0){
                for(int i = 0; i < results.length; i++){
                    MatchResult result = (MatchResult) results[i];
                    int referenceID = Integer.parseInt(content.substring(result.start() + 1, result.end()));
                    int endIndex;
                    if((i + 1) < results.length){
                        MatchResult nextMatch = (MatchResult) results[i + 1];
                        endIndex = nextMatch.start() -1;
                    }
                    else{
                        endIndex = content.length();
                    }
                    String referenceText = content.substring(result.end(), endIndex);
                    referenceText = referenceText.substring(referenceText.indexOf("=")+1);
                    _references.put(referenceID, new TRAReference(referenceText));
                    //System.out.println("RefID: " + referenceID + ", Text: " + referenceText );
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddUsedReference(int referenceID){
        System.out.println("Adding used reference: " + referenceID);
        _usedReferences.add(referenceID);
    }

    public void WriteTRAFile(){
        if(!_usedReferences.isEmpty()){
            ArrayList<Integer> refsSorted = new ArrayList(_usedReferences);
            Collections.sort(refsSorted);
            StringBuilder toWrite = new StringBuilder();
            for(int i = 0; i < refsSorted.size(); i++){
                int usedReference = refsSorted.get(i);
                if(_references.containsKey(usedReference)){
                    toWrite.append("@" + usedReference + "=" + _references.get(usedReference).GetReferenceText().trim() + "\n");
                }
            }
            try{
                Files.writeString(_filePath, toWrite.toString());
                //System.out.println(_filePath.toString() + " " + toWrite.toString());
            } catch(Exception ex){
                System.err.println(ex.getMessage());
            }

        }
    }
}
