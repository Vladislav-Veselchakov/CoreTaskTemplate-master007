package jm.task.core.jdbc.model;

import javax.persistence.*;

// Added by me (VL Antipov): import org.hibernate.annotations.Entity;
@Entity
@Table (name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //можно не указывать Column name, если оно совпадает с названием столбца в таблице
    @Column (name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result + ((name == null)? 0 : (name.getBytes().length<=0)? 0 : (name.getBytes())[0]);
        result = prime*result + ((lastName == null)? 0 : (lastName.getBytes().length<=0)? 0 : (lastName.getBytes())[0]);
        result = result + (int) Math.sin(age) + 100;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        User outUser = (User) obj;
        if (name.compareTo(outUser.name)==0 && lastName.compareTo(outUser.lastName) == 0 && age == outUser.age) {
            return true;
        }
        return false;
    }
}
