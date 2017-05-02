package com.allen.core.feature.velocity.resolver;

public final class LayoutFinder
{
  private static final int Seq = 47;
  private char[] layoutName;
  private char[] sameNameLayoutUrl;
  private int lastSeq;
  
  public LayoutFinder(String screenUrl, char[] layoutPrefix, char[] screenPrefix, char[] defaultLayoutName)
  {
    this.layoutName = defaultLayoutName;
    int layoutPrefixLen = layoutPrefix.length;
    int len = layoutPrefixLen + screenUrl.length() - screenPrefix.length;
    this.sameNameLayoutUrl = new char[len];
    System.arraycopy(layoutPrefix, 0, this.sameNameLayoutUrl, 0, 
      layoutPrefixLen);
    int pos = layoutPrefixLen;
    for (int i = screenPrefix.length; i < screenUrl.length(); pos++)
    {
      this.sameNameLayoutUrl[pos] = screenUrl.charAt(i);i++;
    }
    this.lastSeq = lastIndexOf(len);
  }
  
  private final int lastIndexOf(int last)
  {
    for (int i = last - 1; i >= 0; i--) {
      if (this.sameNameLayoutUrl[i] == '/') {
        return i;
      }
    }
    return -1;
  }
  
  public String getSameNameLayoutUrl()
  {
    return new String(this.sameNameLayoutUrl);
  }
  
  public String getLayoutUrl()
  {
    if (this.lastSeq == -1) {
      return null;
    }
    int newLen = this.lastSeq + 1 + this.layoutName.length;
    char[] newChar = new char[newLen];
    System.arraycopy(this.sameNameLayoutUrl, 0, newChar, 0, this.lastSeq + 1);
    System.arraycopy(this.layoutName, 0, newChar, this.lastSeq + 1, 
      this.layoutName.length);
    this.lastSeq = lastIndexOf(this.lastSeq);
    return new String(newChar);
  }
}
