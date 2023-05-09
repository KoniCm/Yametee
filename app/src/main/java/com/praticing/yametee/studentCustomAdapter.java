package com.praticing.yametee;

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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class studentCustomAdapter extends RecyclerView.Adapter<studentCustomAdapter.MyViewHolder>
{
    private Activity activity;
    private Context context;
    private ArrayList student_id, student_name, student_level, student_section, student_strand;
    Animation animation;

    //Constructor with parameter
    studentCustomAdapter(Activity activity, Context context, ArrayList student_id, ArrayList student_name,
                         ArrayList student_level, ArrayList student_section, ArrayList student_strand)
    {
        this.activity = activity;
        this.context = context;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_level = student_level;
        this.student_section = student_section;
        this.student_strand = student_strand;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowstudent, parent, false);
        return new MyViewHolder(view);
    }
    //Tap . get position
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        holder.student_id_txt.setText(String.valueOf(student_id.get(position)));
        holder.student_name_txt.setText(String.valueOf(student_name.get(position)));
        holder.student_level_txt.setText(String.valueOf(student_level.get(position)));
        holder.student_section_txt.setText(String.valueOf(student_section.get(position)));
        holder.student_strand_txt.setText(String.valueOf(student_strand.get(position)));
        holder.studentMainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, updateStudentActivity.class);
                intent.putExtra("row_id",String.valueOf(student_id.get(position)));
                intent.putExtra("name",String.valueOf(student_name.get(position)));
                intent.putExtra("level",String.valueOf(student_level.get(position)));
                intent.putExtra("section",String.valueOf(student_section.get(position)));
                intent.putExtra("strand",String.valueOf(student_strand.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }
    //book ID size 100+
    @Override
    public int getItemCount()
    {
        return student_id.size();
    }
    //Implementing method find by ID
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView student_id_txt, student_name_txt, student_level_txt, student_section_txt, student_strand_txt;
        LinearLayout studentMainLayout;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            student_id_txt = itemView.findViewById(R.id.student_id_txt);
            student_name_txt = itemView.findViewById(R.id.student_name_txt);
            student_level_txt = itemView.findViewById(R.id.student_level_txt);
            student_section_txt = itemView.findViewById(R.id.student_section_txt);
            student_strand_txt = itemView.findViewById(R.id.student_strand_txt);
            studentMainLayout = itemView.findViewById(R.id.studentMainLayout);

            //Simple Animation
            animation = AnimationUtils.loadAnimation(context, R.anim.anim_trans);
            studentMainLayout.setAnimation(animation);

        }
    }
}