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
import java.util.*;

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
        PrintWriter out = resp.getWriter();

        Enumeration parameterNames = req.getParameterNames();

        while(parameterNames.hasMoreElements())
        {
            Object obj = parameterNames.nextElement();
            String fieldName = (String) obj;
            String fieldValue = req.getParameter(fieldName);
            out.println(fieldName + " = " + fieldValue );
        }


        }
}
