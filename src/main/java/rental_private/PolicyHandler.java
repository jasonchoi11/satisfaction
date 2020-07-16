package rental_private;

import rental_private.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    SatisfactionRepository satisfactionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverChecked_SatisfactionSurvey(@Payload Checked checked){

        if(checked.isMe()){
            Satisfaction satisfaction = new Satisfaction();
            satisfaction.setCheckId(checked.getId());
            satisfaction.setScore(checked.getScore());
            satisfactionRepository.save(satisfaction);
            System.out.println("##### listener SatisfactionSurvey : " + checked.toJson());
        }
    }

}
