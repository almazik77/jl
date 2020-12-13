package ru.itis.mongo.hateoas.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.itis.mongo.hateoas.jpa.models.User;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

        User user = User.builder()
                .createdAt(LocalDateTime.now())
                .firstName("user")
                .lastName("user")
                .build();

//        template.insert(user, "users");
      /*  Query query = new Query();
        query.addCriteria(Criteria.where("name").is("user"));
        user = template.findOne(query, User.class);
        template.remove(user, "users");


        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("user"));
        Update update = new Update();
        update.set("name", "updatedUser");
        template.updateMulti(query, update, User.class);

       */
    }
}
