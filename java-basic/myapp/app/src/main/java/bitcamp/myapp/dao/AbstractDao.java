package bitcamp.myapp.dao;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {

  ArrayList<T> list;

  void loadData(String filepath) {
//    Class<?> clazz = this.getClass();
//    ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
//    Type[] types = type.getActualTypeArguments();
//    for (Type t : types) {
//      System.out.println(t.getTypeName());
//    }

    long start = 0;

    try (BufferedReader in = new BufferedReader((new FileReader(filepath)))) {
      Class<?> dataType = (Class<?>) ((ParameterizedType) this.getClass()
          .getGenericSuperclass()).getActualTypeArguments()[0];
//      List<E> list = (List<E>) in.readObject();
//      dataList.addAll((List<E>) in.readObject());
      start = System.currentTimeMillis();

      StringBuilder sb = new StringBuilder();
      String str;

      while ((str = in.readLine()) != null) {
        sb.append(str);
      }

      this.list = new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
          .fromJson(sb.toString(), TypeToken.getParameterized(ArrayList.class, dataType).getType());

    } catch (Exception e) {
      throw new DaoException("Error", e);
    } finally {
      long end = System.currentTimeMillis();
      if (start != 0) {
        System.out.println(end - start);
      }
    }
  }

  <E> List<E> loadData(String filepath, Class<E> clazz) throws Exception {
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

      return new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
          .fromJson(sb.toString(), TypeToken.getParameterized(ArrayList.class, clazz).getType());
    }

  }
}
