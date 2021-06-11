package Classes;

public class User {
    private int passport;
    private String name;
    private String secondName;
    private String lastName;
    private String address;
    private String phone;

    public User(int passport, String name, String secondName, String lastName, String address, String phone) throws Exception {
        if(passport < 100000 || passport > 999999){
            throw new Exception("Не правильные паспортные данные");
        }
        if(name.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели имя");
        }
        if(lastName.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели фамилию");
        }
        if(address.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели адрес");
        }
        if(phone.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели телефон");
        }
        this.passport = passport;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public User() {
        this.passport = 0;
        this.name = "name";
        this.secondName = "secondName";
        this.lastName = "lastName";
        this.address = "address";
        this.phone = "phone";
    }


    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) throws Exception {
        if(passport < 100000 || passport > 999999){
            throw new Exception("Не правильные паспортные данные");
        }
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели имя");
        }
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) throws Exception {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if(lastName.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели фамилию");
        }
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if(address.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели адрес");
        }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws Exception {
        if(phone.replaceAll(" ", "").length() == 0){
            throw new Exception("Вы не ввели телефон");
        }
        this.phone = phone;
    }
}
