package tech.maret.bookstore.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository catR;
	
	private Category cat;
	
	@Test
	public void createNewCat() {
		cat = new Category("JavaTesting");
		catR.save(cat);
		
		assertThat(cat.getIdC()).isNotNull();
	}
	
	@Test
	public void deleteCat() {
		Category cat1 = catR.findByName("Polar");
		Long id = cat1.getIdC();
		
		catR.deleteById(id);	
		assertThat(catR.findByName("Polar")).isNull();
	}

}
