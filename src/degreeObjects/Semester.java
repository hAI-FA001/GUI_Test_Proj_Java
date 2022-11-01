package degreeObjects;

import jdk.jfr.Description;

import javax.swing.*;

public class Semester implements DegreeObjectCommon, DegreeDateCommon{

    @Description("ignore in GetInfoPanel OptionsPanel")
    JPanel[] panelsCourses;

    @Description("ignore in GetInfoPanel, degree object")
    private Course[] courses;
    @Description("ignore in GetInfoPanel")
    private int semester_No;
    private String batch;
    private Date[] important_Dates;

    public Semester(Course[] courses, int semesterNo, String batch, Date[] importantDates) {
        this.courses = courses;
        this.semester_No = semesterNo;
        this.batch = batch;
        this.important_Dates = importantDates;
    }

    public Semester(){
        this.courses = new Course[]{new Course()};
        this.batch = "(No Batch Info Found)";
        this.important_Dates = new Date[] {new Date()};
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public void setSemester_No(int semester_No) {
        this.semester_No = semester_No;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setImportant_Dates(Date[] important_Dates) {
        this.important_Dates = important_Dates;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getSemester_No() {
        return semester_No;
    }

    public String getBatch() {
        return batch;
    }

    public Date[] getImportant_Dates() {
        return important_Dates;
    }

    public void addCourse(Course c){
        if(this.courses == null)
            this.courses = new Course[] {c};
        else {
            Course[] courses = new Course[this.courses.length+1];

            for(int i=0; i < this.courses.length; i++)
                courses[i] = this.courses[i];

            courses[courses.length-1] = c;

            this.courses = courses;
        }
    }

    public void addImportantDate(Date d){
        if(this.important_Dates == null)
            this.important_Dates = new Date[] {d};
        else {
            Date[] importantDates = new Date[this.important_Dates.length+1];

            for(int i = 0; i < this.important_Dates.length; i++)
                importantDates[i] = this.important_Dates[i];

            importantDates[importantDates.length-1] = d;

            this.important_Dates = importantDates;
        }
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {
        setCourses((Course[]) o);
    }

    @Override
    public Object[] getInnerDegreeObject() {
        return courses;
    }

    @Override
    public Object getDateObject() {
        return getImportant_Dates();
    }
}
