package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GakuseiDataBean;
import dao.GakuseiDAO;

@WebServlet("/insert")
public class GakuseiInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GakuseiInsert() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String strId = request.getParameter("id");
		String strJoutai = request.getParameter("joutai");
		String strKakuteibi = request.getParameter("kakuteibi");
		String strGakusei_name = request.getParameter("gakusei_name");
		String strGakusei_nameH = request.getParameter("gakusei_nameH");
		String strBorn = request.getParameter("born");
		String strYuubinnbanngou = request.getParameter("yuubinnbanngou");
		String strGakusei_juusyo = request.getParameter("gakusei_juusyo");
		String strGakusei_phone = request.getParameter("gakusei_phone");
		String strGakusei_mail = request.getParameter("gakusei_mail");
		String strHogosya_name = request.getParameter("hogosya_name");
		String strHogosya_nameH = request.getParameter("hogosya_nameH");
		String strHogosya_yuubinnbanngou = request.getParameter("hogosya_yuubinnbanngou");
		String strHogosya_juusyo = request.getParameter("hogosya_juusyo");
		String strHogosya_phone = request.getParameter("hogosya_phone");
		String strHogosya_mail = request.getParameter("hogosya_mail");
		
		List<String> mess = new ArrayList<String>();//---メッセージ格納用配列
		
		//---エラーチェック
		boolean errSw = false;
		// 送信されたデータに誤りがあればtrueにする
		int id = -1; 
		// ダミーの値をとりあえず入れておく//---番号が空か、および値が数値かを判断
		if (strId == null || strId == "") {
			mess.add("番号が入力されていません。");
			errSw = true;
		}else {
			try {
				id = Integer.parseInt(strId);
			} catch(Exception e) {
			mess.add("番号が数字ではありません。");
			errSw = true;
			}
		}
		
		//---氏名が空かどうか判断
		if (strJoutai == null || strJoutai == "") {
			mess.add("氏名が入力されていません");
			errSw = true;
		}
		
		//確定日の判断
		if (strKakuteibi == null || strKakuteibi == "") {
			mess.add("確定日が入力されていません");
			errSw = true;
		}
		
		//学生名（漢字）の判断
		if (strGakusei_name == null || strGakusei_name == "") {
			mess.add("名前（漢字）が入力されていません");
			errSw = true;
		}
		
		//学生名（かな）の判断
		if (strGakusei_nameH == null || strGakusei_nameH == "") {
			mess.add("名前（かな）が入力されていません");
			errSw = true;
		}
		
		//生年月日の判断
		if (strBorn == null || strBorn == "") {
			mess.add("生年月日が入力されていません");
			errSw = true;
		}
		
		//郵便番号の判断
		String strYuubinnbanngouPattern = "^[0-9]+{7}$";
		Pattern Yuubinnbanngoup = Pattern.compile(strYuubinnbanngouPattern);
		Matcher Yuubinnbanngoum = Yuubinnbanngoup.matcher(strYuubinnbanngou);
		if (Yuubinnbanngoum.find()) {/* findメソッドがtrueなら一致する*/
			System.out.println("一致します");
		} else {
			System.out.println("郵便番号が不一致です");
		}
		
		//住所の判断
		if (strGakusei_juusyo == null || strGakusei_juusyo == "") {
			mess.add("住所が入力されていません");
			errSw = true;
		}
		
		//電話番号の判断
		String strphonePattern = "^[0-9][0-9¥¥-]{12}$";
		Pattern phonep = Pattern.compile(strphonePattern);
		Matcher phonem = phonep.matcher(strGakusei_phone);
		if (phonem.find()) {/* findメソッドがtrueなら一致する*/
			System.out.println("一致します");
		} else {
			System.out.println("電話番号が不一致です");
		}
		
		//保護者名（漢字）の判断
		if (strHogosya_name == null || strHogosya_name == "") {
			mess.add("保護者名（漢字）が入力されていません");
			errSw = true;
		}
		
		//保護者名（かな）の判断
		if (strHogosya_nameH == null || strHogosya_nameH == "") {
			mess.add("保護者名（かな）が入力されていません");
			errSw = true;
		}
		
		//保護者郵便番号の判断
		String strHogosya_yuubinnbanngouPattern = "^[0-9]+{7}$";
		Pattern Hogosya_yuubinnbanngoup = Pattern.compile(strHogosya_yuubinnbanngouPattern);
		Matcher Hogosya_yuubinnbanngoum = Hogosya_yuubinnbanngoup.matcher(strHogosya_yuubinnbanngou);
		if (Hogosya_yuubinnbanngoum.find()) {/* findメソッドがtrueなら一致する*/
			System.out.println("保護者郵便番号一致します");
		} else {
			System.out.println("保護者郵便番号が不一致です");
		}
		
		//保護者住所の判断
		if (strHogosya_juusyo == null || strHogosya_juusyo == "") {
			mess.add("保護者住所が入力されていません");
			errSw = true;
		}
		
		//保護者電話番号の判断
		String strHogosya_phonePattern = "^[0-9][0-9¥¥-]{12}$";
		Pattern Hogosya_phonep = Pattern.compile(strHogosya_phonePattern);
		Matcher Hogosya_phonem = Hogosya_phonep.matcher(strHogosya_phone);
		if (Hogosya_phonem.find()) {/* findメソッドがtrueなら一致する*/
			System.out.println("保護者電話番号一致します");
		} else {
			System.out.println("保護者電話番号が不一致です");
		}
		
		//---送信されたデータにエラーが無ければ登録する
		if (!errSw) {
			GakuseiDAO dao = new GakuseiDAO();
			//---insertDataは追加したレコード数を返すので0かどうかで成功かを判断する
			//if (!dao.isExists(strId)) {
				GakuseiDataBean bean = new GakuseiDataBean();
				bean.setId(id);
				bean.setJoutai(strJoutai);
				bean.setKakuteibi(strKakuteibi);
				bean.setGakusei_name(strGakusei_name);
				bean.setGakusei_nameH(strGakusei_nameH);
				bean.setBorn(strBorn);
				bean.setYuubinnbanngou(strYuubinnbanngou);
				bean.setGakusei_juusyo(strGakusei_juusyo);
				bean.setGakusei_phone(strGakusei_phone);
				bean.setGakusei_mail(strGakusei_mail);
				bean.setHogosya_name(strHogosya_name);
				bean.setHogosya_nameH(strHogosya_nameH);
				bean.setHogosya_yuubinnbanngou(strHogosya_yuubinnbanngou);
				bean.setHogosya_juusyo(strHogosya_juusyo);
				bean.setHogosya_phone(strHogosya_phone);
				bean.setHogosya_mail(strHogosya_mail);
				int result = dao.insertData(bean);
				if(result == 0) {
				mess.add("登録できませんでした");
				} else{
				mess.add("登録完了しました。");
				}
			}else {
			mess.add("IDが重複しています。");
			}
		//}
		
		//---メッセージ表示用のJSPへ遷移
		request.setAttribute("message", mess);
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}
=======

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GakuseiDataBean;
import dao.GakuseiDAO;

