
package com.kt.airmap.base.common;

import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectConverter {

	public static String toJSONString(Object obj) throws JsonProcessingException {
		return ObjectConverter.toJSONString(obj, Include.NON_NULL);
	}

	public static String toJSONString(Object obj, Include include) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(include);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

	public static JSONObject toJSON(Object obj) throws JsonProcessingException, ParseException {
		return ObjectConverter.toJSON(obj, Include.NON_NULL);
	}

	public static JSONObject toJSON(Object obj, Include include) throws JsonProcessingException, ParseException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(include);

		String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		jsonStr = jsonStr.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll("\\t", "");

		JSONObject json = (JSONObject)new JSONParser().parse(jsonStr);

		return json;
	}

	public static JSONObject toJSON(String jsonStr) throws JsonProcessingException, ParseException {
		return ObjectConverter.toJSON(jsonStr, Include.NON_NULL);
	}

	public static JSONObject toJSON(String jsonStr, Include include) throws JsonProcessingException, ParseException {

		String inputStr = jsonStr;

		JSONObject orgJson = (JSONObject)new JSONParser().parse(inputStr);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(include);

		inputStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgJson);

		JSONObject newJson = (JSONObject)new JSONParser().parse(inputStr);

		return newJson;
	}

	public static <T> T toObject(String content, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(content, valueType);
	}

	public static <T> T toObject(Map<String, Object> map, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		/** 
		 *  {"response":{"header":{"resultCode":"0000","resultMsg":"OK"},"body":
		 *  {"items":"","numOfRows":30000,"pageNo":1,"totalCount":0}}
		 *  ==>"items":"" 인 경우 오류발생을 피하기 위해"ACCEPT_EMPTY_STRING_AS_NULL_OBJECT"설정함.
		 */
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.convertValue(map, valueType);
	}

	public static String toGsonString(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static <T> T toGsonObject(String string, Class<T> valueType) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
		return gson.fromJson(string, valueType);
	}

}
