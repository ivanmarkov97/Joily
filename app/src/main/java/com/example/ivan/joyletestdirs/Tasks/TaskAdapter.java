package com.example.ivan.joyletestdirs.Tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.joyletestdirs.MainActivity;
import com.example.ivan.joyletestdirs.R;
import com.example.ivan.joyletestdirs.ShowSubtask1Level;
import com.example.ivan.joyletestdirs.ShowSubtask2Level;
import com.example.ivan.joyletestdirs.ShowSubtask3Level;
import com.example.ivan.joyletestdirs.ShowSubtask4Level;
import com.example.ivan.joyletestdirs.ShowTask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ivan on 07.09.2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private Context context;
    private List<Task> tasks;
    private LinearLayout linearLayout;
    private Intent intent;
    private ArrayList<String> navTab = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private final String sharedPrefName = "task_way";
    private final String WAY_TO_SUBTASK_1 = "";

    public TaskAdapter(){
        this.context = null;
        this.tasks = null;

    }

    public TaskAdapter(Context context, List<Task> tasks){
        this.context = context;
        this.tasks = tasks;
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public ArrayList<String> getNavTab() {
        return navTab;
    }

    public void setNavTab(ArrayList<String> navTab) {
        this.navTab = navTab;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new TaskHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textView.setText(task.getTaskName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public TaskHolder(final View item){
            super(item);
            textView = (TextView) item.findViewById(R.id.task_text);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if(v.getContext().getClass().getName().equals("com.example.ivan.joyletestdirs.MainActivity")) {
                        intent = new Intent(v.getContext(), ShowSubtask1Level.class);
                        Toast.makeText(v.getContext(), "0", Toast.LENGTH_SHORT).show();
                        navTab.add(textView.getText().toString());
                        intent.putStringArrayListExtra("tasks_nav_0", navTab);
                        Log.d("MyTAG", "0");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WAY_TO_SUBTASK_1, textView.getText().toString());
                        editor.apply();

                        v.getContext().startActivity(intent);
                        Log.d("MyTAG", "startted_1");
                    }
                }
            });
        }
    }
}
