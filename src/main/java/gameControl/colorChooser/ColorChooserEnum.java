package gameControl.colorChooser;

/**
 * Enums for storing all default ColorChoosers to then use in UI
 * or to create ColorChooser from string sent to the server
 */
public enum ColorChooserEnum {
    RANDOM_CHOOSER(new RandomColorChooser(),"Random"),
    WHITE_CHOOSER(new WhiteChooser(),"White"),
    BLACK_CHOOSER(new BlackChooser(),"Black");

    private final ColorChooser colorChooser;
    private final String name;

    ColorChooserEnum(ColorChooser colorChooser, String name) {
        this.colorChooser = colorChooser;
        this.name = name;
    }

    public ColorChooser getColorChooser() {
        return colorChooser;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
