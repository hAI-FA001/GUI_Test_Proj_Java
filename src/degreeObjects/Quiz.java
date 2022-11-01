package degreeObjects;

import jdk.jfr.Description;

public class Quiz implements DegreeObjectCommon, DegreeDateCommon{

    @Description("make set button in GetInfoPanel")
    private String[] topics;
    private Date dueDate;

    public Quiz(String[] topics, Date dueDate) {
        this.topics = topics;
        this.dueDate = dueDate;
    }

    public Quiz(){
        this.dueDate = null;
        this.topics = new String[]{"(No Topics Found)"};
    }

    public Quiz(Quiz q){
        this.topics = new String[q.topics.length];
        this.dueDate = new Date(q.getDueDate().getDay(), q.getDueDate().getMonth(), q.getDueDate().getYear());

        for(int i=0; i < q.topics.length; i++)
            this.topics[i] = new String(q.topics[i]);
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String[] getTopics() {
        return topics;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void addTopic(String topic){
        if(this.topics == null)
            this.topics = new String[] {topic};
        else {
            String[] topics = new String[this.topics.length + 1];

            for(int i=0; i < this.topics.length; i++)
                topics[i] = this.topics[i];

            topics[topics.length - 1] = topic;

            this.topics = topics;
        }
    }

    @Override
    public Object clone(){
        return new Quiz(this);
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {
        setTopics((String[]) o);
    }

    @Override
    public Object getDateObject() {
        return getDueDate();
    }
}
