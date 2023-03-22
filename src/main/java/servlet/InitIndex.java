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

@WebServlet(urlPatterns = "/index/api/init")
public class InitIndex extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String graphName = req.getParameter("dataset");
        String indexName = req.getParameter("index_name");
        PrintWriter pw = resp.getWriter();
        JSONObject js=new JSONObject();
        if (IndexManager.getInstance().Add(graphName,indexName)==0){
            js.put("message","Init failed!");
            pw.write(js.toString());
        } else {
            js.put("message","Init success!");
            pw.write(js.toString());
        }
        pw.flush();
    }
    public static void main(String[] args) throws Exception {
        String s="23333 66666";
        Scanner scan=new Scanner(s);
        System.out.println(scan.nextInt());
    }
}