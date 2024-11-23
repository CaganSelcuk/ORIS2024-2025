public class Sportsman {
    private Long id;
    private String name;
    private String surname;
    private Integer power;
    private Integer age;
    private String email;
    private String telefon;

    public Sportsman(Long id, String name, String surname, Integer power, Integer age, String email, String telefon){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.power = power;
        this.age = age;
        this.email = email;
        this.telefon = telefon;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
}
