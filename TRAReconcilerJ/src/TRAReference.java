public class TRAReference {
    private String _referenceText;
    private Integer _referenceID;

    public TRAReference(Integer referenceID, String referenceText){
        _referenceID = referenceID;
        _referenceText = referenceText;
    }

    public String GetReferenceText(){
        return _referenceText;
    }
}
