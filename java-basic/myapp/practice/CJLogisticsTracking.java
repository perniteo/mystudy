//package practice;
//
//import java.io.IOException;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class CJLogisticsTracking {
//
//  public static void main(String[] args) {
//    String trackingNumber = "577453451342";
//    String trackingUrl = "https://trace.cjlogistics.com/web/detail.jsp?slipno=" + trackingNumber;
//
//    try {
//      // Jsoup을 사용하여 웹 페이지 가져오기
//      Document doc = Jsoup.connect(trackingUrl).get();
//
//      // "table#detailContent" 안에 있는 모든 "tr"을 선택
//      Elements rows = doc.select("table#detailContent tr");
//
//      // 행 반복
//      for (Element row : rows) {
//        // 행 안에 있는 모든 "td"를 선택
//        Elements cells = row.select("td");
//
//        // 셀 반복
//        for (Element cell : cells) {
//          // 각 셀의 내용 출력
//          System.out.print(cell.text() + "\t");
//        }
//        // 한 행의 출력이 끝나면 개행
//        System.out.println();
//      }
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}