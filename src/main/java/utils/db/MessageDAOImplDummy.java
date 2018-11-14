package utils.db;

import Entity.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MessageDAOImplDummy implements MessageDAO {
    @Override
    public @Nullable Message getMessage(int id) {
        return storage.get(id);
    }

    @Override
    public Message addMessage(String message, int userId) {
        Message mes = new Message();
        mes.setId(id_);
        id_++;
        mes.setMessage(message);
        mes.setUserId(userId);
        return mes;
    }

    @Override
    public @NotNull Collection<Message> getMessages(Predicate<Message> predicate) {
        return storage.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    static int id_ = 0;
    static HashMap<Integer, Message> storage;
}
