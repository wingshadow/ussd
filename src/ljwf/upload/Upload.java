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

  public String savePath = null; //�������˴��·��
  private String prefix = "upd"; //�������˴����ʱ�ļ�������
  private String strSectFlag = ""; //�ϴ���������α��
  private String CharacterEncoding = "GBK"; // //���뷽��
  private byte readByte[] = new byte[4096]; //������������
  private byte writeByte[] = new byte[4096]; //д����������
  private int readCount[] = new int[1]; //readCount[]Ϊ���ַ���
  private int writeCount[] = new int[1]; //����ָ�����
  private int upFilesCount = 0; //��ǰʵ�������ļ���
  private int pageFileCount = 0; //����ҳ����ڵ��ļ�����
  private int upFilesMaxCount = 10; //���������ļ���Ĭ��ֵ
  private boolean totalCountOverflowFlag = false; //�ļ����������־
  private long totalFilesMaxSize = 1024 * 1024 * 100; //������ȫ���ļ������ܺ����ֵ,��λKB
  private long singleFileMaxSize = 1024 * 1024 * 10; //�����ص����ļ��������ֵ
  private long upFilesTotalSize = 0; //��ǰʵ����������
  private boolean totalSizeOverflowFlag = false; //�ϴ��ļ����ܳ������־
  private boolean fileSizeOverflowFlag = false; //�ļ����������־
  private ArrayList upFilesList = new ArrayList(); //���سɹ��ļ�����
  private ArrayList inputFieldList = new ArrayList(); //�������������
  private ArrayList selectFieldList = new ArrayList(); //���������������������

  //�������������ļ���
  public void setTotalFilesMaxCount(int count) {
    upFilesMaxCount = count;
  }

  //���ÿ�����ȫ���ļ������ܺ����ֵ,��λbyte
  public void setTotalFilesMaxSize(long size) {
    totalFilesMaxSize = size;
  }

  //���õ��������ļ��������
  public void setSingleFileMaxSize(long size) {
    singleFileMaxSize = size;
  }

  //�������سɹ����ļ�����
  public int getUpFilesCount() {
    return upFilesCount;
  }

  //��������ҳ����ļ�����
  public int getPageFileCount() {
    return pageFileCount;
  }

  //�������سɹ����ļ��ܳ���
  public long getUpFilesToatalSize() {
    return upFilesTotalSize;
  }

  //�����Ƿ�����ļ��ܳ�������־
  public boolean isTotalSizeOverFlow() {
    return totalSizeOverflowFlag;
  }

  //�����Ƿ���ڵ����ļ����������־
  public boolean isSizeOverFlow() {
    return fileSizeOverflowFlag;
  }

  //�����Ƿ�����ļ������������
  public boolean isTotalCountOverFlow() {
    return totalCountOverflowFlag;
  }

  //���ض�Ӧ���������� ����Ϊ����������
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

  //���ض�Ӧ���������� ����Ϊ����������
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
    String inputmultiValues[] = new String[j]; //��Ҫ�����б�Ķ�����
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

  //���ض�Ӧ���������� ����Ϊ����������
  public String[] getParameters(String inputName) {
    int i = 0;
    int j = 0;
    String inputmultiValues[] = new String[50]; //��Ҫ�����б�Ķ�����

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

  //���ض�Ӧ���������� ����Ϊ����������
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

  //��������������
  public int getInputFieldCount() {
    return inputFieldList.size();
  }

  //����������ָ��
  public InputField getInputField(int i) {
    if (i < 0 || i > inputFieldList.size() - 1) {
      return null;
    }
    return (InputField) inputFieldList.get(i);
  }

  //�����������ļ���������ָ��
  public FileInfo getUpFilesInfo(int i) {
    if (i < 0 || i > pageFileCount) {
      return null;
    }
    else {
      return (FileInfo) upFilesList.get(i);
    }
  }

  //����������ʽ����FileInfo
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

  //get���ش���·��
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

  //get���ش���·��
  public String getSavePath() {
    return savePath;
  }

  //�������ش���·��
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
  //��������ȫ·��
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

  //�����������ļ�
  public void uploadFile(HttpServletRequest req) throws ServletException,
    IOException {
    //���������
    init();
    //����Request���ַ����뷽ʽ
    setCharacterEncoding(req.getCharacterEncoding());
    //����Request��strSectFlag
    setSectFlag(req.getContentType());
    //���������������ݲ�д�뵽�ļ�
    read$writeFile(req.getInputStream());
  }

  private void init() {
    //���������
    upFilesCount = 0;
    upFilesTotalSize = 0;
    pageFileCount = 0;
    totalSizeOverflowFlag = false;
    fileSizeOverflowFlag = false;
    totalCountOverflowFlag = false;
    inputFieldList.clear();
    upFilesList.clear();
  }

  //ȡ���ļ�����(����·��)
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

  //���ýڱ�־
  private void setSectFlag(String sectFlag) {
    strSectFlag = sectFlag;
    int j;
    if ( (j = strSectFlag.indexOf("boundary=")) != -1) {
      //�������ݿ���ʼ�α�־
      strSectFlag = strSectFlag.substring(j + 9);
      strSectFlag = "--" + strSectFlag;
    }
  }

  //�����ַ����뷽ʽ
  public void setCharacterEncoding(String str) {
    CharacterEncoding = str;
  }

  //����������ȡ�ļ���д���������,������ز���
  public void read$writeFile(ServletInputStream servletInputStream) throws
    ServletException, IOException {
    String filename = null;
//    String realname = null;
    String line;
    //���������в��� "filename"��־ ���˴����²��ֽ�Ϊ���������ļ�����
    //i �ڴ�Ϊ"filename"��־�����е�λ��
    //ʵ�ʶ�ȡ���ַ�������readCount[0]�� readCount�ڴ˴�Ϊָ�����
    while ( (line = readLine(readByte, readCount, servletInputStream,
                             CharacterEncoding)) != null) {
      //�����ļ��ϴ�
      int i = line.indexOf("filename=");
      if (i >= 0) {
        pageFileCount++; //����ҳ����ļ�������������
        //����������
        String reqName = line;
        int j = reqName.indexOf("name=");
        reqName = reqName.substring(j + 6, reqName.length() - 1);
        j = reqName.lastIndexOf("filename=");
        reqName = reqName.substring(0, j - 3);
        //������"filename"��־��ʮλ��Ϊ���ص�Դ�ļ��ִ�
        //ȡ"filename"��־��ʮλ�Ժ���ִ�Ϊ�Ӵ�
        filename = line.substring(i + 10, line.length() - 3);
        filename = getFileName(filename);
        if (filename != null) {
          //�½��ļ� ����λ��ΪsavePath ,����Ϊfilename
          this.setSavePath();
//          File.createTempFile���ļ������ļ���׺��ָ���ļ�·����
//          File file = File.createTempFile(prefix, ljwf.File.getSuffix(filename, true),
//                                          new File(savePath));
          String fileName = savePath + File.separator + filename;
          File file = new File(fileName);
         
          //д�ļ�����ǰ���ȼ���ϴ��ļ������Ƿ񳬹�����
          if (! ( (upFilesCount >= upFilesMaxCount) ||
                 (upFilesTotalSize >= totalFilesMaxSize))) {
            //û�г������ޣ��ļ������ܸ������ܳ��ȣ�
            //������д���ļ�
            writeFile(file, servletInputStream, CharacterEncoding);
          }
          //�ļ���ȷ����֤
          int validate = validateFile(file);
          if (validate == 1) {
            setUpFilesInfo(file, reqName, filename); //��¼�ϴ��ɹ����ļ��б�
            upFilesCount++;
          }
          else {
            file.delete();
            setUpFilesInfo(null, reqName, filename);
          }
        }
        else {
          setUpFilesInfo(null, reqName, filename); //��¼���ļ���Ϣ
        }
      }
      //����input����
      int j = line.indexOf("name=\"");
      if (j > 0 && i < 0) {
        String inputName = line.substring(j + 6, line.length() - 3);
        String inputContent = "";
        //����һ����
        line = readLine(readByte, readCount, servletInputStream,
                        CharacterEncoding);
        while ( (line = readLine(readByte, readCount, servletInputStream,
                                 CharacterEncoding)).indexOf(strSectFlag) < 0) {
          //ȥ��β���ֵ�/r/n���ţ��س����з�)
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
    //�����ļ������
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    //������һ��,����Ϊд������������
    String lineContent = readLine(readByte, readCount, servletInputStream,
                                  CharacterEncoding);
    if (lineContent.indexOf("Content-Type") >= 0) {
      //���пմ���
      readLine(readByte, readCount, servletInputStream, CharacterEncoding);
    }

    //ѭ�������������벢д���ļ� readCount[0]Ϊʵ��д����ַ���
    while ( (lineContent = readLine(readByte, readCount, servletInputStream,
                                    CharacterEncoding)) != null) {
      //�������һ�н�����־ strSectFlag ���˳�ѭ��
      if (lineContent.indexOf(strSectFlag) == 0 && readByte[0] == 45) {
        break;
      }

      if (line != null) {
        fileOutputStream.write(writeByte, 0, writeCount[0]);
        fileOutputStream.flush();
      }

      //readCount[0]Ϊʵ�ʶ�����ַ���
      line = readLine(writeByte, writeCount, servletInputStream,
                      CharacterEncoding);
      if (line == null || line.indexOf(strSectFlag) == 0 && writeByte[0] == 45) {
        break;
      }
      fileOutputStream.write(readByte, 0, readCount[0]);
      fileOutputStream.flush();
    }
    //�����һ�н��д���
    if (line != null && writeByte[0] != 45 && writeCount[0] > 2) {
      fileOutputStream.write(writeByte, 0, writeCount[0] - 2);

    }
    if (lineContent != null && readByte[0] != 45 && readCount[0] > 2) {
      fileOutputStream.write(readByte, 0, readCount[0] - 2);
    }
    fileOutputStream.close();
  }

  //���������ж��������� readLine(byte[] buffer, int offset, int length)
  private String readLine(byte readByte[], int readCount[],
                          ServletInputStream servletInputStream,
                          String CharacterEncoding) {

    try {
      //readLine(byte[] buffer, int offset, int length)
      //����������ȡ���� ��ȡ��������󳤶�ΪreadByte��������ĳ���
      //readCount[0]Ϊʵ�ʶ�����ַ�������
      readCount[0] = servletInputStream.readLine(readByte, 0, readByte.length);
      //�������ĳ���Ϊ -1 ,�������������Ѷ���
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
        //��ȱʡ�ı��뷽ʽ�Ѹ�����byte����ת��Ϊ�ַ���
        return new String(readByte, 0, readCount[0]);
      }
      else {
        //�ø����ı��뷽ʽ�Ѹ�����byte����ת��Ϊ�ַ���
        return new String(readByte, 0, readCount[0], CharacterEncoding);
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  //�ļ�������Ч�Լ��
  //�Ϸ����� 1�������ļ���������2��
  //�ܳ���������3���ϴ��ļ������������4
  private int validateFile(File file) {
    if ( (upFilesTotalSize + file.length()) > totalFilesMaxSize) {
      //�ܳ������־��1
      totalSizeOverflowFlag = true;
      return 3;
    }
    if (file.length() > singleFileMaxSize) {
      //�����ļ��������λ��1
      fileSizeOverflowFlag = true;
      return 2;
    }
    //�ļ������Ƿ����
    if (upFilesCount > upFilesMaxCount) {
      //���������־��1
      totalCountOverflowFlag = true;
      return 4;
    }
    upFilesTotalSize = upFilesTotalSize + file.length();
    return 1;
  }

  //ȡ�õ��ı�������ı�����������
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

  //�����������ļ�����
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
