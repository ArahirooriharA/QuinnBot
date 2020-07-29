package controller;

import model.dao.BuildRuneDAO;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import view.BuildRuneView;

import javax.annotation.Nonnull;

public class BuildRuneController extends ListenerAdapter{
    private BuildRuneView view = new BuildRuneView();
    private BuildRuneDAO dao;

    public BuildRuneController(BuildRuneView view, BuildRuneDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public BuildRuneController() {
    }

    public BuildRuneView getView() {
        return view;
    }

    public void setView(BuildRuneView view) {
        this.view = view;
    }

    public BuildRuneDAO getDao() {
        return dao;
    }

    public void setDao(BuildRuneDAO dao) {
        this.dao = dao;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        String tag = event.getAuthor().getAsTag().substring(event.getAuthor().getName().length());

        switch (msg.getContentRaw().toLowerCase()) {
            case "!build tank":
                view.build(channel, "tank", 1);
                break;
            case "!build poke":
                view.build(channel, "poke", 2);
                break;
            case "!build bruiser":
                view.build(channel, "bruiser", 3);
                break;
            case "!build slowcomp":
                view.build(channel, "slowcomp", 4);
                break;
        }
    }
}