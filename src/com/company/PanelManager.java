package com.company;

import com.company.logic.AppModel;
import com.company.panels.LecturePanel;
import com.company.panels.PracticePanel;

/**
 * Created by pin on 01.03.2017.
 */
public class PanelManager {
    private AppModel model;
    private LecturePanel lecturePanel;
    private PracticePanel practicePanel;

    public PanelManager() {
        this.model = AppModel.getInstance();
    }
}
