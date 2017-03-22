public class DBPreferences implements Navigation
{
    //JSON object file still to be added
    public DBPreferences()
    {
        //JSON parser to still be added
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
}