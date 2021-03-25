import java.util.Scanner; //Scanner사용하기 위해 추가
public class One {

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in); //Scanner객체를 생성
		
		System.out.print
		("Enter the number of Fibonacci numbers to print: ");
		int j=scanner.nextInt(); //숫자 입력받기
		int num1=0; //초기값 설정
		int num2=1;
		int sum=0;
		int total=0;
		
		System.out.println("Here is the sequence of first 10 Fibonacci numbers: ");
		System.out.print("   ");
		
		
		for(int i=0;i<j; i++)
		{
			
			System.out.printf(sum+" ");

			
			num1=num2;//num1에 num2값을 넣어주고
			num2=sum;//  피보나치 구현위해 두번째 값에 더한 값을 넣어준다
			total+=sum;
			sum=num1+num2; //두 값을 더한 후 
			
			if(i==j-1)
			{
				break;
			}
			System.out.printf(","); //마지막에는 쉼표 출력 안되게
			
		 
		}
		System.out.print("\n");
		System.out.println("The sum of the numbers is " +total+ "."); //총합구하기
		

}
}
