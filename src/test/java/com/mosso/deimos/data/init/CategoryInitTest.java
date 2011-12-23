package com.mosso.deimos.data.init;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mosso.deimos.common.SystemConfig;
import com.mosso.deimos.common.test.BaseTest;

/**
 * 初始化Category数据
 * @author liubin
 *
 */
public class CategoryInitTest extends BaseTest {

	private String initFilePath = null;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Before
	public void init() {
		String workspacePath = SystemConfig.getConfigValue(SystemConfig.WORKSPACE_DIR);
		initFilePath = workspacePath + "/init/category.mongo"; 
	}
	
	@Test
	public void initCategory() throws Exception{
		DB db = mongoTemplate.getDb();
		String json = FileUtils.readFileToString(new File(initFilePath));
		System.out.println(db.eval(json));
	}

}
