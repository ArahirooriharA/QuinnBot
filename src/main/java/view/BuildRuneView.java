package view;

import model.dao.BuildRuneDAO;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class BuildRuneView extends ListenerAdapter {
    final String[] statusRune = {"%10 Attack Speed","+9 Adaptive","+6 Armor"};

    public void build(MessageChannel channel,String whoBuild, int idBuild){
        channel.sendMessage("Build: "+ BuildRuneDAO.searchBuild(whoBuild).toString()
                +"\nStatus: "+ Arrays.toString(statusRune)
                +"\nRunes: "+ BuildRuneDAO.searchRune(idBuild).toString()).queue();
    }
}
