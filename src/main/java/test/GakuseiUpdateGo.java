package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GakuseiDataBean;
import dao.GakuseiDAO;

@WebServlet("/updatego")
public class GakuseiUpdateGo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GakuseiUpdateGo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//--- 変更ボタンクリック以外は一覧へ戻す
		String submit = (String) request.getParameter("submit");
		if (submit == null || !submit.equals("1")) {
			response.sendRedirect("displayall");
			return;
		}

		//--- エラーメッセージを格納する配列
		List<String> list = new ArrayList();

		//--- フォームデータの取得
		GakuseiDataBean bean = new GakuseiDataBean();
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

		//--- IDの設定（エラーチェックもする）
		try {
			bean.setId(Integer.parseInt(strId));
		} catch (Exception e) {
			list.add("IDが数値ではありません。 ");
		}
		
		if (strJoutai.isEmpty()) {
			list.add("在籍状態が未設定になっています");
		} else {
			bean.setJoutai(strJoutai);
		}
		
		if (strKakuteibi.isEmpty()) {
			list.add("在籍状態確定日が未設定になっています");
		} else {
			bean.setKakuteibi(strKakuteibi);
		}

		//---　氏名の設定（エラーチェックもする）
		if (strGakusei_name.isEmpty()) {
			list.add("学生氏名の値が未設定になっています");
		} else {
			bean.setGakusei_name(strGakusei_name);
		}
		
		if (strGakusei_nameH.isEmpty()) {
			list.add("学生氏名のふりがなの値が未設定になっています");
		} else {
			bean.setGakusei_nameH(strGakusei_nameH);
		}
		
		if (strBorn.isEmpty()) {
			list.add("生年月日の値が未設定になっています");
		} else {
			bean.setBorn(strBorn);
		}
		
		if (strYuubinnbanngou.isEmpty()) {
			list.add("本人郵便番号が未設定になっています");
		} else {
			bean.setYuubinnbanngou(strYuubinnbanngou);
		}
		
		if (strGakusei_juusyo.isEmpty()) {
			list.add("本人住所の値が未設定になっています");
		} else {
			bean.setGakusei_juusyo(strGakusei_juusyo);
		}
		
		if (strGakusei_phone.isEmpty()) {
			list.add("本人電話番号の値が未設定になっています");
		} else {
			bean.setGakusei_phone(strGakusei_phone);
		}
		
		if (strGakusei_mail.isEmpty()) {
			list.add("本人メールアドレスの値が未設定になっています");
		} else {
			bean.setGakusei_mail(strGakusei_mail);
		}
		
		if (strHogosya_name.isEmpty()) {
			list.add("保護者氏名の値が未設定になっています");
		} else {
			bean.setHogosya_name(strHogosya_name);
		}
		
		if (strHogosya_nameH.isEmpty()) {
			list.add("保護者氏名のふりがなの値が未設定になっています");
		} else {
			bean.setHogosya_nameH(strHogosya_nameH);
		}
		
		if (strHogosya_yuubinnbanngou.isEmpty()) {
			list.add("保護者郵便番号の値が未設定になっています");
		} else {
			bean.setHogosya_yuubinnbanngou(strHogosya_yuubinnbanngou);
		}
		
		if (strHogosya_juusyo.isEmpty()) {
			list.add("保護者住所の値が未設定になっています");
		} else {
			bean.setHogosya_juusyo(strHogosya_juusyo);
		}
		
		if (strHogosya_phone.isEmpty()) {
			list.add("保護者電話番号の値が未設定になっています");
		} else {
			bean.setHogosya_phone(strHogosya_phone);
		}
		
		if (strHogosya_mail.isEmpty()) {
			list.add("保護者メールアドレスの値が未設定になっています");
		} else {
			bean.setHogosya_mail(strHogosya_mail);
		}

		//--- DAOのupdatedataを呼び出す
		if (list.size() == 0) {
			GakuseiDAO dao = new GakuseiDAO();
			int result = dao.updateData(bean);
			if (result == 1) {
				list.add("修正完了しました。");
			} else {
				list.add("修正できませんでした。");
			}
		}

		//--- 結果表示のjspへ遷移
		request.setAttribute("message", list);
		request.getRequestDispatcher("sampleUpdateGo.jsp").forward(request, response);
	}

}
