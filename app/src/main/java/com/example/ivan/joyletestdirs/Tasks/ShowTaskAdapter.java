package com.example.ivan.joyletestdirs.Tasks;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.joyletestdirs.R;
import com.example.ivan.joyletestdirs.ShowSubtask1Level;
import com.example.ivan.joyletestdirs.ShowSubtask2Level;
import com.example.ivan.joyletestdirs.ShowSubtask3Level;
import com.example.ivan.joyletestdirs.ShowSubtask4Level;
import com.example.ivan.joyletestdirs.ShowTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 08.09.2017.
 */

public class ShowTaskAdapter extends RecyclerView.Adapter<ShowTaskAdapter.ShowTaskViewHolder>{

    private Context context;
    private List<Task> tasks;
    private LinearLayout linearLayout;
    private ArrayList<String> navTab = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private final String WAY_TO_SUBTASK_1 = "way_to_sub1";
    private final String WAY_TO_SUBTASK_2 = "way_to_sub2";
    private final String WAY_TO_SUBTASK_3 = "way_to_sub3";
    private final String WAY_TO_SUBTASK_4 = "way_to_sub4";

    public ShowTaskAdapter(Context context, List<Task> tasks){
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

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public ShowTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ShowTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowTaskViewHolder holder, int position) {
        Task task = this.tasks.get(position);
        holder.textView.setText(task.getTaskName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ShowTaskViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ShowTaskViewHolder(View itemView){
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.task_text);
            itemView.setOnClickListener(new View.OnClickListener() {
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

                        v.getContext().startActivity(intent);
                    }
                    if(v.getContext().getClass().getName().equals("com.example.ivan.joyletestdirs.ShowSubtask1Level")) {
                        intent = new Intent(v.getContext(), ShowSubtask2Level.class);
                        Toast.makeText(v.getContext(), "1", Toast.LENGTH_SHORT).show();
                        navTab.add(textView.getText().toString());
                        intent.putStringArrayListExtra("tasks_nav_1", navTab);
                        Log.d("MyTAG", "1");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WAY_TO_SUBTASK_2, textView.getText().toString());

                        v.getContext().startActivity(intent);
                    }
                    if(v.getContext().getClass().getName().equals("com.example.ivan.joyletestdirs.ShowSubtask2Level")) {
                        intent = new Intent(v.getContext(), ShowSubtask3Level.class);
                        Toast.makeText(v.getContext(), "2", Toast.LENGTH_SHORT).show();
                        navTab.add(textView.getText().toString());
                        intent.putStringArrayListExtra("tasks_nav_2", navTab);
                        Log.d("MyTAG", "2");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WAY_TO_SUBTASK_3, textView.getText().toString());

                        v.getContext().startActivity(intent);
                    }
                    if(v.getContext().getClass().getName().equals("com.example.ivan.joyletestdirs.ShowSubtask3Level")) {
                        intent = new Intent(v.getContext(), ShowSubtask4Level.class);
                        Toast.makeText(v.getContext(), "4", Toast.LENGTH_SHORT).show();
                        navTab.add(textView.getText().toString());
                        intent.putStringArrayListExtra("tasks_nav_3", navTab);
                        Log.d("MyTAG", "3");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(WAY_TO_SUBTASK_4, textView.getText().toString());

                        v.getContext().startActivity(intent);
                    }
                    if(v.getContext().getClass().getName().equals("com.example.ivan.joyletestdirs.ShowSubtask3Level")) {
                        Toast.makeText(v.getContext(), "final dir", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void setLinearlayoutManager(LinearLayout linearLayout){
        this.linearLayout = linearLayout;
    }

}
