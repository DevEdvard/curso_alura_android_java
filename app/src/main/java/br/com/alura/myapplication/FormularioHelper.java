package br.com.alura.myapplication;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.alura.myapplication.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoIdade;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){
        campoNome = activity.findViewById(R.id.formulario_nome);
        campoIdade = activity.findViewById(R.id.formulario_idade);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoNota = activity.findViewById(R.id.formulario_nota);
    }

    public Aluno PegaAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(campoNome.getText().toString());
        aluno.setIdade(Integer.parseInt(String.valueOf(campoIdade.getText())));
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota((double) campoNota.getProgress());
        return aluno;
    }
}
