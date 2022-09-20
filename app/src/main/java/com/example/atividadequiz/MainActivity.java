package com.example.atividadequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalquestoesTextView;
    TextView questaoTextView;
    Button ResA,ResB,ResC,ResD;
    Button submitBtn;

    int score= 0;
    int totalquestao = PerguntasRespostas.questoes.length;
    int acertosquestaoIndex = 0;
    String RespostaSelecionada = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalquestoesTextView = findViewById(R.id.totalquestoes);
        questaoTextView = findViewById(R.id.questao);
        ResA = findViewById(R.id.resA);
        ResB = findViewById(R.id.resB);
        ResC = findViewById(R.id.resC);
        ResD = findViewById(R.id.resD);
        submitBtn = findViewById(R.id.btn_submit);

        ResA.setOnClickListener(this);
        ResB.setOnClickListener(this);
        ResC.setOnClickListener(this);
        ResD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalquestoesTextView.setText("Total de questões : "+totalquestao);

        carregarNovaPergunta();
    }

    @Override
    public void onClick(View view) {

        ResA.setBackgroundColor(Color.WHITE);
        ResB.setBackgroundColor(Color.WHITE);
        ResC.setBackgroundColor(Color.WHITE);
        ResD.setBackgroundColor(Color.WHITE);


        Button cliquebotao = (Button) view;
        if (cliquebotao.getId()==R.id.btn_submit){
            if(RespostaSelecionada.equals(PerguntasRespostas.resposta[acertosquestaoIndex])){
                score++;
            }
            acertosquestaoIndex++;
            carregarNovaPergunta();



        }else {
            //escolhas clique botao
            RespostaSelecionada = cliquebotao.getText().toString();
            cliquebotao.setBackgroundColor(Color.GREEN);
        }
    }

    void carregarNovaPergunta(){

        if (acertosquestaoIndex == totalquestao){
            finishQuiz();
            return;
        }

        questaoTextView.setText(PerguntasRespostas.questoes[acertosquestaoIndex]);
        ResA.setText(PerguntasRespostas.escolhas[acertosquestaoIndex][0]);
        ResB.setText(PerguntasRespostas.escolhas[acertosquestaoIndex][1]);
        ResC.setText(PerguntasRespostas.escolhas[acertosquestaoIndex][2]);
        ResD.setText(PerguntasRespostas.escolhas[acertosquestaoIndex][3]);
    }

    void finishQuiz(){
        String resultado = "";
        if(score > totalquestao*0.90){
            resultado = "Parabéns, você acertou todas as questões!";
        }else{
            resultado = "Boa, mas não acertou todas!";
        }
        if(score <= totalquestao*0){
            resultado = "Seu burro, você errou tudo!";
        }
        new AlertDialog.Builder(this)
                .setTitle(resultado)
                .setMessage("Você acertou " + score + " de " + totalquestao)
                .setPositiveButton("Recomeçar",(dialogInterface, i) -> ReiniciarQuiz())
                .setCancelable(false)
                .show();

    }

    void ReiniciarQuiz(){
        score = 0;
        acertosquestaoIndex= 0;
        carregarNovaPergunta();
    }
}