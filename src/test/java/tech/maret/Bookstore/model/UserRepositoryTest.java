package tech.maret.bookstore.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository usrR;
	
	private User usr;
	
	@Test
	public void createNewUser() {
		usr = new User("user1", "$2a$10$htMeYR95oApoqIO6yKPxVuooN.EhKAveLg6hf207TCFt7N9RuVTA2", "user@maret.tech","USER");
		usrR.save(usr);
		
		assertThat(usr.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		User usr1 = usrR.findByUsername("user");
		Long id = usr1.getId();
		
		usrR.deleteById(id);	
		assertThat(usrR.findByUsername("user")).isNull();
	}

}
