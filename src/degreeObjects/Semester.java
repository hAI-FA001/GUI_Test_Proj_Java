package degreeObjects;

import jdk.jfr.Description;

import javax.swing.*;

public class Semester implements DegreeObjectCommon, DegreeDateCommon{

    @Description("ignore in GetInfoPanel OptionsPanel")
    JPanel[] panelsCourses;

    @Description("ignore in GetInfoPanel")
    private Course[] courses;
    private int semesterNo;
    private String batch;
    private Date[] importantDates;

    public Semester(Course[] courses, int semesterNo, String batch, Date[] importantDates) {
        this.courses = courses;
        this.semesterNo = semesterNo;
        this.batch = batch;
        this.importantDates = importantDates;
    }

    public Semester(){
        this.courses = new Course[]{new Course()};
        this.batch = "(No Batch Info Found)";
        this.importantDates = new Date[] {new Date()};
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setImportantDates(Date[] importantDates) {
        this.importantDates = importantDates;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public String getBatch() {
        return batch;
    }

    public Date[] getImportantDates() {
        return importantDates;
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
        if(this.importantDates == null)
            this.importantDates = new Date[] {d};
        else {
            Date[] importantDates = new Date[this.importantDates.length+1];

            for(int i=0; i < this.importantDates.length; i++)
                importantDates[i] = this.importantDates[i];

            importantDates[importantDates.length-1] = d;

            this.importantDates = importantDates;
        }
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {
        setCourses((Course[]) o);
    }

    @Override
    public Object getDateObject() {
        return getImportantDates();
    }
}
