package ru.pvv.crud.business.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 2, message = "Имя должно состоять не менее, чем из 2 символов")
    @Size(max = 30, message = "Имя должно состоять не более, чем из 30 символов")
    private String name;

    @Column
    @NotEmpty(message = "Поле email не должно быть пустым")
    @Email(message = "Почта указана неверно")
    private String email;

    @Column
    @Min(value = 1, message = "Возраст должен быть положительным целым числом")
    @Max(value = 120, message = "Возраст должен быть адекватным (меньше 120 лет)")
    private short age;

    public User() {
    }

    public User(String name,  String email, byte age) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
