package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.GakuseiDataBean;
import conn.Conng;

public class GakuseiDAO extends Conng implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAXROW = 10;

	Connection con;

	public GakuseiDAO() {
		con = conn(); // --- スーパークラスのデータベース接続部分を呼び出す。connという変数を利用して参照できる。
	}
	
	public List<GakuseiDataBean> getAllData(int page, String keyword) {
		List<GakuseiDataBean> data = new ArrayList<GakuseiDataBean>();
		try {
			if(keyword == null || keyword == "") {
				keyword = "";
			}
			String sql = "select * from sample where name like ? limit ?,?";
			PreparedStatement st = con.prepareStatement(sql);
			int baseRow = (page - 1) * MAXROW;
			st.setString(1, "%" + keyword + "%");
			st.setInt(2, baseRow);
			st.setInt(3, MAXROW);
			ResultSet rs = st.executeQuery(); // ---

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String huri = rs.getString("huri");
				// ---- ArrayListへデータを追加する
				GakuseiDataBean b = new GakuseiDataBean();
				b.setId(id);
				b.setName(name);
				b.setHuri(huri);
				data.add(b);

			}
		} catch (Exception e) {
			data = null;
		}
		return data;
	}
	
	public List<GakuseiDataBean> getData(String keyword) {
		List<GakuseiDataBean> data = new ArrayList<GakuseiDataBean>();
		try {
			String sql = "select * from sample where name like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String huri = rs.getString("huri");
				// ---- ArrayListへデータを追加する
				GakuseiDataBean b = new GakuseiDataBean();
				b.setId(id);
				b.setName(name);
				b.setHuri(huri);
				data.add(b);

			}
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
}
