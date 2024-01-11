package bitcamp.myapp.dao;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public abstract class AbstractDao<T> {

  private final String filepath;
  protected ArrayList<T> list;
  private int lastKey;

  public AbstractDao(String filepath) {
    this.filepath = filepath;
    loadData();
  }

  protected void loadData() {
//    Class<?> clazz = this.getClass();
//    ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
//    Type[] types = type.getActualTypeArguments();
//    for (Type t : types) {
//      System.out.println(t.getTypeName());
//    }\
    long start = 0;

    try (BufferedReader in = new BufferedReader((new FileReader(filepath)))) {
//      List<E> list = (List<E>) in.readObject();
//      dataList.addAll((List<E>) in.readObject());
      start = System.currentTimeMillis();

      StringBuilder sb = new StringBuilder();
      String str;

      while ((str = in.readLine()) != null) {
        sb.append(str);
      }

      Class<T> dataType = (Class<T>) ((ParameterizedType) this.getClass()
          .getGenericSuperclass()).getActualTypeArguments()[0];

      this.list = (ArrayList<T>) new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
          .fromJson(sb.toString(), TypeToken.getParameterized(ArrayList.class, dataType));

    } catch (Exception e) {
      throw new DaoException("Error", e);
    } finally {
      long end = System.currentTimeMillis();
      if (start != 0) {
        System.out.println(end - start);
      }
    }
  }

  protected void saveData() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(filepath))) {
      out.write(new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(list));
    } catch (Exception e) {
      throw new DaoException("데이터 저장 오류!", e);
    }
  }

}
