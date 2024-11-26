package kafka.bank.infra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import org.apache.kafka.common.serialization.Serializer;

import java.time.LocalDateTime;

public class KafkaJsonSerializer<T> implements Serializer<T> {

    private final static Gson GSON = createGson();


    private static Gson createGson() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, getTypeAdapter())
                .create();
        return gson;

    }

    private static JsonSerializer<LocalDateTime> getTypeAdapter() {
        return (localDateTime, _, _) -> new JsonPrimitive(localDateTime.toString());
    }

    @Override
    public byte[] serialize(String s, T t) {
        return GSON.toJson(t).getBytes();
    }
}
