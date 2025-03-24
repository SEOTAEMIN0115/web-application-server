package db;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import model.User;

public class DataBase {

    private DataBase(){}

    private static class DatabaseSingleton{
        private static final DataBase database = new DataBase();
    }

    public static DataBase getInstance(){
        return DatabaseSingleton.database;
    }

    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
