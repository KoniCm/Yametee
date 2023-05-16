package com.praticing.yametee.Student;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.praticing.yametee.R;
import com.praticing.yametee.Librarian.UpdateStudentActivity;
import java.util.ArrayList;

public class StudentCustomAdapter extends RecyclerView.Adapter<StudentCustomAdapter.MyViewHolder> {
    private Activity activity;
    private Context context;
    private ArrayList student_id, student_name, student_level, student_section, student_strand, student_pass;
    Animation animation;

    //Constructor with parameter
    public StudentCustomAdapter(Activity activity, Context context, ArrayList student_id, ArrayList student_name,
                                ArrayList student_level, ArrayList student_section, ArrayList student_strand, ArrayList student_pass) {
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_level = student_level;
        this.student_section = student_section;
        this.student_strand = student_strand;
        this.student_pass = student_pass;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowstudent, parent, false);
        return new MyViewHolder(view);
    }

    //Tap . get position
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.student_id_txt.setText(String.valueOf(student_id.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.student_level_txt.setText(String.valueOf(student_level.get(position)));
        holder.student_section_txt.setText(String.valueOf(student_section.get(position)));
        holder.student_strand_txt.setText(String.valueOf(student_strand.get(position)));
        holder.student_pass_txt.setText(String.valueOf(student_pass.get(position)));
        holder.studentMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStudentActivity.class);
                intent.putExtra("row_id",String.valueOf(student_id.get(position)));
                intent.putExtra("name",String.valueOf(student_name.get(position)));
                intent.putExtra("level",String.valueOf(student_level.get(position)));
                intent.putExtra("section",String.valueOf(student_section.get(position)));
                intent.putExtra("strand",String.valueOf(student_strand.get(position)));
                intent.putExtra("pass",String.valueOf(student_pass.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }
    //book ID size 100+
    @Override
    public int getItemCount() {
        return student_id.size();
    }
    //Implementing method find by ID
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_id_txt, student_name_txt, student_level_txt, student_section_txt, student_strand_txt,student_pass_txt;
        LinearLayout studentMainLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_level_txt = itemView.findViewById(R.id.student_level_txt);
            student_section_txt = itemView.findViewById(R.id.student_section_txt);
            student_strand_txt = itemView.findViewById(R.id.student_strand_txt);
            student_pass_txt = itemView.findViewById(R.id.student_pass_txt);
            studentMainLayout = itemView.findViewById(R.id.studentMainLayout);

            //Simple Animation
            animation = AnimationUtils.loadAnimation(context, R.anim.anim_trans);
            studentMainLayout.setAnimation(animation);

        }
    }
}