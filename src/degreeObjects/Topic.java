package degreeObjects;

public class Topic {
    private String desc;

    public Topic(String desc) {
        this.desc = desc;
    }

    public Topic(){
        desc = "(No Desc)";
    }

    public Topic(Topic q){
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
        return new Topic(this);
    }
}
