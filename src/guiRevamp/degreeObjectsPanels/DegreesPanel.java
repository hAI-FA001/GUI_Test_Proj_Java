package guiRevamp.degreeObjectsPanels;

import degreeObjects.DegreeProgram;

import javax.swing.*;
import java.awt.*;

public class DegreesPanel extends GeneralDegreeObjectPanel {
    public DegreeProgram[] degreePrograms;
    public SemestersPanel[] semestersPanels;

    @Override
    public void incrementSizeBy(int size) {
        super.incrementSizeBy(size);

        if(degreePrograms == null){
            degreePrograms = new DegreeProgram[size];
            semestersPanels = new SemestersPanel[size];

            for(int i=0; i < degreePrograms.length; i++) {
                degreePrograms[i] = new DegreeProgram(null, 0, null, null);
                semestersPanels[i] = new SemestersPanel(i);
            }
        }
        else {
            DegreeProgram[] degreePrograms = new DegreeProgram[this.degreePrograms.length + size];
            SemestersPanel[] semestersPanels = new SemestersPanel[this.semestersPanels.length + size];

            System.arraycopy(this.degreePrograms, 0, degreePrograms, 0, this.degreePrograms.length);
            System.arraycopy(this.semestersPanels, 0, semestersPanels, 0, this.semestersPanels.length);

            for(int i=this.degreePrograms.length; i < degreePrograms.length; i++) {
                degreePrograms[i] = new DegreeProgram(null, 0, null, null);
                semestersPanels[i] = new SemestersPanel(i);
            }

            this.degreePrograms = degreePrograms;
            this.semestersPanels = semestersPanels;
        }
    }

    @Override
    public DegreeProgram[] getDegreeObjects(){
        return degreePrograms;
    }

    @Override
    public Object[] getInnerPanels() {
        return semestersPanels;
    }

    @Override
    public void showInfo(int index){
        super.showInfo(index);
        infoLabelOnHover = new JLabel(
                String.format("<html><pre> %s<br/> %s</pre></html>",
                        degreePrograms[index].getDuration() + " Years",
                        (degreePrograms[index].getSemesters()!=null)?
                                degreePrograms[index].getSemesters().length + " Semesters" : "0 Semesters")
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
