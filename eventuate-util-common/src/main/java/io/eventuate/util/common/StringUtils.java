package io.eventuate.util.common;

import java.nio.charset.Charset;

public class StringUtils {
  public static String bytesToString(byte[] bytes) {
    return new String(bytes, Charset.forName("UTF-8"));
  }

  public static byte[] stringToBytes(String string) {
    return string.getBytes(Charset.forName("UTF-8"));
  }
}
