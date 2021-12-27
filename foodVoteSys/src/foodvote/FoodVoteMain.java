package foodvote;

import java.util.List;
import java.util.Scanner;

/*
 * 1.설문참여(이름만 셀렉해서 불러오고 0은 직접입력)
 * 2.설문현황보기(select all)
 * 
 * */
public class FoodVoteMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		FoodVoteDAO dao = new FoodVoteDAO();
		boolean flag = true;
		
		while (flag) {
			List<FoodVoteDTO> result = null;
			System.out.println("<<<<< 오늘 저녁 뭐먹을래? >>>>>");
			System.out.println("1.설문참여하기");
			System.out.println("2.투표현황보기");
			System.out.println("3.종료");
			System.out.print(">> ");
			int menu = scan.nextInt();
			
			switch (menu) {
			case 1:
				//select
				result =  dao.statusVote();	//목록 출력
				for(FoodVoteDTO dto : result) {
					int num = dto.getNum();
					String name = dto.getName();
					
					System.out.println(num+")"+name);
				}
				System.out.println("0)기타(직접입력)");
				System.out.print("선택 >>");
				int choice = scan.nextInt();
				if(choice == 0) {
					System.out.print("음식 이름 입력 >>");
					scan.nextLine();
					String newFood = scan.nextLine();
					if(dao.foodExist(newFood)) {
						System.out.println("같은 음식이 존재합니다.");
					}else {						
						int res = dao.vote(newFood);
						System.out.println(res + "개 추가 완료!");
					}
				}else {
					int res = dao.vote(choice);
					System.out.println(res+"개 투표완료!");
				}
				break;
			case 2:
				//select * from "FOODVOTE";
				result =  dao.statusVote();	//목록 출력
				for(FoodVoteDTO dto : result) {
					int num = dto.getNum();
					String name = dto.getName();
					int vote = dto.getVote();
					System.out.println(num+")"+name+" : "+vote+"표");
				}
				System.out.println();
				break;
			default:
				System.out.println("설문조사를 종료합니다.");
				flag = false;
			}
		}
		
		scan.close();
		
	}

}
