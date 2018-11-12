package cn.hzr0523.test;

class Info{	// ¶¨ÒåÐÅÏ¢Àà
    private String name = "ÀîÐË»ª";	 // ¶¨ÒånameÊôÐÔ
    private String content = "JAVA½²Ê¦"  ;		// ¶¨ÒåcontentÊôÐÔ
    private boolean flag = false ;	// ÉèÖÃ±êÖ¾Î»
    public synchronized void set(String name,String content){
        if(!flag){
            try{
                super.wait() ;
            }catch(InterruptedException e){
                e.printStackTrace() ;
            }
        }
        this.setName(name) ;	// ÉèÖÃÃû³Æ
        try{
            Thread.sleep(300) ;
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }
        this.setContent(content) ;	// ÉèÖÃÄÚÈÝ
        flag  = false ;	// ¸Ä±ä±êÖ¾Î»£¬±íÊ¾¿ÉÒÔÈ¡×ß
        super.notify() ;
    }
    public synchronized void get(){
        if(flag){
            try{
                super.wait() ;
            }catch(InterruptedException e){
                e.printStackTrace() ;
            }
        }
        try{
            Thread.sleep(300) ;
        }catch(InterruptedException e){
            e.printStackTrace() ;
        }
        System.out.println(this.getName() +
                " --> " + this.getContent()) ;
        flag  = true ;	// ¸Ä±ä±êÖ¾Î»£¬±íÊ¾¿ÉÒÔÉú²ú
        super.notify() ;
    }
    public void setName(String name){
        this.name = name ;
    }
    public void setContent(String content){
        this.content = content ;
    }
    public String getName(){
        return this.name ;
    }
    public String getContent(){
        return this.content ;
    }
};
class Producer implements Runnable{	// Í¨¹ýRunnableÊµÏÖ¶àÏß³Ì
    private Info info = null ;		// ±£´æInfoÒýÓÃ
    public Producer(Info info){
        this.info = info ;
    }
    public void run(){
        boolean flag = false ;	// ¶¨Òå±ê¼ÇÎ»
        for(int i=0;i<50;i++){
            if(flag){
                this.info.set("ÀîÐË»ª","JAVA½²Ê¦") ;	// ÉèÖÃÃû³Æ
                flag = false ;
            }else{
                this.info.set("mldn","www.mldnjava.cn") ;	// ÉèÖÃÃû³Æ
                flag = true ;
            }
        }
    }
};
class Consumer implements Runnable{
    private Info info = null ;
    public Consumer(Info info){
        this.info = info ;
    }
    public void run(){
        for(int i=0;i<50;i++){
            this.info.get() ;
        }
    }
};
public class ThreadCaseDemo03{
    public static void main(String args[]){
        Info info = new Info();	// ÊµÀý»¯Info¶ÔÏó
        Producer pro = new Producer(info) ;	// Éú²úÕß
        Consumer con = new Consumer(info) ;	// Ïû·ÑÕß
        new Thread(pro).start() ;
        new Thread(con).start() ;
    }
};
