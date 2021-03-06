/**
	 * Java. Chatbot
	 * 
	 * Class ChatBot
	 * @author Antonius
	 * @version 0.1 dated May 01, 2017
	 */
package chatBot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class ChatBot {

	final String[] COMMON_PHRASES = {
	        "��� ������ ������ ����, ��������� � ����� � �� �������.",
	        "����� �������� ����� ������� ������, ������ ���� ����.",
	        "����� ��� ��� ������/�������� ������ ����� ��������.",
	        "�������� � ��������� ���� ������� � ������� ����.",
	        "������� ����� ����� ��� ��������������� ������.",
	        "����������� ���� ������� ���������������� ���.",
	        "����� ����� ������, �� ����� � ��������.",
	        "��������� �����, �� ��������� �� ����.",
	        "��� ���� ������, ��� ���� ��������.",
			"����� �� ���-�� �� �������������." 
	        };
	final String[] ELUSIVE_ANSWERS = {
	        "������ ���������, ����� ����-��� �� ��������.",
	        "�� ������, ��� ���������� ����� �����������.",
	        "����� ����� ��������� � ���-�� ������?",
	        "��������, �� ��� ����� ������ ������.",
	        "�� ������, ��� ��� ���������� �����.",
	        "��������, � ��� ����� �� ��� �����.",
	        "�� ������������� ������ ��� �����?",
	        "������, �� ��� ���������� ����.",
	        "����� ��� ����� ����������?",
	        "������� �������� �������?"
	        };
	final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			// hello
			put("���", "hello");
			put("������", "hello");
			put("�������", "hello");
			put("����������", "hello");
			// who
			put("���\\s.*��", "who");
			put("��\\s.*���", "who");
			// name
			put("���\\s.*�����", "name");
			put("���\\s.*���", "name");
			put("����\\s.*���", "name");
			put("�����\\s.*���", "name");
			// howareyou
			put("���\\s.*����", "howareyou");
			put("���\\s.*�����", "howareyou");
			// whatdoyoudoing
			put("�����\\s.*���", "whatdoyoudoing");
			put("�����\\s.*�����", "whatdoyoudoing");
			put("���\\s.*�������", "whatdoyoudoing");
			put("���\\s.*�����������", "whatdoyoudoing");
			// whatdoyoulike
			put("���\\s.*��������", "whatdoyoulike");
			put("���\\s.*������", "whatdoyoulike");
			// iamfeelling
			put("�������", "iamfeelling");
			put("��������", "iamfeelling");
			put("���������", "iamfeelling");
			// yes
			put("^��", "yes");
			put("��������", "yes");
			// whattime
			put("�������\\s.*���", "whattime");
			put("�������\\s.*�����", "whattime");
			// bye
			put("����", "bye");
			put("������", "bye");
			put("��������", "bye");
			put("��\\s.*��������", "bye");
		}
	};
	final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("hello", "������������, ��� ��� ������.");
			put("who", "� ������� ���-���.");
			put("name", "������ ���� ������ :)");
			put("howareyou", "�������, ��� �������������. � ���� �� ������.");
			put("whatdoyoudoing", "� ������ �������� � ������.");
			put("whatdoyoulike", "��� ��������� ������ ��� � �� ������ ���������.");
			put("iamfeelling", "��� ����� ��� ��������? ���������� ���� ���������.");
			put("yes", "�������� ���� ������� ��� ������ ������������� ������.");
			put("bye", "�� ��������. �������, ��� ��������.");
		}
	};
	public Pattern pattern; // for regexp
	public Random random; // for random answers
	public Date date; // for date and time

	public ChatBot() {
		random = new Random();
		date = new Date();
	}

	public String sayInReturn(String iMessage, boolean ai) {
		String say = (iMessage.trim().endsWith("?")) ? ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]
				: COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
		if (ai) {
			String message = String.join(" ", iMessage.toLowerCase().split("[ {,|.}?]+"));
			for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
				pattern = Pattern.compile(o.getKey());
				if (pattern.matcher(message).find()) {
					if (o.getValue().equals("whattime")) {
						return date.toString();
					} else {
						return ANSWERS_BY_PATTERNS.get(o.getValue());
					}
				}
			}
		}
		return say;
	}

}