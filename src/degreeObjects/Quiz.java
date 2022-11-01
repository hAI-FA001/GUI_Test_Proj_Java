package degreeObjects;

import jdk.jfr.Description;

public class Quiz implements DegreeObjectCommon, DegreeDateCommon{

    @Description("make set button in GetInfoPanel, degree object")
    private Topic[] topics;
    private Date due_Date;

    public Quiz(Topic[] topics, Date dueDate) {
        this.topics = topics;
        this.due_Date = dueDate;
    }

    public Quiz(){
        this.due_Date = null;
        this.topics = null;
    }

    public Quiz(Quiz q){
        this.topics = new Topic[q.topics.length];
        this.due_Date = new Date(q.getDue_Date().getDay(), q.getDue_Date().getMonth(), q.getDue_Date().getYear());

        for(int i=0; i < q.topics.length; i++)
            this.topics[i] = new Topic(q.topics[i]);
    }

    public void setTopics(Topic[] topics) {
        this.topics = topics;
    }

    public void setDue_Date(Date due_Date) {
        this.due_Date = due_Date;
    }

    public Topic[] getTopics() {
        return topics;
    }

    public Date getDue_Date() {
        return due_Date;
    }

    public void addTopic(Topic topic){
        if(this.topics == null)
            this.topics = new Topic[] {topic};
        else {
            Topic[] topics = new Topic[this.topics.length + 1];

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
        setTopics((Topic[]) o);
    }

    @Override
    public Object[] getInnerDegreeObject() {
        return topics;
    }

    @Override
    public Object getDateObject() {
        return getDue_Date();
    }
}
