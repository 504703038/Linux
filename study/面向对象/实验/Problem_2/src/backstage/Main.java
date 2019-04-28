//package backstage;
//
//import java.util.Scanner;
//
//public class Main {
//	
//	public static void main(String[] args) {
//		Digital digi = new Digital();
//		Scanner in = new Scanner(System.in);
//		int tot=0;
//		while (true) {
//			System.out.print("请输入人要猜的数：");
//			int x=in.nextInt();
//			tot++;
//			String string=digi.check(x);
//			if (string.charAt(0)=='4') {
//				System.out.println("恭喜你猜对了，一共猜了"+tot+"次。");
//				break;
//			}
//			else {
//				System.out.println(string);
//			}
//		}
//	}
//}
