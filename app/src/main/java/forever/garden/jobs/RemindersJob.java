package forever.garden.jobs;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import java.util.List;

import forever.garden.model.Reminder;
import forever.garden.model.SharedManger;
import forever.garden.model.Utils;

public class RemindersJob implements JobCreator {

    private Context mContext;
    private SharedManger sharedManger;

    public RemindersJob(Context applicationContext) {
        mContext = applicationContext;
        sharedManger = new SharedManger(applicationContext);
    }

    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        List<Reminder> reminders = sharedManger.getReminderValues();

        TimeSync timeSync = null;
        for (int s = 0; s < reminders.size(); s++) {
            Reminder reminder = reminders.get(s);
            timeSync = new TimeSync(mContext, reminder.getPlantName(), Utils.getTimeInMints(reminder.getTime()));
            break;
        }
        return timeSync;
    }
}
