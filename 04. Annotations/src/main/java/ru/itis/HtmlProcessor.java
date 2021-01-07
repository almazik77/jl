package ru.itis;

import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            // получаем путь с class-файлам
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // создадим путь к html-файлу
            // User.class -> User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            // формируем путь для записи данных
            Path out = Paths.get(path);
            try {
                Configuration cfg = new Configuration(new Version("2.3.29"));
                cfg.setDirectoryForTemplateLoading(new File("D:\\JAVA_lab\\homeworks\\Annotations\\src\\main\\resources"));
                cfg.setDefaultEncoding("UTF-8");
                Map<String, Object> root = new HashMap<>();
                Template template = cfg.getTemplate("form.ftl");
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                root.put("action", annotation.action());
                root.put("method", annotation.method());
                List<Input> inputs = new ArrayList<>();
                for (Element element1 : element.getEnclosedElements()) {
                    HtmlInput htmlInput = element1.getAnnotation(HtmlInput.class);
                    if (htmlInput != null) {
                        inputs.add(Input.builder()
                                .name(htmlInput.name())
                                .type(htmlInput.type())
                                .placeholder(htmlInput.placeholder())
                                .build());
                    }
                }
                root.put("inputs", inputs);
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                template.process(root, writer);
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}
