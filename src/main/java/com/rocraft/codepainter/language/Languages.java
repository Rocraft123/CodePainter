package com.rocraft.codepainter.language;

import com.rocraft.codepainter.language.languages.JavaLanguage;
import com.rocraft.codepainter.language.languages.JavaScriptLanguage;
import com.rocraft.codepainter.language.rules.JavaRules;
import com.rocraft.codepainter.language.rules.JavaScriptRules;

public class Languages {

    public static final JavaLanguage JAVA = new JavaLanguage();
    public static final JavaRules JAVA_RULES = new JavaRules(JAVA);

    public static final JavaScriptLanguage JAVA_SCRIPT = new JavaScriptLanguage();
    public static final JavaScriptRules JAVA_SCRIPT_RULES = new JavaScriptRules(JAVA_SCRIPT);
}
