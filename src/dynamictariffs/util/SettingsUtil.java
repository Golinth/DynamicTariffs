package dynamictariffs.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;


public class SettingsUtil {
    
    public static SettingsAPI settings = Global.getSettings();
    public static Logger log = Global.getLogger(SettingsUtil.class);
    
    public static int[] percents = new int[6];
    public static Boolean granular = false;
    public static Boolean commission = false; // States whether or not you want Tariffs to also be modified by commission
    public static Integer commDiscount = 0; // This will be how much the tariff should change based on commission


    /**
     * This reads in the settings.json file
     */
    public static void readSettings(){
        try {
            JSONObject modSettings = settings.loadJSON("settings.json", "dynamictariffs");
            JSONArray jsonPercents = modSettings.getJSONArray("dt_percents");
            granular = modSettings.getBoolean("dt_granular");
            commission = modSettings.getBoolean("dt_commission");
            commDiscount = modSettings.getInt("dt_commDiscount");
            for(int i = 0; i < jsonPercents.length(); i++){
                percents[i] = jsonPercents.getInt(i);
            }
        } catch (IOException | JSONException e) {
            log.info(e.getMessage());
        }
    }

    
    /** 
     * Returns the array of tariff percents that are read in.
     * @return int[]
     */
    public static int[] getPercents(){
        return percents;
    }

    
    /** 
     * This returns whether or not granular was flipped on
     * @return Boolean
     */
    public static Boolean getGranular(){
        return granular;
    }
    
}
