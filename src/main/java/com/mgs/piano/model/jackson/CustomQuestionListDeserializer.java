package com.mgs.piano.model.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mgs.piano.model.Question;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Класс, обеспечивающий десеарилизацию для jackson
 */
public class CustomQuestionListDeserializer extends StdDeserializer<List<Question>> {
    CustomQuestionListDeserializer(){
        this(null);
    }
    CustomQuestionListDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public List<Question> deserialize(JsonParser parser, DeserializationContext deserializer) {
        List<Question> result = new ArrayList<>();
        ObjectCodec codec = parser.getCodec();
        JsonNode rootNode = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //we need set Timezone to UTC or else in different timezones will be different results
        simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        try {
            rootNode = codec.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Iterator<JsonNode> it = rootNode.elements(); it.hasNext(); ) {
            JsonNode node = it.next();


            Question question = new Question();
            JsonNode dateNode = node.get("creation_date");
            String dateAsText = dateNode.asText();

            long unixSeconds = Long.parseLong(dateAsText);
            // convert seconds to milliseconds
            Date date = new Date(unixSeconds*1000L);
            question.setDate(simpleDateFormat.format(date));

            JsonNode titleNode = node.get("title");
            String title = titleNode.asText();
            //преобразуем строку с названием в читаемый вид
            String parsedTitle = Jsoup.parse(title).text();
            question.setTitle(parsedTitle);

            JsonNode ownerNode = node.path("owner").get("display_name");
            String owner = ownerNode.asText();
            question.setOwner(owner);

            JsonNode isAnsweredNode = node.get("is_answered");
            boolean isAnswered = isAnsweredNode.asBoolean();
            question.setIsAnswered(isAnswered);

            JsonNode linkNode = node.get("link");
            String link = linkNode.asText();
            question.setLink(link);

            result.add(question);
        }
        return result;
    }
}
