package org.lyon_yan.android.utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * SharedPreferences方式进行日志文件的存取(仅Android平台)
 * @author 颜宁
 *
 */
public class DataRecordByAndroid {

    private SharedPreferences preferences = null;

    public DataRecordByAndroid(Context c){
        preferences=PreferenceManager.getDefaultSharedPreferences(c);
    }

    public void saveValue(String key, String value) throws Exception {
        // TODO Auto-generated method stub
        //获得编辑器
        Editor editor = preferences.edit();
        //存值
        editor.putString(key, value);
        //提交
        editor.commit();
    }

    public String getValue(String key) throws Exception {
        // TODO Auto-generated method stub
        //取值，第二个参数是当key不存在时返回的默认值。
        return getValue(key, "");
    }

    public String getValue(String key,String defValue) throws Exception {
        // TODO Auto-generated method stub
        //取值，第二个参数是当key不存在时返回的默认值。
        if(preferences.contains(key)){
            String values = preferences.getString(key, defValue);
            return values;
        }else{
            return defValue;
        }
    }

    public boolean remove(String key) {
        // TODO Auto-generated method stub
        try {

            if(preferences.contains(key)){
                //获得编辑器
                Editor editor = preferences.edit();
                //存值
                editor.remove(key);
                //提交
                editor.commit();
            }
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }


}