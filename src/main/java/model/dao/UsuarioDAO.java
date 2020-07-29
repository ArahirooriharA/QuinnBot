package model.dao;

import model.Usuario;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static void save(Usuario usuario)  {
        //String sql = "SELECT * FROM usuario.userprops";
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".usuario";

        try (JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()){
            jrs.setCommand(sql);
            jrs.execute();
            jrs.moveToInsertRow();
            jrs.updateInt("points",usuario.getPoints());
            jrs.updateString("name",usuario.getNome());
            jrs.updateString("tag",usuario.getTag());
            jrs.insertRow();

            ConnectionDAO.close(jrs);
            System.out.println("Registrado com sucesso");
        } catch (SQLException throwables) {
            System.out.println("Não está cadastrado no banco");
        }
    }

    public static void updatePoints(Usuario usuario) {
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".usuario where tag = ?";

        try (JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()){
            jrs.setCommand(sql);
            jrs.setString(1,usuario.getTag());
            jrs.execute();
            jrs.next();
            jrs.updateInt("points",usuario.getPoints());
            jrs.updateRow();
            ConnectionDAO.close(jrs);
            System.out.println("Pontos atualizados");
        } catch (SQLException throwables) {
            System.out.println("Não está cadastrado no banco");
        }
    }

    public static boolean searchTag(String tag){
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".usuario where tag = ?";
        boolean isTrue = false;
        try(JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()) {
            jrs.setCommand(sql);
            jrs.setString(1,tag);
            jrs.execute();
            isTrue = jrs.next();
            ConnectionDAO.close(jrs);
        } catch (SQLException throwables) {
            System.out.println("Não está cadastrado no banco");
        }
        return isTrue;
    }

    public static int searchPointsByTag(String tag) {
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".usuario where tag = ?";

        int pontos = 0;
        try(JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()) {
            jrs.setCommand(sql);
            jrs.setString(1,tag);
            jrs.execute();
            while (jrs.next()){
                pontos = jrs.getInt("points");
            }
            ConnectionDAO.close(jrs);
            return pontos;
        } catch (SQLException throwables) {
            System.out.println("Não está cadastrado no banco");
        }
        return 0;
    }

    public static void DeleteDB(String tag) {
        if(tag.equals("#5412")) {
            String quinnDBName = "sql10356689";
            String sql = "SELECT * FROM "+ quinnDBName +".usuario";

            try (JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()){
                jrs.setCommand(sql);
                jrs.execute();

                while (jrs.next()) {
                    jrs.deleteRow();
                }
                ConnectionDAO.close(jrs);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
