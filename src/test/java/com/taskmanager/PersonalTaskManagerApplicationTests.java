package com.taskmanager;

import com.taskmanager.service.ReportServiceTests;
import com.taskmanager.service.TaskServiceTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonalTaskManagerApplicationTests.class, ReportServiceTests.class, TaskServiceTests.class})
public class PersonalTaskManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
