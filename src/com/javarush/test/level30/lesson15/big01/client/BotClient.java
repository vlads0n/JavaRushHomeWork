package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Влад on 09.02.2016.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String userName = "";
            String text = "";
            if (message.split(": ").length == 2) {
                userName = message.split(": ")[0];
                text = message.split(": ")[1];
            }
            if (text.equals("дата"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime())));
            else if (text.equals("день"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("d").format(Calendar.getInstance().getTime())));
            else if (text.equals("месяц"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime())));
            else if (text.equals("год"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime())));
            else if (text.equals("время"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime())));
            else if (text.equals("час"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("H").format(Calendar.getInstance().getTime())));
            else if (text.equals("минуты"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("m").format(Calendar.getInstance().getTime())));
            else if (text.equals("секунды"))
                sendTextMessage(String.format("Информация для %s: %s", userName, new SimpleDateFormat("s").format(Calendar.getInstance().getTime())));
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) Math.random() * 99;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
