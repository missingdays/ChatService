package utils.db;

import Entity.Message;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public interface MessageDAO {
    @Nullable Message getMessage(int id);

    @Contract(value = "null,_->null;_,_->new")
    Message addMessage(String message, int userId);
}
