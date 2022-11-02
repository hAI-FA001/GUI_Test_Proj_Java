package guiRevamp;

public enum constants {
     ;
    static final float[] PANEL_RATIOS = {3F/32, 9F/16, 11F/32};
    public static final int GO_BACK = 0, SHOW_FAVS = 1, SEARCH = 2, EDIT = 3, ADD_TO_FAV = 4, ADD_NOTE = 5, ADD_BTN = 6,
            FRAME_WIDTH = 750, FRAME_HEIGHT = 750,
            PANEL_ABOVE_HEIGHT = (int) (PANEL_RATIOS[0]*FRAME_HEIGHT),
            PANEL_CENTRE_HEIGHT = (int) (PANEL_RATIOS[1]*FRAME_HEIGHT),
            PANEL_BELOW_HEIGHT = (int) (PANEL_RATIOS[2]*FRAME_HEIGHT);
}
