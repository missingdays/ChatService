package utils.db;

import Entity.Message;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

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
    static int id_ = 0;
    static HashMap<Integer, Message> storage;
}
