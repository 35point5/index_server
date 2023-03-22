package servlet;

import com.alibaba.fastjson.JSON;
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

@WebServlet(urlPatterns = "/index/api/operator")
public class Operator extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String graphName = req.getParameter("dataset");
        String indexName = req.getParameter("index_name");
        String operatorType = req.getParameter("operator_type");
        String paraStr = req.getParameter("operator_param");
        System.out.println(paraStr);
        JSONObject para= JSONObject.parseObject(paraStr);
        PrintWriter pw = resp.getWriter();
        JSONObject js=new JSONObject();
        Object res=IndexManager.getInstance().Operator(graphName,indexName,operatorType,para);
        if (res==null){
            js.put("message","Operator failed!");
            pw.write(js.toString());
        } else {
            js.put("message","Operator success!");
            js.put("results",res);
            pw.write(js.toString());
        }
        pw.flush();
    }
}