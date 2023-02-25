package ljwf;

public class ListView {
    protected java.util.HashMap desc;
    protected String[] fldname;
    protected String data[][];
    protected int cur = -1; //begin with 0
    protected ljwf.MultiPages mp;
    protected ljwf.db.RsMData rm;

    public static ljwf.ListView getDefault() {
        ljwf.ListView v = new ljwf.ListView();
        v.data = new String[0][0];
        v.fldname = new String[0];
        v.desc = new java.util.HashMap(0);
        return v;
    }

    public ListView() {
    }

    public int getLen() {
        return data.length;
    }

    public int getFldCount() {
        return desc.size();
    }

    public String[] getFlds(String name) {
        Integer a = (Integer) (desc.get(name));
        String r[] = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            r[i] = data[i][a.intValue()];
        }
        return r;
    }

    public String[] getFlds(int iFld) {
        String r[] = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            r[i] = data[i][iFld];
        }
        return r;
    }

    public String getFld(String name) {
        if (cur < 0 || cur >= data.length) {
            return "";
        } else {
            Integer a = (Integer) (desc.get(name));
            return data[cur][a.intValue()];
        }
    }

    public String getFld(int iFld) {
        if (cur < 0 || cur >= data.length) {
            return "";
        } else {
            return data[cur][iFld];
        }
    }

    public String getFld(int iRow, String name) {
        if (iRow < 0 || iRow >= data.length) {
            return "";
        } else {
            Integer a = (Integer) (desc.get(name));
            return data[iRow][a.intValue()];
        }
    }

    public String getFld(int iRow, int iFld) {
        if (iRow < 0 || iRow >= data.length) {
            return "";
        } else {
            return data[iRow][iFld];
        }
    }

    public String getFldName(int iFld) {
        if (fldname == null) {
            fldname = new String[this.getFldCount()];
            java.util.Iterator i = desc.keySet().iterator();
            for (; i.hasNext(); ) {
                Object o = i.next();
                Integer k = new Integer(desc.get(o).toString());
                fldname[k.intValue()] = o.toString();
            }
        }
        return fldname[iFld];
    }

    public void setFld(int iFld, String val) {
        if (cur >= 0 && cur < data.length) {
            data[cur][iFld] = val;
        }
    }

    public void setFld(int iRow, int iFld, String val) {
        if (iRow >= 0 && iRow < data.length) {
            data[iRow][iFld] = val;
        }
    }

    public void setFld(String name, String val) {
        if (cur >= 0 && cur < data.length) {
            Integer a = (Integer) (desc.get(name));
            data[cur][a.intValue()] = val;
        }
    }

    public void setFld(int iRow, String name, String val) {
        if (iRow >= 0 && iRow < data.length) {
            Integer a = (Integer) (desc.get(name));
            data[iRow][a.intValue()] = val;
        }
    }

    public boolean beforefirst() {
        cur = -1;
        if (data.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean first() {
        if (data.length == 0) {
            cur = -1;
            return false;
        } else {
            cur = 0;
            return true;
        }
    }

    public boolean afterlast() {
        if (data.length == 0) {
            cur = -1;
            return false;
        } else {
            cur = data.length;
            return true;
        }
    }

    public boolean last() {
        if (data.length == 0) {
            cur = -1;
            return false;
        } else {
            cur = data.length - 1;
            return true;
        }
    }

    public boolean next() {
        if (data.length == 0) {
            cur = -1;
            return false;
        } else if (data.length > cur + 1) {
            cur++;
            return true;
        } else {
            cur++;
            return false;
        }
    }

    public boolean move(int idx) {
        if (data.length == 0) {
            return false;
        } else if (data.length > idx) {
            cur = idx;
            return true;
        } else {
            return false;
        }
    }

    public boolean pre() {
        if (data.length == 0) {
            cur = -1;
            return false;
        } else if (cur > -1) {
            cur--;
            return true;
        } else {
            return false;
        }
    }

    public void setLen(int row, int col) {
        data = new String[row<0 ? 0 : row][col < 0 ? 0 : col];
    }

    public void setDesc(String name, int col) {
        if (desc == null) {
            desc = new java.util.HashMap();
        }
        desc.put(name, new Integer(col));
    }

    public void setDesc(String[] name) {
        desc = new java.util.HashMap();
        for (int i = 0; i < name.length; i++) {
            desc.put(name[i], new Integer(i));
        }
    }

    /**
     * 得行的下标，从0开始记数
     * @return int
     */
    public int getCur() {
        return cur;
    }

    /**
     * 得序号，从1开始记数
     * @return int
     */
    public int getNo() {
        if (cur == -1) {
            return -1;
        }
        return cur + 1;
    }

    /**
     * 得当前行在各页中所有行中的下标,begin with 0
     * @return int
     */
    public int getCurInAll() {
        if (cur == -1) {
            return -1;
        }
        return (this.getCurPage() - 1) * this.getNumPerPage() + cur;
    }

    /**
     * 得当前行在各页中所有行中的序号,begin with 1
     * @return int
     */
    public int getNoInAll() {
        if (cur == -1) {
            return -1;
        }
        return (this.getCurPage() - 1) * this.getNumPerPage() + cur + 1;
    }

    public boolean Valid() {
        return cur < getLen() && cur > -1;
    }

    public boolean isLast() {
        return cur == getLen() - 1;
    }

    public boolean isFirst() {
        return cur == 0;
    }

    //the functions below is for split pages,if havn't splited pages,don't use them
    public int getPageNum() { //get page count
        if (mp != null) {
            return mp.getPageNum();
        } else if (getLen() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getNumPerPage() { //get num per page
        if (mp != null) {
            return mp.getNumPerPage();
        } else {
            return getLen();
        }
    }

    public int getRowNum() { //get row count of all pages
        if (mp != null) {
            return mp.getRowNum();
        } else {
            return getLen();
        }
    }

    public int getCurPage() { //get current page index,from 1
        if (mp != null) {
            return mp.getCurPage();
        } else {
            return 1;
        }
    }

    public ljwf.MultiPages getPage() {
        return mp;
    }

    public void setPage(ljwf.MultiPages mp) {
        this.mp = mp;
    }

    public static ljwf.ListView add(ljwf.ListView v1, ljwf.ListView v2, int col,
                                    int iPage, int numpp) {

        ljwf.ListView lv = new ListView();
        int row = v1.getLen() + v2.getLen();
        lv.setLen(row, col);

        ljwf.MultiPages mp = new ljwf.MultiPages(row, iPage, numpp);
        lv.setPage(mp);

        for (int i = 0; i < col; i++) {
            lv.setDesc(String.valueOf(i), i);
        }
        int i = 0;
        v1.beforefirst();
        while (v1.next()) {
            for (int j = 0; j < v1.getFldCount(); j++) {
                lv.setFld(i, j, v1.getFld(j));
            }
            i++;
        }
        v2.beforefirst();
        while (v2.next()) {
            for (int j = 0; j < v2.getFldCount(); j++) {
                lv.setFld(i, j, v2.getFld(j));
            }
            i++;
        }
        return lv;
    }

    public void copy(ListView v) {
        v.cur = cur;
        v.desc = desc;
        v.data = data;
        v.fldname = fldname;
        v.mp = mp;
        v.rm = rm;
    }

    public void setRsMData(ljwf.db.RsMData rm) {
        this.rm = rm;
    }

    public int getType(String name) {
        return rm == null ? java.sql.Types.NULL : rm.getType(name);
    }
}
