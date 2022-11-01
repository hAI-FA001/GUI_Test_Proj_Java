package degreeObjects;

import jdk.jfr.Description;

public class Assignment implements DegreeObjectCommon, DegreeDateCommon{

    @Description("make set button in GetInfoPanel")
    private Question[] questions;
    private Date dueDate;

    public Assignment(Question[] Question, Date dueDate) {
        this.questions = Question;
        this.dueDate = dueDate;
    }
    
    public Assignment(){
        this.questions = new Question[] {new Question()};
    }

    public Assignment(Assignment a){
        this.questions = new Question[a.questions.length];
        this.dueDate = new Date(a.dueDate.getDay(), a.dueDate.getMonth(), a.dueDate.getYear());

        for(int i=0; i<a.questions.length; i++)
            this.questions[i] = (Question) a.questions[i].clone();
    }

    public void setQuestion(Question[] Question) {
        this.questions = Question;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Question[] getQuestion() {
        return questions;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void addQuestion(Question q){
        if(this.questions == null)
            this.questions = new Question[]{new Question(q)};
        else
        {
            Question[] questions = new Question[this.getQuestion().length+1];

            for(int i=0; i< this.questions.length; i++){
                questions[i] = this.questions[i];
            }

            questions[questions.length-1] = q;

            this.questions = questions;
        }
    }

    @Override
    public Object clone(){
        return new Assignment(this);
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {
        setQuestion((Question[]) o);
    }

    @Override
    public Object getDateObject() {
        return getDueDate();
    }
}
