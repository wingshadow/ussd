package ljwf;

public class File {
  public File() {
  }

  public static String getSuffix(String filename) {
	return getSuffix(filename, false);
  }

  public static String getSuffix(String filename, boolean withPeriod) {
	int i = filename.lastIndexOf(".");
	if (i == -1) {
	  return "";
	}
	else {
	  if (withPeriod) {
		return filename.substring(i);
	  }
	  else {
		return filename.substring(i + 1);
	  }
	}
  }

  public static void copy(String srcFile, String destFile) throws Exception {
	java.io.File fs = new java.io.File(srcFile);
	java.io.File ds = new java.io.File(destFile);
	copy(fs, ds);
  }

  public static void copy(java.io.File srcFile, java.io.File destFile) throws
	Exception {
	long k = srcFile.length();
	if (k > 100000) {
	  k = 100000;
	}
	else {
	  k = 10000;
	}
	byte[] b = new byte[ (int) k];

	int u;
	java.io.FileInputStream fi = new java.io.FileInputStream(srcFile);
	java.io.FileOutputStream fo = new java.io.FileOutputStream(destFile);
	for (; ; ) {
	  u = fi.read(b);
	  fo.write(b, 0, u);
	  if (u < k) {
		break;
	  }
	}
	fi.close();
	fo.close();
  }

  public static void copy(String srcFile, java.io.File destFile) throws
	Exception {
	java.io.File fs = new java.io.File(srcFile);
	copy(fs, destFile);
  }

  public static String getPath(String[] paths) {
	String s = "";
	for (int i = 0; i < paths.length; i++) {
	  s += java.io.File.separator + paths[i];
	}
	return s;
  }

  public static void delFile(java.util.Vector vt) {
	if (vt != null) {
	  java.io.File f;
	  for (int i = 0; i < vt.size(); i++) {
		f = new java.io.File(vt.get(i).toString());
		f.delete();
	  }
	}
  }
}
