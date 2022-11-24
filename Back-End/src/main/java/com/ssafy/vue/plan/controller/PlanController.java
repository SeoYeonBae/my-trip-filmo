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
	private Integer lastIdx;

	@Autowired
	public PlanController(PlanServcie planService) {
		super();
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> registPlan(@RequestBody PlanDto planDto) throws Exception {
		try {
			planService.regist(planDto);
			lastIdx = planService.getLastIdx(planDto.getUser_id());
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/detail/{id}")
	public ResponseEntity<?> addPlanDetail(@RequestBody List<Integer> list, @PathVariable("id") String user_id) throws Exception {
		PlanIdxDto idxDto = new PlanIdxDto();
		try {
			for (int i = 0; i < list.size(); i++) {
				logger.info("여행지들 목록 : " + list.get(i));
				
				idxDto.setIdx(list.get(i));
				idxDto.setPlan_idx(lastIdx);
				
				planService.addPlanDetail(idxDto);
			}	
			// 추가된 계획의 번호를 반환한다
			return new ResponseEntity<Integer>(lastIdx, HttpStatus.OK);
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
