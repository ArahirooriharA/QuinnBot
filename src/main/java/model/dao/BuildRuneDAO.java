package model.dao;

import model.dao.ConnectionDAO;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildRuneDAO {
    public static List<String> searchBuild(String whoBuild) {
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".build where against = ?";

        List<String> buildItems = new ArrayList<>();
        try (JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()){
            jrs.setCommand(sql);
            jrs.setString(1,whoBuild.toLowerCase());
            jrs.execute();
            while (jrs.next()){
                buildItems.add(jrs.getString("firstItem"));
                buildItems.add(jrs.getString("secondItem"));
                buildItems.add(jrs.getString("thirdItem"));
                buildItems.add(jrs.getString("fourthItem"));
                buildItems.add(jrs.getString("fifthItem"));
                buildItems.add(jrs.getString("sixthItem"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buildItems;
    }

    public static List<String> searchRune(int idBuild) {
        String quinnDBName = "sql10356689";
        String sql = "SELECT * FROM "+ quinnDBName +".rune where idBuild = ?";

        List<String> buildRune = new ArrayList<>();
        try (JdbcRowSet jrs = ConnectionDAO.getRowSetConnection()){
            jrs.setCommand(sql);
            jrs.setInt(1,idBuild);
            jrs.execute();
            while (jrs.next()){
                buildRune.add(jrs.getString("firstRune"));
                buildRune.add(jrs.getString("secondRune"));
                buildRune.add(jrs.getString("thirdRune"));
                buildRune.add(jrs.getString("fourthRune"));
                buildRune.add(jrs.getString("fifthRune"));
                buildRune.add(jrs.getString("sixthRune"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buildRune;
    }
}
