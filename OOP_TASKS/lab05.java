package com.company;
import java.util.*; //scanner와 random 사용하기 위해 선언
import java.text.*;

    enum StatusType{available, checkedOut, inService, discorded, sold} //차의 상태 표시
    enum CustomerStatusType{Silver,Gold,Diamond} //고객들의 등급정보

    class Customer {
        int customerID; //고객아이디
        String name;
        String driverLicense;
        int creditPoints; //포인트


        CustomerStatusType cStatus;


        public static ArrayList<Customer> customerList = new ArrayList<>();
        static Random random = new Random();


        Customer(String n, String d) {       //생성자
            this.customerID = random.nextInt(90000) + 10000; //5자리 아이디를 만들기 위해 10000에다가 99999까지 더해줌
            for(int i=0;i<customerList.size();i++)  //아이디 중복검사를 위한 끝까지 읽고 중복시 제거후 다시 생성
            {
                for(int j=0;j<customerList.size();j++){
                    if(i==j){
                    }
                    else if(customerList.get(j).equals(customerList.get(i))){
                        customerList.remove(j);
                    }
                }
                customerList.add(this);
            }
            this.name = n;
            this.driverLicense = d;
            cStatus = CustomerStatusType.Silver; //silver로 초기화
            creditPoints = 0; //처음에 포인트는 0으로 시작함
            if (creditPoints < 1)//포인트 정수형,  포인트가 음수가 될 수는 없다는 예외처리
            {
                Scanner scanner = new Scanner(System.in);
                while (creditPoints < 0) { //포인트가 0포인트일때 에러처리
                    System.out.println("points error! enter the points more than 0");
                    creditPoints = scanner.nextInt();
                    scanner.close();
                }
            }
            customerList.add(this);

            }



        int getPoints() {
            return creditPoints; //가진 포인트 출력
        }

        void addPoints(int rentalFee) { //렌탈 비용에 대한 포인트 적립해주기
            if(this.cStatus==CustomerStatusType.Silver)
                rentalFee=rentalFee*0;
            else if(this.cStatus==CustomerStatusType.Gold)
                rentalFee=(int)(rentalFee*0.1);
            else if(this.cStatus==CustomerStatusType.Diamond)
                rentalFee=(int)(rentalFee*0.2);

            creditPoints += rentalFee;

        }


        void reducePoints(int points) { //사용할 포인트 기능
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many points do you want to use for rental?");
            creditPoints = points - scanner.nextInt();
            System.out.println("Now points: " + creditPoints + "(" + points + "-" + scanner.nextInt()); //남은 포인트
            scanner.close();
        }

        void promote(){ //fee에 따라 등급을 올려주는 기능
            int totalCarRentalFee=0;
            for(int i=0;i<Rental.totalRentals;i++){
                if(Rental.rentalList.get(i).customer.customerID==this.customerID)
                    totalCarRentalFee+=Rental.rentalList.get(i).fee;

            }
            if(totalCarRentalFee>=500000)
                cStatus=CustomerStatusType.Diamond;
            else if(totalCarRentalFee>=100000)
                cStatus= CustomerStatusType.Gold;
        }

        void printInfo() { //정보를 출력해주는 기능
            System.out.println("CustomerID :" + this.customerID);
            System.out.println("Name:" + this.name);
            System.out.println("DriverLicense :" + this.driverLicense);
            System.out.println("CustomerStatusType :" + this.cStatus);
            System.out.println("CreditPoints :" + this.creditPoints);


        }
    }
class Rental{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
int rentalID; //렌탈아이디
Customer customer; //고객
Car car;
Date dateOut;
Date dateIn;
int fee=0;
public static ArrayList<Rental> rentalList=new ArrayList<>();
static int totalRentals=0;

static Random random= new Random();
Rental(Customer cust, Car ccar, Date out, int Fee ){
    rentalID=random.nextInt(900000)+100000; //6자리 100000~999999
    customer=cust;
    car=ccar;

    dateOut=out;
    dateIn=null;
    fee=Fee;
    this.totalRentals+=1;
    rentalList.add(this);



}
void returnCar(Date in, int mileage){
    dateIn=in;
    car.addMileage(mileage);
    customer.addPoints(fee);

}
//Customer getCustomer
    int getFee(){
    return fee;
    }
    void printInfo(){
    System.out.println("RentalID:"+rentalID);
    customer.printInfo();
    car.printInfo();

    System.out.println("DateOut:"+dateFormat.format(dateOut));
        if (dateIn!=null) //만약에 datein이 비어있는, 반납이 안되어있는  경우에 필요한 조건문
    System.out.println("DateIn:"+dateFormat.format(dateIn));
        System.out.println("Fee:"+fee);
        System.out.println("=============");

    }
    }
class Car {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    int carID; //차 번호를 받기 위한 변수 선언
    StatusType status; //차 상태를 표시하기 위해 선언
    Date datePurchased; //차 구매날짜를 표기하기 위해 선언
    int mileage; //마일리지 값을 정수형으로 선언
    public static ArrayList<Car> carList = new ArrayList<>();//새로 추가

