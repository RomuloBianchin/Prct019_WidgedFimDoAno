package com.example.prct019_widgetfimdoano;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;

public class NewAppWidget extends AppWidgetProvider{

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Obtendo a data atual do sistema operacional
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // Obtendo o ano.
        int mMonth = c.get(Calendar.MONTH); //Obtendo o mês.
        int mDay = c.get(Calendar.DAY_OF_MONTH); // Obtendo o dia.

        // Determinando a data que queremos realizar a comparação, nesse caso o fim do ano, ou seja, o último dia do mês
        // de Dezembro do ano atual.
        Calendar fimDeAno = Calendar.getInstance();
        fimDeAno.set(mYear+1,0,1);

        // Calculareos abaixo a diferença entre o dia atual e a data do fim do ano, mas para isso,
        // precisamos representar a data em milisssegundos, para que seja possível fazer o cálculo.
        long milis1 = c.getTimeInMillis(); // Convertendo a data atual em milissegundos, e armazenando em milis1.
        long milis2 = fimDeAno.getTimeInMillis(); // Convertendo a data do fim do ano atual em milissegundos, e armazenando em milis2.

        // Calculando a diferença entre as duas datas.
        long diff = milis2 - milis1;

        // Convertendo a diferença para dias, e armazendo a resposta na variável diffDays.
        long diffDays = diff / (24 * 60 * 60 * 1000);


        //Ciclo for para percorrer todos os widgets que temoos desse tipo, ou seja, toda vez que for arrastado um
        // novo para o ecrã.
        for(int i = 0; i < appWidgetIds.length; i++){
            int currentWidgetsId = appWidgetIds[i];

            //Associamos o remoteView ao nosso layout widget, esse que será o Layout exibido ao usuário.
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);

            // Colocamos o valor pretendido na caixa de texto textView2 do Layout.
            views.setTextViewText(R.id.textView2,""+diffDays+ " dias");
            // Atribuíndo o atr+ibuto cor ao texto.
            views.setTextColor(R.id.textView2, Color.parseColor("#FF0000"));
            // Atualizando o widget.
            appWidgetManager.updateAppWidget(currentWidgetsId,views);

            //Objeto Toast, esse objeto será responsável por exibir a string abaixo toda vez que o widget
            // for selecionado para ser inserido no ecrã.
            Toast.makeText(context,"widget added",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the last widget is enabled
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


