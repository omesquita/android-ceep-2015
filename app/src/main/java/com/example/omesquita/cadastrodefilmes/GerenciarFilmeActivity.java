package com.example.omesquita.cadastrodefilmes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.omesquita.cadastrodefilmes.DAO.FilmeDAO;
import com.example.omesquita.cadastrodefilmes.model.Filme;

import java.util.ArrayList;

public class GerenciarFilmeActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtEstudio;
    private Spinner spGenero;
    private RadioGroup rgClassificacao;
    private RadioButton rbLivre;
    private RadioButton rb10Anos;
    private RadioButton rb12Anos;
    private RadioButton rb14Anos;
    private RadioButton rb16Anos;
    private RadioButton rb18Anos;
    private RatingBar ratBarAvaliacao;
    private FilmeDAO dao;
    private Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_filme);
        initComponentes();
        carregarGeneros();

        filme = (Filme) getIntent().getSerializableExtra("filme");
        if(filme != null){
            preencheCampos();
        }
        dao = new FilmeDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gerenciar_filme, menu);

        if(filme != null){
            menu.setGroupVisible(R.id.group_editar, true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_salvar:
                carregaFilmes();
                dao.inserir(filme);
                Toast.makeText(this, getString(R.string.msg_salvo_com_sucesso), Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.action_excluir:
                dao.excluir(filme);
                Toast.makeText(this, getString(R.string.msg_excluido_com_sucesso), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initComponentes(){
        edtTitulo = (EditText) findViewById(R.id.edtTituloFilme);
        edtEstudio = (EditText) findViewById(R.id.edtEstudioFilme);
        spGenero = (Spinner) findViewById(R.id.spGenero);
        rgClassificacao = (RadioGroup) findViewById(R.id.rgClassificacao);
        rbLivre = (RadioButton) findViewById(R.id.rbLivre);
        rb10Anos = (RadioButton) findViewById(R.id.rb10anos);
        rb12Anos = (RadioButton) findViewById(R.id.rb12anos);
        rb14Anos = (RadioButton) findViewById(R.id.rb14anos);
        rb16Anos = (RadioButton) findViewById(R.id.rb16anos);
        rb18Anos = (RadioButton) findViewById(R.id.rb18anos);
        ratBarAvaliacao = (RatingBar) findViewById(R.id.ratBarAvaliacao);;
    }

    private void carregarGeneros(){
        ArrayList<String> generos =  new ArrayList<>();
        generos.add("Ação");
        generos.add("Aventura");
        generos.add("Comédia");
        generos.add("Documentário");
        generos.add("Drama");
        generos.add("Infantil");
        generos.add("Suspense");
        generos.add("Terror");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenero.setAdapter(adapter);
    }

    private void carregaFilmes(){
        filme = new Filme();
        filme.setTitulo(edtTitulo.getText().toString());
        filme.setEstudio(edtEstudio.getText().toString());
        filme.setGenero(spGenero.getSelectedItemPosition());
        spGenero.setSelection(filme.getGenero());
        filme.setAvaliacao(ratBarAvaliacao.getRating());

        switch (rgClassificacao.getCheckedRadioButtonId()){
            case R.id.rbLivre :
                filme.setClassficacao("Livre");
                break;
            case R.id.rb10anos :
                filme.setClassficacao("10Anos");
                break;
            case R.id.rb12anos :
                filme.setClassficacao("12Anos");;
                break;
            case R.id.rb14anos :
                filme.setClassficacao("14Anos");
                break;
            case R.id.rb16anos :
                filme.setClassficacao("16Anos");
                break;
            case R.id.rb18anos :
                filme.setClassficacao("18Anos");
                break;
        }
    }

    private void preencheCampos(){
        edtTitulo.setText(filme.getTitulo());
        edtEstudio.setText(filme.getEstudio());
        spGenero.setSelection(filme.getGenero());
        switch (filme.getClassficacao()){
            case "Livre" :
                rbLivre.setChecked(true);
                break;
            case "10Anos" :
                rb10Anos.setChecked(true);
                break;
            case "12Anos" :
                rb12Anos.setChecked(true);
                break;
            case "14Anos" :
                rb14Anos.setChecked(true);
                break;
            case "16Anos" :
                rb16Anos.setChecked(true);
                break;
            case "18Anos" :
                rb18Anos.setChecked(true);
                break;
        }
        ratBarAvaliacao.setRating(filme.getAvaliacao());
    }
}
