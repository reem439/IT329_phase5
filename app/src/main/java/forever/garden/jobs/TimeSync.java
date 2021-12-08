package forever.garden.jobs;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.evernote.android.job.DailyJob;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import forever.garden.model.Utils;


public class TimeSync extends DailyJob {

    private static Context mContext;
    private static String mTag;
    private static int mMinutes;

    TimeSync(Context context, String tag, int mints) {
        mContext = context;
        mTag = tag;
        mMinutes = mints;
    }

    public static void schedule(Context context, String tag, int mints) {
        mContext = context;
        mTag = tag;
        mMinutes = mints;
        DailyJob.schedule(
                new JobRequest.Builder(mTag), TimeUnit.MINUTES.toMillis(mMinutes), TimeUnit.MINUTES.toMillis(mMinutes));
    }

    @NonNull
    @Override
    protected DailyJobResult onRunDailyJob(Params params) {
        Log.e(mTag, "SUCCESS");
        Utils.buildNotification(mContext, mTag);
        return DailyJobResult.SUCCESS;
    }
}
