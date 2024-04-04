package com.example.drops_of_blood;

public class Donor {
    String id = "";
    String name = "";
    String phoneNo = "";
    int age = 0;
    String address = "";
    String bloodGroup = "";
    public Donor(String id,String name,String phoneNo,int age,String address,String bloodGroup)
    {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.age = age;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }
}
