package pa.greeenvox.ru;

import net.dv8tion.jda.api.entities.ThreadChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Bot implements EventListener {

    public static ThreadsBot Plugin;
    public List<Long> getChannelsList() {
        return Plugin.getConfig().getLongList("channels");
    }

    public Bot(ThreadsBot plugin) {
        Plugin = plugin;
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (!(genericEvent instanceof MessageReceivedEvent)) return;
        MessageReceivedEvent event = (MessageReceivedEvent) genericEvent;

        if (!getChannelsList().contains(event.getMessage().getChannel().getIdLong())) return;

        String name = "Ветка";
        if (event.getMessage().getContentRaw().split(" ").length != 0)
            name = event.getMessage().getContentRaw().split(" ")[0];

        event.getMessage().createThreadChannel(name)
                .queue((ThreadChannel c) -> c.leave().queue());
    }
}
