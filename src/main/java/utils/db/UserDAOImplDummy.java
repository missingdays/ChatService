package utils.db;

import Entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDAOImplDummy implements UserDAO {
    @Override
    public @Nullable User getUserByName(String name) {
        return nameHashMap.get(name);
    }

    @Override
    public @Nullable User getUserByToken(String token) {
        return tokenHashMap.get(token);
    }

    @Override
    public boolean isUserExist(User user) {
        return nameHashMap.containsKey(user.getUsername());
    }

    @Override
    public void removeUser(User user) {
    }

    @Override
    public User addUser(String name) {
        if (nameHashMap.get(name) != null) {
            return null;
        }
        User newUser = new User();
        String token = generateToken();
        newUser.setToken(token);
        newUser.setUsername(name);
        newUser.setId(id);
        id++;
        nameHashMap.put(name,newUser );
        tokenHashMap.put(token,newUser );
        return newUser;
    }

    @Override
    public @NotNull Collection<User> getUsers(Predicate<User> predicate) {
        return nameHashMap.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private String generateToken(){
        return String.valueOf(UUID.randomUUID());
    }
    private static HashMap<String, User> tokenHashMap = new HashMap<>();
    private static HashMap<String, User> nameHashMap = new HashMap<>();
    private static int id = 0;
}
