package sonar;

import org.sonar.plugins.java.api.CheckRegistrar;

import java.util.Collections;
import java.util.List;

/**
 * CheckRegistrar 에 등록되어 있지 않으면, 룰을 인식만 하고 실행은 하지 않음
 */
public class MyCustomCheckRegistrar implements CheckRegistrar {
    @Override
    public void register(RegistrarContext registrarContext) {
        registrarContext.registerClassesForRepository(
                MyCustomRulesDefinition.REPOSITORY_KEY,
                List.of(MyCustomRule.class), // 메인 룰
                Collections.emptyList()      // 테스트 룰
        );
    }
}