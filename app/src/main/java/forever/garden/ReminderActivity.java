package forever.garden;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import forever.garden.jobs.TimeSync;
import forever.garden.model.Plant;
import forever.garden.model.Reminder;
import forever.garden.model.SharedManger;
import forever.garden.model.Utils;

public class ReminderActivity extends AppCompatActivity implements ReminderRvAdapter.ReminderCLick {

    RecyclerView rv;
    TextView empty;
    FloatingActionButton add;

    SharedManger sharedManger;

    List<Reminder> reminders;
    List<Plant> plants;
    ReminderRvAdapter mReminderRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        sharedManger = new SharedManger(this);

        rv = findViewById(R.id.remRv);
        empty = findViewById(R.id.empty);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReminderDialog();
            }
        });

        setupList();
    }

    private void setupList() {

        plants = sharedManger.getPlants();
        reminders = sharedManger.getReminderValues();

        if (reminders == null || reminders.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
            return;
        }

        empty.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);

        rv.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mReminderRvAdapter = new ReminderRvAdapter(this, reminders);
        rv.setAdapter(mReminderRvAdapter);

        try {
            Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequests();
            if (!jobRequests.isEmpty()) {
                for (JobRequest request : jobRequests) {
                    JobManager.instance().cancelAllForTag(request.getTag());
                }
            }

            for (int s = 0; s < reminders.size(); s++) {
                Reminder reminder = reminders.get(s);
                TimeSync.schedule(this, reminder.getPlantName(), Utils.getTimeInMints(reminder.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showTimeDialog(final TextView textView) {

        Calendar now = Calendar.getInstance();

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

                        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                        String minuteString = minute < 10 ? "0" + minute : "" + minute;

                        String time = hourString + ":" + minuteString;
                        textView.setText(time);
                    }
                }, now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );

        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
    }

    public void showReminderDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_new_reminder);

        ArrayAdapter<Plant> doctorsAdapter = new ArrayAdapter<>(this,
                R.layout.view_spinner_item, plants);

        Spinner spinner = dialog.findViewById(R.id.spinner);
        spinner.setAdapter(doctorsAdapter);
        spinner.setSelection(0);

        final TextView time = dialog.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time);
            }
        });

        Button send = dialog.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(time.getText().toString().trim())) {
                    Toast.makeText(ReminderActivity.this, "Insert time", Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                reminders.add(new Reminder(
                        ((Plant) spinner.getSelectedItem()).getName(),
                        time.getText().toString().trim()));
                sharedManger.setReminderValues(reminders);
                setupList();

                Toast.makeText(ReminderActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onRemove(Reminder rem, int pos) {
        reminders.remove(pos);
        mReminderRvAdapter.mReminders = reminders;
        mReminderRvAdapter.notifyDataSetChanged();
        sharedManger.setReminderValues(reminders);
        Toast.makeText(ReminderActivity.this, "Deleted", Toast.LENGTH_LONG).show();

        try {
            Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequests();
            if (!jobRequests.isEmpty()) {
                for (JobRequest request : jobRequests) {
                    JobManager.instance().cancelAllForTag(request.getTag());
                }
            }

            for (int s = 0; s < reminders.size(); s++) {
                Reminder reminder = reminders.get(s);
                TimeSync.schedule(this, reminder.getPlantName(), Utils.getTimeInMints(reminder.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupList();
    }
}
