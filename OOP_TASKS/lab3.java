import java.util.*;//*이 모든문자를 의미
import java.text.SimpleDateFormat;
import java.text.*;
enum StatusType{available, checkedOut,inService,discorded,sold}



	class Car{   //클래스생성
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int carID;
		StatusType status;
		Date datePurchased;
		int mileage;
		//클래스랑 이름이 같으면 생성자임
		 Car(Date d,int m){
			Random generator= new Random();
			
			
			
			carID = 1000 +generator.nextInt(9000); //1000~9999
			status = StatusType.available;
			datePurchased=d;
			mileage=m;
		
			if(m<1)
			{
				Scanner scanner= new Scanner(System.in);
				while(mileage<1) {
					System.out.println("mileage error! Please reenter the mileage more than 0");
					mileage= scanner.nextInt();
					scanner.close();  //스캐너 닫아
								}
			}
		}
			//차의 주행거리 기록
		 
			
			void setMileage(int x) {
			if (x>0) //주행거리 입력이 0이상으로 제대로 된 경우 실행
				mileage+=x;
				else//주행거리가 0보다 작은 값으로 입력이 될때 실행
				{
				Scanner scanner=new Scanner(System.in);
				while(mileage<1)
				{
					System.out.println("mileage error! enter the mileage more than 0");
					x=scanner.nextInt();
				}
				mileage+=x;
				scanner.close();
				//다시 입력하라고 하고 더해주기
			
			}
			}
		
			
		
			 //마일리지 값에 탓다고 한거 계속 더해준다
			
		
		//차의 상태표시
		void setStatus(StatusType s) {
			status=s;
			System.out.println("setStatus( "+s+")*)");
					//setStatus(sold)
		}
	
	public void printInfo() {
		System.out.println("CarId : "+carID);
		System.out.println("Status : "+status);
		System.out.println("DataPurchased : "+dateFormat.format(datePurchased));
		System.out.println("Mileage"  +mileage);
		System.out.println("");
	
		

	}
	}
	
class LAB3{
	public static void main(String[] args) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Car[] carArray= new Car[3];
		//생성자 앞에는 new를 붙인다
		
		Date d1 = dateFormat.parse("2016-03-03"); //소스값이 데이트

		Date d2 = dateFormat.parse("2016-03-07");
		Date d3 = dateFormat.parse("2016-03-05");
		
		
		carArray[0]=new Car(d1, 6); //마일리지 값
		carArray[1]=new Car(d2, 7);
		carArray[2]=new Car(d3, 8);
		
		for(int i=0;i<3;i++)
		{
			carArray[i].printInfo();
		}
		carArray[0].setStatus(StatusType.checkedOut);
		System.out.println();
		for(int i=0;i<3;i++)
		{
			carArray[i].printInfo();
		}
		
		
				
			}
		
		
	}
	
	
