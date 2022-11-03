package guiRevamp.degreeObjectsPanels;

import degreeObjects.Semester;

import javax.swing.*;
import java.awt.*;

public class SemestersPanel extends GeneralDegreeObjectPanel {
    public Semester[] semesters;
    public CoursesPanel[] coursesPanels;

    SemestersPanel(int parentPanelNo) {
        super();

        this.parentPanelNo = parentPanelNo;
    }

    @Override
    public void addDegreeObject(JFrame parentFrame, String labelText, int numAdded){
        super.addDegreeObject(parentFrame, labelText, numAdded);
        for(int i = this.semesters.length - numAdded; i < this.semesters.length; i++)
            semesters[i].setSemester_No(i+1);
    }

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(semesters == null){
            semesters = new Semester[size];
            coursesPanels = new CoursesPanel[size];

            for(int i=0; i < semesters.length; i++) {
                semesters[i] = new Semester();
                coursesPanels[i] = new CoursesPanel(i);
            }
        }
        else {
            Semester[] semesters = new Semester[this.semesters.length + size];
            CoursesPanel[] coursesPanels = new CoursesPanel[this.coursesPanels.length + size];

            System.arraycopy(this.semesters, 0, semesters, 0, this.semesters.length);
            System.arraycopy(this.coursesPanels, 0, coursesPanels, 0, this.coursesPanels.length);

            for(int i=this.semesters.length; i < semesters.length; i++) {
                semesters[i] = new Semester();
                coursesPanels[i] = new CoursesPanel(i);
            }

            this.semesters = semesters;
            this.coursesPanels = coursesPanels;
        }
    }

    public Semester[] getDegreeObjects(){
        return semesters;
    }

    @Override
    public Object[] getInnerPanels() {
        return coursesPanels;
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s<br/> %s</pre></html>",
                        "Batch " + semesters[index].getBatch(),
                        (semesters[index].getCourses()!=null)?
                                semesters[index].getCourses().length + " Courses" : "0 Courses")
        );
        infoLabelOnHover.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        infoLabelOnHover.setForeground(new Color(0x774455));
        infoLabelOnHover.setHorizontalAlignment(SwingConstants.LEADING);
        infoLabelOnHover.setVerticalAlignment(SwingConstants.CENTER);
        infoLabelOnHover.setPreferredSize(new Dimension(250, 40));

        degreeObjectPanels[index].add(infoLabelOnHover, BorderLayout.CENTER);
        validate();
    }
}
