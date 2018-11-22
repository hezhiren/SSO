package cn.hzr0523.DI;

/**
 * hezhi
 * 2018/11/17 16:32
 */
public class BraveKnight {
    private Quest quest;

    public BraveKnight(Quest quest){
        this.quest = quest;
    }

    public void save() {
        quest.embark();
    }

}
