package Server.util;

import java.util.Arrays;
import java.util.List;

public class ListHandler {

	public static List<String> StringListByCommaEnd(String input) {
		return Arrays.asList(input.split("\\s*,\\s*"));
	}

}
