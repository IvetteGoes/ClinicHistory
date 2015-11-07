package com.example.saraivette.clinichistory;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private TextView tv;
    private ImageButton bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



        /* if (savedInstanceSatete == null){
        getFragmentManager().beginTransaction()
        .add(R.id.coontainer, new PlaceholderFragment())
        .commit();
         */

        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.editText);
        bt = (ImageButton) findViewById(R.id.imageButton);

        bt.setOnClickListener(this);
        /*ejemplos
        manager.insertar("Jaime","36811523");
        manager.insertar2("Sandy", "36571712");
        manager.insertar2("Javier","365740445");
        manager.eliminar("Jaime");
        manager.modificarTelefono("Sandy","36591717");*/

        String[] from = new String[]{manager.CN_Nombre, manager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        cursor = manager.cargarCursorPaciente();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to);
        lista.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageButton) {
            new BuscarTask().execute();
        }

    }

    private class BuscarTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplication(), "Buscando Paciente...",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            cursor = manager.buscarPaciente(tv.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplication(), "Finalizada...",Toast.LENGTH_SHORT).show();
            adapter.changeCursor(cursor);
        }


    }
}
