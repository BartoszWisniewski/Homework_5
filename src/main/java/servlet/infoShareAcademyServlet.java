package servlet;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import template.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/infoShareAcademy")
public class infoShareAcademyServlet extends HttpServlet {


    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String TEMPLATE_NAME = "infoShareTemplate";

        Map<String, Object> model = new HashMap<>();
        model.put("date", LocalDateTime.now());
        model.put("grup", "jjdd5-zajavka");
        model.put("student", "Bartosz Wisniewski");

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param1new = req.getParameter("param1");
        String param2new = req.getParameter("param2");

        String param3new = req.getParameter("param3");
        String[] paramArray = param3new.split("\\s+");

        Map<String, Object> modelPost = new HashMap<>();
        modelPost.put("param1", param1new);
        modelPost.put("param2", param2new);

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        modelPost.entrySet().forEach(e->{
            out.println(e.getKey()+ " = " + e.getValue());
            out.println("<br>");
        });
        for(int i=0; i< paramArray.length;i++){
            out.println("param3 = "+ paramArray[i]);
            out.println("<br>");
        }
    }
}
