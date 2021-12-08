package forever.garden;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;

import forever.garden.jobs.RemindersJob;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        JobManager.create(this).addJobCreator(new RemindersJob(base));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
