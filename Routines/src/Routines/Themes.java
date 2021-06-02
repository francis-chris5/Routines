
package Routines;


/**
 * Trying something new with this project, available themes enumerated with a method to return a long string to write to the main CSS file, instead of the more complicated writing methods to switch out which stylesheet(s) objects reference
 * @author Chris Francis
 */
public enum Themes {
    
    LIGHT, DARK;
    
    /**
     * call this method when changing the preset styling of the software
     * @return <b>String</b> of CSS code including line-feeds and white-space to write directly to a file
     */
    public String changeTheme(){
        switch(this){
            case LIGHT:
                return "*{\n" +
                        "    -fx-font-family: \"Times New Roman\";\n" +
                        "}";
            case DARK:
                return "*{\n" +
                        "    -fx-font-family: \"Times New Roman\";\n" +
                        "    -fx-background-color: #323232;\n" +
                        "    -fx-text-fill: #e2e2e2;\n" +
                        "}\n" +
                        "\n" +
                        ".from-file{\n" +
                        "   -fx-fill: #e2e2e2;\n" + 
                        "}\n" +
                        "\n" + 
                        ".tab{\n" +
                        "    -fx-opacity: 0.50;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".tab-pane>*.tab-header-area>*.tab-header-background{\n" +
                        "    -fx-background-color: #545454;\n" +
                        "}\n" +
                        "\n" +
                        ".tab:selected{\n" +
                        "    -fx-background-color: #3876a288;\n" +
                        "}\n" +
                        ".button, .list-view, .tab-pane{\n" +
                        "    -fx-border-color: #545454;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".list-cell:filled:selected{\n" +
                        "    -fx-background-color: #3876a288;\n" +
                        "}\n" +
                        "\n" +
                        ".list-cell:even{\n" +
                        "    -fx-background-color: #545454;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".chart-pie-label{\n" +
                        "    -fx-fill: #e2e2e2; \n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".scroll-bar:horizontal .thumb, .scroll-bar:vertical .thumb{\n" +
                        "    -fx-background-color: #545454;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".split-pane > .split-pane-divider, .text-field, .radio-button .radio, .slider .thumb, .date-picker>.arrow-button>.arrow, .combo-box-base>.arrow-button>.arrow{\n" +
                        "    -fx-background-color: #545454;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        ".radio-button:selected .radio{\n" +
                        "    -fx-background-color: #3876a288;\n" +
                        "}";
            default:
                return "*{\n" +
                        "    -fx-font-family: \"Times New Roman\";\n" +
                        "}";
        }
    }//end changeTheme()
}//end Themes
