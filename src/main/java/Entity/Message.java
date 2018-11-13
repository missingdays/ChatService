package Entity;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@DatabaseTable(tableName = "messages")
public class Message {
    @DatabaseField
    private String message;
    @DatabaseField(columnName = "send_time",dataType = DataType.DATE)
    private Date sendTime;
    @DatabaseField(columnName = "username")
    private String userName;
    @DatabaseField(id = true)
    private int id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message() {
    }

    public static void main(String[] args) throws SQLException, IOException {
        String databaseUrl = "jdbc:sqlite:C:\\Users\\NEOfr\\Documents\\chat_service.db";
// create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);

// instantiate the DAO to handle Entity.User with String id
        Dao<Message,String> messageDao =
                DaoManager.createDao(connectionSource, Message.class);

// if you need to create the 'Users' table make this call
        //TableUtils.createTable(connectionSource, Entity.User.class);

// create an instance of Entity.User
        String name = "Jim Smith";
        Message message = new Message();
        message.setId(0);
        message.setMessage("message body");
        message.setUserName(name);
        message.setSendTime(new Date());
        message.setId(3);
// persist the Entity.User object to the database
        messageDao.create(message);
// retrieve the Entity.User
        Message message1 = messageDao.queryForId("3");
// show its password
        System.out.println("Entity.User: " + message1.getMessage()+message1.sendTime);
// close the connection source
        connectionSource.close();
    }
}
