package utils.db;

import Entity.Message;
import org.jetbrains.annotations.Nullable;

public interface MessageDAO {
    @Nullable Message getMessage(int id);
    void addMessage(Message message) throws Exception;
}
