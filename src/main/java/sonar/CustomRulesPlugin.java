package sonar;

import org.sonar.api.Plugin;

public class CustomRulesPlugin implements Plugin {
    @Override
    public void define(Context context) {
        // 커스텀 룰을 등록합니다.
        context.addExtensions(MyCustomRulesDefinition.class, MyCustomRule.class, MyCustomCheckRegistrar.class);
    }
}
