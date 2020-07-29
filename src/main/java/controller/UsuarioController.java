package controller;

import model.Usuario;
import model.dao.UsuarioDAO;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import view.BuildRuneView;
import view.UsuarioView;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.awt.*;

public class UsuarioController extends ListenerAdapter {
    private UsuarioView view = new UsuarioView();
    private Usuario model;

    public Usuario getModel() {
        return model;
    }

    public void setModel(Usuario model) {
        this.model = model;
    }

    public UsuarioController(UsuarioView view) {
        this.view = view;
    }

    public UsuarioController() {
    }

    public UsuarioView getView() {
        return view;
    }

    public void setView(UsuarioView view) {
        this.view = view;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.toLowerCase().equals("!shutdown")){
            view.timer.cancel();
            event.getJDA().shutdown();
        }
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        String tag = event.getAuthor().getAsTag().substring(event.getAuthor().getName().length());

        switch (msg.getContentRaw().toLowerCase()) {
            case "!points":
                view.points(channel,tag,event);
                break;
            case "!cadastro":
                Usuario user = new Usuario(event.getAuthor().getName(), 0, tag);
                view.verificarBanco(channel,tag, user);
                break;
            case "!deletedb":
                UsuarioDAO.DeleteDB(tag);
                break;
        }
    }
}
