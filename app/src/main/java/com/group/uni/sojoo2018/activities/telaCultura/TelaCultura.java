package com.group.uni.sojoo2018.activities.telaCultura;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group.uni.sojoo2018.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class TelaCultura extends AppCompatActivity {


////
////    "<html><body>"
////            + "<p align=\"justify\">"
////            + getString(R.string.lorem_ipsum)
////               + "</p> "
////                       + "</body></html>";
//
//    String textd = "<html><body>"
//            + "<p align=\"justify\">"
//            +
//            "“Olha Pro Céu, Meu Amor”…\n" +
//            "\n" +
//            "Por: Samy Araújo\n" +
//            "\n" +
//            "   Campina Grande dá início esta semana a um dos maiores eventos populares do Brasil. O “Maior São João do Mundo” já é um evento consolidado no calendário de eventos turísticos do País, e este ano, acontece de 03 de Junho a 03 de Julho.Serão trinta dias de muito forró no espaço conhecido como “Parque do Povo” e chamado carinhosamente por muitos de “Quartel General do Forró”, uma área de aproximadamente 42.500 metros quadrados que pretende atrair cerca de 2 milhões de turistas e visitantes durante o evento.\n" +
//            "\n" +
//            "   A primeira festa junina no local conhecido em seus primórdios como, Palhoção do Forró, aconteceu em 1983 com apresentações de quadrilhas, casamento matuto e desfile de carroças. Não demorou muito e em 1984 o evento foi inscrito e registrado no calendário da EMBRATUR(Empresa Brasileira de Turismo) vinculada ao Ministério do Turismo. Em 1985, foi inaugurada a casa de show, Forrock, com destaque para as apresentações de Luiz Gonzaga, Dominguinhos e Elba Ramalho. Em 1986 foi inaugurado o então chamado, Parque do Povo, com destaque para a Pirâmide do Forró batizada como Forródromo. O evento ganhou projeções nacionais em 1992, ano que foi gravado o clipe da cantora, Elba Ramalho, para o programa, Fantástico, da Rede Globo de Televisão. Outro destaque foi a participação da quadrilha Virgens da Seca em um quadro do programa da apresentadora Regina Casé.\n" +
//            "\n" +
//            "   Ainda nos anos 90, o Parque do Povo recebeu a infraestrutura básica com a ampliação do Arraial Hilton Motta, que possibilitou a criação da cidade cenográfica com réplicas de prédios antigos da cidade como a Catedral de Nossa Senhora da Conceição, além da Vila Nova da Rainha onde pode ser encontrado artesanato local, cantador de viola, cordelistas, repentistas.\n" +
//            "\n" +
//            "   A programação diversificada do São João de Campina valoriza os artistas regionais com atrações como, Biliu de Campina, Flávio José, Pinto do Acordeon, os Três do Nordeste, além de bandas de renome nacional como Wesley Safadão, Magníficos,Cavalo de Pau, dentre outras. A programação religiosa e outro grande atrativo do evento e para este ano já estão confirmadas as apresentações do Padre Fábio de Melo e Reginaldo Mazzonti.\n" +
//            "\n" +
//            "   Além dos shows que acontecerão no palco principal, os turistas podem se divertir em umas das três ilhas de forró distribuídas no parque do povo. Lá o chamado forró pé de serra pode ser prestigiado ao som da sanfona, zabumba e triângulo e só tem acesso ao espaço quem estiver acompanhado, portanto é bom arrumar logo uma boa pareia e arrastar o pé.Para quem deseja provar o gostinho da nossa culinária regional são mais de 160 barracas e 90 quiosques que oferecem comidas nordestinas como tapiocas, canjica, pamonhas, buchada de bode, etc. Se o frio apertar, temos famosos caldinhos, que dá água na boca só de pensar." +
//            "</p> "
//            + "</body></html>";
//
//
//    String textd2 = "<html><body>"
//            + "<p align=\"justify\">" +
//            "   Mas, não é só isso! O São João de Campina é descentralizado e durante o dia podem ser encontrados outros atrativos como, Sítio São João, com um cenário rústico nordestino composto por casa de farinha, moinho de cana, bodega difusora e tudo isso acompanhado de muito forró.Para quem quem quer fazer uma viagem inesquecível a dica e curtir o passeio da, Locomotiva Forrozeira, um trêm que sai lotado de turistas com destino ao distrito de Galante em um percurso de 13 quilômetros, cerca de uma hora e meia de pura animação.Após a viagem os turistas são recepcionados com mais forró que ocorre o dia todo em Galante e se tiver pique, podem escolher retornar a Campina no trêm. Para quem aprecia o bom artesanato temos o Salão de Artesanato da Paraíba e a Vila do Artesão, dois espaços que reúnem todas as tipologias do artesanato da Paraíba. As casas de Shows da cidade são outras atrações que reúnem um grande número de forrozeiros que vão prestigiar as apresentações de artistas vindos de todos os estados do Brasil. Ainda temos, o São João nos bairros e distritos que atraem um grande número de moradores que vão prestigiar as apresentações das tradicionais quadrilhas de bairro e o forró pé-de -erra. Com tanta coisa assim, o jeito é se programar e vir curtir os 30 dias do Maior São João do Mundo!"
//            + "</p> "
//            + "</body></html>";
//    String titulo_postagem = "<html><body>"
//            + "<p align=\"center\">"
//            + "Como surgiu o Maior São João do Mundo?"
//            + "</p> "
//            + "</body></html>";

    String textd = "“Olha Pro Céu, Meu Amor”…\n" +
            "\n" +
            "Por: Samy Araújo\n" +
            "\n" +
            "   Campina Grande dá início esta semana a um dos maiores eventos populares do Brasil. O “Maior São João do Mundo” já é um evento consolidado no calendário de eventos turísticos do País, e este ano, acontece de 03 de Junho a 03 de Julho.Serão trinta dias de muito forró no espaço conhecido como “Parque do Povo” e chamado carinhosamente por muitos de “Quartel General do Forró”, uma área de aproximadamente 42.500 metros quadrados que pretende atrair cerca de 2 milhões de turistas e visitantes durante o evento.\n" +
            "\n" +
            "   A primeira festa junina no local conhecido em seus primórdios como, Palhoção do Forró, aconteceu em 1983 com apresentações de quadrilhas, casamento matuto e desfile de carroças. Não demorou muito e em 1984 o evento foi inscrito e registrado no calendário da EMBRATUR(Empresa Brasileira de Turismo) vinculada ao Ministério do Turismo. Em 1985, foi inaugurada a casa de show, Forrock, com destaque para as apresentações de Luiz Gonzaga, Dominguinhos e Elba Ramalho. Em 1986 foi inaugurado o então chamado, Parque do Povo, com destaque para a Pirâmide do Forró batizada como Forródromo. O evento ganhou projeções nacionais em 1992, ano que foi gravado o clipe da cantora, Elba Ramalho, para o programa, Fantástico, da Rede Globo de Televisão. Outro destaque foi a participação da quadrilha Virgens da Seca em um quadro do programa da apresentadora Regina Casé.\n" +
            "\n" +
            "   Ainda nos anos 90, o Parque do Povo recebeu a infraestrutura básica com a ampliação do Arraial Hilton Motta, que possibilitou a criação da cidade cenográfica com réplicas de prédios antigos da cidade como a Catedral de Nossa Senhora da Conceição, além da Vila Nova da Rainha onde pode ser encontrado artesanato local, cantador de viola, cordelistas, repentistas.\n" +
            "\n" +
            "   A programação diversificada do São João de Campina valoriza os artistas regionais com atrações como, Biliu de Campina, Flávio José, Pinto do Acordeon, os Três do Nordeste, além de bandas de renome nacional como Wesley Safadão, Magníficos,Cavalo de Pau, dentre outras. A programação religiosa e outro grande atrativo do evento e para este ano já estão confirmadas as apresentações do Padre Fábio de Melo e Reginaldo Mazzonti.\n" +
            "\n" +
            "   Além dos shows que acontecerão no palco principal, os turistas podem se divertir em umas das três ilhas de forró distribuídas no parque do povo. Lá o chamado forró pé de serra pode ser prestigiado ao som da sanfona, zabumba e triângulo e só tem acesso ao espaço quem estiver acompanhado, portanto é bom arrumar logo uma boa pareia e arrastar o pé.Para quem deseja provar o gostinho da nossa culinária regional são mais de 160 barracas e 90 quiosques que oferecem comidas nordestinas como tapiocas, canjica, pamonhas, buchada de bode, etc. Se o frio apertar, temos famosos caldinhos, que dá água na boca só de pensar.";
    String textd2 = "   Mas, não é só isso! O São João de Campina é descentralizado e durante o dia podem ser encontrados outros atrativos como, Sítio São João, com um cenário rústico nordestino composto por casa de farinha, moinho de cana, bodega difusora e tudo isso acompanhado de muito forró.Para quem quem quer fazer uma viagem inesquecível a dica e curtir o passeio da, Locomotiva Forrozeira, um trêm que sai lotado de turistas com destino ao distrito de Galante em um percurso de 13 quilômetros, cerca de uma hora e meia de pura animação.Após a viagem os turistas são recepcionados com mais forró que ocorre o dia todo em Galante e se tiver pique, podem escolher retornar a Campina no trêm. Para quem aprecia o bom artesanato temos o Salão de Artesanato da Paraíba e a Vila do Artesão, dois espaços que reúnem todas as tipologias do artesanato da Paraíba. As casas de Shows da cidade são outras atrações que reúnem um grande número de forrozeiros que vão prestigiar as apresentações de artistas vindos de todos os estados do Brasil. Ainda temos, o São João nos bairros e distritos que atraem um grande número de moradores que vão prestigiar as apresentações das tradicionais quadrilhas de bairro e o forró pé-de -erra. Com tanta coisa assim, o jeito é se programar e vir curtir os 30 dias do Maior São João do Mundo!";

    String titulo_postagem = "Como surgiu o Maior São João do Mundo?";

    BitmapDrawable bitmap;

    TextView corpoText1;
    TextView corpoText2;
    TextView titulo;
    ImageView imagem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cultura);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setTitle(titulo_postagem);
        //   toolbar.setTitleTextAppearance(this, R.style.textTitle);

        toolbar.setTitleTextColor(getResources().getColor(R.color.laranja));
        //toolbar.setBackground(getDrawable(R.drawable.fabio));
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout a = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        a.setBackground(getDrawable(R.drawable.sj));
//        a.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
//        a.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
//
//        a.setForegroundTintMode(PorterDuff.Mode.SRC_ATOP);


        a.setExpandedTitleTextAppearance(R.style.textTitle);
        a.setExpandedTitleColor(getResources().getColor(R.color.transparente));
        //a.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        //get the bitmap of the drawable image we are using as background
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fabio);
        //using palette, change the color of collapsing toolbar layout
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int mutedColor = palette.getMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.laranja));
                int mutedDarkColor = palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.laranja));
                int vibrantColor = palette.getVibrantColor(ContextCompat.getColor(getApplicationContext(), R.color.laranja));
                a.setContentScrimColor(getResources().getColor(R.color.laranja));
                a.setStatusBarScrimColor(getResources().getColor(R.color.laranja));

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent i = new Intent(TelaCultura.this, ImagemExpandida.class);
                i.putExtra("filepath", R.drawable.sj);
                startActivity(i);
            }
        });
        titulo = findViewById(R.id.titulo_cultura);
        corpoText1 = findViewById(R.id.text_um_publicacao);
        corpoText2 = findViewById(R.id.text_dois_publicacao);
        imagem1 = findViewById(R.id.image_um_publicacao);


        // Create an object for subclass of AsyncTask
        //  GetXMLTask task = new GetXMLTask();
        // Execute the task
        // task.execute(new String[]{"https://www.wscom.com.br/arqs/noticias/original/201704170828570000004798.jpg"});


        //Postagem


        titulo.setText(titulo_postagem);
        corpoText1.setText(textd);
        corpoText2.setText(textd2);
        // titulo.loadData(titulo_postagem, "text/html", "iso-8859-1");
        // corpoText1.loadData(textd, "text/html", "iso-8859-1");
        //corpoText2.loadData(textd2, "text/html", "iso-8859-1");


    }


    class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imagem1.setImageBitmap(result);
        }

        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                Toast.makeText(TelaCultura.this, "Sem conexão!", Toast.LENGTH_SHORT);
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
}




