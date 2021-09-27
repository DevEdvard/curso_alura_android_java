package br.com.alura.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.myapplication.modelo.Aluno;

public class AlunoDao extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "Agenda", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT, idade INTEGER, endereco TEXT, telefone TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesAlunos(aluno);

        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues getContentValuesAlunos(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("idade", aluno.getIdade());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getReadableDatabase();

        String [] params = {String.valueOf(aluno.getId())};
        db.delete("Alunos", "id = ?", params);
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getInt(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setIdade(c.getInt(c.getColumnIndex("idade")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void alteraAluno(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesAlunos(aluno);
        String [] params = {String.valueOf(aluno.getId())};
        db.update("Alunos", dados, "id = ?", params);
    }
}
