package entity;

import java.util.List;

public class Page<T> {
    private int page = 1;  //   默认页数为1
    private int size = 5;  //   查询记录为5
    private int count = 0; //   数据库中的记录总数
    private List<T> list;  //   存放查询到的数据
    private int num = 10;  //   默认显示的固定页数
    private int current ;   //   当前页

    public Page(String p,String s){
        if(p!=null){
            this.page = Integer.parseInt(p);
        }

        if(s!=null){
            this.size = Integer.parseInt(s);
        }
    }

    // limit 的 起始位置
    public int getStart(){
        return (page-1)*size;
    }

    // limit 的查询的大小
    public  int getSize(){
        return size;
    }

    // 得到总记录数
    public int getTotal(){

        return (count-1)/size +1;

        /*if( (count % size) ==0){
            return count/size;
        }else {
            return (count/size) +1;
        }*/
    }

    //首页
    public int getFirst(){
        return 1;
    }

    //尾页
    public int getLast(){
        return getTotal();
    }

    //上一页
    public int getPre(){
        if(page>1){
            return page-1;
        }else{
            return 1;
        }
    }

    //下一页
    public int getNext(){
        if(page< getTotal()){
            return page+1;
        }else{
            return getTotal();
        }
    }

    // 是否有上一页
    public boolean hasPre(){

        return page>1;

    }

    // 是否有下一页
    public boolean hasNext(){

        return page<getTotal();

    }

    // 是否是当前页面
    public boolean isCurrent(int i){
        this.current = i;
        return page == i ;
    }

   /* // 起始页面
    public  int getOne(){
        int startNum = 0;
        if (page<= num) {
            startNum = 1;
        } else {
            if ((page + num) >= getTotal()) {
                if ((2 * num + 1) >= getTotal()) {
                    if ((getTotal() - 2 * num) >= 1) {
                        startNum = getTotal() - 2 * num;
                    } else {
                        startNum = 1;
                    }
                } else {
                    startNum = getTotal() - 2 * num;
                }
            } else {
                if ((page - num) >= 1) {
                    startNum = page- num;
                } else {
                    startNum = 1;
                }
            }
        }

        return startNum;
    }



    // 结束页面
    public int getEnd(){

        int endNum = 0;

        if (getTotal() <= num) {
            endNum = getTotal();
        } else {
            if ((num + page) >= getTotal()) {
                endNum = getTotal();
            } else {
                endNum = num + page;
                if ((num + page) <= (2 * num + 1)) {
                    if ((2 * num + 1) >= getTotal()) {
                        endNum = getTotal();
                    } else {
                        endNum = 2 * num + 1;
                    }
                } else {
                    endNum = num + page;
                }
            }
        }

        return endNum;
    }*/

    public  int getOne(){
        int startNum = 0;



        int right = num/2-1;   // 当前页面右边 固定 4 页
        int left  = num/2; // 当前页面左边 固定 5 页

        if(getTotal() < num ){
            startNum = 1;
        }else{
            if(page < (num/2)+1){ // <6
                startNum = 1;
            }else{
                if((page+right)<= getTotal()){
                    startNum = page-left;
                }else{
                    startNum =getTotal()-num +1;
                }
            }
        }

        return startNum;
    }

    public int getEnd() {
        int endNum = 0;


        int right = num/2-1;   // 当前页面右边 固定 4 页
        int left  = num/2; // 当前页面左边 固定 5 页


        if(getTotal() < num ){
            endNum   = getTotal();
        }else{
            if(page < (num/2)+1){ // <6
                endNum   = num;
            }else{
                if((page+right) <= getTotal()){
                    endNum   = page + right;
                }else{
                    endNum = getTotal();
                }
            }
        }

        return endNum;
    }


    /*public void getStartEnd(){
        int right = num - current;   // 当前页面右边 固定 4 页
        int left  = num - current-1; // 当前页面左边 固定 5 页
        if(current <= getTotal() ){
            startNum = 1;
            endNum   = getTotal();
        }else{
            if(current < (num/2)+1){ // <6
                startNum = 1;
                endNum   = num;
            }else{
               if((current+right) <= getTotal()){
                   startNum = current-left;
                   endNum   = current + right;
               }else{
                    startNum = current - left;
                    endNum = getTotal();
               }
            }
        }
    }*/

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
