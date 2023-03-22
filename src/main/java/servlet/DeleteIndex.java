package servlet;

import com.alibaba.fastjson.JSONObject;
import index.IndexManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet(urlPatterns = "/index/api/delete")
public class DeleteIndex extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String graphName = req.getParameter("dataset");
        String indexName = req.getParameter("index_name");
        PrintWriter pw = resp.getWriter();
        JSONObject js=new JSONObject();
        if (IndexManager.getInstance().Delete(graphName,indexName)==0){
            js.put("message","Delete failed!");
            pw.write(js.toString());
        } else {
            js.put("message","Delete success!");
            pw.write(js.toString());
        }
        pw.flush();
    }
}