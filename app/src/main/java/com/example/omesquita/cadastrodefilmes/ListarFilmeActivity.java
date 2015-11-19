package com.example.omesquita.cadastrodefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omesquita.cadastrodefilmes.DAO.FilmeDAO;
import com.example.omesquita.cadastrodefilmes.model.Filme;

import java.util.ArrayList;

public class ListarFilmeActivity extends AppCompatActivity {

    private ListView lvFilmes;
    private FilmeDAO filmeDAO;
    private ArrayList<Filme> filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_filme);
        lvFilmes = (ListView) findViewById(R.id.lvFilmes);
        filmeDAO = new FilmeDAO(this);

        lvFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Filme filme = filmes.get(position);
                Intent itAlterarFilme = new Intent(getBaseContext(), GerenciarFilmeActivity.class);
                itAlterarFilme.putExtra("filme", filme);
                startActivity(itAlterarFilme);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_novo:
                Intent intent = new Intent(getBaseContext(), GerenciarFilmeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_sobre :
                Toast.makeText(this, getString(R.string.msg_sobre), Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        filmes = (ArrayList<Filme>) filmeDAO.getFilmes();
        ArrayAdapter<Filme> adapter = new ArrayAdapter<Filme>(
                this, android.R.layout.simple_list_item_1, filmes);
        lvFilmes.setAdapter(adapter);
        super.onResume();
    }
}
