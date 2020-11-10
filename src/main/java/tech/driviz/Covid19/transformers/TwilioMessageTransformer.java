package tech.driviz.Covid19.transformers;

import java.util.HashMap;
import java.util.Map;

import tech.driviz.Covid19.models.backend.TwilioMessage;

public class TwilioMessageTransformer {

	private static final String BODY = "Body";
	private static final String TO = "To";
	private static final String FROM = "From";
	private static final String SPACE = " ";
	private static final String AMP = "&";
	private static final String EQUALS = "=";

	public static TwilioMessage transform(String decodedMessage) {
		TwilioMessage message = new TwilioMessage();
		Map<String, String> objectMap = getMap(decodedMessage);
		if (objectMap == null)
			return null;

		mapToMessage(objectMap, message);
		return message;
	}

	private static void mapToMessage(Map<String, String> objectMap, TwilioMessage message) {
		if (objectMap.containsKey(BODY)) {
			String msgBody = objectMap.get(BODY);
			String[] keyValue = msgBody.split(SPACE);
			message.setCaseType(keyValue[0]);
			message.setCountry(keyValue[1]);
		}
		if (objectMap.containsKey(FROM))
			message.setFrom(objectMap.get(FROM));
		if (objectMap.containsKey(TO))
			message.setTo(objectMap.get(TO));
	}

	private static Map<String, String> getMap(String decodedMessage) {
		if (decodedMessage == null || decodedMessage.isEmpty())
			return null;

		Map<String, String> objectMap = new HashMap<>();
		String[] properties = decodedMessage.split(AMP);
		for (String property : properties) {
			String[] keyValue = property.split(EQUALS);
			objectMap.put(keyValue[0], keyValue[1]);
		}

		return objectMap;
	}

}
