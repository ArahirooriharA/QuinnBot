import controller.BuildRuneController;
import controller.UsuarioController;
import model.Usuario;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import view.BuildRuneView;
import view.UsuarioView;

import javax.security.auth.login.LoginException;

public class Main{
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.createDefault("Token")
                .addEventListeners(new BuildRuneController(), new UsuarioController()).setActivity(Activity.watching("TV"))
                .build();
        jda.awaitReady();
        System.out.println("Finished Building JDA");
    }
}
