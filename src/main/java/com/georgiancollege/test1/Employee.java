package com.georgiancollege.test1;

import java.util.Arrays;
import java.util.List;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String phoneNo;
    // controller
    public Employee(int employeeId, String firstName, String lastName, String address, String city, String province, String phoneNo) {
        setEmployeeId(employeeId);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setCity(city);
        setProvince(province);
        setPhoneNo(phoneNo);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        if (employeeId > 200465000) {
            this.employeeId = employeeId;
        } else {
            throw new IllegalArgumentException("Employee ID should be greater than 200465000");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 1) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("First name must be more than 1 character");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 1) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Last name must be more than 1 character");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 5) {
            this.address = address;
        } else {
            throw new IllegalArgumentException("Address must be more than 5 characters");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.length() > 3) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("City must be more than 3 characters");
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        List<String> validProvinces = Arrays.asList("AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT");
        if (validProvinces.contains(province)) {
            this.province = province;
        } else {
            throw new IllegalArgumentException("Province should be in the list of valid provinces");
        }
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        String strippedPhoneNo = phoneNo.replaceAll("-", "");

        if (strippedPhoneNo.length() == 10 && strippedPhoneNo.matches("\\d+")) {
            this.phoneNo = strippedPhoneNo;
        } else {
            throw new IllegalArgumentException("Phone number should match the North American dialing plan");
        }
    }
}
