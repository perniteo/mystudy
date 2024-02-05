package myDelivery.handler;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import myDelivery.menu.AbstractMenuHandler;
import myDelivery.util.AnsiEscape;
import myDelivery.util.Prompt;
import org.json.JSONArray;
import org.json.JSONObject;


public class HelpHandler extends AbstractMenuHandler {

  Map<String, String> nameIdMap = new HashMap<>();

  public HelpHandler(Prompt prompt) {
    super(prompt);
  }

  void prepareCarrier() {
    try {
      // JSON 파일 경로 설정
      String filePath = "./carriers.json";

      // JSON 파일 읽기
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      StringBuilder buf = new StringBuilder();
      String str;
      while ((str = reader.readLine()) != null) {
        buf.append(str);
      }
      // JSON 배열 생성
      JSONArray jsonArray = new JSONArray(buf.toString());

      // "name: id" 매핑을 담을 Map 생성
//      Map<String, String> nameIdMap = new HashMap<>();

      // 배열 순회하며 매핑 생성
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);

        // "name"과 "id" 가져오기
        String name = jsonObject.getString("name");
        String id = jsonObject.getString("id");

        // 매핑 추가
        nameIdMap.put(name, id);
      }

//      // 결과 출력
//      for (Map.Entry<String, String> entry : nameIdMap.entrySet()) {
//        System.out.println("Name: " + entry.getKey() + ", ID: " + entry.getValue());
//      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void action() {
    prepareCarrier();
    System.out.println("택배사 목록");
    System.out.println(AnsiEscape.ANSI_BOLD_RED + "주의 : 아래에 맞는 택배사를 이용할 것" + AnsiEscape.ANSI_CLEAR);
    List<String> list = new ArrayList<>(nameIdMap.keySet());
    for (String str : list) {
      System.out.println(str);
    }

  }
}