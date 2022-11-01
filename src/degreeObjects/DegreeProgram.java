package degreeObjects;

import jdk.jfr.Description;

import javax.swing.*;

public class DegreeProgram implements DegreeObjectCommon{

    @Description("ignore in GetInfoPanel OptionsPanel")
    JPanel[] panelsSem;

    @Description("ignore in GetInfoPanel, degree object")
    private Semester[] semesters;
    private int duration;
    private String name;

    @Description("ignore in GetInfoPanel OptionsPanel")
    private ImageIcon logo;

    public DegreeProgram(Semester[] semesters, int duration, String name, ImageIcon logo) {
        this(semesters, duration, name);
        this.logo = logo;
    }

    public DegreeProgram(Semester[] semesters, int duration, String name) {
        this.semesters = semesters;
        this.duration = duration;
        this.name = name;
    }

    public DegreeProgram(){
        this.semesters = null;
        this.duration = 0;
        this.name = null;
        this.logo = null;
    }

    public Semester[] getSemesters() {
        return semesters;
    }

    public void setSemesters(Semester[] semesters) {
        this.semesters = semesters;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getLogo() {
        return logo;
    }

    public void setLogo(ImageIcon logo) {
        this.logo = logo;
    }

    @Override
    public void setInnerDegreeObjectTo(Object o) {
        setSemesters((Semester[]) o);
    }

    @Override
    public Object[] getInnerDegreeObject() {
        return semesters;
    }
}
