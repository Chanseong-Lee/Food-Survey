package foodvote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverLoader {
	private static DriverLoader instance;

	private static String user = "c##tester";
	private static String password = "1234";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";

	static {	//스태틱에서는 쓰레드동기화가 사실상 필요는 없다.
		synchronized (DriverLoader.class) {	//쓰레드 동기화, 여러쓰레드가 일을 진행하더라도 무조건 한번만 실행되도록 보장
			if (instance == null) { // 객체가 생성되어있지 않을때만 생성되도록
				instance = new DriverLoader();
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					System.out.println("Class Loading Success");
				} catch (ClassNotFoundException e) {
					System.out.println("Class Loading Failed");
					e.printStackTrace();
				}
			}
		}
	}

	private DriverLoader() {} //private 생성자가 있어야 싱글톤으로써 의미가 있음

	public static DriverLoader getInstance() throws SQLException {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}
