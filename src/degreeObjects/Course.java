package degreeObjects;

import jdk.jfr.Description;

import javax.swing.*;

public class Course implements DegreeObjectCommon{
    @Description("ignore in GetInfoPanel OptionsPanel")
    JPanel[] panelsAssignments, panelsQuizzes;

    private String name, code, desc;
    private int credit_hrs;

    @Description("ignore in GetInfoPanel")
    private Assignment[] assignments;
    @Description("ignore in GetInfoPanel")
    private Quiz[] quizzes;

    public Course(String name, String code, String desc, int credit_hrs, Assignment[] assignments, Quiz[] quizzes) {
        this.name = name;
        this.code = code;
        this.desc = desc;
        this.credit_hrs = credit_hrs;
        this.assignments = assignments;
        this.quizzes = quizzes;
    }

    public Course(){
        this.name = "(No Name Found)";
        this.code = "(No degreeObjects.Course Code Found)";
        this.desc = "(No Desc Found)";

        this.assignments = new Assignment[] {new Assignment()};
        this.quizzes = new Quiz[] {new Quiz()};
    }

    public Course(Course c){
        this.name = c.name;
        this.code = c.code;
        this.desc = c.desc;
        this.credit_hrs = c.credit_hrs;

        this.assignments = new Assignment[c.assignments.length];
        this.quizzes = new Quiz[c.quizzes.length];

        for(int i=0; i < c.assignments.length; i++)
            this.assignments[i] = (Assignment) c.assignments[i].clone();

        for(int i=0; i < c.quizzes.length; i++)
            this.quizzes[i] = (Quiz) c.quizzes[i].clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCredit_hrs(int credit_hrs) {
        this.credit_hrs = credit_hrs;
    }

    public void setAssignments(Assignment[] assignments) {
        this.assignments = assignments;
    }

    public void setQuizzes(Quiz[] quizzes) {
        this.quizzes = quizzes;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCredit_hrs() {
        return credit_hrs;
    }

    public Assignment[] getAssignments() {
        return assignments;
    }

    public Quiz[] getQuizzes() {
        return quizzes;
    }

    public void addAssignment(Assignment a){
        if(this.assignments == null)
            this.assignments = new Assignment[]{a};
        else {
         Assignment[] assignments = new Assignment[this.assignments.length+1];

         for(int i=0; i < this.assignments.length; i++)
             assignments[i] = this.assignments[i];

         assignments[assignments.length-1] = a;

         this.assignments = assignments;
        }
    }

    public void addQuiz(Quiz q){
        if(this.quizzes == null)
            this.quizzes = new Quiz[] {q};
        else {
            Quiz[] quizzes = new Quiz[this.quizzes.length+1];

            for(int i=0; i < this.quizzes.length; i++)
                quizzes[i] = this.quizzes[i];

            quizzes[quizzes.length-1] = q;

            this.quizzes = quizzes;
        }
    }

    @Override
    public Object clone(){
        return new Course(this);
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {

        if(o instanceof Assignment[])
            setAssignments((Assignment[]) o);
        else
            setQuizzes((Quiz[]) o);
    }
}
