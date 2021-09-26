package br.com.alura.myapplication;

import android.widget.EditText;
import android.widget.RatingBar;

import org.w3c.dom.Text;

import br.com.alura.myapplication.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoIdade;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final RatingBar campoNota;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = activity.findViewById(R.id.formulario_nome);
        campoIdade = activity.findViewById(R.id.formulario_idade);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoNota = activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno PegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setIdade(Integer.parseInt(String.valueOf(campoIdade.getText())));
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota((double) campoNota.getProgress());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoIdade.setText(String.valueOf(aluno.getIdade()));
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
