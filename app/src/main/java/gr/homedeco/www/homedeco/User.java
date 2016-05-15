package gr.homedeco.www.homedeco;


public class User {

    private int userID, addressNumber, postalCode;
    private String username, password, email, firstName, lastName, birthday, address, city, state,
            country, phone, mobilePhone;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email, String firstName,
                String lastName, String birthday, String address, int addressNumber, int postalCode,
                String city, String state, String country, String phone, String mobilePhone) {
        this.addressNumber = addressNumber;
        this.postalCode = postalCode;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
    }

    public User(int userID, String username, String password, String email, String firstName,
                String lastName, String birthday, String address, int addressNumber, int postalCode,
                String city, String state, String country, String phone, String mobilePhone) {
        this.userID = userID;
        this.addressNumber = addressNumber;
        this.postalCode = postalCode;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
