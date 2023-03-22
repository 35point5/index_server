package servlet;

import com.alibaba.fastjson.JSONObject;
import index.IndexEntry;
import index.IndexManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

@WebServlet(urlPatterns = "/index/api/query")
public class QueryIndex extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String graphName = req.getParameter("dataset");
        String indexName = req.getParameter("index_name");
        String node = req.getParameter("v_id");
        PrintWriter pw = resp.getWriter();
        JSONObject js=new JSONObject();
        List<IndexEntry> idx=IndexManager.getInstance().QueryIndex(graphName,indexName,node);
        if (idx==null){
            js.put("message","Query failed!");
            pw.write(js.toString());
        } else {
            js.put("message","Query success!");
            js.put("results",idx);
            pw.write(js.toString());
        }
        pw.flush();
    }
}