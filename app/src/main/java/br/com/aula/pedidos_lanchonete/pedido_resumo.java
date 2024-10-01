package br.com.aula.pedidos_lanchonete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class pedido_resumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido_resumo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recebe os dados do Intent
        Intent intent = getIntent();
        String nomeCliente = intent.getStringExtra("NOME_CLIENTE");
        ArrayList<String> produtosSelecionados = intent.getStringArrayListExtra("PRODUTOS_SELECIONADOS");

        // Formata a lista de produtos selecionados
        StringBuilder produtosFormatados = new StringBuilder();
        if (produtosSelecionados != null) { // Verifica se a lista não é nula
            for (String produto : produtosSelecionados) {
                produtosFormatados.append(produto).append("\n");
            }
        }

        // Atualiza o TextView com os dados recebidos
        TextView resumoPedido = findViewById(R.id.textViewDetalhesPedido);
        resumoPedido.setText("Nome: " + nomeCliente + "\n\nProdutos Selecionados:\n" + produtosFormatados.toString());

        // Configurando o botão de voltar ao início
        Button btnVoltarInicio = findViewById(R.id.btnVoltarInicio);
        btnVoltarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarIntent = new Intent(pedido_resumo.this, MainActivity.class); // Altere MainActivity para o nome da sua Activity inicial
                voltarIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpa a pilha de atividades
                startActivity(voltarIntent);
                finish(); // Finaliza a atividade atual
            }
        });
    }
}
