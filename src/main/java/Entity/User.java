package Entity;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.sql.SQLException;

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(id = true)
    private String username;
    @DatabaseField
    private String token;
    private int id;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public static void main(String[] args) throws SQLException, IOException {
        String databaseUrl = "jdbc:sqlite:C:\\Users\\NEOfr\\Documents\\chat_service.db";
// create a connection source to our database
        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);

// instantiate the DAO to handle Entity.User with String id
        Dao<User,String> userDao =
                DaoManager.createDao(connectionSource, User.class);
        System.out.println(userDao.queryBuilder().where().idEq("Jim Smith").countOf());
// if you need to create the 'Users' table make this call
        //TableUtils.createTable(connectionSource, Entity.User.class);

// create an instance of Entity.User
        String name = "Jim Smith";
        User user = new User();
        user.token="token1";
        user.username=name;
// persist the Entity.User object to the database
        //userDao.create(user);
// retrieve the Entity.User
        User User2 = userDao.queryForId(name);
// show its password
        System.out.println("Entity.User: " + User2.getUsername());
// close the connection source
        connectionSource.close();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
