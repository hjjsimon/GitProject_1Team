package console;

import java.util.Scanner;

import common.utility.CommonUtil;

public class BaseBall {
	//1.랜덤하게 숫자 발생후 중복되지 않게 인자로 전달된 배열에 저장하는 메소드
	public static void setRandomNumber(int[] random, int start, int end) {
		for(int i=0;i<random.length;i++) {
			while(true) {
				//랜덤하게 숫자를 발생 시킴
				int randomNumber= (int)(Math.random()*(end-start+1))+start;
				//랜덤하게 발생한 숫자 중복여부 페크를 위한 변수 선언
				boolean isDuplicated = false;
				//랜덤하게 발생시킨 숫자(randomNumber)와 배열(random)에 저장된 값의 중복 여부 체크
				for(int k=0;k <=i-1;k++) {
					if(randomNumber == random[k]) {//중복된 경우
						isDuplicated = true;
						break;
					}//if
				}//for
				if(!isDuplicated) {//중복되지 않는 경우
					random[i]=randomNumber;
					break;
				}
			}//while
		}//for
	}//setRandomNumber
	//사용자 입력용 메소드
	private static void setUserNumber(int[] user) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세 자리 숫자를 입력하세요");
		//int userNumber=sc.nextInt();
		//문]먼저 입력한 문자열일 숫자 형식인지 아닌지 판단하고
		//숫자형식이 아니라면 다시 입력받도록 하여라
		//또한 숫자형식이 경우에는 3자리만 입력 받도록 하고
		//3자리가 아니면 "숫자는 3자리만 입력하세요"출력하고 다시 입력 받는다
		//만약 중복된 경우, "중복된 숫자가 있어요"라는 메시지를 출력하고
		//다시 입력받도록 하여라
		String userStr = sc.nextLine();
		int userNumber=0;
		while (true) {
            if(!userStr.matches("[0-9]+")) {
                System.out.println("숫자로만 입력해주세요.");
                break;
            }
             else if(Integer.parseInt(userStr)/100 == (Integer.parseInt(userStr)%100)/10 ||
					 Integer.parseInt(userStr)/100 ==Integer.parseInt(userStr)%10 ||
					 (Integer.parseInt(userStr)%100)/10 ==	Integer.parseInt(userStr)%10 ) {
				 System.out.println("중복된 숫자가 있습니다");
				 break;
			 }
             else if(userStr.matches("[0-9]{4,}") || userStr.matches("[0-9]{1,2}")) {
				 System.out.println("숫자는 3자리만 입력해주세요");
				 break;
			 }
			 else if (userStr.matches("[0-9]{3,3}")) { 
            	 userNumber= Integer.parseInt(userStr);
                 break;
             }
            /*
            userStr=sc.nextLine();
            if(!CommonUtil.isNumber(userStr)) {
            	System.out.println("숫자만 입력하세요");
            	continue;
            }
            else if(userStr.length()!=3) {
            	System.out.println("숫자는 3자리만 입력하세요");
            	continue;
            }
            else {
            	char []chArr=userStr.toCharArray();
            	boolean isDuplicated =false;
            	for(int i=0;i<chArr.length-1;i++) {
            		for(int k=i+1;k<chArr.length;k++) {
            			if(chArr[i]==chArr[k]) {
            				isDuplicated=true;
            				break;
            			}
            		}
            		if(isDuplicated) break;
            	}
            	if(isDuplicated){
            		System.out.println("중복된 숫자가 있습니다");
            		break;
            	}
            }*/
         }//while

		user[0]=userNumber/100;
		user[1]=(userNumber%100)/10;
		user[2]=userNumber%10;
	}
	//3. 판단후 스트라이크/볼 저장용 메소드
	private static void setStrikeOrBall(int[] strikeOrBall, int[] computer, int[] user) {
		for(int i=0;i<computer.length;i++) {
			for(int k=0;k<user.length;k++) {
				if(i==k &&computer[i]==user[k])strikeOrBall[0]++;
				else if(i!=k && computer[i]==user[k])strikeOrBall[1]++;
			}
		}
	}//serStrikeOrBall
	//4. 계속 여부 판단용 메소드
	public static void isContinue() {
		Scanner sc = new Scanner(System.in);
		System.out.println("종료하려면 'X' or 'x'\r\n계속하려면 아무키나 입력해주세요");
		String exitCode = sc.nextLine();
		if(exitCode.equalsIgnoreCase("X")) {
			System.out.println("즐거우셨나요.다음에 또 플레이 해주세요");
			System.exit(0);
		}
	}//isContinue
	public static void main(String[] args) {
		while(true) {
		int computer[] = new int[3];
		setRandomNumber(computer,1,9);
		for (int i=0;i<computer.length;i++)System.out.printf("%-3d",computer[i]);
		System.out.println("");//줄바꿈
		int tryCount=0;
		while(true) {
		//2]사용자로 부터 3자리 숫자를 입력받자
		int user[] = new int[3];//사용자 입력 숫자를 저장잘 1차원 배열 선언
		setUserNumber(user);
		//3]판단하기 즉 자리(인덱스)가 같고 같이 같으면 strike,자리가 다르고 같만 같으면 ball
		//0번째 방에는 strike,1번째 방에는 ball
		int[] strikeOrBall= new int[2];
		setStrikeOrBall(strikeOrBall,computer,user);
		//strike,ball출력
		System.out.printf("%d 스트라이크,%d 볼%n",strikeOrBall[0],strikeOrBall[1]);
		tryCount++;
		if(strikeOrBall[0]==3) {
			System.out.printf("정답을 맞추셨습니다  %d번째에 맞혔습니다. 축하드립니다%n",tryCount);
			break;
				}//if
			}//안쪽while
		//게임 플레이 여부 판단용 메소드 호출
		isContinue();
		}//바깥while
	}///main
}//class
