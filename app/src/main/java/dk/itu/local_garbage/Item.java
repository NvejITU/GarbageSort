package dk.itu.local_garbage;

public class Item {
    private String mWhat = null;
    private String mPlace = null;
    public Item(String what, String place) { mWhat = what;  mPlace = place; }
    @Override
    public String toString() { return oneLine(""," in: "); }
    public String getWhat() { return mWhat; }
    public void setWhat(String what) { mWhat = what; }
    public String getPlace() { return mPlace; }
    public void setWhere(String place) { mPlace = place; }
    public String oneLine(String pre, String post) {
        return pre+mWhat + post + mPlace;
    }
}