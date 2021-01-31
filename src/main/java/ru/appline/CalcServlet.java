package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null ){
                jb.append(line);
            }
        } catch (Exception e){
            System.out.println("error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        String sign = jobj.get("sign").getAsString();
        double result = 0;

        PrintWriter pw = response.getWriter();

        switch (sign){
            case ("+"): result = a + b;
            break;
            case ("-"): result = a - b;
            break;
            case ("*"): result = a * b;
            break;
            case ("/"): result = a / b;
            break;
            default: pw.println("Введите корректный знак (+,-,*,/)");
            }

        response.setContentType("application/json;charset=utf-8");

            pw.println(gson.toJson(result));
    }
}