package sonar.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

@Rule(key = "RestControllerMustHaveTag")
public class Rule2RestControllerMustHaveTag extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree classTree) {
        boolean isRestController = classTree.symbol().metadata().isAnnotatedWith("org.springframework.web.bind.annotation.RestController");
        boolean hasTagAnnotation = classTree.symbol().metadata().isAnnotatedWith("io.swagger.v3.oas.annotations.tags.Tag");

        if (isRestController && !hasTagAnnotation) {
            context.reportIssue(this, classTree.simpleName(), "@RestController 클래스에는 @Tag 값을 추가해야 합니다.");
        }

        super.visitClass(classTree);
    }
}
