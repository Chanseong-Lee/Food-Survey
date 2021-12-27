package foodvote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodVoteDAO {

	private DriverLoader dl;

	public FoodVoteDAO() {
		try {
			dl = DriverLoader.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int vote(int choice) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = "update \"FOODVOTE\" set vote=vote+1 where num = ?";
		try {
			con = dl.getConnection();
			//System.out.println("Access complete!");
			ps = con.prepareStatement(sql);
			ps.setInt(1, choice);
			res = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public int vote(String newFood) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = "insert into \"FOODVOTE\" values(\"FOOD_SEQ\".nextval, ?, 1)";
		// 새로운음식 추가되면 자동으로 1표추가되어있음
		try {
			con = dl.getConnection();
			//System.out.println("Access complete!");
			ps = con.prepareStatement(sql);
			ps.setString(1, newFood);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;

	}

	public boolean foodExist(String newFood) {
		boolean isExist = false;
		List<FoodVoteDTO> result = statusVote();
		for (FoodVoteDTO dto : result) {
			String name = dto.getName();
			if (name.equals(newFood)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	public List<FoodVoteDTO> statusVote() {
		ArrayList<FoodVoteDTO> result = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {

			sql = "select * from \"FOODVOTE\"";
			con = dl.getConnection();
			//System.out.println("Access complete!");
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("NUM");
				String name = rs.getString("NAME");
				int vote = rs.getInt("VOTE");
				FoodVoteDTO dto = new FoodVoteDTO(num, name, vote);
				result.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
