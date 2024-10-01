package br.com.aula.pedidos_lanchonete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class pedido extends AppCompatActivity {

    private ArrayList<String> produtosSelecionados = new ArrayList<>(); // Lista para armazenar os produtos selecionados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lista de produtos
        String[] produtos = {"Pizza (R$ 40,00)", "Coxinha (R$ 8,00)", "Pastel (R$ 10,00)", "Hamburguer (R$ 25,00)", "Refrigerante (R$ 5,00)", "Batata Frita (R$ 12,00)"};

        // Configurando o ListView para múltiplas seleções
        ListView listaProdutos = findViewById(R.id.lista_produtos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, produtos);
        listaProdutos.setAdapter(adapter);
        listaProdutos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // Permite múltiplas seleções

        // Adicionando o comportamento ao clicar em um item da lista
        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String produtoSelecionado = produtos[position];

                // Adiciona ou remove o produto da lista de selecionados
                if (produtosSelecionados.contains(produtoSelecionado)) {
                    produtosSelecionados.remove(produtoSelecionado); // Remove se já estiver selecionado
                } else {
                    produtosSelecionados.add(produtoSelecionado); // Adiciona se não estiver selecionado
                }
            }
        });

        EditText editTextNome = findViewById(R.id.editTextNome);
        Button btnConfirmar = findViewById(R.id.btnconfirmar);

        btnConfirmar.setOnClickListener(v -> {
            String nomeCliente = editTextNome.getText().toString().trim();

            // Verifica se o nome do cliente ou produtos selecionados estão vazios
            if (nomeCliente.isEmpty() || produtosSelecionados.isEmpty()) {
                Toast.makeText(pedido.this, "Por favor, preencha o nome e selecione pelo menos um produto", Toast.LENGTH_SHORT).show();
                return;
            }

            // Passando o nome do cliente e os produtos selecionados para a próxima Activity
            Intent resumoIntent = new Intent(pedido.this, pedido_resumo.class);
            resumoIntent.putExtra("NOME_CLIENTE", nomeCliente);
            resumoIntent.putStringArrayListExtra("PRODUTOS_SELECIONADOS", produtosSelecionados);
            startActivity(resumoIntent);
        });
    }
}
