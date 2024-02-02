package myDelivery.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Deliver implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String carrierName;
  private String trackId;
  private List<DetailInfo> detailInfos;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }


  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getTrackId() {
    return trackId;
  }

  public void setTrackId(String trackId) {
    this.trackId = trackId;
  }

  public List<DetailInfo> getDetailInfos() {
    return detailInfos;
  }

  public void setDetailInfos(List<DetailInfo> detailInfos) {
    this.detailInfos = detailInfos;
  }
}
