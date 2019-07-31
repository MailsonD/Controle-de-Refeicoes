package br.com.loopis.controle_refeicoes.controle.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorClass {
	public static Integer countFields(Class c, String regex) {
		return toListNameFields(c, regex).size();
	}

	public static List<String> toListNameFields(Class c, String regex) {
		List<String> fields = new ArrayList<>();
		for (Field f : c.getDeclaredFields()) {
			String s = f.getName();
			if (!s.matches(regex)) {
				fields.add(s);
			}
		}
		return fields;
	}
}
