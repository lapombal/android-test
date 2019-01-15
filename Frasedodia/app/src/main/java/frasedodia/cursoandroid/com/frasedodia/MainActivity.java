package frasedodia.cursoandroid.com.frasedodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;
    private int lastNumAleatorio;

    private String[] frases = {
            "Se você traças metas absurdamente altas e falhar, seu fracasso será muito melhor que o sucesso de todos.",
            "O sucesso normalmente vem para quem está ocupado demais para procurar por ele.",
            "Se você não está disposto a arriscar, esteja disposto a uma vida comum.",
            "As boas amizades são como um bom vinho. Melhoram com o tempo.",
            "Você é uma dessas pessoas 'que vai chegar lá na vida'.",
            "Os seus talentos serão reconhecidos e recompensados à altura.",
            "Uma boa época para terminar tarefas antigas.",
            "O urso polar é canhoto!",
            "Hipótese é aquilo que não é, mas suponhamos que seja para saber como seria se fosse.",
            "Sou feliz por ser como sou, por ter o que tenho, mas amanhã quero ser ainda mais e para isso luto diariamente.",
            "Nada melhor para a alma que treinar a gratidão e diariamente agradecer pelas bênçãos que receber.",
            "Amar a vida é amar seus amigos, pois sem eles nada mais faz sentido no seu dia a dia.",
            "Nem toda mudança importante acontece de repente e faz barulho, algumas são silenciosas e vão se fazendo no dia a dia.",
            "Deseje uma boa semana a todos aqueles que diariamente persistem em lutar pelos seus objetivos!",
            "Viver não é fácil, mas com coragem para suportar os desafios do dia a dia tudo se torna mais simples.",
            "Porque a vida é mágica, aproveite os milagres do dia a dia.",
            "Gratidão não é pagamento, mas um reconhecimento que se demonstra no dia a dia.",
            "Felicidade não se compra, mas se conquista diariamente com pensamentos positivos!",
            "A vida somos nós que a construímos diariamente por isso lute e nunca desista de criar a que sempre sonhou.",
            "A fé é conquistada na tranquilidade do dia a dia e é capaz de maravilhas inexplicáveis!",
            "Mais importante que mostrar ao mundo que temos valores e princípios é lutar por eles, e viver diariamente sua verdade.",
            "A alegria está sempre à nossa disposição, basta que a deixemos entrar na nossa vida e seremos felizes no dia a dia.",
            "Amor incondicional e sensações memoráveis fazem parte do dia a dia de uma mãe com seus filhos!",
            "É tempo de esquecer os problemas, jogar fora as frustrações do dia a dia e desfrutar do Carnaval em grande estilo!",
            "Alimente seus sonhos diariamente, pois são eles que dão verdadeiro sentido à sua vida.",
            "Sou apenas um pequeno planeta que se perde diariamente em todo o seu universo.",
            "Os maiores gestos de coragem não estão nos livros, mas nos atos que praticamos no dia a dia.",
            "Novas amizades serão sempre bem-vindas para darem cor e alegria ao meu dia a dia.",
            "Obrigado aos professores que lutam diariamente para que todos os seus alunos tenham a melhor educação possível!",
            "Poucas coisas me tiram do sério, pois deixei de dar importância às trivialidades do dia a dia.",
            "Vivo sob a proteção de Deus e a Ele agradeço toda força e segurança para enfrentar as dificuldades do dia a dia.",
            "Educação é adquirida no dia a dia para que o amanhã seja sempre um dia melhor.",
            "Vivo de consciência tranquila, pois me empenho diariamente pela missão de espalhar mais amor pelo mundo.",
            "Faça do mundo o seu território e da vida o seu passatempo favorito e desfrute de todos os instantes do dia a dia.",
            "Novas amizades trazem ar fresco para o dia a dia de qualquer pessoa.",
            "A razão é importante, mas nunca será tão emocionante como os sentimentos do dia a dia.",
            "No cansaço do dia a dia uma nova amizade representa ar fresco e mais alegria.",
            "Contra as angústias do dia a dia nada como uma noite de vida loka!",
            "A serenidade com que uma folha voa no outono é a mesma com que devemos encarar o dia a dia.",
            "Enfrente os problemas e leve a melhor!",
            "Nem todos os dias são bons, mas há algo de bom em cada dia!",
            "Para chegar ao topo, o que importa é começar!",
            "Suba o primerio degrau com fé. Não é necessário que você veja toda a escada. Apenas dê o primeiro passo.",
            "De nada adianta ter sonhos, se você não se empenhar em correr atrás",
            "Toda manhã você tem duas escolhas: continuar dormindo com seus sonhos, ou acordar e persegui-los!",
            "Não deixe nada nem ninguém estragar o seu bom humor",
            "A sua irritação não solucionará problema algum. O seu mau humor não modifica a vida. Não estrague o seu dia.",
            "Ontem já passou, é hora de planejar o futuro!",
            "Vamos inventar o amanhã, no lugar de se preocupar com o que aconteceu ontem.",
            "Você pode não ter o melhor do mundo, mas tem muito pelo que agradecer!",
            "As pessoas mais felizes não têm as melhores coisas. Elas sabem fazer o melhor das oportunidades que aparecem em seus caminhos.",
            "Não deixe sua felicidade nas mãos de ninguém!",
            "Ninguém além de você está no controle de sua felicidade. Portanto, ajuste as velas e corrija o rumo.",
            "Nunca é tarde para fazer o que você realmente quer fazer",
            "Cada segundo é tempo para mudar tudo para sempre.",
            "Mais palavras para quê?",
            "Não importa a cor do céu. Quem faz o dia lindo é você!",
            "Nunca duvide que você é especial!",
            "A beleza começa quando você decide ser você mesma.",
            "Tenha um objetivo na vida e lute sempre para alcançá-lo!",
            "Se você quer viver uma vida feliz, amarre-se a um objetivo, não às pessoas ou aos objetos.",
            "Só existem dois dias no ano que nada pode ser feito. Um se chama ONTEM e o outro se chama AMANHÃ, portanto HOJE é o dia certo para amar, acreditar, fazer e principalmente viver.",
            "É muito melhor perceber um defeito em sim mesmo, do que dezenas no outro, pois o seu defeito você pode mudar.",
            "Neste mundo não existe verdade universal. Uma mesma verdade pode apresentar diferentes fisionomias. Tudo depende das decifrações feitas através de nossos prismas intelectuais, filosóficos, culturais e religiosos.",
            "As criaturas que habitam esta terra em que vivemos, sejam elas seres humanos ou animais, estão aqui para contribuir, cada uma com sua maneira peculiar, para a beleza e a prosperidade do mundo.",
            "A raiva não pode ser superada pela raiva. Se uma pessoa demonstra raiva de você, e você mostrar raiva em troca, o resultado é um desastre.",
            "Se a criança não receber a devida atenção, em geral, quando adulta, tem dificuldade de amar seus semelhantes.",
            "O que é meu inimigo? Eu mesmo. Minha ignorância, meu apego, meus ódios. Aí está meu inimigo... ",
            "Lar é onde você se sente em casa e é bem tratado.",
            "É engraçado como depositamos tanta confiança e tanto sentimento nas pessoas. Em pessoas que achávamos conhecer, mas, que no fim, só mostraram ser iguais a todos. E por esperar demais, sonhar demais, criar expectativas demais, sempre acabamos nos decepcionando e nos machucando cada vez mais.",
            "Se você quer transformar o mundo, experimente primeiro promover o seu aperfeiçoamento pessoal e realizar inovações no seu próprio interior. Estas atitudes se refletirão em mudanças positivas no seu ambiente familiar. Deste ponto em diante, as mudanças se expandirão em proporções cada vez maiores. Tudo o que fazemos produz efeito, causa algum impacto.",
            "Um 'não' dito com convicção é melhor e mais importante que um 'sim' dito meramente para agradar, ou, pior ainda, para evitar complicações.",
            "Assim como uma gota de veneno compromete um balde inteiro, também a mentira por menor que seja estraga toda a nossa vida.",
            "O fraco jamais perdoa: o perdão é uma das características do forte.",
            "A alegria está na luta, na tentativa, no sofrimento e não na vitória propriamente dita.",
            "Olho por olho e o mundo acabará cego.",
            "Você nunca sabe que resultados virão da sua ação. Mas se você não fizer nada não existirão resultados.",
            "Só quando se veem os próprios erros através de uma lente de aumento, e se faz exatamente o contrário com os outros é que se pode chegar à justa avaliação de uns e de outros.",
            "Cada dia a natureza produz o suficiente para nossa carência. Se cada um tomasse o que lhe fosse necessário, não havia pobreza no mundo e ninguém morreria de fome.",
            "As religiões são caminhos diferentes convergindo para o mesmo ponto. Que importância faz se seguimos por caminhos diferentes, desde que alcancemos o mesmo objetivo?",
            "Aprendi através da experiência amarga a suprema lição: controlar minha ira e torná-la como o calor que é convertido em energia. Nossa ira controlada pode ser convertida numa força capaz de mover o mundo.",
            "Para não causar ferimentos é necessário evitar atitudes rudes.",
            "Numa busca, a insistência não é suficiente"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseID);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomico = new Random();

                int numeroAleatorio = randomico.nextInt(frases.length);

                while (numeroAleatorio == lastNumAleatorio){
                    numeroAleatorio = randomico.nextInt(frases.length);
                }

                lastNumAleatorio = numeroAleatorio;

                textoNovaFrase.setText(frases[numeroAleatorio]);
            }
        });
    }
}
