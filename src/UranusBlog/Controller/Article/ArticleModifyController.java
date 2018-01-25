package UranusBlog.Controller.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class ArticleModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties dbProps = new Properties();
        dbProps.setProperty("url", "jdbc:mysql://db.sporadic.nz/xliu617");
        dbProps.setProperty("user", "xliu617");
        dbProps.setProperty("password", "123");
    //------------------------------------------------------------------------------------------------------------------------------
        //String articleid= req.getParameter("articleid");
        //String userId=req.getParameter("userId");
        String userId="2";
        String articleid="2";
        String modifiedTitle= "hello louis this is the test for article modifier";
        //String modifiedTitle= req.getParameter("newtitle");
        String modifiedContent=req.getParameter("newcontent");
        String modifiedPostTime=req.getParameter("newposttime");
        int modifiedPrivacy=Integer.parseInt(req.getParameter("privacy"));
    // Test Printer
        PrintWriter out = resp.getWriter();

        try (Connection conn1 = DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("user"), dbProps.getProperty("password"))) {
            try (PreparedStatement stmt = conn1.prepareStatement("call GetArticleById (?,?)")) {
                stmt.setString(1, userId);
                stmt.setString(2, articleid);
                try (ResultSet r = stmt.executeQuery()) {
                    while (r.next()) {
                        if (modifiedTitle.equals(null) || modifiedTitle.equals("")){
                            modifiedTitle=r.getString(3);
                        }
                        if (modifiedContent.equals(null) || modifiedContent.equals("")){
                            modifiedContent=r.getString(4);
                        }
                        if (modifiedPostTime.equals(null) || modifiedPostTime.equals("")){
                            modifiedContent=r.getString(7);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        boolean completed=false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        try (Connection conn2 = DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("user"), dbProps.getProperty("password"))) {
            try (PreparedStatement stmt = conn2.prepareStatement("call UpdateArticle (?,?,?,?,?)")) {
                stmt.setString(1,articleid);
                stmt.setString(2,modifiedTitle);
                stmt.setString(3,modifiedContent);
                stmt.setString(4,modifiedPostTime);
                stmt.setInt(5,modifiedPrivacy);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 }
