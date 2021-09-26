package br.com.alura.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import br.com.alura.myapplication.dao.AlunoDao;
import br.com.alura.myapplication.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAlunos = findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent intentAlterarAluno = new Intent(MainActivity.this, FormularioActivity.class);
                intentAlterarAluno.putExtra("aluno", aluno);
                startActivity(intentAlterarAluno);
            }
        });

        //Click Longo
//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
//                Toast.makeText(MainActivity.this, "Longo", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        Button novoAluno = findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intentFormulario);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    private void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();


        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                AlunoDao dao = new AlunoDao(MainActivity.this);
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                dao.deleta(aluno);
                carregaLista();
                Toast.makeText(MainActivity.this, "Aluno " + aluno.getNome() + " Deletado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}