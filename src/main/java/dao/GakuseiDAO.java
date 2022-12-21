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
			String sql = "select * from gakusei_master where gakusei_name like ? limit ?,?";
			PreparedStatement st = con.prepareStatement(sql);
			int baseRow = (page - 1) * MAXROW;
			st.setString(1, "%" + keyword + "%");
			st.setInt(2, baseRow);
			st.setInt(3, MAXROW);
			System.out.println(st.toString());
			ResultSet rs = st.executeQuery(); // -

			while (rs.next()) {
				int id = rs.getInt("id");
				String joutai = rs.getString("joutai");
				String kakuteibi = rs.getString("kakuteibi");
				String gakusei_name = rs.getString("gakusei_name");
				String gakusei_nameH = rs.getString("gakusei_nameH");
				String born = rs.getString("born");
				String yuubinnbanngou = rs.getString("yuubinnbanngou");
				String gakusei_juusyo = rs.getString("gakusei_juusyo");
				String gakusei_phone = rs.getString("gakusei_phone");
				String gakusei_mail = rs.getString("gakusei_mail");
				String hogosya_name = rs.getString("hogosya_name");
				String hogosya_nameH = rs.getString("hogosya_nameH");
				String hogosya_yuubinnbanngou = rs.getString("hogosya_yuubinnbanngou");
				String hogosya_juusyo = rs.getString("hogosya_juusyo");
				String hogosya_phone = rs.getString("hogosya_phone");
				String hogosya_mail = rs.getString("hogosya_mail");
				// ---- ArrayListへデータを追加する
				GakuseiDataBean b = new GakuseiDataBean();
				b.setId(id);
				b.setJoutai(joutai);
				b.setKakuteibi(kakuteibi);
				b.setGakusei_name(gakusei_name);
				b.setGakusei_nameH(gakusei_nameH);
				b.setBorn(born);
				b.setYuubinnbanngou(yuubinnbanngou);
				b.setGakusei_juusyo(gakusei_juusyo);
				b.setGakusei_phone(gakusei_phone);
				b.setGakusei_mail(gakusei_mail);
				b.setHogosya_name(hogosya_name);
				b.setHogosya_nameH(hogosya_nameH);
				b.setHogosya_yuubinnbanngou(hogosya_yuubinnbanngou);
				b.setHogosya_juusyo(hogosya_juusyo);
				b.setHogosya_phone(hogosya_phone);
				b.setHogosya_mail(hogosya_mail);
				data.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
	
	public List<GakuseiDataBean> getData(String keyword) {
		List<GakuseiDataBean> data = new ArrayList<GakuseiDataBean>();
		try {
			String sql = "select * from team_c_db.gakusei_master where gakusei_name "
					+ " like ? or gakusei_nameH like ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + keyword + "%");
			st.setString(2, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String joutai = rs.getString("joutai");
				String kakuteibi = rs.getString("kakuteibi");
				String gakusei_name = rs.getString("gakusei_name");
				String gakusei_nameH = rs.getString("gakusei_nameH");
				String born = rs.getString("born");
				String yuubinnbanngou = rs.getString("yuubinnbanngou");
				String gakusei_juusyo = rs.getString("gakusei_juusyo");
				String gakusei_phone = rs.getString("gakusei_phone");
				String gakusei_mail = rs.getString("gakusei_mail");
				String hogosya_name = rs.getString("hogosya_name");
				String hogosya_nameH = rs.getString("hogosya_nameH");
				String hogosya_yuubinnbanngou = rs.getString("hogosya_yuubinnbanngou");
				String hogosya_juusyo = rs.getString("hogosya_juusyo");
				String hogosya_phone = rs.getString("hogosya_phone");
				String hogosya_mail = rs.getString("hogosya_mail");
				// ---- ArrayListへデータを追加する
				GakuseiDataBean b = new GakuseiDataBean();
				b.setId(id);
				b.setJoutai(joutai);
				b.setKakuteibi(kakuteibi);
				b.setGakusei_name(gakusei_name);
				b.setGakusei_nameH(gakusei_nameH);
				b.setBorn(born);
				b.setYuubinnbanngou(yuubinnbanngou);
				b.setGakusei_juusyo(gakusei_juusyo);
				b.setGakusei_phone(gakusei_phone);
				b.setGakusei_mail(gakusei_mail);
				b.setHogosya_name(hogosya_name);
				b.setHogosya_nameH(hogosya_nameH);
				b.setHogosya_yuubinnbanngou(hogosya_yuubinnbanngou);
				b.setHogosya_juusyo(hogosya_juusyo);
				b.setHogosya_phone(hogosya_phone);
				b.setHogosya_mail(hogosya_mail);
				data.add(b);

			}
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
	
	public GakuseiDataBean getOneRec(String id) {
		GakuseiDataBean data = new GakuseiDataBean();
		try {
			String sql = "select * from gakusei_master where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(id));
			ResultSet rs = st.executeQuery();
			rs.next();
			data.setId(rs.getInt("id"));
			data.setJoutai(rs.getString("joutai"));
			data.setKakuteibi(rs.getString("kakuteibi"));
			data.setGakusei_name(rs.getString("gakusei_name"));
			data.setGakusei_nameH(rs.getString("gakusei_nameH"));
			data.setBorn(rs.getString("born"));
			data.setYuubinnbanngou(rs.getString("yuubinnbanngou"));
			data.setGakusei_juusyo(rs.getString("gakusei_juusyo"));
			data.setGakusei_phone(rs.getString("gakusei_phone"));
			data.setGakusei_mail(rs.getString("gakusei_mail"));
			data.setHogosya_name(rs.getString("hogosya_name"));
			data.setHogosya_nameH(rs.getString("hogosya_nameH"));
			data.setHogosya_yuubinnbanngou(rs.getString("hogosya_yuubinnbanngou"));
			data.setHogosya_juusyo(rs.getString("hogosya_juusyo"));
			data.setHogosya_phone(rs.getString("hogosya_phone"));
			data.setHogosya_mail(rs.getString("hogosya_mail"));
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
	
	public int updateData(GakuseiDataBean bean) {
		int result = -1;
		try {
			String sql = "update gakusei_master set joutai=?, "
					+ "kakuteibi=?, gakusei_name=?, gakusei_nameH=?, born=?, "
					+ "yuubinnbanngou=?, gakusei_juusyo=?, "
					+ "gakusei_phone=?, gakusei_mail=?, hogosya_name=?, "
					+ "hogosya_nameH=?, hogosya_yuubinnbangou=?, "
					+ "hogosya_juusyo=?, hogosya_phone=?, hogosya_mail=? "
					+ "where id=?"; // SQL文
			PreparedStatement st = con.prepareStatement(sql); // プリペアドステートメント
			st.setString(1, bean.getJoutai());
			st.setString(2, bean.getKakuteibi());
			st.setString(3, bean.getGakusei_name());// 氏名の登録
			st.setString(4, bean.getGakusei_nameH());
			st.setString(5, bean.getBorn());
			st.setString(6, bean.getYuubinnbanngou());
			st.setString(7, bean.getGakusei_juusyo());
			st.setString(8, bean.getGakusei_phone());
			st.setString(9, bean.getGakusei_mail());
			st.setString(10, bean.getHogosya_name());
			st.setString(11, bean.getHogosya_nameH());
			st.setString(12, bean.getHogosya_yuubinnbanngou());
			st.setString(13, bean.getHogosya_juusyo());
			st.setString(14, bean.getHogosya_phone());
			st.setString(15, bean.getHogosya_mail());
			st.setInt(16, bean.getId()); // IDの登録
			result = st.executeUpdate(); // 更新の実行
		} catch (Exception e) {
			e.printStackTrace(); // エラーなので、とりあえずスタックトレースを表示する
			result = 0;
		}
		return result;
	}
	
	public int getMaxPage(String keyword) {
		int allPage = -1;
		try {
			String sql = "select count(*) as cnt from gakusei_master where gakusei_name like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			rs.next();
			int records = rs.getInt("cnt");
			allPage = (records - 1) / MAXROW + 1;
		} catch (Exception e) {
			e.printStackTrace();
			allPage = 0;
		}
		return allPage;

	}
}
