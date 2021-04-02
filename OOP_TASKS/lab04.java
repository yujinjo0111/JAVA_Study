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
                while (creditPoints < 0) {
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

        void addPoints(int rentalFee) { //렌탈 비용에 대한 포인트

            creditPoints += rentalFee;
        }

        void reducePoints(int points) { //사용할 포인트 기능
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many points do you want to use for rental?");
            creditPoints = points - scanner.nextInt();
            System.out.println("Now points: " + creditPoints + "(" + points + "-" + scanner.nextInt()); //남은 포인트
            scanner.close();
        }

        void promote(int fee) {
            if (this.cStatus == CustomerStatusType.Silver) { //렌탈비용 지불에 대한 등급 변화
                if ((fee >= 100000))
                    this.cStatus = CustomerStatusType.Gold;
            } else if (this.cStatus == CustomerStatusType.Gold) {
                if ((fee >= 500000))
                    this.cStatus = CustomerStatusType.Diamond;

            }

        }

        void printInfo() {
            System.out.println("CustomerID :" + this.customerID);
            System.out.println("Name:" + this.name);
            System.out.println("DriverLicense :" + this.driverLicense);
            System.out.println("CustomerStatusType :" + this.cStatus);
            System.out.println("CreditPoints :" + this.creditPoints);


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
            customerList.add(customer1); //리스트에 추가
            customerList.add(customer2);
            ArrayList<Car> carList = new ArrayList<Car>(3);
            Car car1 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2016-03-03"), 300);
            Car car2 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2015-03-03"), 200);
            Car car3 = new Car(random.nextInt(9000) + 1000, dateFormat.parse("2011-03-02"), 100);


            carList.add(car1);//리스트에 추가
            carList.add(car2);
            carList.add(car3);

            System.out.println("=====Printing Customer Information====");
            for (Customer customer : customerList) {
                customer.printInfo();
                System.out.println();

            }
            System.out.println("=====Printing Car Information====");
            for (Car car : carList) {
                car.printInfo();
                System.out.println();

            }
            System.out.println();

            System.out.println("=====Adding 300000points to customer1===="); //고객1에게 300000포인트를 추가해줌
            customer1.addPoints(300000);
            System.out.println("=====Printing Customer Information====");
            for (Customer customer : customerList) {
                customer.printInfo();
                System.out.println();

            }

        }
    }
