package validationfo.spring;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.fozone.validation.IValidateService;

public class SpringTest {
	public static User createUser() {
		User user = new User();

		user.setEmail("fozone#41zone.cc");

		user.setPassword("12345");
		user.setPasswordOne("abcde");

		Calendar date = Calendar.getInstance();

		user.setStarttime(new Timestamp(date.getTimeInMillis()));
		user.setEndtime(new Timestamp(date.getTimeInMillis() - 1000));

		return user;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"validationfo/spring/context.xml");

		// 获取验证服务
		IValidateService service = context.getBean(IValidateService.class);
		// 创建用户对象
		User user = createUser();

		// 执行验证
		Map<String, String> map = service.validate(user, "user.validate");
		// 输出结果
		if (map == null || map.size() == 0) {
			System.out.println("验证成功");
		} else {
			System.out.println("验证失败，结果如下");
			System.out.println(map);
		}
	}
}
