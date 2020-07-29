package view;

import model.Usuario;
import model.dao.UsuarioDAO;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;

public class UsuarioView extends ListenerAdapter {
    List<String> hourList = new ArrayList<>();
    final long dia = 1000*60*60;
    boolean timerIsTrue = true;
    public Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            hourList.clear();
        }
    };

    public void verificarBanco(MessageChannel channel,String tag, Usuario user){
        if(!UsuarioDAO.searchTag(tag)){
            UsuarioDAO.save(user);
            channel.sendMessage("Registered!").queue();
        }
        else{
            channel.sendMessage("You are already registered!").queue();
        }
    }

    public void points(MessageChannel channel, String tag, MessageReceivedEvent event){
        if(!hourList.contains(tag) && UsuarioDAO.searchTag(tag)){
            int points = (int) (Math.random() * 10);
            channel.sendMessage("You win " + points + " points" ).queue();
            points += UsuarioDAO.searchPointsByTag(tag);
            Usuario userPoints = new Usuario(event.getAuthor().getName(), points, tag);
            UsuarioDAO.updatePoints(userPoints);
            hourList.add(tag);
            if(timerIsTrue){
                timer.scheduleAtFixedRate(timerTask, 0, dia);
                timerIsTrue = false;
            }
        }
    }
}