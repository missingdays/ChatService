package utils.db;


import Entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Predicate;

public interface UserDAO {
    @Nullable
    User getUserByName(String name);
    @Nullable
    User getUserByToken(String token);
    boolean isUserExist(User user);
    void removeUser(User user);
    User addUser(String name);

    @NotNull
    Collection<User> getUsers(Predicate<User> predicate);
}
