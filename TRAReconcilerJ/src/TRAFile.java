import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class TRAFile {
    private HashSet<Integer> _usedReferences;
    private Hashtable<Integer, TRAReference> _references;
    private Path _filePath;

    public TRAFile(String path){
        _filePath = Path.of(path);
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
            while(matcher.find()){
                MatchResult result = matcher.toMatchResult();
                String match = result.group(1);
                int referenceID = Integer.parseInt(match.substring(1));
                System.out.println(referenceID);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddUsedReference(int referenceID){
        _usedReferences.add(referenceID);
    }

    public void WriteTRAFile(){
        if(!_usedReferences.isEmpty()){
            ArrayList<Integer> refsSorted = new ArrayList(_usedReferences);
            Collections.sort(refsSorted);
            StringBuilder toWrite = new StringBuilder();
            for(int i = 0; i < refsSorted.size(); i++){
                int usedReference = refsSorted.get(i);
                if(_references.contains(usedReference)){
                    //toWrite.append("@" + usedReference + "=" + )
                }
            }
            try{
                Files.writeString(_filePath, toWrite.toString());
            } catch(Exception ex){
                System.err.println(ex.getMessage());
            }

        }
    }
}
