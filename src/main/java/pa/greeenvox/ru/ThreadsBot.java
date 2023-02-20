package pa.greeenvox.ru;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class ThreadsBot extends JavaPlugin {

    public static JDA Bot;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        String token = getConfig().getString("bot_token");

        try {
            Bot = JDABuilder.createDefault(token)
                    .addEventListeners(new Bot(this))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        Bot.shutdownNow();
    }
}
