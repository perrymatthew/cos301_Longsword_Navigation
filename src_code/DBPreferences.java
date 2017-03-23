
import jdk.nashorn.internal.parser.JSONParser;

public class DBPreferences
{

    //JSONParser parser = new JSONParser();
    private String prefUserID;
    public DBPreferences()
    {
      prefUserID = "zzz-bf3-25f-acdc";
       /* try{
            Object obj = parser.parse(new FileReader());           
            JSONObject jsonObject = (JSONObject) obj;
            String start_point = (String) jsonObject.get("start_point");
            String end_point = (String) jsonObject.get("end_point");
            Integer popularity = (Integer) jsonObject.get("popularity");
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }*/
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
