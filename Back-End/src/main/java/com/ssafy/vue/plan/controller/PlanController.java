package com.ssafy.vue.plan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.plan.service.PlanServcie;

@RestController
@RequestMapping("/plan")
public class PlanController {
	private Logger logger = LoggerFactory.getLogger(PlanController.class);
	private PlanServcie planService;

	@Autowired
	public PlanController(PlanServcie planService) {
		super();
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> registPlan(@RequestBody PlanDto planDto) throws Exception {
		logger.info("-------------regist plan 호출");
		try {
			planService.regist(planDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/add/detail/{id}")
	public ResponseEntity<String> addPlanDetail(@RequestBody List<Integer> list, @PathVariable("id") String user_id) throws Exception {
		logger.info("-------------add detail 호출");
		PlanIdxDto idxDto = new PlanIdxDto();
		try {
			int lastIdx = planService.getLastIdx(user_id) + 1;
			logger.info("새로추가될 계획 인덱스: " + lastIdx);
			for (int i = 1; i < list.size(); i++) {
				logger.info("여행지들 목록 : " + list.get(i));
				
				idxDto.setIdx(list.get(i));
				idxDto.setPlan_idx(lastIdx);
				
				planService.addPlanDetail(idxDto);
			}
			
			
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	
	@DeleteMapping("/delete/{plan_idx}")
	public ResponseEntity<String> deletePlan(@PathVariable("plan_idx") int plan_idx) throws Exception {
		logger.info("-------------delete plan 호출");
		try {
			planService.deletePlan(plan_idx);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
