
package ac.marche.be.apptravaux.lib;

import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * Created by jfsenechal on 06-11-16.
 * Help to debug
 */

public class AcLog {

    public static void d(String key, String message) {
        if (BuildConfig.DEBUG) {
            AcLog.d(key, message);
        }
    }

    public static void e(String key, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(key, message);
        }
    }

    public static void i(String key, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(key, message);
        }
    }

    public static void v(String key, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(key, message);
        }
    }
}
