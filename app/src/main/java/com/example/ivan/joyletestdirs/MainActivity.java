package com.example.ivan.joyletestdirs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.ivan.joyletestdirs.Tasks.Task;
import com.example.ivan.joyletestdirs.Tasks.TaskAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ItemTouchHelper itemTouchHelper;
    List<Task> tasks = new ArrayList<>();
    ArrayList<String> navTab = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.top_level_dirs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i = 0; i < 15; i++){
            Task task = new Task("task " + i);
            tasks.add(task);
        }
        taskAdapter = new TaskAdapter(this, tasks);
        taskAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(taskAdapter);

        taskAdapter.setNavTab(navTab);

        SimpleItemTouchHelperCallback simpleItemTouchHelperCallback = new SimpleItemTouchHelperCallback();
        simpleItemTouchHelperCallback.setTaskAdapter(taskAdapter);

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
        private TaskAdapter taskAdapter;

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public void setTaskAdapter(TaskAdapter taskAdapter){
            this.taskAdapter = taskAdapter;
        }
        private Paint p = new Paint();

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.START | ItemTouchHelper.END);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            Bitmap icon;
            if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                View itemView = viewHolder.itemView;
                float height = (float) itemView.getBottom() - (float)itemView.getTop();
                float width = height / 3;

                if(dX > 0){
                    p.setColor(Color.parseColor("#388e3c"));
                    RectF background = new RectF((float)itemView.getLeft(), (float)itemView.getTop(), dX, (float)itemView.getBottom());
                    c.drawRect(background, p);
                    icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_edit_task);
                    RectF icon_dest = new RectF(
                            (float)itemView.getLeft() + width,
                            (float)itemView.getTop() + width,
                            (float)itemView.getLeft() + 2*width,
                            (float)itemView.getBottom() - width
                    );
                    c.drawBitmap(icon, null, icon_dest, p);
                }
                else {
                    p.setColor(Color.parseColor("#d32f2f"));
                    RectF background = new RectF(
                            (float)itemView.getRight() + dX,
                            (float)itemView.getTop(),
                            (float)itemView.getRight(),
                            (float)itemView.getBottom()
                    );
                    c.drawRect(background, p);
                    icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_delete_task);
                    RectF icon_dest = new RectF(
                            (float)itemView.getRight() - 2*width,
                            (float)itemView.getTop() + width,
                            (float)itemView.getRight() - width,
                            (float)itemView.getBottom() - width
                    );
                    c.drawBitmap(icon, null, icon_dest, p);
                }

            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            if(viewHolder.getAdapterPosition() < target.getAdapterPosition()) {
                for (int i = viewHolder.getAdapterPosition(); i < target.getAdapterPosition(); i++) {
                    Collections.swap(taskAdapter.getTasks(), i, i + 1);
                }
            }
            else {
                for (int i = viewHolder.getAdapterPosition(); i > target.getAdapterPosition(); i--) {
                    Collections.swap(taskAdapter.getTasks(), i , i- 1);
                }
            }
            taskAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            if(direction == ItemTouchHelper.START) {
                int pos = viewHolder.getAdapterPosition();
                taskAdapter.getTasks().remove(pos);
                taskAdapter.notifyItemRemoved(pos);
            }
            if(direction == ItemTouchHelper.END){
                //startActivity(new Intent(getApplicationContext(), ShowSubtask1Level.class));
                taskAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onPostResume() {
        Log.d("MyTAG", "ResumeLevel_Root");
        if(navTab.size() > 0){
            navTab.remove(0);
        }
        super.onPostResume();
    }
}
