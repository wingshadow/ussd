package ljwf.upload;

public class FileInfo {
  private String reqName;
  private String fileName;
  private boolean success;
  private String filePath;
  private long fileSize;
  private String realName; //real file name,auto generate by system

  //设置文件信息
  //上载文件是否有效标志
  public void setSuccess(boolean success) {
    this.success = success;
  }

  //设置表单请求名
  public void setReqName(String reqName) {
    this.reqName = reqName;
  }

  //文件名
  public void setFileName(String filename) {
    this.fileName = filename;
  }

  //存贮路径
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  //文件大小
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  //real name
  public void setRealName(String realName) {
    this.realName = realName;
  }

  //返回表单请求名
  public String getReqName() {
    return reqName;
  }

  //上载文件是否有效标志
  public boolean isSuccess() {
    return success;
  }

  //取得文件名
  public String getFileName() {
    return fileName;
  }

  //取得存贮路径
  public String getFilePath() {
    return filePath;
  }

  //取得文件大小
  public long getFileSize() {
    return fileSize;
  }

  //取得real name
  public String getRealName() {
    return realName;
  }
}
