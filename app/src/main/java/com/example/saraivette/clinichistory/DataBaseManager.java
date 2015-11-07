package com.example.saraivette.clinichistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SaraIvette on 07/11/2015.
 */
public class DataBaseManager {
    public static final String TABLE_NAME="Paciente";
    public static final String CN_ID="_id";
    public static final String CN_Nombre="nombre";
    public static final String CN_PHONE="telefono";

    // create table Paciente(
    // _id integer primary key autoincrement,
    // nomre text not null, telefono text);

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_Nombre + " text not null,"
            + CN_PHONE + " text);";


    private BDHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new BDHelper(context);
        db = helper.getWritableDatabase();

    }

    public ContentValues generarContenentValues(String nombre, String telefono){
        ContentValues valores = new ContentValues();
        valores.put(CN_Nombre, nombre);
        valores.put(CN_PHONE, telefono);

        return valores;
    }


    public void insertar (String nombre, String telefono){

        //bd.insert(TABLA, NullColumnHack, ContentValues);
        db.insert(TABLE_NAME, null, generarContenentValues(nombre, telefono));
    }

    public void insertar2(String nombre, String telefono){
        //INSERT INTO Paciente VALUES (null, 'paco', 36811523)
        db.execSQL("insert into " + TABLE_NAME + " values (null, '" + nombre + "'," + telefono + ")");
    }

    public void eliminar (String nombre){
        //bd.delete(Tabla, Clausula Where, Argumentos where)
        db.delete(TABLE_NAME, CN_Nombre + "=?", new String[]{nombre});
    }

    public void eliminarMultiple (String nom1, String nom2){
        db.delete(TABLE_NAME,CN_Nombre +"IN (?,?)",new String[]{nom1, nom2});
    }

    public void modificarTelefono (String nomre, String nuevoTelefono){
        //*bd.update(TABLA, COntentValues, CLausula Where, Arguentos  where)
        db.update(TABLE_NAME, generarContenentValues(nomre, nuevoTelefono), CN_Nombre + "=?", new String[]{nomre});
    }

    public Cursor cargarCursorPaciente(){
        String [] columnas = new String[]{CN_ID, CN_Nombre, CN_PHONE};
        //query (String table, String [] columns, String selection, String [] slectionArgs, String groupBy, String having, String orderby)
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);

       /* try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    public Cursor buscarPaciente(String nombre){
        String[] columnas = new String[]{CN_ID, CN_Nombre, CN_PHONE};
        return db.query(TABLE_NAME,columnas,CN_Nombre +"=?",new String[]{nombre},null,null,null);

    }

}
