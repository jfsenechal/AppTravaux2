package ac.marche.be.apptravaux.sync;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import ac.marche.be.apptravaux.R;


/**
 * Created by jfsenechal on 20-09-16.
 */

public class DataObserver extends ContentObserver {

    // Global variables
    // A content URI for the content provider's data table
    Uri mUri;
    // A content resolver for accessing the provider
    ContentResolver mResolver;
    Activity activity;
    Handler mHandler;
    Context context;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public DataObserver(Handler handler, Context contextParam) {
        super(handler);
        context = contextParam;
        mHandler = handler;
        // AcLog.d("observer", "uuuuuuu");
    }

    /**
     * Define a method that's called when data in the
     * observed content provider changes.
     */
    @Override
    public void onChange(boolean selfChange, Uri uri) {
        // super.onChange(selfChange, uri);
        /**
         * Ask the framework to run your sync adapter.
         * To maintain backward compatibility, assume that
         * changeUri is null.
         */
        //  AcLog.d("observer change", uri.toString());
        long remoteId = Long.valueOf(uri.getLastPathSegment());
        // Log.e("observer insert", String.valueOf(remoteId));

        final String url = context.getResources().getString(R.string.url_publish_suivis);
        final Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
        //    PublishSuivi publishSuivi = new PublishSuivi(context, cursor);
         //   publishSuivi.execute(url);
        }

        // Log.e("observer", "finish");

    }

    /**
     * This method signature is provided for compatibility with
     * older platforms.
     */
    @Override
    public void onChange(boolean selfChange) {
        // AcLog.d("observer", "fffff");
        super.onChange(selfChange, null);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }


}
