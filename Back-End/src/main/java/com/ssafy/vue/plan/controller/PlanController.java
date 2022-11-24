package com.ssafy.vue.plan.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.plan.model.PlanDto;
import com.ssafy.vue.plan.model.PlanIdxDto;
import com.ssafy.vue.plan.service.PlanServcie;
import com.ssafy.vue.tourlist.model.TourListDto;

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
	@PostMapping("/regist/info")
	public ResponseEntity<String> registInfo(@RequestBody PlanDto planDto) throws Exception {
		try {
			logger.info("여행 계획 title : " + planDto.toString());
			planService.registInfo(planDto);
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
	
	
//	@GetMapping("/myplan/{idx}")
//	public ResponseEntity<?> getMyPlanDetail(@PathVariable("idx") int plan_idx) throws Exception {
//		logger.info("-------------get My Plan 호출");
//		try {
//			List<Integer> myplanList = new ArrayList<Integer>();
//			myplanList = planService.getMyPlan(plan_idx);
//			return new ResponseEntity<List<Integer>>(myplanList , HttpStatus.OK);
//		}
//		catch(Exception e){
//			return exceptionHandling(e);
//		}
//	}
	
	@GetMapping("/myplan/{idx}")
	public ResponseEntity<?> getMyPlanDetail(@PathVariable("idx") int plan_idx) throws Exception {
		logger.info("-------------get My Plan 호출");
		try {
			List<Integer> myplanList = new ArrayList<Integer>();
			List<TourListDto> tourInfoList = new ArrayList<TourListDto>();

			myplanList = planService.getMyPlan(plan_idx);
			
			logger.info("-------------My Plan => 방문지들의 정보받아오기 호출");
			for (int i = 0; i < myplanList.size(); i++) {
				logger.info("방문지들 idx: " + myplanList.get(i) + "번 여행지");
				tourInfoList.add(planService.getTourInfo(myplanList.get(i)));
			}	
			return new ResponseEntity<List<TourListDto>>(tourInfoList, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	
//	@PostMapping("/myplan/details")
//	public ResponseEntity<?> getTourInfo(@RequestBody List<Integer> list) throws Exception {
//
//		logger.info("-------------방문지들의 정보받아오기 호출");
//		List<TourListDto> tourInfoList = new ArrayList<TourListDto>();
//		try {
//			for (int i = 0; i < list.size(); i++) {
//				logger.info("방문지들 idx: " + list.get(i) + "번 여행지");
//				tourInfoList.add(planService.getTourInfo(list.get(i)));
//			}	
//			// 추가된 계획의 번호를 반환한다
//			return new ResponseEntity<List<TourListDto>>(tourInfoList, HttpStatus.OK);
//		}
//		catch(Exception e){
//			return exceptionHandling(e);
//		}
//	}
	
	
	@GetMapping("/total/planlist/{id}")
	public ResponseEntity<?> getMyPlanList(@PathVariable("id") String user_id) throws Exception {

		logger.info("-------------마이페이지 모든 나의 계획 호출");
		try {
			List<PlanDto> list = planService.getMyPlanList(user_id);
			logger.info(list.size()+"개");
			for (int i = 0; i < list.size(); i++) {
				logger.info(list.get(i).toString());
			}
			return new ResponseEntity<List<PlanDto>>(list, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}

	@GetMapping("/myplan/info/{plan_idx}")
	public ResponseEntity<?> getMyPlanInfo(@PathVariable("plan_idx") int plan_idx) throws Exception {
		logger.info("-------------현재 조회하려는 여행계획 info");
		try {
			PlanDto planDto = planService.getMyPlanInfo(plan_idx);
			return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
		}
		catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
