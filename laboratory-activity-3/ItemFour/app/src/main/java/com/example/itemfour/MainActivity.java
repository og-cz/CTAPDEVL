package com.example.itemfour;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNote;
    Button btnSave, btnLoad, btnDelete;

    SharedPreferences prefs;
    private static final String PREFS_NAME = "MyNotes";
    private static final String KEY_NOTE = "note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = findViewById(R.id.etNote);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnDelete = findViewById(R.id.btnDelete);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Auto-load last note
        String lastNote = prefs.getString(KEY_NOTE, "");
        etNote.setText(lastNote);

        btnSave.setOnClickListener(v -> {
            String note = etNote.getText().toString();
            if(!note.isEmpty()){
                prefs.edit().putString(KEY_NOTE, note).apply();
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cannot save empty note", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoad.setOnClickListener(v -> {
            String noteLoaded = prefs.getString(KEY_NOTE, "");
            if(!noteLoaded.isEmpty()){
                etNote.setText(noteLoaded);
                Toast.makeText(this, "Note loaded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No note saved yet", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            prefs.edit().remove(KEY_NOTE).apply();
            etNote.setText("");
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        });
    }
}
