package at.kaindorf.employeedb.bl;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    // 12/09/1953
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return LocalDate.parse(jp.readValueAs(String.class), DTF);
    }
}
