package kr.or.basic.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleCheckTask {

	/* 리눅스 cron과 달리 초 단위부터 있음
	 * 실제론 이미 많은 것들이 스케줄링 되어있어 현업에서 활용도가 그리 높지는 않음
	 */
	@Scheduled(cron = "0/10 * * * * *")
	public void checkTask() {
		log.warn("이 메세지가 주기적으로 10초마다 나와야 해용!");
	}
}