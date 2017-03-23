
import jdk.nashorn.internal.parser.JSONParser;

public class DBPreferences
{
    //JSON object file still to be added
    //JSONParser parser = new JSONParser();
    private String prefUserID;  
    public DBPreferences()
    {
        //JSON parser to still be added
        prefUserID = "zzz-bf3-25f-acdc";
    }
    
    public void addPref(String userID, boolean[] prefs)
    {
        System.out.println("prefs added");
    }
    
    public boolean[] getPref (String userID)
    {
        return (new boolean[]{true, true, false});
    }
    
    public void deletePrefs(String userID)
    {
        System.out.println("user deleted");
    }
    
    public void updatePrefs(String userID, boolean[] prefs)
    {
        System.out.println("prefs updated");
    }
}
