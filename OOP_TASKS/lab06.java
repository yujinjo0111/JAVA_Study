package com.company;
import java.text.*;
import java.util.*;

class Person{
    private int personID; //아이디
    private String name;
    private String phone;

    private static ArrayList<Person> persons= new ArrayList<>();
    private static Random random= new Random();

    public Person(String n,String p){
        this.personID= random.nextInt(90000)+10000; //5자리 아이디 생성
        this.name=n;
        this.phone=p;

    }
    void printInfo(){
        System.out.println("personID: "+this.personID);
        System.out.println("Name: "+this.name);
        System.out.println("Phone: "+this.phone);
    }
    public static void addPerson(Person p){
        persons.add(p); //리스트에 객체 더해주는 기능

    }
    public static Boolean deletePerson(Person p)throws Throwable{
        persons.remove(p); //객체 삭제해주는 기능
        return true;
    }
    public static void printAllPersons(){
        for(Person person: persons)
            person.printInfo();


    }
}
class Customer extends Person{
    private int customerID;
    private String driverLicense;

    public static ArrayList<Customer> customers= new ArrayList<>();
    private static Random random= new Random();

    Customer(String n, String p, String license){
        super(n,p);
        this.customerID= random.nextInt(90000)+10000;
        this.driverLicense= license;

        addPerson(this); //추가시켜줌
        System.out.println("new customer added"+n);
        customers.add(this);

    }
    void printInfo(){
        super.printInfo();
        System.out.println("CustomerID:"+this.customerID);
        System.out.println("Driver License:"+this.driverLicense);
    }
    public void finalize()throws Throwable{ //예외처리
        deletePerson(this);
        customers.remove(this);
        super.finalize();

    }

}
class Staff extends Person{
    private int staffID;
    private String dept;
    private Date dateHired;

    public static ArrayList<Staff> staffs= new ArrayList<>();

    private static Random random= new Random();

    public Staff(String n, String p, String d){
        super(n,p); //위에 정보들을 불러옴
        this.staffID= random.nextInt(90000)+10000;
        this.dept=d;
        this.dateHired= new Date(); //현재날짜 설정

        addPerson(this);
        System.out.println("new staff is added"+n);
        staffs.add(this);

    }
    void printInfo(){
        super.printInfo();
        SimpleDateFormat yyr= new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("StaffID: "+this.staffID);
        System.out.println("Department: "+this.dept);
        System.out.println("Date Hired: "+this.dateHired);
    }
    public void finalize() throws Throwable{
        deletePerson(this);
        super.finalize();

    }
}
public class Main {
    public static void main(String[] args )throws Throwable{
        System.out.println("=====Register Persons=====");//3명생성
        Customer customer1= new Customer("yu jin", "000-0000-0000","souel");
        Customer customer2= new Customer("yu han", "000-0000-0000","souel");
        Customer customer3= new Customer("yu sun", "000-0000-0000","souel");
//2명 생성
        Staff staff1= new Staff("Mike", "000-0000-0000","Selling");
        Staff staff2= new Staff("Hike", "000-0000-0000","Selling");

        System.out.println();
        System.out.println("=====print all persons=====");
        Person.printAllPersons();
        System.out.println("=====delete persons=====");
        customer1.finalize();
        System.gc();

        staff1.finalize();
        System.gc();//삭제시켜주는 기능

        System.out.println("=====deleted=====");

        System.out.println("=====print all persons=====");
        Person.printAllPersons(); //삭제 후 모든 사람들의 정보 출력


    }
}
