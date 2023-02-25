package ljwf;

public class MultiPages {

  int bg; //begin of cur page,begin from 0
  int sz; //size of cur page
  int iPage; //cur page,begin from 1
  int numpp; //num per page
  int PageNum; //num of pages
  int RowNum; //num of rows

  public MultiPages(int RowsNum, Object CurPage, Object NumPerPage) {
    RowNum = RowsNum;
    if (CurPage == null) {
      iPage = 1;
    }
    else {
      iPage = Integer.parseInt(CurPage.toString());
    }
    if (NumPerPage == null) {
      numpp = 10;
    }
    else {
      numpp = Integer.parseInt(NumPerPage.toString());
    }
    SplitPages();
  }

  public MultiPages(int RowsNum, int CurPage, int NumPerPage) {
    RowNum = RowsNum;
    iPage = CurPage;
    numpp = NumPerPage;
    SplitPages();
  }

  void SplitPages() {
    if (numpp == 0) { //not split to pages
      bg = 0;
      sz = RowNum;
    }
    else {
      PageNum = (int) java.lang.Math.ceil( (double) RowNum / numpp);
      bg = numpp * (iPage - 1);
      if (bg < 0 || (bg >= RowNum && bg != 0)) { //exceed scope of row
        iPage = PageNum;
        bg = numpp * (iPage - 1);
      }
      if (iPage * numpp <= RowNum) {
        sz = numpp;
      }
      else {
        sz = RowNum - (iPage - 1) * numpp;
      }
    }
  }

  public int getBegin() { //begin index of row of cur page
    return bg;
  }

  public int getSize() { //real size of cur page
    return sz;
  }

  public int getPageNum() {
    return PageNum;
  }

  public int getNumPerPage() { //
    return numpp;
  }

  public int getRowNum() { //
    return RowNum;
  }

  public int getCurPage() { //
    return iPage;
  }
}
