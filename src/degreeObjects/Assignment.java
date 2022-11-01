package degreeObjects;

import jdk.jfr.Description;

public class Assignment implements DegreeObjectCommon, DegreeDateCommon{

    @Description("make set button in GetInfoPanel, degree object")
    private Question[] questions;
    private Date due_Date;

    public Assignment(Question[] Question, Date dueDate) {
        this.questions = Question;
        this.due_Date = dueDate;
    }
    
    public Assignment(){
        this.questions = null;
    }

    public Assignment(Assignment a){
        this.questions = new Question[a.questions.length];
        this.due_Date = new Date(a.due_Date.getDay(), a.due_Date.getMonth(), a.due_Date.getYear());

        for(int i=0; i<a.questions.length; i++)
            this.questions[i] = (Question) a.questions[i].clone();
    }

    public void setQuestion(Question[] Question) {
        this.questions = Question;
    }

    public void setDue_Date(Date due_Date) {
        this.due_Date = due_Date;
    }

    public Question[] getQuestion() {
        return questions;
    }

    public Date getDue_Date() {
        return due_Date;
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
    public Object[] getInnerDegreeObject() {
        return questions;
    }

    @Override
    public Object getDateObject() {
        return getDue_Date();
    }
}
