import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import telegram.Bot;

import java.util.Map;

public class MentalCalculationApplication {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {

        ApiContextInitializer.init();
        System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "127.0.0.1" );
        System.getProperties().put( "socksProxyPort", "9150" );
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot(getenv.get("@simpleIvanBot"), getenv.get("1554140149:AAFS1RI7WwMW_7_C^J_IFHkCzkShs-Iz5MM")));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