    public Car(int id, Date d, int m) {
        Random generator = new Random();

        carID = 1000 + generator.nextInt(9000); //1000~9999까지 4자리 수를 만들어냄
        for (int i = 0; i < carList.size(); i++) { //자동차 아이디 중복검사 기능 구현
            for (int j = 0; j < carList.size(); j++) {
                if (i == j) {
                } else if (carList.get(j).equals(carList.get(i))) {
                    carList.remove(j);
                }
            }
            carList.add(this);
        }
        status = StatusType.available; //초기값을 available로 설정함
        datePurchased = d;
        setMileage(m);
        mileage = m;

        if (m < 1)//마일리지는 정수형,  운행을 했으면0보다 크기 때문에 예외처리
        {
            Scanner scanner = new Scanner(System.in);
            while (mileage < 1) {
                System.out.println("mileage error! Please reenter the mileage more than 0");
                mileage = scanner.nextInt();
                scanner.close();
            }
        }
        carList.add(this);


    }

    void setMileage(int x) {
        if (x > 0)
            mileage += x;
        else {
            Scanner scanner = new Scanner(System.in);
            while (mileage < 1) { //마일리지 0보다 작으면 예외처리 해줌
                System.out.println("mileage error! reenter the mileage");
                x = scanner.nextInt();
            }
            mileage += x;
            scanner.close();
        }
    }
    void addMileage(int x){
        if(x>0)
            mileage+=x;
        else
        {
            Scanner scanner= new Scanner(System.in);
            while (mileage<1){
                System.out.println("mileage error! Please enter the mileage again!");
                x=scanner.nextInt();

            }
            mileage+=x;
            scanner.close();

        }
    }

    void setStatus(StatusType s) {
        status = s;
    }

    public void printInfo() { //차들의 정보를 출력하는 함수
        System.out.println("CarId : " + carID);
        System.out.println("Status : " + status);
        System.out.println("DataPurchased : " + dateFormat.format(datePurchased));
        System.out.println("Mileage" + mileage);
        System.out.println("");
    }


        public static void main(String[] args) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Random random = new Random();

            ArrayList<Customer> customerList = new ArrayList<Customer>(2);
            Customer customer1 = new Customer("amilly", "soeul2020");
            Customer customer2 = new Customer("alex", "soeul2021");
            customerList.add(customer1);
            customerList.add(customer2);
            ArrayList<Car> carList = new ArrayList<Car>(3);
            Car car1 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2016-03-03"), 1000);
            Car car2 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2015-03-03"), 200);
            Car car3 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2011-03-02"), 100);


            carList.add(car1);
            carList.add(car2);
            carList.add(car3);

            System.out.println("=====Printing Customer Information===="); //모든 고객정보 출력
            for (Customer customer : customerList) {
                customer.printInfo();
                System.out.println();

            }
            System.out.println("=====Printing Car Information====");//모든 차의 정보 출력
            for (Car car : carList) {
                car.printInfo();
                System.out.println();

            }
            System.out.println();
            ArrayList<Rental> rentalList= new ArrayList<Rental>();
            Rental rental1= new Rental(customer1, car1, dateFormat.parse("2020-01-01"),500000);
            Rental rental2= new Rental(customer2, car2,  dateFormat.parse("2020-01-01"),2000000);

            rentalList.add(rental1);
            rentalList.add(rental2);

            car1.setStatus(StatusType.checkedOut);
            car2.setStatus(StatusType.checkedOut);

            System.out.println("=====rental information=====");//렌탈을 한 정보들을 출력
            for(Rental rental : rentalList){
                rental.printInfo();
                System.out.println();

            }
            System.out.println();

            rental1.returnCar( dateFormat.parse("2020-01-08"),10000);//차를 반납해줌
            rental2.returnCar( dateFormat.parse("2020-01-12"),10000);

            car1.setStatus(StatusType.available);
            car2.setStatus(StatusType.available);

            customer1.promote(); //등급을 올려줌
            customer2.promote();

            System.out.println("====반납 후 고객 정보 출력===="); //고객1,2 차 반납 후의 정보를 출력
            for(Customer customer: customerList){
                customer.printInfo();
                System.out.println("==============");

            }
            System.out.println();

            Rental rental3= new Rental(customer1, car1, dateFormat.parse("2020-01-04"),5000000); //고객1의 렌탈
            rentalList.add(rental3);
            car1.setStatus(StatusType.checkedOut);
            customer1.promote();

            rental3.returnCar( dateFormat.parse("2020-03-03"),1000);
            car1.setStatus(StatusType.available);
            System.out.println("customer1의 추가 렌탈과 반납");
            customer1.printInfo();
            System.out.println();


            System.out.println("=====Printing Customer1 Information===="); //반납 후 고객1의 정보 출력


                customer1.printInfo();
                System.out.println();



        }
    }



