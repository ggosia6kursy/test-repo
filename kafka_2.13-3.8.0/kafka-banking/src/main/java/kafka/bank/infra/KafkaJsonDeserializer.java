package kafka.bank.infra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import kafka.bank.PaymentConstants;
import kafka.bank.payment.operationlog.OperationLog;
import kafka.bank.payment.request.PaymentRequest;
import org.apache.kafka.common.serialization.Deserializer;

import java.time.LocalDateTime;

public class KafkaJsonDeserializer<T> implements Deserializer<T> {

    private final static Gson GSON = createGson();


    private static Gson createGson() {

        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, getSerilalizer())
                .registerTypeAdapter(LocalDateTime.class, getDeserializer())
                .create();

    }

    private static JsonDeserializer<LocalDateTime> getDeserializer() {
        return (jsonElement, _, _) -> LocalDateTime.parse(jsonElement.getAsString());
    }

    private static JsonSerializer<LocalDateTime> getSerilalizer() {
        return (localDateTime, _, _) -> new JsonPrimitive(localDateTime.toString());
    }


    @Override
    public T deserialize(String topic, byte[] payload) {
        String s = new String(payload);
        if (topic.equals(PaymentConstants.REQUEST_KAFKA_TOPIC)) {
            return (T) GSON.fromJson(s, PaymentRequest.class);
        } else if (topic.equals(PaymentConstants.OPERATION_LOG_KAFKA_TOPIC)) {
            return (T) GSON.fromJson(s, OperationLog.class);
        }
        throw new IllegalArgumentException("Unknown topic " + topic);
    }
}
