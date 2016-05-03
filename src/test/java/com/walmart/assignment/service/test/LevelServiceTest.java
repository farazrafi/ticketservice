package com.walmart.assignment.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.assignment.entity.Level;
import com.walmart.assignment.service.impl.LevelServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class LevelServiceTest {

	@Resource(name="levelService")
	protected LevelServiceImpl levelService;

	@Test
	public void testGetAll() {
		List<Level> levels = levelService.getAll();
		assertNotNull(levels);		
	}

	@Test
	public void testAdd() {
		Level level = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		assertNotNull(level);
	}

	@Test
	public void testDelete() {
		Level level = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		levelService.delete(level.getLevelId());
		level = levelService.findById(level.getLevelId());
		assertNull(level);
	}

	@Test
	public void testEdit() {
		Level level = levelService.add("Orchestra", new BigDecimal(100), new BigDecimal(25), new BigDecimal(50));
		levelService.edit(level.getLevelId(),"Main", new BigDecimal(75), new BigDecimal(20), new BigDecimal(100));
		assertTrue(true);
	}

}
