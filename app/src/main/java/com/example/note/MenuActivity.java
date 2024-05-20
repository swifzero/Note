package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    NoteAdapter adapter;

    private List<Note> notelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Connector.getDatabase();
        setContentView(R.layout.activity_menu);
        initNotes();
        recyclerView = (RecyclerView) findViewById(R.id.fruit_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(notelist);
        recyclerView.setAdapter(adapter);

        Button addButton = (Button) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note();
                note.save();
                Long id = note.getId();
                Intent intent = new Intent(MenuActivity.this, EditActivity.class);
                intent.putExtra("note_id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        notelist = LitePal.findAll(Note.class);
        adapter = new NoteAdapter(notelist);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private void initNotes(){
        notelist = LitePal.findAll(Note.class);
    }
}