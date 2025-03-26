package sonar;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(key = "MethodMustHaveComment", name = "Method Must Have Comment", description = "모든 함수는 주석을 달아야 해요")
public class MyCustomRule extends IssuableSubscriptionVisitor{

    @Override
    public List<Tree.Kind> nodesToVisit(){
        return Collections.singletonList(Tree.Kind.METHOD);
    }

    @Override
    public void visitNode(Tree tree){
        MethodTree method = (MethodTree)tree;
        Symbol.MethodSymbol methodSymbol = method.symbol();

        if(methodSymbol.isOverridable()){
            return;
        }
        if(method.firstToken().trivias().isEmpty()){
            reportIssue(method.simpleName(), "This method doesn't have a comment");
        }
    }
}
