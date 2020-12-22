package rc.springbootmongodb.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import rc.springbootmongodb.EnumTypes.UserType;

import java.util.Date;

@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private UserType type;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String password_hash;
    private Date created_at;
    private boolean is_deleted;

    public User() {}

    public User(UserType type,String username, String first_name, String last_name, String email, String phone, String password_hash, Date created_at, boolean is_deleted) {
        this.type = type;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password_hash = password_hash;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
    }

    @PersistenceConstructor
    public User(UserType type, String username, String first_name, String last_name, String email, String phone, String password_hash, Date created_at) {
        this.type = type;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password_hash = password_hash;
        this.created_at = created_at;
        this.is_deleted = false;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getId() {
        return id;
    }
}
