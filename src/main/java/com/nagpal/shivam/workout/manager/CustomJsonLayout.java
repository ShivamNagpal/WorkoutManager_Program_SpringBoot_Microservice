package com.nagpal.shivam.workout.manager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

@Plugin(name = "CustomJsonLayout", category = "Core", elementType = "layout", printObject = true)
public class CustomJsonLayout extends AbstractStringLayout {

    private final ObjectWriter objectWriter;

    protected CustomJsonLayout(Charset charset) {
        super(charset);
        this.objectWriter = new ObjectMapper().writer(new MinimalPrettyPrinter() {
            @Override
            public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException {
                jsonGenerator.writeRaw(",   ");
            }

            @Override
            public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException {
                jsonGenerator.writeRaw(": ");
            }

            @Override
            public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException {
                jsonGenerator.writeRaw(",   ");
            }
        });
    }

    @PluginFactory
    public static CustomJsonLayout createLayout() {
        return new CustomJsonLayout(Charset.defaultCharset());
    }

    @Override
    public String toSerializable(LogEvent event) {
        Map<String, Object> logMap = new LinkedHashMap<>();
        // TODO:
        logMap.put("timestamp", event.getTimeMillis());
        logMap.put("level", event.getLevel().toString());
        logMap.put("thread", event.getThreadName());
        logMap.put("logger", event.getLoggerName());
        logMap.putAll(event.getContextData().toMap());
        logMap.put("message", event.getMessage().getFormattedMessage());
        if (event.getThrown() != null) {
            try(StringWriter stringWriter = new StringWriter()) {
                try (PrintWriter printWriter = new PrintWriter(stringWriter, true)) {
                    event.getThrown().printStackTrace(printWriter);
                    logMap.put("stackTrace", stringWriter.toString());
                }
            } catch (IOException e) {
                // TODO
                throw new RuntimeException(e);
            }
        }

        try {
            return objectWriter.writeValueAsString(logMap) + "\n";
        } catch (Exception e) {
            return "{}\n";
        }
    }
}

