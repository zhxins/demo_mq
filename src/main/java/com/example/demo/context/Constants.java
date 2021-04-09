package com.example.demo.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

	@Value("${collect_flag}")
	public static boolean collect_flag;
}
