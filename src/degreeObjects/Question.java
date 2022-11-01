package degreeObjects;

public class Question {
    private String desc;

    public Question(String desc) {
        this.desc = desc;
    }

    public Question(){
        desc = "(No Desc)";
    }

    public Question(Question q){
        this.desc = new String(q.desc);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Object clone(){
        return new Question(this);
    }
}
