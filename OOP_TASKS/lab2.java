import java.util.Random;
import java.util.Scanner; //scanner사용하기 위해 선언
public class lab2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		Random generator = new Random( );
		int total=0;//랜덤 숫자들의 편차 제곱의 합을 저장할때 쓸 변수를 선언
		int number;//난수를 저장할 변수를 선언
		int sum=0;//난수들의 총합을 저장할 변수를 선언
		int large=0;//난수들 중 제일 큰 값을 저장할 변수
		int small=0;//난수들 중 제일 작은 값을 저장할 변수
		float average=0;//난수들의 평균을 저장할 변수
		double deviation;//난수들의 표준편차를 저장할 변수
		System.out.print("enter the number of random numbers to create(between10~20): "); 
		int count=scanner.nextInt(); //사용자가 요구한 10에서 20사이의 난수의 갯수를 입력받는다.
		while(10>count || count>19) //만약에 10보다 작거나 20이상의 값을 입력하면 예외처리를 해주기 위한 반복문
		{
			System.out.print("Wrong! enter random numbers to create(between10~20): ");
			count=scanner.nextInt();
		}
		
		System.out.print("enter the  the lower bound: "); //하한범위 입력을 위한 출력
		int lowerbound = scanner.nextInt();
		
		System.out.print("enter the  the upper bound:");//상한 범위를 입력하기 위한 출력
		int upperbound=scanner.nextInt();
		while(lowerbound>=upperbound) //만약 하한 범위가 상한 범위랑 같거나 클 경우 예외처리하기 위한 반복문, 상한 범위를 다시 입력받는다
		{
			System.out.print("Wrong! enter the  the upper bound again:");
			upperbound = scanner.nextInt();
			
		}
		int [] numArray=new int[count]; //난수를 저장하기 위한 배열 선언
		for(int i=0;i<count;i++) //사용자가 지정한 갯수만큼 난수를 생성하고 출력
		{
			number=generator.nextInt(upperbound-lowerbound+1);
			numArray[i]=number+lowerbound;
			sum+=numArray[i]; //난수들의 합을 구하는 식
		}
		large=numArray[0];//난수들 중 가장 큰 수 변수 선언 및 초기화
		small=numArray[0];//난수들 중 가장 작은 수 변수 선언 및 초기화
		for(int i=0;i<count;i++) //난수들 중 최대값과 최솟값을 구하기 위한 반복문
		{
			if(large<numArray[i])
				large=numArray[i];
			if(small>numArray[i])
				small=numArray[i];
			
		}
		average=sum/(float)count; //평균을 구하기 위한 식
	
		for(int i=0;i<count;i++) //난수들의 편차 제곱의 합을 구하기 위한 반복문
		{
			total+=(numArray[i]-average)*(numArray[i]-average);
		}
		
		System.out.println(" 배열 속 수들:"); //배열 속 수들의 다 출력한다.
		for(int i=0;i<count;i++)
		{
			System.out.print(numArray[i]+" ");

		}
		deviation=total/count; //표준편차를 구하기 위한 식
		
		System.out.println("");
		System.out.println("난수 중 제일 큰수:"+large); //배열 속 난수들 중 가장 큰 수를 출력한다
		System.out.println("난수 중 제일 작은 수:"+small);//배열 속 난수들 중 가장 작은 수를 출력한다.
		System.out.println("모든 난수들의 평균:"+average);//배열 속 난수들의 평균을 출력한다
		System.out.println("모든 난수들의 합:"+sum); //배열 속 난수들의 합을 구한다.
		System.out.println("난수들의 표준편차 : "+Math.sqrt(deviation)); //난수들의 표준편차를 출력한다.
		
		
 
	}


}
