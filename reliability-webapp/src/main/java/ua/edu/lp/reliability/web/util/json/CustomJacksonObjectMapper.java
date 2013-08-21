package ua.edu.lp.reliability.web.util.json;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import ua.edu.lp.reliability.web.util.Constants;

public class CustomJacksonObjectMapper extends ObjectMapper {

	public CustomJacksonObjectMapper() {
		super();
		
		SerializationConfig serialConfig = getSerializationConfig().withDateFormat(new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN));
		this.setSerializationConfig(serialConfig);

		enable(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		enable(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}
}
