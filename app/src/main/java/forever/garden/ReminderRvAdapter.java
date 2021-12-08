package forever.garden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import forever.garden.model.Reminder;

public class ReminderRvAdapter extends RecyclerView.Adapter<ReminderRvAdapter.ViewHolder> {

    public List<Reminder> mReminders;
    public Context context;
    ReminderCLick reminderCLick;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public TextView name;
        public TextView time;
        public ImageView delete;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            delete = view.findViewById(R.id.delete);
        }
    }

    public Reminder getValueAt(int position) {
        return mReminders.get(position);
    }

    public ReminderRvAdapter(Context context, List<Reminder> reminders) {
        this.context = context;
        this.reminderCLick = (ReminderCLick) context;
        this.mReminders = reminders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_reminder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Reminder reminder = getValueAt(position);

        holder.name.setText(reminder.getPlantName());
        holder.time.setText(reminder.getTime());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderCLick.onRemove(reminder, position);
            }
        });
    }

    interface ReminderCLick {
        void onRemove(Reminder reminder, int pos);
    }
}
