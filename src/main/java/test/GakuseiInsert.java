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
		
		String Id = request.getParameter("id");// IDの取得
		String name = request.getParameter("simei");// 氏名の取得
		
		List<String> mess = new ArrayList<String>();//---メッセージ格納用配列
		
		//---エラーチェック
		boolean errSw = false;
		// 送信されたデータに誤りがあればtrueにする
		int id = -1; 
		// ダミーの値をとりあえず入れておく//---番号が空か、および値が数値かを判断
		if (Id == null || Id == "") {
			mess.add("番号が入力されていません。");
			errSw = true;
		}else {
			try {
				id = Integer.parseInt(Id);
			} catch(Exception e) {
			mess.add("番号が数字ではありません。");
			errSw = true;
			}
		}
		
		//---氏名が空かどうか判断
		if (name == null || name == "") {
			mess.add("氏名が入力されていません");
			errSw = true;
		}
		
		//---送信されたデータにエラーが無ければ登録する
		if (!errSw) {
			GakuseiDAO dao = new GakuseiDAO();
			//---insertDataは追加したレコード数を返すので0かどうかで成功かを判断する
			if (!dao.isExists(Id)) {
				GakuseiDataBean bean = new GakuseiDataBean();
				bean.setId(id);
				bean.setName(name);
				//int result = dao.insertData(bean);
				if(result == 0) {
				mess.add("登録できませんでした");
				} else{
				mess.add("登録完了しました。");
				}
			}else {
			mess.add("IDが重複しています。");
			}
		}
		
		//---メッセージ表示用のJSPへ遷移
		request.setAttribute("message", mess);
		request.getRequestDispatcher("sampleInsert.jsp").forward(request, response);
	}
}
