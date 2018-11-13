package utils.db;


import Entity.User;
import org.jetbrains.annotations.Nullable;

public interface UserDAO {
    @Nullable
    User getUserByName(String name);
    @Nullable
    User getUserByToken(String token);
    boolean isUserExist(User user);
    void removeUser(User user);
    void addUser(User user) throws Exception;
}
