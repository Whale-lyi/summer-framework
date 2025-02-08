package top.whalefall.summerframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import top.whalefall.summerframework.aop.Pointcut;
import top.whalefall.summerframework.aop.PointcutAdvisor;

/**
 * @author Liu Yu
 * @description Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 * @date 2025-02-07 23:07:33
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }
}
