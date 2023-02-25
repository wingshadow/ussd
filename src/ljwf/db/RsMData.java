package ljwf.db;

public class RsMData {
  private java.util.HashMap m;
  private int[] tp; //type

  public RsMData(java.sql.ResultSetMetaData rm) throws Exception {
    //this.rm = rm;
    m = new java.util.HashMap();
    int k = rm.getColumnCount();
    tp = new int[k];
    for (int i = 1; i <= k; i++) {
      m.put(rm.getColumnName(i).toLowerCase(), new Integer(i));
      tp[i-1] = rm.getColumnType(i);
    }
  }

  public int getType(String name) {
    try{
      Integer i = (Integer) (m.get(name));
      return tp[i.intValue() - 1];
    }catch(Exception e)
    {
      ljwf.Log.error("name="+name,e);
    }
    return java.sql.Types.NULL;
  }
}
