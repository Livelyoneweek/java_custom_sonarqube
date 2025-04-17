package sonar.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;

@Rule(key = "ApiMethodMustHaveOperation")
public class Rule3ApiMethodMustHaveOperation extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree classTree) {
        boolean isRestController = classTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.RestController");

        if (isRestController) {
            super.visitClass(classTree); // 메서드까지 스캔 시작
        }
    }

    @Override
    public void visitMethod(MethodTree methodTree) {
        // Controller의 API 핸들러 메서드인지 확인
        boolean isRequestMapping = methodTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.RequestMapping")
                || methodTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.GetMapping")
                || methodTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.PostMapping")
                || methodTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.PutMapping")
                || methodTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.DeleteMapping");

        boolean hasOperationAnnotation = methodTree.symbol().metadata().isAnnotatedWith("io.swagger.v3.oas.annotations.Operation");

        if (isRequestMapping && !hasOperationAnnotation) {
            context.reportIssue(this, methodTree.simpleName(), "API 메서드에 @Operation(summary=...) 애너테이션을 반드시 추가해야 합니다.");
        }

        super.visitMethod(methodTree);
    }
}
