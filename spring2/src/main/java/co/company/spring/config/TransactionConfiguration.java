package co.company.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


@Configuration
@EnableAspectJAutoProxy
public class TransactionConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConfiguration.class);
	private static final int TX_METHOD_TIMEOUT = 3;
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* co.company..service.impl.*ServiceImpl.*(..))";

	@Autowired
	//private PlatformTransactionManager transactionManager;
	private DataSourceTransactionManager transactionManager;

	@Bean // 1. 어드바이스를 빈 등록
	public TransactionInterceptor txAdvice() {
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties txAttributes = new Properties();
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class));

		/** If need to add additionall exceptio, add here **/
		DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readOnlyAttribute.setReadOnly(true);
		readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);
		RuleBasedTransactionAttribute writeAttribute = 
				new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(TX_METHOD_TIMEOUT);

		String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
		String writeTransactionAttributesDefinition = writeAttribute.toString();

		//read only : 이런이름으로 시작하는 메소드들은 트랜잭션 처리할필요없다. 단순 조회성 메소드
		txAttributes.setProperty("retrieve*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("select*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("get*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("list*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("search*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);
		txAttributes.setProperty("count*", readOnlyTransactionAttributesDefinition);

		// write rollback-rule : 그 외의 나머지것들은 트랜잭션 처리
		txAttributes.setProperty("*", writeTransactionAttributesDefinition);
		txAdvice.setTransactionAttributes(txAttributes);
		txAdvice.setTransactionManager(transactionManager); // 트랜잭션매니저 등록
		return txAdvice;
	}

	@Bean
	public Advisor txAdviceAdvisor() {
		// 포인트컷 만듦
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		// pointcut.setExpression("(execution(* *..*.service..*.*(..)) ||
		// execution(* *..*.services..*.*(..)))");
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION); // 이 포인트컷에 해당하는 메소드를 실행할때 트랜잭션 처리를 해라
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
