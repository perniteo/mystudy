package myDelivery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import myDelivery.dao.DeliverDao;
import myDelivery.dao.mysql.DeliverDaoImpl;
import myDelivery.handler.HelpHandler;
import myDelivery.handler.deliver.DeliverAddHandler;
import myDelivery.handler.deliver.DeliverDeleteHandler;
import myDelivery.handler.deliver.DeliverListHandler;
import myDelivery.handler.deliver.DeliverModifyHandler;
import myDelivery.handler.deliver.DeliverViewHandler;
import myDelivery.menu.MenuGroup;
import myDelivery.util.Prompt;
import myDelivery.vo.DetailInfo;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeliveryApp {

  private static final String TRACKER_URL = "https://apis.tracker.delivery/carriers/";
  static Map<String, String> nameIdMap = new HashMap<>();
  static List<DetailInfo> list = new ArrayList<>();
  Prompt prompt = new Prompt(System.in);
  DeliverDao deliverDao;
  MenuGroup mainMenu;
  DetailInfo detailInfo;

  DeliveryApp() throws Exception {
    prepareDatabase();
    prepareMenu();
    prepareCarrier();
  }

  private static String readBodyAPI(InputStream body) {

    InputStreamReader streamReader = new InputStreamReader(body);

    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
      StringBuilder responseBody = new StringBuilder();

      String line;
      while ((line = lineReader.readLine()) != null) {
        responseBody.append(line);
      }

      return responseBody.toString();
    } catch (IOException e) {
      throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
    }
  }

  private static String GETMethod(String apiUrl) {
//    System.out.println(apiUrl);
    try {

      URL url = new URL(apiUrl);

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setDoOutput(true);
      connection.setDoInput(true);

      int code = connection.getResponseCode();
      if (code == 200) {
        return readBodyAPI(connection.getInputStream());
      } else {
        return "{\"message\":\"보내시는 고객님께서 상품 발송 준비중입니다.\"}";
      }

    } catch (IOException e) {
      throw new RuntimeException("API 요청과 응답 실패", e);
    }
  }

  public static void main(String[] args) throws Exception {

    new DeliveryApp().run();
//    list = callLogAPI("CJ대한통운", "577453451342");
//    for (DetailInfo d : list) {
//      System.out.print(d.getContent());
//      System.out.print(d.getTime());
//      System.out.print(d.getStatus());
//      System.out.println(d.getLocation());
//    }
  }

  public static List<DetailInfo> callLogAPI(String carrierName, String trackId) {

    String responseBody = "";
    List<DetailInfo> list = new ArrayList<>();
    JSONObject retVal = new JSONObject();

    String carrierId = nameIdMap.get(carrierName);

    String apiURL = TRACKER_URL + carrierId + "/tracks/" + trackId.replaceAll("-", "");

    responseBody = GETMethod(apiURL);

    JSONObject statusJson = new JSONObject(responseBody);
    JSONObject carrierJson;
    JSONArray progressJson;

//    System.out.println(statusJson);

    String mess = statusJson.has("message") ? statusJson.get("message").toString() : "";

    //내용이 없는 경우
    if (!mess.equals("")) {
      DetailInfo detailInfo1 = new DetailInfo();
      //fromJson()
      detailInfo1.setContent("보내시는 고객님께서 상품 발송 준비중입니다.");
      detailInfo1.setTime("미정");
      detailInfo1.setLocation("미정");
      detailInfo1.setStatus("발송준비");

      list.add(detailInfo1);

      return list;
    }

    //Tracker API 결과값 확인
    carrierJson = new JSONObject(statusJson.get("carrier").toString());
//    System.out.println(carrierJson);
    progressJson = new JSONArray(statusJson.get("progresses").toString());
//    System.out.println(progressJson);
    statusJson = new JSONObject(statusJson.get("state").toString());
//    System.out.println(statusJson);
//    String latest = statusJson.getJSONObject("state").getString("text");

    for (int i = 0; i < progressJson.length(); i++) {
      JSONObject initJson = progressJson.getJSONObject(i);
      JSONObject stat = initJson.getJSONObject("status");
//      System.out.println(progressJson.getJSONObject(i));

      try {
        DetailInfo detailInfo2 = new DetailInfo();
        //fromJson()
        detailInfo2.setContent(initJson.getString("description"));
        detailInfo2.setStatus(initJson.getJSONObject("status").getString("text"));
        detailInfo2.setTime(initJson.getString("time"));
        detailInfo2.setLocation(initJson.getJSONObject("location").getString("name"));

        list.add(detailInfo2);
        String progress = initJson.getString("description");
        stat = initJson.getJSONObject("location");
        retVal.put("description", progress == null ? "" : progress);
        retVal.put("time", initJson.getString("time"));
        retVal.put("location", stat.getString("name"));
        retVal.put("status", statusJson.getString("text"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return list;
  }

  void prepareDatabase() {
    try {
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://db-ld250-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "bitcamp!@#123"
//          "jdbc:mysql://localhost/studydb", "study", "bitcamp!@#123"
      );

      System.out.println("loading");
      System.out.println("success");

      deliverDao = new DeliverDaoImpl(connection);

    } catch (Exception e) {
      System.out.println("Error");
      e.printStackTrace();
    }

  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup deliverMenu = mainMenu.addGroup("내 택배");
    deliverMenu.addItem("등록", new DeliverAddHandler(deliverDao, prompt));
    deliverMenu.addItem("조회", new DeliverViewHandler(deliverDao, prompt));
    deliverMenu.addItem("변경", new DeliverModifyHandler(deliverDao, prompt));
    deliverMenu.addItem("삭제", new DeliverDeleteHandler(deliverDao, prompt));
    deliverMenu.addItem("목록", new DeliverListHandler(deliverDao, prompt));

    mainMenu.addItem("도움말", new HelpHandler(prompt));
  }

  void run() throws Exception {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.err.println("Exception !");
      }
    }
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
      // 결과 출력
//      for (Map.Entry<String, String> entry : nameIdMap.entrySet()) {
//        System.out.println("Name: " + entry.getKey() + ", ID: " + entry.getValue());
//      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

