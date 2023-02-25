package ljwf.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class Upload {
  //public static String defaultPath = "c:\\transfile" +
  //  File.separator + "upload"; //don't with last \ or /
  public static String defaultPath = "/logs/upload" ;

  public String savePath = null; //服务器端存放路径
  private String prefix = "upd"; //服务器端存放临时文件的名称
  private String strSectFlag = ""; //上传数据区域段标记
  private String CharacterEncoding = "GBK"; // //编码方法
  private byte readByte[] = new byte[4096]; //读入流缓冲区
  private byte writeByte[] = new byte[4096]; //写入流缓冲区
  private int readCount[] = new int[1]; //readCount[]为行字符数
  private int writeCount[] = new int[1]; //设置指针变量
  private int upFilesCount = 0; //当前实际上载文件数
  private int pageFileCount = 0; //请求页面存在的文件项数
  private int upFilesMaxCount = 10; //最大可上载文件数默认值
  private boolean totalCountOverflowFlag = false; //文件个数溢出标志
  private long totalFilesMaxSize = 1024 * 1024 * 100; //可上载全部文件容量总和最大值,单位KB
  private long singleFileMaxSize = 1024 * 1024 * 10; //可上载单个文件容量最大值
  private long upFilesTotalSize = 0; //当前实际上载容量
  private boolean totalSizeOverflowFlag = false; //上传文件容总长溢出标志
  private boolean fileSizeOverflowFlag = false; //文件超长溢出标志
  private ArrayList upFilesList = new ArrayList(); //上载成功文件链表
  private ArrayList inputFieldList = new ArrayList(); //输入框内容链表
  private ArrayList selectFieldList = new ArrayList(); //多项输入数组框内容链表

  //设置最大可上载文件数
  public void setTotalFilesMaxCount(int count) {
    upFilesMaxCount = count;
  }

  //设置可上载全部文件容量总和最大值,单位byte
  public void setTotalFilesMaxSize(long size) {
    totalFilesMaxSize = size;
  }

  //设置单个上载文件最大容量
  public void setSingleFileMaxSize(long size) {
    singleFileMaxSize = size;
  }

  //返回上载成功的文件个数
  public int getUpFilesCount() {
    return upFilesCount;
  }

  //返回请求页面的文件项数
  public int getPageFileCount() {
    return pageFileCount;
  }

  //返回上载成功的文件总长度
  public long getUpFilesToatalSize() {
    return upFilesTotalSize;
  }

  //返回是否存在文件总长超长标志
  public boolean isTotalSizeOverFlow() {
    return totalSizeOverflowFlag;
  }

  //返回是否存在单个文件长度溢出标志
  public boolean isSizeOverFlow() {
    return fileSizeOverflowFlag;
  }

  //返回是否存在文件个数超过溢出
  public boolean isTotalCountOverFlow() {
    return totalCountOverflowFlag;
  }

  //返回对应输入项内容 参数为输入项名称
  public String getParameter(String inputName) {
    int i = 0;
    for (i = 0; i < inputFieldList.size(); i++) {
      InputField inputField = new InputField();
      inputField = (InputField) inputFieldList.get(i);
      if (inputName.compareTo(inputField.getInputName()) == 0) {
        return inputField.getInputContent();
      }
    }
    return null;
  }

  //返回对应输入项内容 参数为输入项名称
  public String[] getParams(String inputName) {
    int i = 0;
    int j = 0;

    for (i = 0; i < inputFieldList.size(); i++) {
      InputField inputField = new InputField();
      inputField = (InputField) inputFieldList.get(i);
      if (inputName.compareTo(inputField.getInputName()) == 0) {
        j++;
      }
    }
    if (j == 0) {
      return null;
    }
    String inputmultiValues[] = new String[j]; //主要用来列表的多数据
    j = 0;
    for (i = 0; i < inputFieldList.size(); i++) {
      InputField inputField = new InputField();
      inputField = (InputField) inputFieldList.get(i);
      if (inputName.compareTo(inputField.getInputName()) == 0) {
        inputmultiValues[j] = inputField.getInputContent();
        j++;
      }
    }
    return inputmultiValues;
  }

  //返回对应输入项内容 参数为输入项名称
  public String[] getParameters(String inputName) {
    int i = 0;
    int j = 0;
    String inputmultiValues[] = new String[50]; //主要用来列表的多数据

    for (i = 0; i < inputFieldList.size(); i++) {
      InputField inputField = new InputField();
      inputField = (InputField) inputFieldList.get(i);
      if (inputName.compareTo(inputField.getInputName()) == 0) {
        inputmultiValues[j] = inputField.getInputContent();
        j++;
      }
    }
    return inputmultiValues;
  }

  //返回对应输入项内容 参数为输入项名称
  public String getParameter(String inputName, String DefaultValue) {
    int i = 0;
    for (i = 0; i < inputFieldList.size(); i++) {
      InputField inputField = new InputField();
      inputField = (InputField) inputFieldList.get(i);
      if (inputName.compareTo(inputField.getInputName()) == 0) {
        return inputField.getInputContent();
      }
    }
    return DefaultValue;
  }

  //返回输入项数量
  public int getInputFieldCount() {
    return inputFieldList.size();
  }

  //返回输入项指针
  public InputField getInputField(int i) {
    if (i < 0 || i > inputFieldList.size() - 1) {
      return null;
    }
    return (InputField) inputFieldList.get(i);
  }

  //返回已上载文件属性链表指针
  public FileInfo getUpFilesInfo(int i) {
    if (i < 0 || i > pageFileCount) {
      return null;
    }
    else {
      return (FileInfo) upFilesList.get(i);
    }
  }

  //以请求名形式返回FileInfo
  public FileInfo getUpFilesInfo(String reqName) {
    int i = 0;
    FileInfo fileInfo = new FileInfo();
    for (i = 0; i < upFilesList.size(); i++) {
      fileInfo = (FileInfo) upFilesList.get(i);
      if (reqName.compareTo(fileInfo.getReqName()) == 0) {
        return fileInfo;
      }
    }
    return null;
  }

  //get上载存贮路径
  static public String getSavePath(String[] SavePath) {
    String s = defaultPath;
    for (int i = 0; i < SavePath.length; i++) {
      s += File.separator + SavePath[i];
    }
    File file = new File(s);
    if (!file.isDirectory()) {
      try {
        file.mkdirs();
      }
      catch (Exception e) {
      }
    }
    return s;
  }

  //get上载存贮路径
  public String getSavePath() {
    return savePath;
  }

  //设置上载存贮路径
  public void setSavePath(String[] SavePath) {
    savePath = defaultPath;
    for (int i = 0; i < SavePath.length; i++) {
      savePath += File.separator + SavePath[i];
    }
    File file = new File(savePath);
    if (!file.isDirectory()) {
      try {
        file.mkdirs();
      }
      catch (Exception e) {
        savePath = null;
      }
    }
  }

  public void setSavePath() {
    if (savePath == null) {
      setSavePath(new String[0]);
    }
  }
  //设置上载全路径
  public void setSavePath(String fullPath) {
	  
	    savePath = fullPath;
	    File file = new File(fullPath);
	    if (!file.isDirectory()) {
	      try {
	        file.mkdirs();
	      }
	      catch (Exception e) {
	        savePath = null;
	      }
	    }
}

  //由请求上载文件
  public void uploadFile(HttpServletRequest req) throws ServletException,
    IOException {
    //清空两个表
    init();
    //设置Request端字符编码方式
    setCharacterEncoding(req.getCharacterEncoding());
    //设置Request端strSectFlag
    setSectFlag(req.getContentType());
    //从输入流读入数据并写入到文件
    read$writeFile(req.getInputStream());
  }

  private void init() {
    //清空两个表
    upFilesCount = 0;
    upFilesTotalSize = 0;
    pageFileCount = 0;
    totalSizeOverflowFlag = false;
    fileSizeOverflowFlag = false;
    totalCountOverflowFlag = false;
    inputFieldList.clear();
    upFilesList.clear();
  }

  //取得文件名称(不带路径)
  private String getFileName(String fileName) {
    if (fileName.length() == 0) {
      return null;
    }
    int i = fileName.lastIndexOf("\\");
    if (i < 0 || i >= fileName.length() - 1) {
      i = fileName.lastIndexOf("/");
      if (i < 0 || i >= fileName.length() - 1) {
        return fileName;
      }
    }
    return fileName.substring(i + 1);
  }

  //设置节标志
  private void setSectFlag(String sectFlag) {
    strSectFlag = sectFlag;
    int j;
    if ( (j = strSectFlag.indexOf("boundary=")) != -1) {
      //定义数据块起始段标志
      strSectFlag = strSectFlag.substring(j + 9);
      strSectFlag = "--" + strSectFlag;
    }
  }

  //设置字符编码方式
  public void setCharacterEncoding(String str) {
    CharacterEncoding = str;
  }

  //从输入流读取文件并写入服务器端,完成上载操作
  public void read$writeFile(ServletInputStream servletInputStream) throws
    ServletException, IOException {
    String filename = null;
//    String realname = null;
    String line;
    //从输入流中查找 "filename"标志 即此处以下部分将为输入流的文件数据
    //i 在此为"filename"标志在行中的位置
    //实际读取的字符数放在readCount[0]中 readCount在此处为指针操作
    while ( (line = readLine(readByte, readCount, servletInputStream,
                             CharacterEncoding)) != null) {
      //处理文件上传
      int i = line.indexOf("filename=");
      if (i >= 0) {
        pageFileCount++; //代表页面的文件上载请求项数
        //分离请求名
        String reqName = line;
        int j = reqName.indexOf("name=");
        reqName = reqName.substring(j + 6, reqName.length() - 1);
        j = reqName.lastIndexOf("filename=");
        reqName = reqName.substring(0, j - 3);
        //该行在"filename"标志后十位处为上载的源文件字串
        //取"filename"标志后十位以后的字串为子串
        filename = line.substring(i + 10, line.length() - 3);
        filename = getFileName(filename);
        if (filename != null) {
          //新建文件 存贮位置为savePath ,名称为filename
          this.setSavePath();
//          File.createTempFile（文件名，文件后缀，指定文件路径）
//          File file = File.createTempFile(prefix, ljwf.File.getSuffix(filename, true),
//                                          new File(savePath));
          String fileName = savePath + File.separator + filename;
          File file = new File(fileName);
         
          //写文件操作前首先检查上传文件个数是否超过上限
          if (! ( (upFilesCount >= upFilesMaxCount) ||
                 (upFilesTotalSize >= totalFilesMaxSize))) {
            //没有超过上限（文件上载总个数、总长度）
            //将数据写入文件
            writeFile(file, servletInputStream, CharacterEncoding);
          }
          //文件正确性验证
          int validate = validateFile(file);
          if (validate == 1) {
            setUpFilesInfo(file, reqName, filename); //记录上传成功的文件列表
            upFilesCount++;
          }
          else {
            file.delete();
            setUpFilesInfo(null, reqName, filename);
          }
        }
        else {
          setUpFilesInfo(null, reqName, filename); //记录空文件信息
        }
      }
      //处理input内容
      int j = line.indexOf("name=\"");
      if (j > 0 && i < 0) {
        String inputName = line.substring(j + 6, line.length() - 3);
        String inputContent = "";
        //读过一空行
        line = readLine(readByte, readCount, servletInputStream,
                        CharacterEncoding);
        while ( (line = readLine(readByte, readCount, servletInputStream,
                                 CharacterEncoding)).indexOf(strSectFlag) < 0) {
          //去结尾部分的/r/n符号（回车换行符)
          //inputContent = inputContent + line.substring(0, line.length() - 2);
          //inputContent = inputContent + line;
          inputContent = inputContent + line.substring(0, line.length() - 1);
        }
        setInputContent(inputName,
                        inputContent.substring(0, inputContent.length() - 1));

      }
    }
  }

  private void writeFile(File file, ServletInputStream servletInputStream,
                         String CharacterEncoding) throws ServletException,
    IOException {
    String line = null;
    //创建文件输出流
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    //读入下一行,该行为写入内容类型行
    String lineContent = readLine(readByte, readCount, servletInputStream,
                                  CharacterEncoding);
    if (lineContent.indexOf("Content-Type") >= 0) {
      //本行空处理
      readLine(readByte, readCount, servletInputStream, CharacterEncoding);
    }

    //循环从输入流读入并写入文件 readCount[0]为实际写入的字符数
    while ( (lineContent = readLine(readByte, readCount, servletInputStream,
                                    CharacterEncoding)) != null) {
      //读到最后一行结束标志 strSectFlag 则退出循环
      if (lineContent.indexOf(strSectFlag) == 0 && readByte[0] == 45) {
        break;
      }

      if (line != null) {
        fileOutputStream.write(writeByte, 0, writeCount[0]);
        fileOutputStream.flush();
      }

      //readCount[0]为实际读入的字符数
      line = readLine(writeByte, writeCount, servletInputStream,
                      CharacterEncoding);
      if (line == null || line.indexOf(strSectFlag) == 0 && writeByte[0] == 45) {
        break;
      }
      fileOutputStream.write(readByte, 0, readCount[0]);
      fileOutputStream.flush();
    }
    //对最后一行进行处理
    if (line != null && writeByte[0] != 45 && writeCount[0] > 2) {
      fileOutputStream.write(writeByte, 0, writeCount[0] - 2);

    }
    if (lineContent != null && readByte[0] != 45 && readCount[0] > 2) {
      fileOutputStream.write(readByte, 0, readCount[0] - 2);
    }
    fileOutputStream.close();
  }

  //从输入流中读入行数据 readLine(byte[] buffer, int offset, int length)
  private String readLine(byte readByte[], int readCount[],
                          ServletInputStream servletInputStream,
                          String CharacterEncoding) {

    try {
      //readLine(byte[] buffer, int offset, int length)
      //从输入流读取数据 读取的数据最大长度为readByte缓冲数组的长度
      //readCount[0]为实际读入的字符串长度
      readCount[0] = servletInputStream.readLine(readByte, 0, readByte.length);
      //如果读入的长度为 -1 ,即输入流数据已读完
      if (readCount[0] == -1) {
        return null;
      }
    }
    catch (IOException _ex) {
      ljwf.Log.error(_ex);
      return null;
    }
    try {
      if (CharacterEncoding == null) {
        //用缺省的编码方式把给定的byte数组转换为字符串
        return new String(readByte, 0, readCount[0]);
      }
      else {
        //用给定的编码方式把给定的byte数组转换为字符串
        return new String(readByte, 0, readCount[0], CharacterEncoding);
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  //文件上载有效性检查
  //合法返回 1；单个文件超长返回2；
  //总长超长返回3；上传文件数量溢出返回4
  private int validateFile(File file) {
    if ( (upFilesTotalSize + file.length()) > totalFilesMaxSize) {
      //总长溢出标志置1
      totalSizeOverflowFlag = true;
      return 3;
    }
    if (file.length() > singleFileMaxSize) {
      //单个文件长度溢出位置1
      fileSizeOverflowFlag = true;
      return 2;
    }
    //文件数量是否溢出
    if (upFilesCount > upFilesMaxCount) {
      //个数溢出标志置1
      totalCountOverflowFlag = true;
      return 4;
    }
    upFilesTotalSize = upFilesTotalSize + file.length();
    return 1;
  }

  //取得的文本项加入文本输入项链表
  private void setInputContent(String inputName, String inputContent) {
    InputField inputField = new InputField();
    inputField.setInputName(inputName);
    inputField.setInputContent(inputContent);
    inputFieldList.add(inputField);
  }

  private void setInputMultiContent(String inputName,
                                    String[] inputmultiContent) {
    InputField inputField = new InputField();
    inputField.setInputName(inputName);
    inputField.setInputmultiContent(inputmultiContent);
    inputFieldList.add(inputField);
  }

  //设置已上载文件属性
  private void setUpFilesInfo(File file, String reqName, String filename) {
    FileInfo fileInfo = new FileInfo();
    if (file == null) {
      fileInfo.setSuccess(false);
      fileInfo.setFileName(null);
      fileInfo.setFilePath(null);
      fileInfo.setFileSize(0);
      fileInfo.setRealName(null);
    }
    else {
      fileInfo.setSuccess(true);
      fileInfo.setFileName(filename);
      fileInfo.setFilePath(file.getPath());
      fileInfo.setFileSize(file.length());
      fileInfo.setRealName(file.getName());
    }
    fileInfo.setReqName(reqName);
    upFilesList.add(fileInfo);
  }

  //set file prefix
  public void setFilePrefix(String prefix) {
    this.prefix = prefix;
  }

  //upload temp file ,return attachment.auto_id
  static public String UpLoadFile(String[] path,
                                  java.io.InputStream inputStream) {
    String id = null;
    try {
      //save to dir
      java.io.File fl = java.io.File.createTempFile("ttt", null,
        new File(getSavePath(path)));
      java.io.FileOutputStream f = new java.io.FileOutputStream(fl); //temp should be replace by staffid
      byte[] bb = new byte[100000];
      for (; ; ) {
        int kk = inputStream.read(bb, 0, 100000);
        f.write(bb, 0, kk);
        if (kk < 100000) {
          break;
        }
      }
      f.close();
      //save to db
      java.util.HashMap m = new java.util.HashMap();
      m.put("attach_name", fl.getName());
      m.put("real_name", fl.getPath());
      ljwf.db.attachment a = new ljwf.db.attachment();
      id = a.AddWithoutKeys(m);
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
    return id;
  }

  //copy files ,return file path
  static public String copyFiles(String[] path,
                                 String srcFile) {
    try {
      //save to dir
      java.io.File fl = java.io.File.createTempFile("sav",
        ljwf.File.getSuffix(srcFile, true),
        new File(getSavePath(path)));
      ljwf.File.copy(srcFile, fl);
      return fl.getPath();
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
    return null;
  }

  //upload file ,return file info
  static public ljwf.upload.FileInfo saveFile(String[] path,
                                              java.io.InputStream inputStream,
                                              String suf) {
    try {
      //save to dir
      java.io.File fl = java.io.File.createTempFile("sav", suf,
        new File(getSavePath(path)));
      java.io.FileOutputStream f = new java.io.FileOutputStream(fl); //temp should be replace by staffid
      byte[] bb = new byte[100000];
      for (; ; ) {
        int kk = inputStream.read(bb, 0, 100000);
        f.write(bb, 0, kk);
        if (kk < 100000) {
          break;
        }
      }
      f.close();
      ljwf.upload.FileInfo fi = new ljwf.upload.FileInfo();
      fi.setFileSize(fl.length());
      fi.setFilePath(fl.getPath());
      fi.setRealName(fl.getName());
      return fi;
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
    return null;
  }

  //upload file ,return file info
  static public ljwf.upload.FileInfo saveFile(String[] path,
                                              java.io.InputStream inputStream) {
    return saveFile(path, inputStream, null);
  }

  //upload file ,return file info
  static public ljwf.upload.FileInfo saveFile(String[] path,
                                              String content, String suf) {
    try {
      //save to dir
      java.io.File fl = java.io.File.createTempFile("sav", suf,
        new File(getSavePath(path)));
      java.io.FileWriter f = new java.io.FileWriter(fl); //temp should be replace by staffid
      f.write(content);
      f.close();
      ljwf.upload.FileInfo fi = new ljwf.upload.FileInfo();
      fi.setFileSize(fl.length());
      fi.setFilePath(fl.getPath());
      fi.setRealName(fl.getName());
      return fi;
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
    return null;
  }

  //upload file ,return file info
  static public void saveFileByName(String[] path, String name, String content) {
    try {
      //save to dir
      java.io.File fl = new java.io.File(getSavePath(path), name);
      java.io.FileWriter f = new java.io.FileWriter(fl); //temp should be replace by staffid
      f.write(content);
      f.close();
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
  }

  //upload file ,return file info
  static public ljwf.upload.FileInfo saveFile(String[] path,
                                              String content) {
    return saveFile(path, content, null);
  }

  //read file ,return file full name
  static public String readFileStr(String path) {
    try {
      //save to dir
      java.io.File fl = new File(path);
//      java.io.FileReader fr = new java.io.FileReader(fl); //temp should be replace by staffid
//      String en=fr.getEncoding();
//      fr.close();
      java.io.FileInputStream f = new java.io.FileInputStream(fl); //temp should be replace by staffid
      byte[] b = new byte[ (int) fl.length()];
      f.read(b);
      f.close();
//      java.util.Arrays.binarySearch(b,)
      return new String(b);
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
    return null;
  }

  //del all attach files uploaded
  public void delUpFiles() {
    int c = this.getUpFilesCount();
    try {
      for (int i = 0; i < c; i++) {
        ljwf.upload.FileInfo f = this.getUpFilesInfo(i);
        new java.io.File(f.getFilePath()).delete();
      }
    }
    catch (Exception e) {
      ljwf.Log.error(e);
    }
  }
}
