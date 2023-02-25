package ljwf.upload;

public class FileInfo {
  private String reqName;
  private String fileName;
  private boolean success;
  private String filePath;
  private long fileSize;
  private String realName; //real file name,auto generate by system

  //�����ļ���Ϣ
  //�����ļ��Ƿ���Ч��־
  public void setSuccess(boolean success) {
    this.success = success;
  }

  //���ñ�������
  public void setReqName(String reqName) {
    this.reqName = reqName;
  }

  //�ļ���
  public void setFileName(String filename) {
    this.fileName = filename;
  }

  //����·��
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  //�ļ���С
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  //real name
  public void setRealName(String realName) {
    this.realName = realName;
  }

  //���ر�������
  public String getReqName() {
    return reqName;
  }

  //�����ļ��Ƿ���Ч��־
  public boolean isSuccess() {
    return success;
  }

  //ȡ���ļ���
  public String getFileName() {
    return fileName;
  }

  //ȡ�ô���·��
  public String getFilePath() {
    return filePath;
  }

  //ȡ���ļ���С
  public long getFileSize() {
    return fileSize;
  }

  //ȡ��real name
  public String getRealName() {
    return realName;
  }
}
