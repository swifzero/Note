package com.example.note;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        View NoteView;
        TextView NoteTitle;
        TextView NoteBody;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            NoteView = itemView;
            NoteTitle = (TextView) itemView.findViewById(R.id.note_title);
            NoteBody = (TextView) itemView.findViewById(R.id.note_body);
        }
    }

    public NoteAdapter(List<Note> notes){
        this.noteList = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        final NoteViewHolder holder = new NoteViewHolder(view);
        holder.NoteView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Note note = noteList.get(position);
                Intent intent = new Intent(parent.getContext(), EditActivity.class);
                intent.putExtra("note_id", note.getId());
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.NoteTitle.setText(note.getTitle());
        holder.NoteBody.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
