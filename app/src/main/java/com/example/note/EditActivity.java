package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

public class EditActivity extends AppCompatActivity {

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit);
        Button deleteButton = (Button) findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id = note.getId();
                LitePal.delete(Note.class, id);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNote();
        EditText title = (EditText) findViewById(R.id.title);
        EditText body = (EditText) findViewById(R.id.body);
        title.setText(note.getTitle());
        body.setText(note.getBody());
    }

    @Override
    protected void onPause() {
        super.onPause();
        save_note();
    }

    private void initNote() {
        Intent intent = getIntent();
        Long id = intent.getLongExtra("note_id", -1);
        if(id == -1){
            note = null;
        }else{
            note = LitePal.find(Note.class, id);
        }
    }

    private void save_note() {
        EditText title = (EditText) findViewById(R.id.title);
        EditText body = (EditText) findViewById(R.id.body);
        String note_title = title.getText().toString();
        String note_body = body.getText().toString();
        if(note_title.isEmpty() && note_body.isEmpty()){
            LitePal.delete(Note.class, note.getId());
            return;
        }
        note.setTitle(note_title);
        note.setBody(note_body);
        note.update(note.getId());
    }
}