@WebServlet("/insert")
public class GakuseiInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GakuseiInsert() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String strId = request.getParameter("id");
		String strJoutai = request.getParameter("joutai");
		String strKakuteibi = request.getParameter("kakuteibi");
		String strGakusei_name = request.getParameter("gakusei_name");
		String strGakusei_nameH = request.getParameter("gakusei_nameH");
		String strBorn = request.getParameter("born");
		String strYuubinnbanngou = request.getParameter("yuubinnbanngou");
		String strGakusei_juusyo = request.getParameter("gakusei_juusyo");
		String strGakusei_phone = request.getParameter("gakusei_phone");
		String strGakusei_mail = request.getParameter("gakusei_mail");
		String strHogosya_name = request.getParameter("hogosya_name");
		String strHogosya_nameH = request.getParameter("hogosya_nameH");
		String strHogosya_yuubinnbanngou = request.getParameter("hogosya_yuubinnbanngou");
		String strHogosya_juusyo = request.getParameter("hogosya_juusyo");
		String strHogosya_phone = request.getParameter("hogosya_phone");
		String strHogosya_mail = request.getParameter("hogosya_mail");

		List<String> message = new ArrayList<String>();

		// --- エラーチェック
		boolean errSw = false; // 送信されたデータに誤りがあればtrueにする
		int id = -1; // ダミーの値をとりあえず入れておく
		// --- 番号が空か、および値が数値かを判断
		if (strId == null || strId == "") {
			message.add("番号が入力されていません");
			errSw = true;
		} else {
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				message.add("番号が数字ではありません");
				errSw = true;
			}
		}
		if (strJoutai == null || strJoutai == "") {
			message.add("状態が入力されていません");
			errSw = true;
		}
		if (strKakuteibi == null || strKakuteibi == "") {
			message.add("確定日が入力されていません");
			errSw = true;
		}
		if (strGakusei_name == null || strGakusei_name == "") {
			message.add("氏名が入力されていません");
			errSw = true;
		}
		
		if (strGakusei_nameH == null || strGakusei_nameH == "") {
			message.add("ふりがなが入力されていません");
			errSw = true;
		}
		if (strBorn == null || strBorn == "") {
			message.add("生年月日が入力されていません");
			errSw = true;
		}
		if (strYuubinnbanngou == null || strYuubinnbanngou == "") {
			message.add("郵便番号が入力されていません");
			errSw = true;
		}
		if (strGakusei_juusyo == null || strGakusei_juusyo == "") {
			message.add("住所が入力されていません");
			errSw = true;
		}
		if (strGakusei_phone == null || strGakusei_phone == "") {
			message.add("電話番号が入力されていません");
			errSw = true;
		}
		if (strGakusei_mail == null || strGakusei_mail == "") {
			message.add("メールアドレスが入力されていません");
			errSw = true;
		}
		if (strHogosya_name == null || strHogosya_name == "") {
			message.add("保護者の氏名が入力されていません");
			errSw = true;
		}
		if (strHogosya_nameH == null || strHogosya_nameH == "") {
			message.add("保護者のふりがなが入力されていません");
			errSw = true;
		}
		if (strHogosya_yuubinnbanngou == null || strHogosya_yuubinnbanngou == "") {
			message.add("保護者の郵便番号が入力されていません");
			errSw = true;
		}
		if (strHogosya_juusyo == null || strHogosya_juusyo == "") {
			message.add("保護者の住所が入力されていません");
			errSw = true;
		}
		if (strHogosya_phone == null || strHogosya_phone == "") {
			message.add("保護者の電話番号が入力されていません");
			errSw = true;
		}
		if (strHogosya_mail == null || strHogosya_mail == "") {
			message.add("保護者のメールアドレスが入力されていません");
			errSw = true;
		}
		// --- 送信されたデータにエラーが無ければ登録する
		if (!errSw) {
			GakuseiDAO dao = new GakuseiDAO();
			// --- insertDataは追加したレコード数を返すので0かどうかで成功か判断する
			if (!dao.isExists(strId)) {
				GakuseiDataBean bean = new GakuseiDataBean();
				bean.setId(id);
				bean.setJoutai(strJoutai);
				bean.setKakuteibi(strKakuteibi);
				bean.setGakusei_name(strGakusei_name);
				bean.setGakusei_nameH(strGakusei_nameH);
				bean.setBorn(strBorn);
				bean.setYuubinnbanngou(strYuubinnbanngou);
				bean.setGakusei_juusyo(strGakusei_juusyo);
				bean.setGakusei_phone(strGakusei_phone);
				bean.setGakusei_mail(strGakusei_mail);
				bean.setHogosya_name(strHogosya_name);
				bean.setHogosya_nameH(strHogosya_nameH);
				bean.setHogosya_yuubinnbanngou(strHogosya_yuubinnbanngou);
				bean.setHogosya_juusyo(strHogosya_juusyo);
				bean.setHogosya_phone(strHogosya_phone);
				bean.setHogosya_mail(strHogosya_mail);
				int result = dao.insertData(bean);
				if (result == 0) {
					message.add("登録できませんでした");
				} else {
					message.add("登録完了しました");
				}
			} else {
				message.add("IDが重複しています。");
			}

		}

		// --- メッセージ表示用のjspへの遷移
		request.setAttribute("message", message);
		request.getRequestDispatcher("insert.jsp").forward(request, response);

	}

}
