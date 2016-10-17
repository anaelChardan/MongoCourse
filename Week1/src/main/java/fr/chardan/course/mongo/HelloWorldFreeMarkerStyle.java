package fr.chardan.course.mongo;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreeMarkerStyle.class, "/");

        get("/hello", (req, res) -> {
                    StringWriter writer = new StringWriter();
                    try {
                        Template template = configuration.getTemplate("hello.ftl");
                        Map<String, Object> helloMap = new HashMap<String, Object>();
                        helloMap.put("name", "Freemarker");
                        template.process(helloMap, writer);
                        System.out.println(writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return writer;
            }
        );
    }
}
